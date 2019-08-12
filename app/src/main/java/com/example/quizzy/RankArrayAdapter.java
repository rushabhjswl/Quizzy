package com.example.quizzy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RankArrayAdapter extends ArrayAdapter<UserPercents> {

    Context mContext;
    int layoutResourceId;
    ArrayList<UserPercents> userPercents;

    public RankArrayAdapter(Context mContext, int layoutResourceId,  ArrayList<UserPercents> userPercents){

        super(mContext, layoutResourceId, userPercents);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.userPercents = userPercents;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        UserPercents userPercent = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_text, parent, false);
        }

        TextView listItem = convertView.findViewById(R.id.listItem);
        listItem.setText( (position+1) +". " + userPercent.getUsername());
        return convertView;

    }
}
