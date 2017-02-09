package com.example.android.codepathtodo;

import static android.R.attr.id;

/**
 * Created by JLV on 2/9/2017.
 */

public class TodoItem {
    //private variables
    private int _id;
    private String _item_text;
    private String _item_date;

    // empty constructor
    public TodoItem(){
    }

    // constructor
    public TodoItem(int id, String text, String date){
        this._id = id;
        this._item_text = text;
        this._item_date = date;
    }

    // constructor
    public TodoItem(String text, String date){
        this._item_text = text;
        this._item_date = date;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting text
    public String getItemText(){
        return this._item_text;
    }

    // setting name
    public void setItemText(String text){
        this._item_text = text;
    }

    // getting date
    public String getItemDate(){
        return this._item_date;
    }

    // setting date
    public void setItemDate(String date){
        this._item_date = date;
    }
}
