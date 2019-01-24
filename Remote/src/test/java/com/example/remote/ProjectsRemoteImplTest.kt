package com.example.remote

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsRemote
import com.example.data.test.factory.ProjectFactory
import com.example.remote.mapper.ProjectsResponseModelMapper
import com.example.remote.model.ProjectsResponseModel
import com.example.remote.service.GithubTrendingService
import com.example.remote.service.ProjectsRemoteImpl
import com.example.remote.test.factory.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)

    @Test
    fun getProjectCompletes() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsServer() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectFactory.makeProjectEntity()
            entities.add(entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    private fun stubGithubTrendingServiceSearchRepositories(observable: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observable)
    }
}