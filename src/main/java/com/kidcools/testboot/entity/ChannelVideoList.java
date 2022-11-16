package com.kidcools.testboot.entity;


import java.util.List;

public class ChannelVideoList {
    private boolean main_cats;

    private String type;

    private int show_up_only;

    private int nb_videos;

    private int nb_per_page;

    private int current_page;

    private List<Videos> videos;

    private boolean is_model;

    private boolean is_channel;

    private boolean result;

    private int code;

    public void setMain_cats(boolean main_cats) {
        this.main_cats = main_cats;
    }

    public boolean getMain_cats() {
        return this.main_cats;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setShow_up_only(int show_up_only) {
        this.show_up_only = show_up_only;
    }

    public int getShow_up_only() {
        return this.show_up_only;
    }

    public void setNb_videos(int nb_videos) {
        this.nb_videos = nb_videos;
    }

    public int getNb_videos() {
        return this.nb_videos;
    }

    public void setNb_per_page(int nb_per_page) {
        this.nb_per_page = nb_per_page;
    }

    public int getNb_per_page() {
        return this.nb_per_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getCurrent_page() {
        return this.current_page;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }

    public List<Videos> getVideos() {
        return this.videos;
    }

    public void setIs_model(boolean is_model) {
        this.is_model = is_model;
    }

    public boolean getIs_model() {
        return this.is_model;
    }

    public void setIs_channel(boolean is_channel) {
        this.is_channel = is_channel;
    }

    public boolean getIs_channel() {
        return this.is_channel;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return this.result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}

