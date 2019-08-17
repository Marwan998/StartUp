package com.gov.startups;

import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;
    private final ArrayList<String> imgid;

    public MyListAdapter(Context context, ArrayList<String> name, ArrayList<String> companyInfo, ArrayList<String> icon) {
        super(context, R.layout.mylist, name);
        this.context=context;
        this.maintitle=name;
        this.subtitle=companyInfo;
        this.imgid=icon;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(maintitle.get(position));
        Picasso.get().load(imgid.get(position)).into(imageView);
        subtitleText.setText(subtitle.get(position));

        return rowView;

    }
}
