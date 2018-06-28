package com.thedancercodes.domain.interactor

import com.thedancercodes.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

// A CompletableUseCase Base Class
abstract class CompletableUseCase<in Params> constructor(

        // postExecutionThread - Provides an abstraction for the Scheduler to be used when our
        // Use Case Completable instance is subscribed to.
        private val postExecutionThread: PostExecutionThread) {

    // Create a CompositeDisposable() instance to store this reference to our Observable
    private val disposables = CompositeDisposable()

    //  An abstract method that requires our inheriting class to implement
    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    // Pass an instance of a DisposableObserver type,
    // which allows us to observe for events on our Completable instance
    open fun execute(observer: DisposableCompletableObserver, params: Params? = null) {

        // Retrieve an instance of the Completable for this class,
        // Subscribe to the stream and observe for events.
        val completable = this.buildUseCaseCompletable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)

        // Add Completable reference to this list
        addDisposable(completable.subscribeWith(observer))
    }

    // Create function to add this Disposable instance
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}