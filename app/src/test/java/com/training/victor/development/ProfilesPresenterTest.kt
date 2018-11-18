package com.training.victor.development

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.ProfileItem
import com.training.victor.development.network.ProfilesRepository
import com.training.victor.development.presenter.ParentView
import com.training.victor.development.presenter.Presenter
import com.training.victor.development.presenter.ProfilesPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ProfilesPresenterTest: ParentUnitTest() {

    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var profilesRepository: ProfilesRepository
    @Mock lateinit var profilesView: ProfilesPresenter.ProfilesView

    private lateinit var testScheduler: TestScheduler
    private lateinit var profilesPresenter: ProfilesPresenter

    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        profilesPresenter = createMockedPresenter()
    }

    override fun <T> createMockedPresenter(): T {
        val profilesPresenter = ProfilesPresenter(testScheduler, testScheduler, dataManager)
        profilesPresenter.view = profilesView
        return profilesPresenter as T
    }

    // --------------------------------------------- TESTING CASES ---------------------------------------------
    @Test
    fun `should call to profiles list and retrieve a list`() {
        whenever(profilesRepository.getProfilesList()).thenReturn(Observable.just(arrayListOf(ProfileItem(0, "hhttps"))))
        profilesPresenter.getProfilesList()
        testScheduler.triggerActions()

        verify(profilesView, times(1)).onProfilesListReceived(any())
    }

    @Test
    fun `should call to profiles list and retrieve an error`() {
        whenever(profilesRepository.getProfilesList()).thenReturn(Observable.error(Throwable()))
        profilesPresenter.getProfilesList()
        testScheduler.triggerActions()

        verify(profilesView, times(1)).onProfilesListError()
    }
}
