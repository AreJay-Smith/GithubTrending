package com.example.data.store

import com.example.data.repository.ProjectsDataStore
import javax.inject.Inject

open class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCachedDataStore: ProjectsCachedDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {

    open fun getDataSore(projectsCached: Boolean, cacheExpired: Boolean): ProjectsDataStore {
        return if (projectsCached && !cacheExpired) {
            projectsCachedDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCachedDataStore
    }

}