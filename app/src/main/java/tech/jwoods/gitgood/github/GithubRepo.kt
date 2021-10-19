package tech.jwoods.gitgood.github

import org.kohsuke.github.GHEventPayload
import java.util.*

data class GithubRepo(
    val name: String,
    val date: Date,
) {
    companion object {
        fun fromRepository(repo: GHEventPayload.Repository): GithubRepo {
            return GithubRepo(
                name = "something here",
                date = Date(),
            )
        }
    }
}