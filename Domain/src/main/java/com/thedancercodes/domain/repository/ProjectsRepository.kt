package com.thedancercodes.domain.repository

import com.thedancercodes.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

// Repository interface that defines the data access methods to be implemented by
// the outer layers of our Clean Architecture.
interface ProjectsRepository {

    // Returns an Observable instance containing a list of project instances.
    fun getProjects(): Observable<List<Project>>

    // A way to bookmark projects. Takes a project ID and returns a Completable instance.
    fun bookmarkProject(projectId: String): Completable

    // // A way to un-bookmark projects. Takes a project ID and returns a Completable instance.
    fun unbookmarkProject(projectId: String): Completable

    // Returns an Observable instance containing a list of project instances.
    fun getBookmarkedProjects(): Observable<List<Project>>
}