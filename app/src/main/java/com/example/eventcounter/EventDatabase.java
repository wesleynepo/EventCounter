package com.example.eventcounter;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class}, version = 1)
public abstract class EventDatabase extends RoomDatabase {

    public abstract EventDAO eventDAO();

}
