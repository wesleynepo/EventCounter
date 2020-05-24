package com.example.eventcounter;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static EventDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(), EventDatabase.class, "events")
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.recycler);
        layoutManager = new GridLayoutManager(this, 3);
        adapter = new EventsAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FloatingActionButton button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.eventDAO().create(LocalDate.now().toString());
                adapter.reload();
            }
        });

        adapter.reload();
    }

}
