package tech.jwoods.gitgood.github

import org.kohsuke.github.GitHubBuilder
import tech.jwoods.gitgood.BuildConfig

class Github {
    fun getData(): List<GithubRepo> {

        // If you don't specify the GitHub user id then the sdk will retrieve it via /user endpoint
        val github = GitHubBuilder().withOAuthToken(BuildConfig.githubApiKey).build()

        val result = github.myself.allRepositories.map {
            GithubRepo(
                name = it.value.name,
                date = it.value.pushedAt
            )
        }

        return result.toList().sortedBy { it.date }.reversed()
    }
}