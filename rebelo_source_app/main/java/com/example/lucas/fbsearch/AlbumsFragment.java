package com.example.lucas.fbsearch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlbumsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AlbumsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    public View view;
    public ResponseDetails.Albums albumdata;
    public ExpandableListView lvalbums;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_albums,container,false);
        lvalbums = (ExpandableListView) view.findViewById(R.id.lvalbums);
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String,List<String>>();
        albumdata = DetailsActivity.detailsdata.getAlbums();
        if(albumdata!=null) {
            setData();
            ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
            lvalbums.setAdapter(adapter);
            lvalbums.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    for(int i=0;i<albumdata.getData().size();i++){
                        if(i!=groupPosition){
                            lvalbums.collapseGroup(i);
                        }
                    }
                }
            });
            return view;
        }else{
            view = inflater.inflate(R.layout.fragment_albums_null,container,false);
            return view;
        }
    }
    @Override
    public void onResume() {
        ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        lvalbums.setAdapter(adapter);
        super.onResume();
    }
    public void setData(){
        listDataHeader.clear();
        listDataChild.clear();
        for(ResponseDetails.DataAlbum x:albumdata.getData()){
            listDataHeader.add(x.getName());
            List<String> photos = new ArrayList<String>();
            if(x.getPhotos()!=null) {
                for (ResponseDetails.DataPhoto y : x.getPhotos().getData()) {
                    String pic_id = y.getId();
                    photos.add("https://graph.facebook.com/v2.8/" + pic_id + "/picture?access_token=EAAQ7likbczkBANXPrjETeKH7uFbkzpGUXuyLl8M3sndbCQsDTqi7wu7gol0DiKtmIuhFRs4Fe4y7HQZAAl9Dfi16zwLADn2kLhh6o33slstxPgP72X4bT5xAYNAZCfSWHcxQ4FG2zukAFr8NkgA6QLv0xzONQZD");
                }
            }else{
                photos.add("No photos available");
            }
            listDataChild.put(x.getName(),photos);
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
