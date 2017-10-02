package com.ravi.practiceapp.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ravi.practiceapp.R;

import java.util.ArrayList;

/**
 * Created by ravi on 01/10/17.
 */

public class ProductDetailsAdapter extends PagerAdapter {

    ArrayList<String> productImages;
    LayoutInflater mlayLayoutInflater;
    Context mContext;

    public ProductDetailsAdapter(ArrayList<String> productImages, Context mContext) {
        this.productImages = productImages;
        this.mContext = mContext;
        mlayLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return productImages.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View myImageLayout = mlayLayoutInflater.inflate(R.layout.image_slide, container, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.imageviewdetail);
        Glide.with(mContext).load(productImages.get(position)).into(myImage);
        container.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
