package com.dvilson.mvvmmparlapratique;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dvilson.mvvmmparlapratique.models.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {

        NoteDatabase database = NoteDatabase.getInstance(application);
        mNoteDao = database.mNoteDao();
        allNotes = mNoteDao.getAllNotes();
    }

    public void  insert(Note note){
        new InsertAsynTask(mNoteDao).execute(note);


    }
    public void update(Note note){
        new UpdatetAsynTask(mNoteDao).execute(note);

    }
    public void delete(Note note){
        new DeletetAsynTask(mNoteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotestAsynTask(mNoteDao).execute();
    }

    public LiveData<List<Note>>getAllNotes(){
        return  allNotes;
    }

    private static class InsertAsynTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public InsertAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdatetAsynTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public UpdatetAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeletetAsynTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        public DeletetAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotestAsynTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        public DeleteAllNotestAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
