package com.emon.domain.usecase

import com.emon.domain.base.ApiUseCaseParams
import com.emon.domain.base.Result
import com.emon.domain.repository.GithubRepository
import com.emon.entity.repositories.RepositoriesListApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoriesApiUseCase @Inject constructor(
    private val githubRepository : GithubRepository
) : ApiUseCaseParams<RepositoriesApiUseCase.Params, RepositoriesListApiEntity> {
    data class Params(val userName: String)

    override suspend fun execute(params: Params): Flow<Result<RepositoriesListApiEntity>> {
        return githubRepository.fetchRepoList(params)
    }
}