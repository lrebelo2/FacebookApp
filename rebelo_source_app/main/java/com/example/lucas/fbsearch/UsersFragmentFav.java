package com.example.lucas.fbsearch;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class UsersFragmentFav extends Fragment {

    private OnFragmentInteractionListener mListener;
    CustomAdapter adapter;
    ListView lvusersfav;
    public static ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    public View view;
    Map<String,?> pref;
    public UsersFragmentFav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_users_fav,container,false);
        lvusersfav = (ListView) view.findViewById(R.id.lvusersfav);
        pref = Main2Activity.preferences.getAll();
        setListData(pref);
        if(!CustomListViewValuesArr.isEmpty()) {
            Resources res = getResources();
            adapter = new CustomAdapter(getActivity(), CustomListViewValuesArr, res);
            lvusersfav.setAdapter(adapter);
            lvusersfav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startDetails(position);
                }
            });
            return view;
        }
            view = inflater.inflate(R.layout.fragment_fav_null,container,false);
            return view;
    }

    @Override
    public void onResume() {
        setListData(pref);
        Resources res = getResources();
        adapter = new CustomAdapter(getActivity(), CustomListViewValuesArr, res);
        lvusersfav.setAdapter(adapter);
        super.onResume();
    }


    public void startDetails(int pos){
        String id = CustomListViewValuesArr.get(pos).getId();
        Intent details = new Intent(getActivity(), DetailsActivity.class);
        details.putExtra("id",id);
        details.putExtra("type","users");
        getActivity().startActivity(details);
        getActivity().overridePendingTransition(R.anim.slide_to_up, R.anim.slide_from_down);
    }

    public void setListData(Map<String,?> pref){
        CustomListViewValuesArr.clear();
        Gson gson = new GsonBuilder().create();
        for(Map.Entry<String,?> entry : pref.entrySet()){
            ListModel lm = gson.fromJson(entry.getValue().toString(),ListModel.class);
            if(lm.getType().equals("users")) {
                CustomListViewValuesArr.add(lm);
            }
        }
    }

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

        void onFragmentInteraction(Uri uri);
    }
}
