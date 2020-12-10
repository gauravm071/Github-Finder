package com.example.githubfinder;

import com.google.gson.annotations.SerializedName;

public class UserRepo {
    @SerializedName("name")
    String name;
    @SerializedName("description")
    String description;
    @SerializedName("language")
    String language;


    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public UserRepo(String name, String language, String description) {
        this.name = name;
        this.language = language;
        this.description = description;
    }
}
