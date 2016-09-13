package com.ivan_prokofyev.kilograpptest.Data.db;

import android.content.Context;

import com.ivan_prokofyev.kilograpptest.Data.db.sqlite.DatabaseHandler;
import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;

import java.util.List;

/**
 * Created by Shiraha on 13.09.2016.
 */
public class DBManager {
    private static final String TAG = "MY_TAG";
    private static DBManager instance = new DBManager();
    private DatabaseHandler mDatabaseHandler;

    private DBManager() {
    }
    public static DBManager getInstance(Context context) {
        instance.mDatabaseHandler = new DatabaseHandler(context);
        return instance;
    }



    public void saveSongs(List<ResponseItem> songs){
        for (ResponseItem song: songs) {
            mDatabaseHandler.addSong(song);
        }
    }
    public List<ResponseItem> getSongs(){
        printAllSongs();
        return mDatabaseHandler.getAllSongs();
    }

    public void updateSongs(List<ResponseItem> newSongs){
        mDatabaseHandler.deleteAll();
        saveSongs(newSongs);
//        List<ResponseItem> oldSongs = showSongs();
//        for (int i = 0; i < newSongs.size();i++) {
//            for (int j = 0; j < oldSongs.size(); j++)
//            if(!newSongs.get(i).equals(oldSongs.get(j)))
//                Log.d(TAG, "updateSongs: delete old song: " + oldSongs.get(i).getLabel());
//            else
//                Log.d(TAG, "updateSongs: add new song: " + newSongs.get(j));
//
//        }
//        return showSongs();
    }

    public int getTableSize(){
        return mDatabaseHandler.getSongsCount();
    }

    public void deleteAll(){
        mDatabaseHandler.deleteAll();
    }

    public void printAllSongs(){
        List<ResponseItem> songs= mDatabaseHandler.getAllSongs();
        for (ResponseItem song : songs) {
            String log = "Id: "+song.getId()+" ,Name: " + song.getAuthor() + " ,Phone: " + song.getLabel();
            System.out.print("Name: ");
            System.out.println("MY_TAG " + log);
        }
    }


}

