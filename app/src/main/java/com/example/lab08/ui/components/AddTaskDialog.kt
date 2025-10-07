package com.example.lab08.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddTaskDialog(
    taskDescription: String,
    onTaskDescriptionChange: (String) -> Unit,
    onAddTask: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier,
        title = {
            Text(
                text = "Nueva tarea",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column {
                TextField(
                    value = taskDescription,
                    onValueChange = onTaskDescriptionChange,
                    label = { Text("Descripción de la tarea") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    placeholder = { Text("Por ej.: Entregar ensayo sobre IA jueves p1") }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Opciones adicionales (Fecha, Prioridad, Recordatorios) - No funcionales en esta demo",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onAddTask,
                enabled = taskDescription.isNotEmpty()
            ) {
                Text("Añadir tarea")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}