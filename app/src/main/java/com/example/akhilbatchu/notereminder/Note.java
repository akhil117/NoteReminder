package com.example.akhilbatchu.notereminder;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by akhilbatchu on 19/12/17.
 */

public class Note implements Serializable {
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    private String Title;
    private String content;
    private long date;


    public Note(long date,String Title,String content)
    {
        this.Title = Title;
        this.content = content;
        this.date = date;
    }

    public String SimpleDate(Context context)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy",context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(getDate()));
    }



}
