package com.example.domain.interactor.browse

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.ObservableUseCase
import com.example.domain.model.Project
import com.example.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

     public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getProjects()
    }

}