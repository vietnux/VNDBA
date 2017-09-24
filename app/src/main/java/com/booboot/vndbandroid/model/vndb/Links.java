package com.booboot.vndbandroid.model.vndb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by od on 12/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {
    public final static String WIKIPEDIA = "https://en.wikipedia.org/wiki/";
    public final static String ENCUBED = "http://novelnews.net/tag/";
    public final static String RENAI = "http://renai.us/game/";
    public final static String ANIDB = "https://anidb.net/perl-bin/animedb.pl?show=anime&aid=";
    public final static String VNDB_REGISTER = "https://vndb.org/u/register";
    public final static String VNDB = "https://vndb.org";
    public final static String VNDB_PAGE = "https://vndb.org/v";
    public final static String GITHUB = "https://github.com/herbeth1u/VNDB-Android";
    public final static String PLAY_STORE = "https://play.google.com/store/apps/details?id=com.booboot.vndbandroid";
    public final static String VNSTAT = "https://vnstat.net/";
    public final static String EMAIL = "vndba.app@gmail.com";

    private String wikipedia;
    private String encubed;
    private String renai;
    private String homepage;
    private String twitter;
    private String anidb;

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getEncubed() {
        return encubed;
    }

    public void setEncubed(String encubed) {
        this.encubed = encubed;
    }

    public String getRenai() {
        return renai;
    }

    public void setRenai(String renai) {
        this.renai = renai;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getAnidb() {
        return anidb;
    }

    public void setAnidb(String anidb) {
        this.anidb = anidb;
    }
}