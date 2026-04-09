package com.example.zadaca3.presentationTasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Color.Red,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun DescriptionText(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        color = Color.Gray,
        lineHeight = 18.sp
    )
}

@Composable
fun CustomButton(
    text: String? = null,
    icon: ImageVector? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
            if (text != null) Spacer(Modifier.width(8.dp))
        }
        if (text != null) {
            Text(text = text, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun Assignment1Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        TitleText(text = "Assignment 1")
        DescriptionText(text = "This is a description component with a smaller font size and a limit of three lines. If the text is too long, it will end with an ellipsis to keep the UI clean.")
        Spacer(modifier = Modifier.height(16.dp))


        CustomButton(
            text = "Custom Action",
            icon = Icons.Default.Info
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Assignment1Preview() {
    MaterialTheme {
        Assignment1Screen()
    }
}