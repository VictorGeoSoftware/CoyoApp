package com.training.victor.development.presenter

import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.ProfileItem
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProfilesPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                            private val subscriberSchedulers: Scheduler,
                                            private val dataManager: DataManager): Presenter<ProfilesPresenter.ProfilesView>() {

    private val compositeDisposable = CompositeDisposable()

    interface ProfilesView: ParentView {
        fun showProgressBar(show: Boolean)
        fun onProfilesListReceived(profilesList: ArrayList<ProfileItem>)
        fun onProfilesListError()
    }


    fun getProfilesList() {
        view?.showProgressBar(true)
        compositeDisposable.add(dataManager.getProfilesList()
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe ({
                view?.showProgressBar(false)
                view?.onProfilesListReceived(it)
            }, {
                view?.showProgressBar(false)
                view?.onProfilesListError()
            }))

    }

    override fun destroy() {
        super.destroy()
        compositeDisposable.clear()
    }
}