package com.ivan_prokofyev.kilograpptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ivan_prokofyev.kilograpptest.Data.DataManager;
import com.ivan_prokofyev.kilograpptest.R;
import com.ivan_prokofyev.kilograpptest.adapter.CardAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        final CardAdapter mCardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mCardAdapter);
        DataManager dm = DataManager.getInstance();
        dm.getSongs(mCardAdapter);

        //TODO: Add Refresh support
    }
}
