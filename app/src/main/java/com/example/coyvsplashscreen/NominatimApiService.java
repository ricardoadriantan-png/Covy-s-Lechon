package com.example.coyvsplashscreen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NominatimApiService {

    // Forward Geocoding: Address/Place string -> Coordinates & details
    @GET("search")
    Call<List<NominatimLocation>> searchAddress(
            @Query("q") String query,
            @Query("format") String format,
            @Query("addressdetails") int addressDetails,
            @Header("User-Agent") String userAgent
    );

    // Reverse Geocoding: Coordinates -> Address details
    @GET("reverse")
    Call<NominatimLocation> reverseGeocode(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("format") String format,
            @Query("addressdetails") int addressDetails,
            @Header("User-Agent") String userAgent
    );
}