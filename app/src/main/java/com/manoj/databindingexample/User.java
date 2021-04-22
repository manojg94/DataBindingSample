package com.manoj.databindingexample;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class User extends BaseObservable {

    String name;
    String email;

    String profileImage;
    String about;

    // profile meta fields are ObservableField, will update the UI
    // whenever a new value is set
    public ObservableField<Long> numberOfFollowers = new ObservableField<>();
    public ObservableField<Long> numberOfPosts = new ObservableField<>();
    public ObservableField<Long> numberOfFollowing = new ObservableField<>();


    public ObservableField<Long> getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(ObservableField<Long> numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public ObservableField<Long> getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(ObservableField<Long> numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public ObservableField<Long> getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public void setNumberOfFollowing(ObservableField<Long> numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }

    @Bindable
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        notifyPropertyChanged(BR.profileImage);
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
       notifyPropertyChanged(BR.email);
    }




    //this binding adapter "name " is linked to the xml layout bind.
    @BindingAdapter({"profileImages"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(view);

        // If you consider Picasso, follow the below
        // Picasso.with(view.getContext()).load(imageUrl).placeholder(R.drawable.placeholder).into(view);
    }
}
