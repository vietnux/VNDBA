package com.booboot.vndbandroid.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by od on 12/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links extends VNDBCommand {
    public final static String WIKIPEDIA = "https://en.wikipedia.org/wiki/";
    public final static String ENCUBED = "http://novelnews.net/tag/";
    public final static String RENAI = "http://renai.us/game/";

    private String wikipedia;
    private String encubed;
    private String renai;

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
}
