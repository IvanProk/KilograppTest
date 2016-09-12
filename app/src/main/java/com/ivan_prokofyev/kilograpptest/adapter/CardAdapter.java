package com.ivan_prokofyev.kilograpptest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;
import com.ivan_prokofyev.kilograpptest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shiraha on 12.09.2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    List<ResponseItem> mItems;
        //TODO: Add animations
    public CardAdapter() {
        super();
        mItems = new ArrayList<>();
    }

    public void addData(List<ResponseItem> serverResponse) {
        mItems.addAll(serverResponse);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ResponseItem serverResponse = mItems.get(i);
        viewHolder.artist.setText(serverResponse.getAuthor());
        viewHolder.song.setText(serverResponse.getLabel());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView artist;
        public TextView song;

        public ViewHolder(View itemView) {
            super(itemView);
            artist = (TextView) itemView.findViewById(R.id.tvArtist);
            song = (TextView) itemView.findViewById(R.id.tvSong);
        }
    }
}
