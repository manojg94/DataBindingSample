package com.manoj.databindingexample

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manoj.databindingexample.adapter.reycleradapter
import com.manoj.databindingexample.adapter.reycleradapter.ReycleradapterListener
import com.manoj.databindingexample.databinding.ActivityRecyclerBinding
import com.manoj.databindingexample.utils.GridSpacingItemDecoration
import java.util.*

class RecyclerActivity : AppCompatActivity(), ReycleradapterListener {
    private var handlers: MyClickHandlers? = null
    private var mAdapter: reycleradapter? = null
    private var recyclerView: RecyclerView? = null
    private var binding: ActivityRecyclerBinding? = null
    private var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_recycler);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler)
        handlers = MyClickHandlers(this)
        renderProfile()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = binding!!.recyclerView
        recyclerView!!.layoutManager = GridLayoutManager(this, 3)
        recyclerView!!.addItemDecoration(GridSpacingItemDecoration(3, dpToPx(4), true))
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.isNestedScrollingEnabled = false
        mAdapter = reycleradapter(posts, this)
        recyclerView!!.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()
    }

    private fun renderProfile() {
        user = User()
        user!!.setName("David Attenborough")
        user!!.setEmail("david@natgeo.com")
        user!!.setProfileImage("https://api.androidhive.info/images/nature/david.jpg")
        user!!.setAbout("Naturalist")

        // ObservableField doesn't have setter method, instead will
        // be called using set() method
        user!!.numberOfPosts.set(3400L)
        user!!.numberOfFollowers.set(3050890L)
        user!!.numberOfFollowing.set(150L)


        // display user
        binding!!.user = user

        // assign click handlers
        binding!!.handlers = handlers
    }

    override fun onPostClicked(post: Post) {
        Toast.makeText(applicationContext, "Post clicked! " + post.imageUrl, Toast.LENGTH_SHORT).show()
    }

    inner class MyClickHandlers(var context: Context) {

        /**
         * Demonstrating updating bind data
         * Profile name, number of posts and profile image
         * will be updated on Fab click
         */
        fun onProfileFabClicked(view: View?) {
            user!!.setName("Sir David Attenborough")
            user!!.setProfileImage("https://api.androidhive.info/images/nature/david1.jpg")

            // updating ObservableField
            user!!.numberOfPosts.set(5500L)
            user!!.numberOfFollowers.set(5050890L)
            user!!.numberOfFollowing.set(180L)
            val arrayList = ArrayList<Post>()
            for (i in 0..29) {
                val post = Post()
                post.imageUrl = "https://api.androidhive.info/images/nature/$i.jpg"
                arrayList.add(post)
            }
            mAdapter = reycleradapter(arrayList, this@RecyclerActivity)
            recyclerView!!.adapter = mAdapter
            mAdapter!!.notifyDataSetChanged()
        }

        fun onProfileImageLongPressed(view: View?): Boolean {
            Toast.makeText(applicationContext, "Profile image long pressed!", Toast.LENGTH_LONG).show()
            return false
        }

        fun onFollowersClicked(view: View?) {
            Toast.makeText(context, "Followers is clicked!", Toast.LENGTH_SHORT).show()
        }

        fun onFollowingClicked(view: View?) {
            Toast.makeText(context, "Following is clicked!", Toast.LENGTH_SHORT).show()
        }

        fun onPostsClicked(view: View?) {
            Toast.makeText(context, "Posts is clicked!", Toast.LENGTH_SHORT).show()
        }

    }

    private val posts: ArrayList<Post>
        private get() {
            val posts = ArrayList<Post>()
            for (i in 1..9) {
                val post = Post()
                post.imageUrl = "https://api.androidhive.info/images/nature/$i.jpg"
                posts.add(post)
            }
            return posts
        }

    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }
}