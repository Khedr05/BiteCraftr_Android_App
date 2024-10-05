package com.example.bitecraftr.Network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse<T> {

    // List of meals fetched from the API
    public List<T> meals;
    // List of categories fetched from the API
    public List<T> categories;
}

