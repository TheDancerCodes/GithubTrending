package com.thedancercodes.domain.executor

import io.reactivex.Scheduler

// Abstraction for our RxJava Observation Thread so that the Domain layer has no knowledge of the
// Android framework
interface PostExecutionThread {

    val scheduler: Scheduler
}