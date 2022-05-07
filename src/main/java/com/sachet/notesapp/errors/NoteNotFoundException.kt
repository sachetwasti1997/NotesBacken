package com.sachet.notesapp.errors

class NoteNotFoundException(private val errorMessage: String): RuntimeException(errorMessage)