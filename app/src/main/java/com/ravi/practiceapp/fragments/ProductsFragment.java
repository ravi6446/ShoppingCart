package com.ravi.practiceapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ravi.practiceapp.DB.ShoppingDataBase;
import com.ravi.practiceapp.R;
import com.ravi.practiceapp.adapters.ProductsAdapter;
import com.ravi.practiceapp.interfaces.IAPIService;
import com.ravi.practiceapp.service.DataFetcher;
import com.ravi.practiceapp.model.Product;
import com.ravi.practiceapp.model.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   /* private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/

    public ProductsAdapter mProductsAdapter;
    public List<Product> mFetchedProducts;
    private OnFragmentInteractionListener mListener;
    private Context mContext;
    ShoppingDataBase mShoppingDataBase;

    public ProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param
     * @param
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance() {
        return new ProductsFragment();
       /* Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        //return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_view, container, false);

        final RecyclerView mRecyclerView;

        mShoppingDataBase =  ShoppingDataBase.getInMemoryDatabase(mContext);
        mFetchedProducts = new ArrayList<>();
        mProductsAdapter = new ProductsAdapter(mContext,mFetchedProducts,mShoppingDataBase);


        mRecyclerView = fragmentView.findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mProductsAdapter);

        //fetch data from server
        IAPIService retroInterface = DataFetcher.getRetroInterface();
        Call<Products> apiCall = retroInterface.getProducts();
        apiCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {

                if(response.code()==200 && response.message().equalsIgnoreCase("OK" )) {
                    mFetchedProducts  = response.body().getProducts();
                    mProductsAdapter = new ProductsAdapter(mContext,mFetchedProducts,mShoppingDataBase);
                    mRecyclerView.setAdapter(mProductsAdapter);
                    Toast.makeText(mContext, "Call success", Toast.LENGTH_SHORT).show();
                }
                else
                 Toast.makeText(mContext, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(mContext, "Server call failed!!!!", Toast.LENGTH_SHORT).show();

            }
        });

        return fragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mContext = context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        ShoppingDataBase.destroyInstance();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
