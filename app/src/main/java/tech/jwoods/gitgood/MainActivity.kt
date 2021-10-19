package tech.jwoods.gitgood

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.jwoods.gitgood.github.Github
import tech.jwoods.gitgood.github.GithubRepo
import tech.jwoods.gitgood.ui.theme.GitGoodTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gg: Github = Github()

        Observable
            .create<List<GithubRepo>> { emitter ->
                emitter.onNext(gg.getData())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setContent {
                    MainActivityView(it)
                }
            }
    }
}


@Composable
fun Title() {
    Text(
        text = "GitGood\n",
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.h1,
    )
}

@Composable
fun MainActivityView(repos: List<GithubRepo>) {
    Title()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    GitGoodTheme {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumn {
                var i = 0
                item { Title() }
                items(repos) { repo ->
                    Row {
                        Text(text = "$i")
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = dateFormat.format(repo.date),
                            color = MaterialTheme.colors.secondaryVariant,
                            style = MaterialTheme.typography.subtitle2,
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = repo.name,
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.body1,
                        )

                    }
                    i++
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val date: Date = Date()

    val repos = listOf<GithubRepo>(
        GithubRepo("Cool Repo 1", date),
        GithubRepo("Cool Repo 2", date),
    )
    MainActivityView(repos)
}