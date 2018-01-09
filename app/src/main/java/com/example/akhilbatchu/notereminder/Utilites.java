package com.example.akhilbatchu.notereminder;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by akhilbatchu on 21/12/17.
 */

public class Utilites {
   public static final String Extension = ".bin";
   public static final String Extra = "extra";
    public static boolean savenote(Context context,Note note)
    {
        FileOutputStream fos;
        ObjectOutputStream out;
        String file =String.valueOf(note.getDate()) + Extension;
        try
        {

            out = new ObjectOutputStream(context.openFileOutput(file,context.MODE_PRIVATE));
            out.writeObject(note);
            out.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    static ArrayList<Note> getAllSavedNotes(Context context) {
        ArrayList<Note> notes = new ArrayList<>();

        File filesDir = context.getFilesDir();
        ArrayList<String> noteFiles = new ArrayList<>();

        //add .bin files to the noteFiles list
        for(String file : filesDir.list()) {
            if(file.endsWith(".bin")) {
                noteFiles.add(file);
            }
        }

        //read objects and add to list of notes
        FileInputStream fis;
        ObjectInputStream ois;

        for (int i = 0; i < noteFiles.size(); i++) {
            try {
                fis = context.openFileInput(noteFiles.get(i));
                ois = new ObjectInputStream(fis);

                notes.add((Note) ois.readObject());
                fis.close();
                ois.close();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return notes;
    }

    public static Note getAllSavedNotesByFileName(Context context, String file)
    {
        Note note;

        File filea = new File(context.getFilesDir(),file);
        if(filea.exists() && !filea.isDirectory()) {
            Log.v("UTILITIES", "File exist = " + file);
            ObjectInputStream oos;
            FileInputStream in;
            try {
                in = context.openFileInput(file);
                oos = new ObjectInputStream(in);
                note = (Note) oos.readObject();
                in.close();
                oos.close();
                return note;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }

    }

    public static void  Delete(Context context , String filename)
    {
        File file = context.getFilesDir();
        File fils= new File(file,filename);
        if(fils.exists())
        {
            fils.delete();

        }
    }

}

