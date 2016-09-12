package com.ivan_prokofyev.kilograpptest.Data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shiraha on 11.09.2016.
 */
public class ResponseItem implements Serializable {
    @SerializedName("label")
    private String label;
    @SerializedName("author")
    private String author;
    @SerializedName("version")
    private Integer version;
    @SerializedName("id")
    private Integer id;


    public String getLabel() {
        return label;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getVersion() {
        return version;
    }

    public Integer getId() {
        return id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
