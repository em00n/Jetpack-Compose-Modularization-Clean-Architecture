package com.emon.data.repoimpl

import com.emon.data.apiservice.GitHubApiService
import com.emon.data.mapper.profile.ProfileApiMapper
import com.emon.data.mapper.repositories.RepositoriesApiMapper
import com.emon.data.utils.mapFromApiResponse
import com.emon.data.wrapper.NetworkBoundResource
import com.emon.domain.base.Result
import com.emon.domain.repository.GithubRepository
import com.emon.domain.usecase.ProfileApiUseCase
import com.emon.domain.usecase.RepositoriesApiUseCase
import com.emon.entity.profile.ProfileApiEntity
import com.emon.entity.repositories.RepositoriesListApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepoImpl @Inject constructor(
    private val gitHubApiService: GitHubApiService,
    private val profileApiMapper: ProfileApiMapper,
    private val repositoriesApiMapper: RepositoriesApiMapper,
    private val networkBoundResources: NetworkBoundResource
) : GithubRepository {

    override suspend fun fetchRepoList(params: RepositoriesApiUseCase.Params): Flow<Result<RepositoriesListApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                gitHubApiService.fetchRepoList(params.userName)
            },repositoriesApiMapper
        )
    }

    override suspend fun fetchProfile(params: ProfileApiUseCase.Params): Flow<Result<ProfileApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResources.downloadData {
                gitHubApiService.fetchProfile(params.userName)
            },profileApiMapper
        )
    }

}