package com.example.eventcounter;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {
    private EditText editText;
    private DatePicker datePicker;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        editText = findViewById(R.id.eventName);
        datePicker = findViewById(R.id.eventDate);
        id = getIntent().getIntExtra("id", 0);
        String eventName = getIntent().getStringExtra("eventName");
        String dateEvent = getIntent().getStringExtra( "eventDate");

        int month = Integer.parseInt(dateEvent.substring(5,7)) -1;
        int day   = Integer.parseInt(dateEvent.substring(8,10));
        int year  = Integer.parseInt(dateEvent.substring(0,4));

        editText.setText(eventName);
        datePicker.updateDate( year,month, day );
        datePicker.getMonth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String dateEvent = "";
        dateEvent += datePicker.getYear() + "-";
        dateEvent += String.format("%02d",datePicker.getMonth()+1 );
        dateEvent +="-" +String.format("%02d",datePicker.getDayOfMonth());

        MainActivity.database.eventDAO().save( editText.getText().toString(), dateEvent, id );

    }
}
