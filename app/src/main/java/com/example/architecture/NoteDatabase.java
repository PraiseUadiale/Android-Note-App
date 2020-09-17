package com.example.architecture;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    // Here we are creating the note database that would hold the note entities using the annotation above


    private static NoteDatabase instance;
    // We create this variable to turn the class into a singleton.
    // Means we cannot create multiple instances of this database

    public abstract NoteDao noteDao();// Use this method to access the DAO operations

    public static synchronized NoteDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database").fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;// we use this to gain access to this database
    }

    private static  RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulatedbAsyncTask(instance).execute();
        }
    };

    private static class PopulatedbAsyncTask extends AsyncTask<Void, Void, Void>{
    private NoteDao noteDao;

    private PopulatedbAsyncTask(NoteDatabase db){
        noteDao= db.noteDao();
    }
        @Override
        protected Void doInBackground(Void... voids) {
        noteDao.insert(new Note("Title 1", "description1", 1));
        noteDao.insert(new Note("Title 2", "description2", 2));
         noteDao.insert(new Note("Title 3", "description3", 3));

            return null;
        }
    }


    //Here we can completed in creating the room database. This is the source of our data
}
