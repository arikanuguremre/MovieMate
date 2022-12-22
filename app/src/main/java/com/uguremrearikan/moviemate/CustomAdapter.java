package com.uguremrearikan.moviemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomAdapter  extends BaseAdapter {

    Context context;
    int images[];
    LayoutInflater inflter;

    public CustomAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        inflter=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {return images.length;}

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflter.inflate(R.layout.spinner_custom_layout,null);
        ImageView icon = view.findViewById(R.id.imageView);
        icon.setImageResource(images[i]);
        return view;
    }


}
