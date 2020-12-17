package com.example.githubfinder;

import com.google.gson.annotations.SerializedName;

public class UserRepo {
    @SerializedName("name")
    String name;
    @SerializedName("description")
    String description;
    @SerializedName("language")
    String language;
    @SerializedName("languages_url")
    String languagesurl;
    @SerializedName("default_branch")
    String defaultBranch;
    @SerializedName("forks_count")
    String forksCount;
    @SerializedName("svn_url")
    String url;

    public String getUrl() {
        return url;
    }

    public String getContributionUrl() {
        return contributionUrl;
    }

    @SerializedName("contributors_url")
    String contributionUrl;
    public String getDefaultBranch() {
        return defaultBranch;
    }

    public String getForksCount() {
        return forksCount;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguagesurl() {
        return languagesurl;
    }

    public UserRepo(String name, String description, String language, String languagesurl, String defaultBranch, String forksCount, String url, String contributionUrl) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.languagesurl = languagesurl;
        this.defaultBranch = defaultBranch;
        this.forksCount = forksCount;
        this.url = url;
        this.contributionUrl = contributionUrl;
    }
}
