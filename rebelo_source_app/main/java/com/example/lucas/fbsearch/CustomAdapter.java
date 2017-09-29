package com.example.lucas.fbsearch;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    private ListModel tempValues = null;
    int i = 0;

    public CustomAdapter(Activity a, ArrayList d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {

        public ImageView detailsbutton;
        public ImageView favimage;
        public TextView nameTextView;
        public ImageView image;
        public String id;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_item, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.detailsbutton = (ImageView) vi.findViewById(R.id.deatilsbutton);
            holder.nameTextView = (TextView) vi.findViewById(R.id.nameTextView);
            holder.image = (ImageView) vi.findViewById(R.id.image);
            holder.favimage = (ImageView) vi.findViewById(R.id.favimage);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0) {
            holder.nameTextView.setText("No Data");

        } else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (ListModel) data.get(position);

            /************  Set Model values in Holder elements ***********/
            holder.id = tempValues.getId();
            holder.nameTextView.setText(tempValues.getName());
            String imgurl = tempValues.getImage();
            Picasso.with(activity).load(imgurl).into(holder.image);
            boolean contains = Main2Activity.preferences.contains(tempValues.getId());
            if (contains) {
                holder.favimage.setImageResource(res.getIdentifier("com.example.lucas.fbsearch:drawable/favorites_on", null, null));
            } else {
                holder.favimage.setImageResource(res.getIdentifier("com.example.lucas.fbsearch:drawable/favorites_off", null, null));
            }
        }
        return vi;
    }
}
