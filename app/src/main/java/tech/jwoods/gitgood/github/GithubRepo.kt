package tech.jwoods.gitgood.github

import org.eclipse.egit.github.core.Repository
import java.util.*

data class GithubRepo(
    val name: String,
    val date: Date,
) {
    companion object {
        fun fromRepository(repo: Repository): GithubRepo {
            return GithubRepo(
                name = repo.name,
                date = repo.updatedAt,
            )
        }
    }
}