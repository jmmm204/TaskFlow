package com.example.taskvmg2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.taskvmg2.ui.screen.TaskDetailScreen
import com.example.taskvmg2.ui.screen.TaskListScreen
import com.example.taskvmg2.ui.viewmodel.TaskViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TaskList,
        modifier = modifier
    ) {
        composable<TaskList> { backStackEntry ->
            val viewModel: TaskViewModel = viewModel(viewModelStoreOwner = backStackEntry)

            TaskListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<TaskDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(TaskList)
            }

            val viewModel: TaskViewModel = viewModel(viewModelStoreOwner = parentEntry)

            TaskDetailScreen(
                navController = navController,
                taskId = route.taskId,
                viewModel = viewModel
            )
        }
    }
}