package com.qhiran.uts;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MusicResponse {
    @SerializedName("data")
    private List<MusicItem> data;

    public List<MusicItem> getData() {
        return data;
    }
}

class MusicItem {
    @SerializedName("title")
    private String title;

    @SerializedName("artist")
    private Artist artist;

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    static class Artist {
        @SerializedName("name")
        private String name;

        public String getName() {
            return name;
        }
    }
}
