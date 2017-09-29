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
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class PagesFragment extends Fragment {

    public static ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    public String next;
    public String prev;
    public View view;
    CustomAdapter adapter;
    ListView lvpages;
    private OnFragmentInteractionListener mListener;

    public PagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pages, container, false);
        lvpages = (ListView) view.findViewById(R.id.lvpages);
        if(!ResultActivity.done){
            view = inflater.inflate(R.layout.fragment_progress,container,false);
            ProgressBar pb =(ProgressBar) view.findViewById(R.id.progressBar);
            pb.setProgress(50);
            return view;
        }
        Response pagesdata = ResultActivity.pagesdata;
        if (pagesdata != null) {
            List<Response.Data> data = pagesdata.getData();
            if(data.size()==0){
                view = inflater.inflate(R.layout.fragment_data_null,container,false);
                return view;
            }
            setListData(data);
            if(pagesdata.getPaging()==null){
                next=null;
                prev=null;
            }else {
                next = pagesdata.getPaging().getNext();
                prev = pagesdata.getPaging().getPrevious();
            }
            updateButtons(view);
            Resources res = getResources();
            adapter = new CustomAdapter(getActivity(), CustomListViewValuesArr, res);
            lvpages.setAdapter(adapter);
            lvpages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startDetails(position);
                }
            });
            view.findViewById(R.id.next_pages).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //NEXT button
                    String response = requestNext(next);
                    Gson gson = new GsonBuilder().create();
                    Response nextdata = gson.fromJson(response, Response.class);
                    next = nextdata.getPaging().getNext();
                    prev = nextdata.getPaging().getPrevious();
                    updateButtons(view);
                    setListData(nextdata.getData());
                    Resources res = getResources();
                    adapter = new CustomAdapter(getActivity(), CustomListViewValuesArr, res);
                    lvpages.setAdapter(adapter);

                }
            });
            view.findViewById(R.id.prev_pages).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //PREVIOUS button
                    String response = requestNext(prev);
                    Gson gson = new GsonBuilder().create();
                    Response prevdata = gson.fromJson(response, Response.class);
                    next = prevdata.getPaging().getNext();
                    prev = prevdata.getPaging().getPrevious();
                    updateButtons(view);
                    setListData(prevdata.getData());
                    Resources res = getResources();
                    adapter = new CustomAdapter(getActivity(), CustomListViewValuesArr, res);
                    lvpages.setAdapter(adapter);

                }
            });
            return view;
        } else {
            view = inflater.inflate(R.layout.fragment_data_null, container, false);
            return view;
        }
    }
    @Override
    public void onResume() {
        Resources res = getResources();
        adapter = new CustomAdapter(getActivity(), CustomListViewValuesArr, res);
        lvpages.setAdapter(adapter);
        super.onResume();
    }

    private void updateButtons(View v) {
        Button nextbtn = (Button) v.findViewById(R.id.next_pages);
        Button prevbtn = (Button) v.findViewById(R.id.prev_pages);
        if (next != null) {
            nextbtn.setEnabled(true);
        } else {
            nextbtn.setEnabled(false);
        }
        if (prev != null) {
            prevbtn.setEnabled(true);
        } else {
            prevbtn.setEnabled(false);
        }
    }

    public String requestNext(String url) {
        HttpGetRequest request = new HttpGetRequest();
        request.execute(url);
        String response = "";
        try {
            response = request.get();
            return response;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void startDetails(int pos) {
        String id = CustomListViewValuesArr.get(pos).getId();
        Intent details = new Intent(getActivity(), DetailsActivity.class);
        details.putExtra("id", id);
        details.putExtra("type", "pages");
        getActivity().startActivity(details);
        getActivity().overridePendingTransition(R.anim.slide_to_up, R.anim.slide_from_down);
    }

    public void setListData(List<Response.Data> data) {
        CustomListViewValuesArr.clear();
        for (Response.Data x : data) {
            ListModel sched = new ListModel();
            sched.setId(x.getId());
            sched.setImage(x.getPicture().getData().getUrl());
            sched.setName(x.getName());
            CustomListViewValuesArr.add(sched);
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
