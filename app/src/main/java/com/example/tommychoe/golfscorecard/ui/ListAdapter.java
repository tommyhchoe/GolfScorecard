package com.example.tommychoe.golfscorecard.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tommychoe.golfscorecard.R;
import com.example.tommychoe.golfscorecard.models.Hole;

public class ListAdapter extends BaseAdapter {

    private final Context mContext;
    private final Hole[] mHoles;

    public ListAdapter(Context context, Hole[] holes){
        mContext = context;
        mHoles = holes;
    }

    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int position) {
        return mHoles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.single_hole, null);
            holder = new ViewHolder();
            holder.holeLabel = (TextView) convertView.findViewById(R.id.holeLabel);
            holder.strokeCount = (TextView) convertView.findViewById(R.id.strokeCountLabel);
            holder.removeStrokeButton = (Button) convertView.findViewById(R.id.subtractOneButton);
            holder.addStrokeButton = (Button) convertView.findViewById(R.id.addOneButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.holeLabel.setText(mHoles[position].getHoleLabel());
        holder.strokeCount.setText(String.format("%d", mHoles[position].getCount()));
        holder.removeStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mHoles[position].subtractOneFromCount();
                if (updatedStrokeCount < 0) updatedStrokeCount = 0;
                mHoles[position].setCount(updatedStrokeCount);
                holder.strokeCount.setText(String.format("%d", updatedStrokeCount));
            }
        });

        holder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mHoles[position].addOneToCount();
                mHoles[position].setCount(updatedStrokeCount);
                holder.strokeCount.setText(String.format("%d", updatedStrokeCount));
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        TextView holeLabel;
        TextView strokeCount;
        Button removeStrokeButton;
        Button addStrokeButton;
    }
}
