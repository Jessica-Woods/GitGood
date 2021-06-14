package tech.jwoods.gitgood.github

import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.RepositoryService
import tech.jwoods.gitgood.BuildConfig

class Github {
    fun getData() {
        val client = GitHubClient()
        client.setOAuth2Token(BuildConfig.githubApiKey)

        val service = RepositoryService(client)
        for (repo in service.getRepositories("Jessica-Woods")) {
            println(repo.name.toString() + " Watchers: " + repo.watchers)
        }
    }
}