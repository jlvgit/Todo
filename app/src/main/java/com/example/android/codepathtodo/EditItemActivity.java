package com.example.android.codepathtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Calendar;
import android.widget.DatePicker;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    DatePicker datePicker;
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        String itemText = getTextToUpdate ();
        EditText myTextBox = (EditText) findViewById(R.id.editText);
        myTextBox.setText(itemText);

        String itemDate = getDateToUpdate();
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        try {
            setDatePickerDate(itemDate);
        } catch(Exception e){
            int year = getCurrentYear();
            int month = getCurrentMonth();
            int day = getCurrentDay();
            datePicker.updateDate(year, month, day);
        }
    }

    private String getTextToUpdate(){
        return getIntent().getStringExtra("itemText");
    }
    private String getDateToUpdate(){
        return getIntent().getStringExtra("itemDate");
    }
    private int getItemDbId(){ return getIntent().getIntExtra("itemID", 0); }
    private int getPositionOfText(){
        return getIntent().getIntExtra("itemPos", 0);
    }

    private int getCurrentDay(){ return c.get(Calendar.DAY_OF_MONTH);}
    private int getCurrentMonth(){ return c.get(Calendar.MONTH);}
    private int getCurrentYear(){ return c.get(Calendar.YEAR);}

    private void setDatePickerDate(String date) {
        String[] splitDate = date.split("/");
        int month = Integer.valueOf(splitDate[0]);
        int day = Integer.valueOf(splitDate[1]);
        int year = Integer.valueOf(splitDate[2]);
        datePicker.updateDate(year, month-1, day);
    }

    public void submitEdit(View v) {
        int itemPos = getPositionOfText();

        EditText etItem = (EditText) findViewById(R.id.editText);
        Intent data = new Intent();

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year =  datePicker.getYear();

        String date = month + "/" + day + "/" + year;

        // Pass relevant data back as a result
        data.putExtra("editedText", etItem.getText().toString());
        data.putExtra("editedDate", date);
        data.putExtra("itemPos", itemPos);
        data.putExtra("itemID", getItemDbId());

        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
