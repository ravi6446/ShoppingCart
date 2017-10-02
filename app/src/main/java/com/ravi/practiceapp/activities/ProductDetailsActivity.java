package com.ravi.practiceapp.activities;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ravi.practiceapp.R;
import com.ravi.practiceapp.adapters.ProductDetailsAdapter;
import com.ravi.practiceapp.utils.PermissionManager;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ImageView mIageVIewShared = findViewById(R.id.imageViewShared);
        TextView textViewName = findViewById(R.id.textViewName);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        supportPostponeEnterTransition();

        ViewPager mViewPager = findViewById(R.id.viewpagerImages);
        CircleIndicator mCircleIndicator = findViewById(R.id.circleindicator);

        ArrayList<String> mProductImageList = (ArrayList<String>) getIntent().getSerializableExtra("productImages");
        textViewName.setText(getIntent().getStringExtra("productName"));


        mViewPager.setAdapter(new ProductDetailsAdapter(mProductImageList, ProductDetailsActivity.this));
        mCircleIndicator.setViewPager(mViewPager);


        Glide.with(this)
                .load(getIntent().getStringExtra("productImage"))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;

                    }
                })
                .into(mIageVIewShared);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
