package com.ivan_prokofyev.kilograpptest.Data;

import android.content.Context;
import android.util.Log;

import com.ivan_prokofyev.kilograpptest.Data.db.DBManager;
import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;
import com.ivan_prokofyev.kilograpptest.Data.network.ServerManager;
import com.ivan_prokofyev.kilograpptest.adapter.CardAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Shiraha on 12.09.2016.
 */
public class DataManager {
    private static final String TAG = "MY_TAG";
    private static DataManager instance = new DataManager();
    private ServerManager mServerManager;
    private DBManager mDBManager;
    private Context mContext;

    CardAdapter mCardAdapter;

    //TODO: add DB support, and smart update(with find only changed items)
    private DataManager() {
    }

    public static DataManager getInstance(Context context) {
        instance.mContext = context;
        instance.mServerManager = ServerManager.getInstance();
        instance.mDBManager = DBManager.getInstance(instance.mContext);
        return instance;
    }

    public void showSongs(CardAdapter adapter) {
        this.mCardAdapter = adapter;
        Log.d(TAG, "showSongs: ");
        if (mDBManager.getTableSize() != 0) {
            downloadSongs(false);
        } else {
            downloadSongs(true);
        }
        adapter.addData(mDBManager.getSongs());
//        return Observable.create(new Observable.OnSubscribe<List<ResponseItem>>() {
//            @Override
//            public void call(Subscriber<? super List<ResponseItem>> subscriber) {
//                Log.d(TAG, "call: " + mDBManager.getTableSize());
//
//
//            }
//        });
    }


    private void downloadSongs(final boolean toUpdate) {
        final List<ResponseItem> songs = new ArrayList<>();
        mServerManager.rxDownloadSongs(new Subscriber<List<ResponseItem>>() {
            @Override
            public void onCompleted() {
                Log.i("MY_TAG", "onCompleted: Download complited succesfully");
            }
            @Override
            public void onError(Throwable e) {
                Log.e("MY_TAG", "onError: Some error occurred while downloading", e);
                if(toUpdate)
                    mCardAdapter.onError();
            }


            @Override
            public void onNext(List<ResponseItem> responseItems) {
                songs.addAll(responseItems);
                if (!toUpdate) {
                    mDBManager.updateSongs(songs);
                }
                else {
                    mDBManager.saveSongs(songs);
                }
            }
        });
    }

    public void clearList() {
        mDBManager.deleteAll();
        mCardAdapter.clear();
    }
}
