package com.example.test_fpop;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class funkoprovider extends ContentProvider {

    public final static String DBNAME = "FunkoDatabase";
    public funkoprovider() {
    }
    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };


    public final static String TABLE_FUNKOTABLE = "funko";
    public final static String POP_NAME = "name";
    public final static String POP_NUMBER = "number";
    public final static String POP_TYPE = "type";
    public final static String FANDOM = "fandom";
    public final static String COLUMN_ON = "onoff";
    public final static String ULTIMATE = "ultimate";
    public final static String PRICE = "price";

    public static final String AUTHORITY = "com.funkprovider";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_FUNKOTABLE);

    private static UriMatcher sUriMatcher;

    private MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_FUNKOTABLE +  // Table's name
            "(" +               // The columns in the table
            " _ID INTEGER PRIMARY KEY, " +
            POP_NAME + " TEXT," +
            POP_NUMBER + " INTEGER," +
            POP_TYPE + " TEXT," +
            FANDOM + " TEXT," +
            COLUMN_ON + " TEXT," +
            ULTIMATE + " TEXT," +
            PRICE + " INTEGER)";


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_FUNKOTABLE, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String name = values.getAsString(POP_NAME).trim();
        int popnum = values.getAsInteger(POP_NUMBER);
        String type = values.getAsString(POP_TYPE).trim();
        String fandom = values.getAsString(FANDOM).trim();
        Boolean on = values.getAsBoolean(COLUMN_ON);
        String ultimate = values.getAsString(ULTIMATE).trim();
        Double price = values.getAsDouble(PRICE);

        if (name.equals(""))
            return null;
        if (popnum == 0)
            return null;
        if (type.equals(""))
            return null;
        if (fandom.equals(""))
            return null;
        if (on.equals(""))
            return null;
        if (ultimate.equals(""))
            return null;
        if (price.equals(""))
            return null;

        long id = mOpenHelper.getWritableDatabase().insert(TABLE_FUNKOTABLE, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_FUNKOTABLE, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_FUNKOTABLE, values, selection, selectionArgs);
    }
}