package com.example.akhilbatchu.notereminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mlist;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlist = (ListView) findViewById(R.id.list);


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_file:
                Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
                break;


        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mlist.setAdapter(null);
        ArrayList<Note> a = Utilites.getAllSavedNotes(getApplicationContext());
        if (a!=null && a.size()>0) {

            Adapter customAdapter = new Adapter(this, R.layout.list_view, a);
            mlist.setAdapter(customAdapter);
            mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String filename = ((Note)mlist.getItemAtPosition(i)).getDate() + Utilites.Extension;
                    Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                    intent.putExtra(Utilites.Extra,filename);
                    startActivity(intent);
                }
            });


        }
        else
        {
            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
        }
    }
}
