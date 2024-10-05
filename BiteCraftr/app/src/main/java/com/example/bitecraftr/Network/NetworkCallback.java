package com.example.bitecraftr.Network;

import java.util.List;

public interface NetworkCallback<T> {

    // Called when the network request is successful and returns a list of meals
    void onSuccess(List<T> resultList);

    // Called when the network request fails, providing an error message
    void onFailure(String errorMessage);
}

