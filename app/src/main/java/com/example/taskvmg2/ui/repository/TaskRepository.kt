package com.example.taskvmg2.ui.repository

import com.example.taskvmg2.ui.model.Task

object TaskRepository {
    private val tasks = mutableListOf(
        Task(1, "Task 1", true),
        Task(2, "Task 2", false),
        Task(3, "Task 3", false),
        Task(4, "Task 4", true),
        Task(5, "Task 5", false)
    )

    fun getTasks(): List<Task> = tasks.toList()

    fun saveTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
        } else {
            tasks.add(task)
        }
    }

    fun getTaskById(id: Int): Task? = tasks.find {
        it.id == id
    }

    fun removeTask(task: Task) {
        tasks.remove(task)
    }

    fun toggleTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task.copy(completed = !task.completed)
        }
    }
}