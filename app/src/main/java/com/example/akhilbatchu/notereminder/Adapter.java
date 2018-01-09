package com.example.akhilbatchu.notereminder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class Adapter extends ArrayAdapter<Note> {
    TextView title,date,content;

    public Adapter(@NonNull Context context, int resource, @NonNull List<Note> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    if(convertView==null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, null);
    }
    Note note = getItem(position);
            if(note!=null) {
                title = (TextView) convertView.findViewById(R.id.title);
                date = (TextView) convertView.findViewById(R.id.date);
                content = (TextView) convertView.findViewById(R.id.content);
                title.setText(note.getTitle());
                date.setText(note.SimpleDate(getContext()));
                if(note.getContent().length()>50)
                {
                    content.setText(note.getContent().substring(0,50));
                }
                else
                {
                    content.setText(note.getContent());
                }
            }
    return convertView;


    }
}
