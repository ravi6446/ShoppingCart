package com.ravi.practiceapp.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ravi.practiceapp.DB.ShoppingDataBase;
import com.ravi.practiceapp.DBentities.CartProducts;
import com.ravi.practiceapp.R;
import com.ravi.practiceapp.utils.PermissionManager;

import java.util.ArrayList;

/**
 * Created by ravi on 01/10/17.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<CartProducts> mCartProducts;
    private Context mContext;
    private ShoppingDataBase mShoppingDataBase;

    public CartAdapter(ArrayList<CartProducts> mCartProducts, Context mContext, ShoppingDataBase mShoppingDataBase) {
        this.mCartProducts = mCartProducts;
        this.mContext = mContext;
        this.mShoppingDataBase = mShoppingDataBase;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productName, price, vendorName, vendorAddress;
        private ImageView productImage;
        private Button deleteItem, callVendor;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageViewProduct);
            productName = itemView.findViewById(R.id.textViewName);
            price = itemView.findViewById(R.id.textViewPrice);
            vendorName = itemView.findViewById(R.id.textViewVendor);
            vendorAddress = itemView.findViewById(R.id.textViewVendorAddress);
            deleteItem = itemView.findViewById(R.id.buttonRemove);
            callVendor = itemView.findViewById(R.id.buttonCall);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.cart_cardview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.productName.setText(mCartProducts.get(position).getProductName());
        holder.price.setText(String.valueOf(mCartProducts.get(position).getProductPrice()));
        holder.vendorName.setText(mCartProducts.get(position).getVendorName());
        holder.vendorAddress.setText(mCartProducts.get(position).getVendorAddress());
        Glide.with(mContext).load(mCartProducts.get(position).getImage())
                .into(holder.productImage);

        holder.callVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(PermissionManager.checkPermission(Manifest.permission.CALL_PHONE,mContext)){
                   mContext.startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel",mCartProducts.get(holder.getAdapterPosition()).getVendorPhone(),null)));
                }
                else{
                    ActivityCompat.requestPermissions((Activity)mContext,new String[]{Manifest.permission.CALL_PHONE},PermissionManager.CALL_PERMISSION_REQUEST_CODE);
                }
            }
        });

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add product to database
                CartProducts mCartProduct = new CartProducts();
                mCartProduct.setSlNo(mCartProducts.get(holder.getAdapterPosition()).getSlNo());
                new DatabaseAsync().execute(mCartProduct);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCartProducts.size();
    }



    private class DatabaseAsync extends AsyncTask<CartProducts, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(mContext, "Deleting from Db", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Integer doInBackground(CartProducts... cartProducts) {
            return mShoppingDataBase.mCartDao().deleteRecords(cartProducts[0]);
        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
            Toast.makeText(mContext, i + " Rows deleted from Db", Toast.LENGTH_SHORT).show();
        }
    }

}
