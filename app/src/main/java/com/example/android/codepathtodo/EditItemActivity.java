package com.example.android.codepathtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String itemText = getTextToUpdate ();
        EditText myTextBox = (EditText) findViewById(R.id.editText);
        myTextBox.setText(itemText);
    }

    private String getTextToUpdate(){
        return getIntent().getStringExtra("itemText");
    }

    private int getPositionOfText(){
        return getIntent().getIntExtra("itemPos", 0);
    }

    public void submitEdit(View v) {
        int itemPos = getPositionOfText();

        EditText etItem = (EditText) findViewById(R.id.editText);
        Intent data = new Intent();

        // Pass relevant data back as a result
        data.putExtra("editedText", etItem.getText().toString());
        data.putExtra("itemPos", itemPos);

        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
