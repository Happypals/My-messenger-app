package edu.bucknell.xizhouli.whatsup;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by xizhouli on 10/27/16.
 */

public class DataProvider extends ContentProvider {

    public static final String COL_ID = "_id";

    public static final String TABLE_MESSAGES = "messages";
    public static final String COL_MSG = "msg";
    public static final String COL_FROM = "email";
    public static final String COL_TO = "email2";
    public static final String COL_AT = "at";

    public static final String TABLE_PROFILE = "profile";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_COUNT = "count";

    private DbHelper dbHelper;
    public static final Uri CONTENT_URI_MESSAGES = Uri.parse("content://com.appsrox.instachat.provider/messages");
    public static final Uri CONTENT_URI_PROFILE = Uri.parse("content://com.appsrox.instachat.provider/profile");

    private static final int MESSAGES_ALLROWS = 1;
    private static final int MESSAGES_SINGLE_ROW = 2;
    private static final int PROFILE_ALLROWS = 3;
    private static final int PROFILE_SINGLE_ROW = 4;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.appsrox.instachat.provider", "messages", MESSAGES_ALLROWS);
        uriMatcher.addURI("com.appsrox.instachat.provider", "messages/#", MESSAGES_SINGLE_ROW);
        uriMatcher.addURI("com.appsrox.instachat.provider", "profile", PROFILE_ALLROWS);
        uriMatcher.addURI("com.appsrox.instachat.provider", "profile/#", PROFILE_SINGLE_ROW);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private static class DbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "Whatsup.db";
        private static final int DATABASE_VERSION = 1;

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table messages (_id integer primary key autoincrement, msg text, email text, email2 text, at datetime default current_timestamp);");
            db.execSQL("create table profile (_id integer primary key autoincrement, name text, email text unique, count integer default 0);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}

}