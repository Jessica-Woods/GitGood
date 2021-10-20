package tech.jwoods.gitgood

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import tech.jwoods.gitgood.view.RepoHomeView
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
            .subscribe { setContent { RepoHomeView(it) } }
    }
}
