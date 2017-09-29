package com.example.lucas.fbsearch;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.lucas.fbsearch.ListModel;
import com.example.lucas.fbsearch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomAdapterPost extends BaseAdapter {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    private ListModelPost tempValues=null;
    int i=0;

    public CustomAdapterPost(Activity a, ArrayList d,Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data=d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{

        public ImageView imagepost;
        public TextView name;
        public TextView message;
        public TextView created_time;
        public String id;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        com.example.lucas.fbsearch.CustomAdapterPost.ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_item_details, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new com.example.lucas.fbsearch.CustomAdapterPost.ViewHolder();
            holder.name=(TextView)vi.findViewById(R.id.name);
            holder.message=(TextView)vi.findViewById(R.id.message);
            holder.created_time=(TextView)vi.findViewById(R.id.created_time);
            holder.imagepost=(ImageView)vi.findViewById(R.id.imagepost);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(com.example.lucas.fbsearch.CustomAdapterPost.ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.name.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( ListModelPost ) data.get( position );

            /************  Set Model values in Holder elements ***********/
            holder.id = tempValues.getId();
            holder.name.setText( tempValues.getName() );
            String imgurl = tempValues.getPic();
            Picasso.with(activity).load(imgurl).resize(200,200).into(holder.imagepost);
            holder.created_time.setText(tempValues.getCreated_time());
            holder.message.setText(tempValues.getMessage());
        }
        return vi;
    }
}