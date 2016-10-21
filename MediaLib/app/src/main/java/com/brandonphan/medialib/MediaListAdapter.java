package com.brandonphan.medialib;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MediaListAdapter extends ArrayAdapter<Object> {
    Activity parentActivity;

    public MediaListAdapter(Context context, ArrayList<Object> mediaList){
        super(context, 0, mediaList);
        parentActivity = (Activity) this.getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = parentActivity.getLayoutInflater()
                    .inflate(R.layout.list_item_song, null);
        }

        Object media = getItem(position);

        TextView nameTextView = (TextView)convertView.findViewById(R.id.song_list_item_title);
        nameTextView.setText(media.getClass().getName());

        return convertView;

    }
}