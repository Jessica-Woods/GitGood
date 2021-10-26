package tech.jwoods.gitgood.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.jwoods.gitgood.github.GithubRepo
import tech.jwoods.gitgood.ui.theme.GitGoodTheme
import java.text.SimpleDateFormat
import java.time.format.TextStyle
import java.util.*

@Composable
fun RepoHomeView(repos: List<GithubRepo>) {
    Title()
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            var i = 0
            item { Title() }
            items(repos) { repo ->
                Row(repo, i, ::onRowClicked)
                i++
            }
        }
    }
}

@Composable
fun Title() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "GitGood",
        color = MaterialTheme.colors.primary,
        fontSize = 50.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun Row(repo: GithubRepo, count: Int, onClick: () -> Unit) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(text = "$count")
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
}

fun onRowClicked() {
    println("Row was clicked!")
}

// Previews
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val date: Date = Date()

    val repos = listOf<GithubRepo>(
        GithubRepo("Cool Repo 1", date),
        GithubRepo("Cool Repo 2", date),
    )
    RepoHomeView(repos)
}