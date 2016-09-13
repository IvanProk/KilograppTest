package com.ivan_prokofyev.kilograpptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;
import com.ivan_prokofyev.kilograpptest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shiraha on 12.09.2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    List<ResponseItem> mItems;
    private int lastPosition = -1;
    private Context context;

        //TODO: Add animations
    public CardAdapter(Context context) {
        super();
        mItems = new ArrayList<>();
        this.context = context;
    }

    public void addData(List<ResponseItem> serverResponse) {
        mItems.clear();
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

        setAnimation(viewHolder.container , i);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public void onError(){
        Toast.makeText(context, "Network trouble", Toast.LENGTH_SHORT).show();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView artist;
        public TextView song;
        public FrameLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (FrameLayout) itemView.findViewById(R.id.list_item_container);
            artist = (TextView) itemView.findViewById(R.id.tvArtist);
            song = (TextView) itemView.findViewById(R.id.tvSong);
        }
    }
}
