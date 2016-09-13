package com.ivan_prokofyev.kilograpptest.Data.network;

import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;
import com.ivan_prokofyev.kilograpptest.Data.network.service.KilograppService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shiraha on 11.09.2016.
 */
public class ServerManager {
    private static ServerManager instance;
    private KilograppService kilograppService;
    private static String BASE_URL = "http://tomcat.kilograpp.com";


    private ServerManager() {
    }

    public static ServerManager getInstance() {
        if (instance == null)
            synchronized (ServerManager.class) {
                if (instance == null) {
                    instance = new ServerManager();
                    instance.kilograppService = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build()
                            .create(KilograppService.class);
                }
            }
        return instance;
    }

//public ArrayList<ResponseItem> showSongs() {
//
//    final ArrayList<ResponseItem> listOfSongs = new ArrayList<>();
//
//    kilograppService.getListOfSongs()
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<ResponseItem>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(ResponseItem value) {
//                    listOfSongs.add(value);
//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
//
//    return listOfSongs;
//}

    public interface ResponseBlock<T> {
        void blockResponse(List<ResponseItem> response, Throwable t);
    }

    public void downloadSongs(final ResponseBlock<List<ResponseItem>> block) {

        Callback<List<ResponseItem>> callback = new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, Response<List<ResponseItem>> response) {
                block.blockResponse(response.body()  ,null);
            }

            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {

            }
        };
        kilograppService.getListOfSongs().enqueue(callback);
    }

    public void rxDownloadSongs(Subscriber<List<ResponseItem>> subscriber){
        kilograppService.getObservableListOfSongs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
