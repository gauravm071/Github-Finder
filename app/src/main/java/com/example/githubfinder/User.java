package com.example.githubfinder;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("followers")
    String followers;
    @SerializedName("following")
    String followings;
    @SerializedName("public_repos")
    String publicRepos;
    @SerializedName("avatar_url")
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public User(String name, String email, String followers, String followings, String publicRepos, String imageUrl) {
        this.name = name;
        this.email = email;
        this.followers = followers;
        this.followings = followings;
        this.publicRepos = publicRepos;
        this.imageUrl = imageUrl;
    }



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowings() {
        return followings;
    }

    public String getPublicRepos() {
        return publicRepos;
    }
}
