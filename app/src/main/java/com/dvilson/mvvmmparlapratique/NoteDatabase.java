package com.dvilson.mvvmmparlapratique;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dvilson.mvvmmparlapratique.models.Note;

@Database(entities = Note.class,version=1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    public abstract NoteDao mNoteDao();
    public static synchronized NoteDatabase getInstance(Context context){

        if(instance == null ){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyntask(instance).execute();

        }
    };

    public static class PopulateDbAsyntask extends AsyncTask<Void,Void,Void>{

        private NoteDao mNoteDao;

        public PopulateDbAsyntask(NoteDatabase db ) {
            mNoteDao = db.mNoteDao();

        }


        @Override
        protected Void doInBackground(Void... voids) {

            mNoteDao.insert(new Note("Titre1","premiere note",3));
            mNoteDao.insert(new Note("Titre2","deuxieme note",2));
            mNoteDao.insert(new Note("Titre3","troisieme note",1));

            return null;
        }
    }


}
