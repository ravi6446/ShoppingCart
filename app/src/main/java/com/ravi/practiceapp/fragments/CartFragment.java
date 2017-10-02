package com.ravi.practiceapp.fragments;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ravi.practiceapp.DB.ShoppingDataBase;
import com.ravi.practiceapp.DBentities.CartProducts;
import com.ravi.practiceapp.R;
import com.ravi.practiceapp.adapters.CartAdapter;
import com.ravi.practiceapp.utils.PermissionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   /* private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/

    private OnFragmentInteractionListener mListener;
    ShoppingDataBase mShoppingDataBase;
    private Context mContext;
    ArrayList<CartProducts> mCartProducts;
    RecyclerView mRecyclerView;
    CartAdapter mCartAdapter;
    TextView mTotal;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance() {
        return new CartFragment();
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

        if (!PermissionManager.checkPermission(Manifest.permission.CALL_PHONE, mContext)) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, PermissionManager.CALL_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_view, container, false);

        mShoppingDataBase = ShoppingDataBase.getInMemoryDatabase(mContext);

        LiveData<List<CartProducts>> mCartLiveData = mShoppingDataBase.mCartDao().fetchAllCart();


        mTotal = fragmentView.findViewById(R.id.textViewTotal);
        mTotal.setVisibility(View.VISIBLE);

        mRecyclerView = fragmentView.findViewById(R.id.my_recycler_view);
        mCartProducts = new ArrayList<>();

        mCartAdapter = new CartAdapter(mCartProducts, mContext, mShoppingDataBase);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mCartAdapter);

        mCartLiveData.observe(this, new Observer<List<CartProducts>>() {
            @Override
            public void onChanged(@Nullable List<CartProducts> cartProducts) {
                // Toast.makeText(mContext, "Length "+ cartProducts.size(), Toast.LENGTH_SHORT).show();

                //populate data
                mCartProducts = (ArrayList<CartProducts>) cartProducts;
                mCartAdapter = new CartAdapter(mCartProducts, mContext, mShoppingDataBase);
                mRecyclerView.setAdapter(mCartAdapter);

                float total =0.0f;
                for (CartProducts product: mCartProducts
                        ) {
                    total += product.getProductPrice();
                }
                mTotal.setText(mContext.getString(R.string.total) +String.valueOf(total));

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PermissionManager.CALL_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(mContext, "You can call the number by clicking on the button", Toast.LENGTH_SHORT).show();
                }
                return;

            default:
                return;
        }
    }
}
