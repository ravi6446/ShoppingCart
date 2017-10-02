package com.ravi.practiceapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
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
import com.ravi.practiceapp.activities.ProductDetailsActivity;
import com.ravi.practiceapp.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 01/10/17.
 */

public class ProductsAdapter extends  RecyclerView.Adapter<ProductsAdapter.ViewHolder>{


    private Context mContext;
    private List<Product> mProductList;
    private ShoppingDataBase mShoppingDataBase;

    public ProductsAdapter(Context mContext, List<Product> mProductList,ShoppingDataBase mShoppingDataBase) {
        this.mContext = mContext;
        this.mProductList = mProductList;
        this.mShoppingDataBase = mShoppingDataBase;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productName,price,vendorName,vendorAddress;
        private ImageView productImage;
        private CardView mCardView;
        private Button addToCart;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageViewProduct);
            productName = itemView.findViewById(R.id.textViewName);
            price = itemView.findViewById(R.id.textViewPrice);
            vendorName = itemView.findViewById(R.id.textViewVendor);
            vendorAddress = itemView.findViewById(R.id.textViewVendorAddress);
            mCardView = itemView.findViewById(R.id.card_view);
            addToCart = itemView.findViewById(R.id.buttonAddToCart);
        }
    }
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent,false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ProductsAdapter.ViewHolder holder, int position) {

        holder.productName.setText(mProductList.get(position).getProductname());
        holder.price.setText(String.valueOf(mProductList.get(position).getPrice()));
        holder.vendorName.setText(mProductList.get(position).getVendorname());
        holder.vendorAddress.setText(mProductList.get(position).getVendoraddress());
        Glide.with(mContext).load(mProductList.get(position).getProductImg())
                .into(holder.productImage);


        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent mIntent = new Intent(mContext, ProductDetailsActivity.class);
               // mIntent.putStringArrayListExtra("productImages",(ArrayList<String>) mProductList.get(position).getProductGallery());
                mIntent.putExtra("productName",mProductList.get(holder.getAdapterPosition()).getProductname());
                mIntent.putExtra("productImages",(ArrayList)mProductList.get(holder.getAdapterPosition()).getProductGallery());
                mContext.startActivity(mIntent);*/

                //share element code
                Intent mIntent = new Intent(mContext, ProductDetailsActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) mContext,holder.productImage,ViewCompat.getTransitionName(holder.productImage));
                mIntent.putExtra("productImage",mProductList.get(holder.getAdapterPosition()).getProductImg());
                mIntent.putExtra("productName",mProductList.get(holder.getAdapterPosition()).getProductname());
                mIntent.putExtra("productImages",(ArrayList)mProductList.get(holder.getAdapterPosition()).getProductGallery());
                mContext.startActivity(mIntent, options.toBundle());
            }
        });

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add product to database
                CartProducts mCartProducts = new CartProducts();
                mCartProducts.setProductName(mProductList.get(holder.getAdapterPosition()).getProductname());
                mCartProducts.setProductPrice(mProductList.get(holder.getAdapterPosition()).getPrice());
                mCartProducts.setImage(mProductList.get(holder.getAdapterPosition()).getProductImg());
                mCartProducts.setVendorName(mProductList.get(holder.getAdapterPosition()).getVendorname());
                mCartProducts.setVendorAddress(mProductList.get(holder.getAdapterPosition()).getVendoraddress());
                mCartProducts.setVendorPhone(mProductList.get(holder.getAdapterPosition()).getPhoneNumber());


                new DatabaseAsync().execute(mCartProducts);



            }
        });
    }



    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    private class DatabaseAsync extends AsyncTask<CartProducts,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(mContext, "Adding to Db", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(CartProducts... cartProducts) {
            mShoppingDataBase.mCartDao().insertSingleRecord(cartProducts[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(mContext, "Added Successfully!!", Toast.LENGTH_SHORT).show();
        }
    }

}
