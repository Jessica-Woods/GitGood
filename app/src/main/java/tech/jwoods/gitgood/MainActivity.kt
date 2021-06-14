package tech.jwoods.gitgood

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import tech.jwoods.gitgood.github.Github
import tech.jwoods.gitgood.ui.theme.GitGoodTheme
import java.util.*

class MainActivity : ComponentActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gg: Github = Github()

        Observable
            .create<Unit> { emitter ->
                emitter.onNext(gg.getData())
            }
            .subscribeOn(Schedulers.io())
            .subscribe {
                println(it)
            }

        val observable = Observable.create<String> { emitter ->
            emitter.onNext("1")
            emitter.onNext("2")
            emitter.onNext("3")
        }

        observable.subscribe { println(it) }


        setContent {
            GitGoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GitGoodTheme {
        Greeting("Phone")
    }
}