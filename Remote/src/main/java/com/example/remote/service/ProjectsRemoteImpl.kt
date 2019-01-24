package com.example.remote.service

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsRemote
import com.example.remote.mapper.ProjectsResponseModelMapper
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper
): ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map {
                it.items.map { mapper.mapFromModel(it)}
            }
    }
}