package com.example.eventcounter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        GridLayout containerView;
        TextView textViewDays;
        TextView textViewEvent;
        TextView textViewSituation;

        EventViewHolder(View view) {
            super(view);
            containerView = view.findViewById(R.id.widgetEvent);
            textViewDays = view.findViewById(R.id.days_text);
            textViewEvent = view.findViewById(R.id.event_text);
            textViewSituation = view.findViewById(R.id.situation_text);

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Event actual = (Event) containerView.getTag();
                    Intent intent = new Intent(v.getContext(), EventActivity.class);
                    intent.putExtra("id", actual.id);
                    intent.putExtra("eventName", actual.event);
                    intent.putExtra("eventDate", actual.eventDate);
                    v.getContext().startActivity(intent);
                }
            });




        }
    }

    private List<Event> events = new ArrayList<>();


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_widget, parent, false);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event current = events.get(position);
        holder.textViewEvent.setText(current.event);
        setTimeText(current.eventDate,holder);
        holder.containerView.setTag(current);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void reload() {
        events = MainActivity.database.eventDAO().getAllEvents();
        notifyDataSetChanged();
    }

    public void setTimeText(String date, EventViewHolder holder){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        LocalDate dateEvent = LocalDate.parse(date,formatter);
        LocalDate dateNow   = LocalDate.parse(LocalDate.now().toString(),formatter);

        long daysBetween = Duration.between(dateEvent.atStartOfDay(),dateNow.atStartOfDay()).toDays();

        if (dateEvent.isBefore(dateNow)) {
            holder.textViewSituation.setText("days since");
        } else {
            holder.textViewSituation.setText("days until");
        }

        holder.textViewDays.setText(Math.abs(daysBetween) + "");
    }

    public void delete(int position ) {
        MainActivity.database.eventDAO().delete(events.get(position).id);
        this.reload();
        notifyDataSetChanged();
    }
}
