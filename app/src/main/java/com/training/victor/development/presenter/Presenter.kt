package com.training.victor.development.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class Presenter<T1> {
    var view: T1? = null
    val disposable = CompositeDisposable()

    open fun destroy() {
        System.out.println("onDestroy!")
        disposable.clear()
        view = null
    }
}