package com.example.taskvmg2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskvmg2.ui.model.Task
import com.example.taskvmg2.ui.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    var id by mutableStateOf("")
        private set
    var title by mutableStateOf("")
        private set
    var completed by mutableStateOf(false)
        private set

    init {
        loadTasks()
    }

    fun onIdChange(newId: String) { id = newId }
    fun onTitleChange(newTitle: String) { title = newTitle }

    fun loadTasks() {
        _tasks.value = TaskRepository.getTasks()
    }

    fun loadTaskData(taskId: Int) {
        if (taskId == -1) {
            clearForm()
        } else {
            val task = TaskRepository.getTaskById(taskId)
            if (task != null) {
                id = task.id.toString()
                title = task.title
                completed = task.completed
            }
        }
    }

    fun saveTask() {
        val taskId = id.toIntOrNull() ?: (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
        TaskRepository.saveTask(Task(id = taskId, title = title, completed = completed))
        loadTasks()
    }

    fun removeTask(task: Task) {
        TaskRepository.removeTask(task)
        loadTasks()
    }

    fun toggleTask(task: Task) {
        TaskRepository.toggleTask(task)
        loadTasks()
    }

    private fun clearForm() {
        id = ""
        title = ""
        completed = false
    }
}