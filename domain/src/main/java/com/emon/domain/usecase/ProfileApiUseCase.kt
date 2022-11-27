package com.emon.domain.usecase

import com.emon.domain.base.ApiUseCaseParams
import com.emon.domain.base.Result
import com.emon.domain.repository.GithubRepository
import com.emon.entity.profile.ProfileApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileApiUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) : ApiUseCaseParams<ProfileApiUseCase.Params, ProfileApiEntity> {

    data class Params(val userName: String)

    override suspend fun execute(params: Params): Flow<Result<ProfileApiEntity>> {
        return githubRepository.fetchProfile(params)
    }
}