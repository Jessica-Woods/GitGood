package tech.jwoods.gitgood

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.jwoods.gitgood.github.Github
import tech.jwoods.gitgood.presenter.RepoHomePresenter
import tech.jwoods.gitgood.ui.theme.GitGoodTheme
import tech.jwoods.gitgood.view.RepoHomeView

class MainActivity : ComponentActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { main() }
    }

    @Preview
    @Composable
    fun main() {
        val github = Github()
        GitGoodTheme() {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "profile") {
                composable("profile") {
                    val presenter = RepoHomePresenter(github)
                    val state by presenter.observeViewModels()
                        .subscribeAsState(initial = emptyList())
                    RepoHomeView(state)
                }
            }
        }
    }
}
