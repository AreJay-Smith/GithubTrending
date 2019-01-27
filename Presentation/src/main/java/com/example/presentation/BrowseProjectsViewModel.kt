package com.example.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.domain.interactor.bookmarked.BookmarkProject
import com.example.domain.interactor.bookmarked.UnbookmarkProject
import com.example.domain.interactor.browse.GetProjects
import com.example.domain.model.Project
import com.example.presentation.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseProjectsViewModel @Inject constructor(
    private val getProjects: GetProjects,
    private val bookmarkProject: BookmarkProject,
    private val unbookmarkProject: UnbookmarkProject,
    private val mapper: ProjectViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProjects.execute(ProjectsSubscriber())
    }

    fun bookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING,
            null, null))
        return bookmarkProject.execute(BookmarkProjectsSbuscriber(), BookmarkProject.Params.forProject(projectId))
    }

    fun unbookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING,
            null, null))
        return unbookmarkProject.execute(BookmarkProjectsSbuscriber(), UnbookmarkProject.Params.forProject(projectId))
    }

    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR,
                null, e.localizedMessage))
        }
    }

    inner class BookmarkProjectsSbuscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR,
                null, e.localizedMessage))
        }

    }
}