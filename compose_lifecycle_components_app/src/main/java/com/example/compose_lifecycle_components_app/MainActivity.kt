package com.example.compose_lifecycle_components_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose_lifecycle_components_app.ui.theme.PagingFlowAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingFlowAppTheme {
                val viewModel: RepositoriesViewModel = viewModel()
                val repositoryFlow = viewModel.repositories
                val timerText = viewModel.timerState.value
                val lazyRepositoryItems: LazyPagingItems<Repository> =
                    repositoryFlow.collectAsLazyPagingItems()
                RepositoriesScreen(
                    lazyRepositoryItems,
                    timerText,
                    getTimer = {
                        viewModel.timer
                    },
                    onPauseTimer = {viewModel.timer.stop()}
                )
            }
        }
    }
}
