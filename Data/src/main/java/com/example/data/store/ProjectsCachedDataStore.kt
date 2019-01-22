package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsDataStore
import com.example.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCachedDataStore @Inject constructor(private val projectsCache: ProjectsCache): ProjectsDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
            .andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsNotBookmarked(projectId)
    }


}