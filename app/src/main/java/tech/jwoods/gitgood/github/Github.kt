package tech.jwoods.gitgood.github

import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.RepositoryService
import tech.jwoods.gitgood.BuildConfig

class Github {
    fun getData(): List<GithubRepo> {
        val client = GitHubClient()
        client.setOAuth2Token(BuildConfig.githubApiKey)

        val service = RepositoryService(client)
        return service.getRepositories("Jessica-Woods")
            .map { GithubRepo.fromRepository(it) }
    }
}