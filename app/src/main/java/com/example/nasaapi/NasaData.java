package com.example.nasaapi;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class NasaData implements Serializable{

    /**
     * date : 2017-05-01
     * explanation : The bright source near
     * hdurl : https://apod.nasa.gov/apod/image/1705/casa_main_960.jpg
     * media_type : image
     * service_version : v1
     * title : Cooling Neutron Star
     * url : https://apod.nasa.gov/apod/image/1705/casa_main_960.jpg
     */

    @SerializedName("date")
    private Date date;
    @SerializedName("explanation")
    private String explanation;
    @SerializedName("hdurl")
    private String hdurl;
    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("service_version")
    private String serviceVersion;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    public String getHdurl() {
        return hdurl;
    }
    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }
    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    public String getServiceVersion() {
        return serviceVersion;
    }
    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
