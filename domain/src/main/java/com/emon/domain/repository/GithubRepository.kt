package com.emon.domain.repository

import com.emon.domain.base.Result
import com.emon.domain.usecase.ProfileApiUseCase
import com.emon.domain.usecase.RepositoriesApiUseCase
import com.emon.entity.profile.ProfileApiEntity
import com.emon.entity.repositories.RepositoriesListApiEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun fetchRepoList(params: RepositoriesApiUseCase.Params):Flow<Result<RepositoriesListApiEntity>>
    suspend fun fetchProfile(params: ProfileApiUseCase.Params):Flow<Result<ProfileApiEntity>>

}