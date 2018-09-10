package com.example.database.entities;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MainDatabase.NAME, version = MainDatabase.VERSION)
public class MainDatabase {
    public static final String NAME = "main";
    public static final int VERSION = 1;
}