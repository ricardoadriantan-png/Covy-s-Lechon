package com.example.coyvsplashscreen;

import com.google.gson.annotations.SerializedName;

public class AddressDetails {
    @SerializedName("road")
    private String road;

    @SerializedName("suburb")
    private String suburb;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("postcode")
    private String postcode;

    @SerializedName("country")
    private String country;

    public String getRoad() {
        return road;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }
}