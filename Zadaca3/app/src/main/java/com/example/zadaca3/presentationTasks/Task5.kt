package com.example.zadaca3.presentationTasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ListScreen(items: List<MyData>, onItemClick: (Int) -> Unit) {
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                Box(modifier = Modifier.clickable { onItemClick(item.id) }) {
                    ItemCard(data = item)
                }
            }
        }
    }
}

@Composable
fun DetailScreen(data: MyData?, onBack: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Button(onClick = onBack) {
                Text("Back")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (data != null) {
                TitleText(text = data.title)
                Spacer(modifier = Modifier.height(8.dp))
                DescriptionText(text = data.description)
            } else {
                Text("Item not found")
            }
        }
    }
}



@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val myDataList = remember {
        listOf(
            MyData(1, "Kotlin", "Modern language for Android."),
            MyData(2, "Compose", "Declarative UI toolkit."),
            MyData(3, "Navigation", "Moving between screens."),
            MyData(4, "State", "Managing data updates."),
            MyData(5, "Red Button", "Custom styled button.")
        )
    }

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListScreen(
                items = myDataList,
                onItemClick = { id -> navController.navigate("details/$id") }
            )
        }
        composable(
            route = "details/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val idFromRoute = backStackEntry.arguments?.getInt("itemId")
            val foundItem = myDataList.find { it.id == idFromRoute }

            DetailScreen(
                data = foundItem,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    MaterialTheme {
        AppNavigation()
    }
}

