package com.thedancercodes.domain.interactor

import com.thedancercodes.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

// A Use Case class that returns an Observable instance from the RxJava API
abstract class ObservableUseCase<T, in Params> constructor(

        // postExecutionThread - Provides an abstraction for the Scheduler to be used when our
        // Use Case Observable instance is subscribed to.
        private val postExecutionThread: PostExecutionThread) {

    // Create a CompositeDisposable() instance to store this reference to our Observable
    private val disposables = CompositeDisposable()

    //  An abstract method that requires our inheriting class to implement
    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    // Pass an instance of a DisposableObserver type,
    // which allows us to observe for events on our Observable instance
    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {

        // Retrieve an instance of the Observable for this class,
        // Subscribe to the stream and observe for events.
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)

        // Add Observable reference to this list
        addDisposable(observable.subscribeWith(observer))
    }

    // Create function to add this Disposable instance
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }



}