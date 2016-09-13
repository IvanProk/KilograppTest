package com.ivan_prokofyev.kilograpptest.Data.db.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ivan_prokofyev.kilograpptest.Data.model.ResponseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shiraha on 13.09.2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "songsManager";
    private static final String TABLE_SONGS = "songs";
    private static final String KEY_ID = "id";
    private static final String KEY_ARTIST = "artist";
    private static final String KEY_SONG = "song";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SONGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ARTIST + " TEXT,"
                + KEY_SONG + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);

        onCreate(sqLiteDatabase);
    }


    @Override
    public void addSong(ResponseItem song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ARTIST, song.getAuthor());
        values.put(KEY_SONG, song.getLabel());

        db.insert(TABLE_SONGS, null, values);
        db.close();
    }

    @Override
    public ResponseItem getSong(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SONGS, new String[]{KEY_ID,
                        KEY_ARTIST, KEY_SONG}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        ResponseItem song = new ResponseItem();
        song.setParameters(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        return song;
    }

    @Override
    public List<ResponseItem> getAllSongs() {
        List<ResponseItem> songsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_SONGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ResponseItem song = new ResponseItem();
                song.setId(cursor.getInt(0));
                song.setAuthor(cursor.getString(1));
                song.setLabel(cursor.getString(2));
                songsList.add(song);
            } while (cursor.moveToNext());
        }

        return songsList;
    }


    @Override
    public int getSongsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SONGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateSong(ResponseItem song) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ARTIST, song.getAuthor());
        values.put(KEY_SONG, song.getLabel());

        return db.update(TABLE_SONGS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(song.getId()) });
    }

    @Override
    public void deleteSong(ResponseItem song) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONGS, KEY_ID + " = ?", new String[] { String.valueOf(song.getId()) });
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONGS, null, null);
        db.close();
    }
}
