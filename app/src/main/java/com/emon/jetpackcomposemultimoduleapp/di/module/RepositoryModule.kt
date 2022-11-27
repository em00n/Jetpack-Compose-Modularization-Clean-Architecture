package com.emon.jetpackcomposemultimoduleapp.di.module

import com.emon.data.repoimpl.GithubRepoImpl
import com.emon.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindGithubRepository(githubRepoImpl: GithubRepoImpl): GithubRepository
}