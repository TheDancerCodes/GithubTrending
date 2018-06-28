package com.thedancercodes.domain.interactor.browse

import com.thedancercodes.domain.executor.PostExecutionThread
import com.thedancercodes.domain.interactor.ObservableUseCase
import com.thedancercodes.domain.model.Project
import com.thedancercodes.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject


class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)

    // To return an instance of the RxObservable class,
    // so that we can observe the data and listen for any errors if they occur.
    : ObservableUseCase<List<Project>, Nothing>(postExecutionThread)
{
    // Implement the abstract method from the parent class,
    // which will return us the instance of the RxJava Observable class.
    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {

        // Make a single call to our Repository interface to retrieve our list of projects.
        return projectsRepository.getProjects()
    }
}