package com.example.zadaca3.presentationTasks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyItemList(initialData: List<MyData>) {
    var itemsList by remember { mutableStateOf(initialData) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(
            onClick = { itemsList = itemsList.shuffled() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Shuffle")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(itemsList) { item ->
                ItemCard(data = item)
            }
        }
    }
}

@Preview(showBackground = true, name = "Task 3 - List Preview")
@Composable
fun MyItemListPreview() {
    val sampleData = listOf(
        MyData(1, "Mountain View", "A beautiful view of the snowy peaks at sunrise."),
        MyData(2, "Forest Trail", "Deep green forest with a narrow walking path."),
        MyData(3, "Ocean Wave", "Powerful blue waves crashing against the rocks."),
        MyData(4, "City Lights", "Night skyline of a busy metropolis glowing in the dark.")
    )
    MaterialTheme {
        MyItemList(initialData = sampleData)
    }
}