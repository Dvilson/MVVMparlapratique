package com.dvilson.mvvmmparlapratique;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    private EditText mEditTextTitle, mEditTextDescription;
    private NumberPicker mNumberPickerPriority;
    public static final String EXTRA_ID = "com.dvilson.mvvmmparlapratique.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.dvilson.mvvmmparlapratique.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION  ="com.dvilson.mvvmmparlapratique.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY= "com.dvilson.mvvmmparlapratique.EXTRA_PRIORITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mEditTextTitle = findViewById(R.id.edit_text_title);
        mEditTextDescription = findViewById(R.id.edit_text_description);
        mNumberPickerPriority = findViewById(R.id.number_picker_priority);
        mNumberPickerPriority.setMinValue(1);
        mNumberPickerPriority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_cancel);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            mEditTextTitle.setText(intent.getStringExtra(AddEditNoteActivity.EXTRA_TITLE));
            mEditTextDescription.setText(intent.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION));
            mNumberPickerPriority.setValue(intent.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY,1));
        }else{
            setTitle("Add Note");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_activity,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_save:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = mEditTextTitle.getText().toString();
        String description = mEditTextDescription.getText().toString();
        int priority = mNumberPickerPriority.getValue();
        if(title.trim().isEmpty() && description.trim().isEmpty()){
            Toast.makeText(this,"The field should not be empty",Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);
        int id  = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();


    }
}