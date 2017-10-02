package com.ravi.practiceapp.interfaces;

import com.ravi.practiceapp.model.Products;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ravi on 01/10/17.
 */

public interface IAPIService {

    @GET("/getdata/")
    Call<Products> getProducts();
}
