package tech.jwoods.gitgood.presenter

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.jwoods.gitgood.github.Github
import tech.jwoods.gitgood.github.GithubRepo

class RepoHomePresenter(val github: Github) {
    fun observeViewModels(): Observable<List<GithubRepo>> {
        return Observable
            .create<List<GithubRepo>> { emitter ->
                emitter.onNext(github.getData())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}