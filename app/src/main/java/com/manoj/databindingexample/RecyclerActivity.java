package com.manoj.databindingexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.manoj.databindingexample.adapter.reycleradapter;
import com.manoj.databindingexample.databinding.ActivityRecyclerBinding;
import com.manoj.databindingexample.utils.GridSpacingItemDecoration;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity implements reycleradapter.ReycleradapterListener {

    private MyClickHandlers handlers;
    private reycleradapter mAdapter;
    private RecyclerView recyclerView;
    private ActivityRecyclerBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_recycler);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler);
        handlers = new MyClickHandlers(this);

        renderProfile();

        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        mAdapter = new reycleradapter(getPosts(), this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void renderProfile() {
        user = new User();
        user.setName("David Attenborough");
        user.setEmail("david@natgeo.com");
        user.setProfileImage("https://api.androidhive.info/images/nature/david.jpg");
        user.setAbout("Naturalist");

        // ObservableField doesn't have setter method, instead will
        // be called using set() method
        user.numberOfPosts.set(3400L);
        user.numberOfFollowers.set(3050890L);
        user.numberOfFollowing.set(150L);


        // display user
        binding.setUser(user);

        // assign click handlers
        binding.setHandlers(handlers);
    }

    @Override
    public void onPostClicked(Post post) {
        Toast.makeText(getApplicationContext(), "Post clicked! " + post.getImageUrl(), Toast.LENGTH_SHORT).show();
    }

    public class MyClickHandlers {


        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }

        /**
         * Demonstrating updating bind data
         * Profile name, number of posts and profile image
         * will be updated on Fab click
         */
        public void onProfileFabClicked(View view) {
            user.setName("Sir David Attenborough");
            user.setProfileImage("https://api.androidhive.info/images/nature/david1.jpg");

            // updating ObservableField
            user.numberOfPosts.set(5500L);
            user.numberOfFollowers.set(5050890L);
            user.numberOfFollowing.set(180L);

            ArrayList<Post> arrayList = new ArrayList<>();
            for (int i=0;i<30;i++){
                Post post = new Post();
                post.setImageUrl("https://api.androidhive.info/images/nature/" + (i) + ".jpg");

                arrayList.add(post);
            }
            mAdapter = new reycleradapter(arrayList, RecyclerActivity.this);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

        public boolean onProfileImageLongPressed(View view) {
            Toast.makeText(getApplicationContext(), "Profile image long pressed!", Toast.LENGTH_LONG).show();
            return false;
        }


        public void onFollowersClicked(View view) {
            Toast.makeText(context, "Followers is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onFollowingClicked(View view) {
            Toast.makeText(context, "Following is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onPostsClicked(View view) {
            Toast.makeText(context, "Posts is clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Post post = new Post();
            post.setImageUrl("https://api.androidhive.info/images/nature/" + i + ".jpg");

            posts.add(post);
        }

        return posts;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}