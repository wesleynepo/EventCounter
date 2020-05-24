package com.example.eventcounter;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDAO {

    @Query("INSERT INTO events (event,eventDate) VALUES ('New event', :date )")
    void create(String date);

    @Query("SELECT * FROM events")
    List<Event> getAllEvents();

    @Query("UPDATE events SET eventDate = :eventDate, event = :event WHERE id = :id")
    void save(String event, String eventDate, int id);

}
