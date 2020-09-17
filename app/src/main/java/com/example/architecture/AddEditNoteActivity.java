package com.example.architecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;
    public static final String EXTRA_TITLE= "com.example.architecture.EXTRA_TITLE";
    public static final String EXTRA_ID= "com.example.architecture.EXTRA_ID";
    public static final String EXTRA_DESCRIPTION= "com.example.architecture.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY= "com.example.architecture.EXTRA_PRIORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker);

        numberPickerPriority.setMaxValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent= getIntent();
        editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
        editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,0));
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
        }else{
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handles any clicks on the menu item

        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveNote() {
        String getTitle = editTextTitle.getText().toString();
        String getDescription = editTextDescription.getText().toString();
        int getNumPicker = numberPickerPriority.getValue();

        if (getTitle.trim().isEmpty() || getDescription.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, getTitle);
        data.putExtra(EXTRA_DESCRIPTION, getDescription);
        data.putExtra(EXTRA_PRIORITY, getNumPicker);
        int id= getIntent().getIntExtra(EXTRA_ID,-1);
        if (id!=-1){
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();


    }
}