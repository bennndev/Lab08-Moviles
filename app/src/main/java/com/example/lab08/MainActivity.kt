package com.example.lab08

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.lab08.data.database.TaskDatabase
import com.example.lab08.ui.screens.TodoistScreen
import com.example.lab08.ui.theme.Lab08Theme
import com.example.lab08.ui.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab08Theme {
                // Crear la base de datos
                val db = Room.databaseBuilder(
                    applicationContext,
                    TaskDatabase::class.java,
                    "task_db"
                ).build()

                // Obtener el DAO
                val taskDao = db.taskDao()

                // Crear el ViewModel
                val viewModel = TaskViewModel(taskDao)

                // Mostrar la pantalla principal
                TodoistScreen(viewModel = viewModel)
            }
        }
    }
}