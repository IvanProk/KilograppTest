package com.ivan_prokofyev.kilograpptest.Data;

import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;
import com.ivan_prokofyev.kilograpptest.Data.network.ServerManager;
import com.ivan_prokofyev.kilograpptest.adapter.CardAdapter;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Shiraha on 12.09.2016.
 */
public class DataManager {
    private static DataManager instance;
    CardAdapter mCardAdapter;


    //TODO: add DB support, and smart update(with find only changed items)
    private DataManager() {
    }

    public static DataManager getInstance() {
        instance = new DataManager();
        return instance;
    }

    public void getSongs(CardAdapter adapter){
        mCardAdapter = adapter;
        downloadSongs(adapter);
    }

    private void downloadSongs(final CardAdapter adapter) {
//        ServerManager.getInstance().downloadSongs(new ServerManager.ResponseBlock<List<ResponseItem>>() {
//            @Override
//            public void blockResponse(List<ResponseItem> response, Throwable t) {
//                adapter.addData(response);
//            }
//        });

        ServerManager.getInstance().rxDownloadSongs(new Subscriber<List<ResponseItem>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ResponseItem> responseItems) {
                mCardAdapter.addData(responseItems);
            }
        });
    }
}
