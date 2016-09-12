package com.ivan_prokofyev.kilograpptest.Data.db.sqlite;

import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;

import java.util.List;

/**
 * Created by Shiraha on 13.09.2016.
 */
public interface IDatabaseHandler {
    void addSong(ResponseItem song);
    ResponseItem getSong(int id);
    List<ResponseItem> getAllSongs();
    void addAllSongs(List<ResponseItem> songs);
    int getSongsCount();
    int updateSong(ResponseItem song);
    void deleteSong(ResponseItem song);
    void deleteAll();
}
