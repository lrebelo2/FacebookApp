package com.example.lucas.fbsearch;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PostsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    public View view;
    CustomAdapterPost adapter;
    public ListView lvposts;
    public ResponseDetails.Posts postsdata;
    public static ArrayList<ListModelPost> CustomListViewValuesArr;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_posts, container, false);
        lvposts = (ListView) view.findViewById(R.id.lvposts);
        CustomListViewValuesArr = new ArrayList<ListModelPost>();
        postsdata = DetailsActivity.detailsdata.getPosts();
        if(postsdata != null) {
            setData(DetailsActivity.detailsdata.getName(), DetailsActivity.detailsdata.getPicture().getData().getUrl(),DetailsActivity.detailsdata.getId());
            Resources res = getResources();
            adapter = new CustomAdapterPost(getActivity(), CustomListViewValuesArr, res);
            lvposts.setAdapter(adapter);
            return view;
        }else{
            return inflater.inflate(R.layout.fragment_posts_null, container, false);
        }
    }
    public void setData(String name,String pic,String id){
        CustomListViewValuesArr.clear();
        for(ResponseDetails.DataPost x:postsdata.getData()){
            ListModelPost lm = new ListModelPost();
            lm.setName(name);
            lm.setId(id);
            lm.setPic(pic);
            lm.setCreated_time(x.getCreatedTime());
            lm.setMessage(x.getMessage());
            CustomListViewValuesArr.add(lm);

        }
    }
    @Override
    public void onResume(){
        Resources res = getResources();
        adapter = new CustomAdapterPost(getActivity(), CustomListViewValuesArr, res);
        lvposts.setAdapter(adapter);
        super.onResume();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
