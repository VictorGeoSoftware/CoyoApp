package com.training.victor.development.ui

import android.content.Intent
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
import com.training.victor.development.ui.adapters.PostAdapter
import com.training.victor.development.ui.settings.SpaceDecorator
import com.training.victor.development.utils.getDpFromValue
import com.training.victor.development.utils.showRequestErrorMessage
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CoyoPresenter.CoyoView, PostAdapter.OnPostClickListener {

    @Inject lateinit var coyoPresenter: CoyoPresenter
    private val mPostList = ArrayList<PostViewModel>()
    private lateinit var postAdapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).createPresenterComponent().inject(this)


        val myLayoutManager = LinearLayoutManager(this)
        lstPost.layoutManager = myLayoutManager
        lstPost.addItemDecoration(SpaceDecorator(getDpFromValue(10)))
        postAdapter = PostAdapter(mPostList, this)
        lstPost.adapter = postAdapter

        coyoPresenter.view = this
        coyoPresenter.getPostList()
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MainApplication).releasePresenterComponent()
    }


    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- USER INTERACTION -------------------------------------------
    override fun onPostClicked(post: PostViewModel) {
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra(POST, post)
        startActivity(intent)
    }


    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- PRESENTER VIEW ---------------------------------------------
    override fun enableProgressBar(enabled: Boolean) {
        if (enabled) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onPostListReceived(postList: List<PostViewModel>) {
        mPostList.clear()
        mPostList.addAll(postList)
        postAdapter.notifyDataSetChanged()
    }

    override fun onPostListError(message: String) {
        mPostList.clear()
        postAdapter.notifyDataSetChanged()
        showRequestErrorMessage(message)
    }

    override fun onPostDetailedInfoReceived(userViewModel: UserViewModel, commentList: List<CommentViewModel>) { }

    override fun onPostDetailedInfoError(message: String) { }
}
