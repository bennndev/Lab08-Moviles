package com.example.lab08.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab08.data.model.Task
import com.example.lab08.ui.components.AddTaskDialog
import com.example.lab08.ui.components.TaskSection
import com.example.lab08.ui.components.TodoistHeader
import com.example.lab08.ui.viewmodel.TaskViewModel

@Composable
fun TodoistScreen(
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    val tasks by viewModel.tasks.collectAsState()
    var showAddTaskDialog by remember { mutableStateOf(false) }
    var newTaskDescription by remember { mutableStateOf("") }

    TodoistScreenContent(
        tasks = tasks,
        onAddTaskClick = { showAddTaskDialog = true },
        onTaskChecked = { task -> viewModel.toggleTaskCompletion(task) },
        modifier = modifier
    )

    if (showAddTaskDialog) {
        AddTaskDialog(
            taskDescription = newTaskDescription,
            onTaskDescriptionChange = { newTaskDescription = it },
            onAddTask = {
                if (newTaskDescription.isNotEmpty()) {
                    viewModel.addTask(newTaskDescription)
                    newTaskDescription = ""
                    showAddTaskDialog = false
                }
            },
            onDismiss = {
                showAddTaskDialog = false
                newTaskDescription = ""
            }
        )
    }
}

@Composable
fun TodoistScreenContent(
    tasks: List<Task>,
    onAddTaskClick: () -> Unit,
    onTaskChecked: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTaskClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Añadir tarea"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            TodoistHeader(taskCount = tasks.size)

            TaskSection(
                tasks = tasks,
                onTaskChecked = onTaskChecked,
                onAddTaskClick = onAddTaskClick,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}