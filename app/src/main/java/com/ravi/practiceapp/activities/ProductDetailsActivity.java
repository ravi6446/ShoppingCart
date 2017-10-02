package com.ravi.practiceapp.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ravi.practiceapp.R;
import com.ravi.practiceapp.adapters.ProductDetailsAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ViewPager mViewPager = findViewById(R.id.viewpagerImages);
        CircleIndicator mCircleIndicator = findViewById(R.id.circleindicator);

        ArrayList<String> mProductImageList = (ArrayList<String>) getIntent().getSerializableExtra("productImages");
        getSupportActionBar().setTitle(getIntent().getStringExtra("productName"));

        mViewPager.setAdapter(new ProductDetailsAdapter(mProductImageList,ProductDetailsActivity.this));
        mCircleIndicator.setViewPager(mViewPager);

    }
}
