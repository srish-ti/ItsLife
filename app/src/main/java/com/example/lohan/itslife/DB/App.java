package com.example.lohan.itslife.DB;

import android.app.Application;

/**
 * Created by lohan on 28-01-2017.
 */

public class App extends Application {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = true;

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
    public static void setDaoSession(DaoSession session){
        daoSession=session;
    }
}

