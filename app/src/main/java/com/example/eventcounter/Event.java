package com.example.eventcounter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "event")
    public String event;

    @ColumnInfo(name = "eventDate")
    public String eventDate;


}
