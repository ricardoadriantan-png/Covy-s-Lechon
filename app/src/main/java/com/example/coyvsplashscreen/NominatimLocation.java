package com.example.coyvsplashscreen;

import com.google.gson.annotations.SerializedName;

public class NominatimLocation {
    @SerializedName("place_id")
    private long placeId;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("lat")
    private String latitude;

    @SerializedName("lon")
    private String longitude;

    @SerializedName("address")
    private AddressDetails address;

    public long getPlaceId() {
        return placeId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public AddressDetails getAddress() {
        return address;
    }
}