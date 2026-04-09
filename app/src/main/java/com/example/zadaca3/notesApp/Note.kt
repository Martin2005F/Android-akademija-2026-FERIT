package com.example.zadaca3.notesApp

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val date: String = "2026-03-30"
)