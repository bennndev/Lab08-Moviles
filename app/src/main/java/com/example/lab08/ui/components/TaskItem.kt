package com.example.lab08.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.lab08.data.model.Task

@Composable
fun TaskItem(
    task: Task,
    onTaskChecked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = if (task.isCompleted) MaterialTheme.colorScheme.primary else Color.Gray,
                    shape = CircleShape
                )
                .background(
                    if (task.isCompleted) MaterialTheme.colorScheme.primary else Color.Transparent
                )
                .clickable { onTaskChecked(!task.isCompleted) },
            contentAlignment = Alignment.Center
        ) {
            if (task.isCompleted) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "Completada",
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
            }
        }
        Text(
            text = task.description,
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
            color = if (task.isCompleted) Color.Gray else Color.Black
        )
    }
}