package com.example.akhilbatchu.notereminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
       EditText et1,et2;
       private boolean updating;
       private Note mLoadednote;
       private long Time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        et1 = (EditText)findViewById(R.id.editText);

        et2 = (EditText)findViewById(R.id.editText2);
        Intent intent = getIntent();
        String mfilename = intent.getStringExtra(Utilites.Extra);
        if(mfilename!=null && !mfilename.isEmpty() && mfilename.endsWith(Utilites.Extension))
        {
            mLoadednote = Utilites.getAllSavedNotesByFileName(getApplicationContext(),mfilename);
            if(mLoadednote!=null)
            {
                et1.setText(mLoadednote.getTitle());
                et2.setText(mLoadednote.getContent());
                updating = true;
                Time = mLoadednote.getDate();

            }
            else
            {
                updating = false;
                Time = System.currentTimeMillis();
            }
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savemenu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.savefile:
                savenote();
            break;
            case R.id.deletefile:
                deletenote();
            break;

        }
        return  true;
    }

    public void savenote()
    {
        Note note;
        if(mLoadednote==null) {
             note = new Note(System.currentTimeMillis(), et1.getText().toString(), et2.getText().toString());
        }
        else
        {
             note = new Note(mLoadednote.getDate(),et1.getText().toString(),et2.getText().toString());
        }
        if(Utilites.savenote(this,note)) {
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            Toast.makeText(this,"can not save",Toast.LENGTH_LONG).show();
        }
    }
    public void deletenote()
    {
        if(mLoadednote==null)
        {
            Toast.makeText(NoteActivity.this,"not working",Toast.LENGTH_LONG).show();
            finish();}
            else
        {

           Utilites.Delete(getApplicationContext(),mLoadednote.getDate()+Utilites.Extension);
           finish();
        }
    }
}
