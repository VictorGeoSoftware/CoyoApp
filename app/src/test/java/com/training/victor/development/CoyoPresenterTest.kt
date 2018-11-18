package com.training.victor.development

import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.training.victor.development.data.DataManager
import com.training.victor.development.data.models.CommentViewModel
import com.training.victor.development.data.models.PostViewModel
import com.training.victor.development.data.room.AppDataBase
import com.training.victor.development.network.CoyoRepository
import com.training.victor.development.network.responses.CommentResp
import com.training.victor.development.network.responses.UserResp
import com.training.victor.development.presenter.CoyoPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeoutException
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class CoyoPresenterTest: ParentUnitTest() {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var coyoRepository: CoyoRepository
    @Mock lateinit var coyoView: CoyoPresenter.CoyoView
    @Mock lateinit var mockDataBase: AppDataBase

    private lateinit var testScheduler: TestScheduler
    private lateinit var coyoPresenter: CoyoPresenter


    @Before
    override fun setUp() {
        super.setUp()

        testNetworkComponent.inject(this)
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        coyoPresenter = createMockedPresenter()
    }

    override fun <T> createMockedPresenter(): T {
        val coyoPresenter = CoyoPresenter(testScheduler, testScheduler, dataManager)
        coyoPresenter.view = coyoView
        return coyoPresenter as T
    }

    private fun getMockedUser(): UserResp {
        val userString = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Leanne Graham\",\n" +
                "    \"username\": \"Bret\",\n" +
                "    \"email\": \"Sincere@april.biz\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Kulas Light\",\n" +
                "      \"suite\": \"Apt. 556\",\n" +
                "      \"city\": \"Gwenborough\",\n" +
                "      \"zipcode\": \"92998-3874\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-37.3159\",\n" +
                "        \"lng\": \"81.1496\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Romaguera-Crona\",\n" +
                "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "      \"bs\": \"harness real-time e-markets\"\n" +
                "    }\n" +
                "  }"
        val gson = Gson()
        return gson.fromJson(userString, UserResp::class.java)
    }



    // --------------------------------------------- TESTING CASES ---------------------------------------------
    @Test
    fun `should call to post service API in a first app launching`() {
        whenever(coyoRepository.getPost()).thenReturn(Observable.just(listOf()))

        coyoPresenter.getPostListForFirstTime()
        verify(coyoView, times(1)).enableProgressBar(true)
        testScheduler.triggerActions()

        verify(coyoView, times(1)).enableProgressBar(false)
        verify(coyoView, times(1)).onPostListReceived(anyList<PostViewModel>())
    }

    @Test
    fun `should call to post service and retrieve a list of post`() {
        whenever(coyoRepository.getPost()).thenReturn(Observable.just(listOf()))
        coyoPresenter.getPostList()
        verify(coyoView, times(1)).enableProgressBar(true)
        testScheduler.triggerActions()

        verify(coyoView, times(1)).enableProgressBar(false)
        verify(coyoView, times(1)).onPostListReceived(anyList<PostViewModel>())
    }

    @Test
    fun `should call to post service and get network problem`() {
        val error = "TIME_OUT"
        whenever(coyoRepository.getPost()).thenReturn(Observable.error(TimeoutException(error)))

        coyoPresenter.getPostList()
        verify(coyoView, times(1)).enableProgressBar(true)
        testScheduler.triggerActions()

        verify(coyoView, times(1)).enableProgressBar(false)
        verify(coyoView, times(1)).onPostListError(error)
    }

    @Test
    fun `should ask for post details and be successful on the process`() {
        val userId = 1
        val postId = 1
        val commentList = ArrayList<CommentResp>()
        val user = getMockedUser()

        whenever(coyoRepository.getComments(postId)).thenReturn(Observable.just(commentList))
        whenever(coyoRepository.getUsers(postId)).thenReturn(Observable.just(user))

        coyoPresenter.getPostDetailedInfo(userId, postId)
        verify(coyoView, times(1)).enableProgressBar(true)
        testScheduler.triggerActions()

        verify(coyoView, times(1)).enableProgressBar(false)
        verify(coyoView, times(1)).onPostDetailedInfoReceived(any(), anyList<CommentViewModel>())
    }

    @Test
    fun `should ask for post details and return an error`() {
        val userId = 1
        val postId = 1
        val user = getMockedUser()

        whenever(coyoRepository.getComments(postId)).thenReturn(Observable.error(Throwable("TIME_OUT")))
        whenever(coyoRepository.getUsers(postId)).thenReturn(Observable.just(user))

        coyoPresenter.getPostDetailedInfo(userId, postId)
        verify(coyoView, times(1)).enableProgressBar(true)
        testScheduler.triggerActions()

        verify(coyoView, times(1)).enableProgressBar(false)
        val message = "TIME_OUT"
        verify(coyoView, times(1)).onPostDetailedInfoError(message)
    }
}