package com.example.sqliteapp;

import android.provider.BaseColumns;

public class UserContract {

    private UserContract() {
    }

    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_NUMBER = "number";
    }
}
