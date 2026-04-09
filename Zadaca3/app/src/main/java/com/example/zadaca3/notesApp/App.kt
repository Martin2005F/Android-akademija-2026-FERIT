package com.example.zadaca3.notesApp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.zadaca3.ui.theme.Zadaca3Theme


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var notesList by remember { mutableStateOf(listOf<Note>()) }
    var nextId by remember { mutableStateOf(1) }

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NoteListScreen(
                notes = notesList,
                onAddNote = { navController.navigate("details/-1") },
                onEditNote = { note -> navController.navigate("details/${note.id}") }
            )
        }

        composable(
            route = "details/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            val existingNote = notesList.find { it.id == noteId }

            NoteDetailScreen(
                note = existingNote,
                onSave = { title, desc ->
                    if (existingNote == null) {
                        notesList = notesList + Note(nextId++, title, desc)
                    } else {
                        notesList = notesList.map {
                            if (it.id == noteId) it.copy(title = title, description = desc) else it
                        }
                    }
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun NoteListScreen(
    notes: List<Note>,
    onAddNote: () -> Unit,
    onEditNote: (Note) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notes) { note ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEditNote(note) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = note.title, fontWeight = FontWeight.Bold)
                            Text(text = note.description, maxLines = 1)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NoteDetailScreen(
    note: Note?,
    onSave: (String, String) -> Unit,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf(note?.title ?: "") }
    var description by remember { mutableStateOf(note?.description ?: "") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(onClick = onBack) {
            Text("←")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Note content") },
            modifier = Modifier.fillMaxWidth().weight(1f)
        )

        Button(
            onClick = { onSave(title, description) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            enabled = title.isNotBlank()
        ) {
            Text("Done")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesAppPreview() {
    Zadaca3Theme {
        AppNavigation()
    }
}

