package com.training.victor.development.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.data.Constants.Companion.POST
import com.training.victor.development.data.models.CommentViewModel
import com.training.victor.development.data.models.PostViewModel
import com.training.victor.development.data.models.UserViewModel
import com.training.victor.development.presenter.CoyoPresenter
import com.training.victor.development.ui.adapters.CommentsAdapter
import com.training.victor.development.ui.settings.SpaceDecorator
import com.training.victor.development.utils.getDpFromValue
import com.training.victor.development.utils.showRequestErrorMessage
import kotlinx.android.synthetic.main.activity_post_detail.*
import javax.inject.Inject

class PostDetailActivity: AppCompatActivity(), CoyoPresenter.CoyoView {
    @Inject
    lateinit var coyoPresenter: CoyoPresenter

    private val commentsList = ArrayList<CommentViewModel>()
    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        (application as MainApplication).createPresenterComponent().inject(this)

        val post = intent.getParcelableExtra<PostViewModel>(POST)
        txtDescription.text = post.body

        lstComments.layoutManager = LinearLayoutManager(this)
        lstComments.addItemDecoration(SpaceDecorator(getDpFromValue(5)))
        commentsAdapter = CommentsAdapter(commentsList)
        lstComments.adapter = commentsAdapter

        coyoPresenter.view = this
        coyoPresenter.getPostDetailedInfo(post.userId, post.id)
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MainApplication).releasePresenterComponent()
    }


    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- PRESENTER VIEW ---------------------------------------------
    override fun enableProgressBar(enabled: Boolean) {
        if (enabled) {
            progressBarDetail.visibility = View.VISIBLE
        } else {
            progressBarDetail.visibility = View.GONE
        }
    }

    override fun onPostListReceived(postList: List<PostViewModel>) { }

    override fun onPostListError(message: String) { }

    override fun onPostDetailedInfoReceived(userViewModel: UserViewModel, commentList: List<CommentViewModel>) {
        txtAuthor.text = userViewModel.name
        txtNickName.text = userViewModel.userName
        txtEmail.text = userViewModel.email
        val commentTitle = resources.getQuantityString(R.plurals.comments_number, commentList.size, commentList.size)
        txtComments.text = commentTitle

        commentsList.clear()
        commentsList.addAll(commentList)
        commentsAdapter.notifyDataSetChanged()
    }

    override fun onPostDetailedInfoError(message: String) {
        commentsList.clear()
        commentsAdapter.notifyDataSetChanged()
        showRequestErrorMessage(message)
    }
}