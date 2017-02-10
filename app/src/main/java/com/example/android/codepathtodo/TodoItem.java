package com.example.android.codepathtodo;

import static android.R.attr.id;

/**
 * Created by JLV on 2/9/2017.
 */

class TodoItem {
    //private variables
    private int _id;
    private String _item_text;
    private String _item_date;

    // empty constructor
    TodoItem() {
    }

    // constructor
    TodoItem(int id, String text, String date) {
        this._id = id;
        this._item_text = text;
        this._item_date = date;
    }

    // constructor
    TodoItem(String text, String date) {
        this._item_text = text;
        this._item_date = date;
    }

    // getting ID
    int getID() {
        return this._id;
    }

    // setting id
    void setID(int id) {
        this._id = id;
    }

    // getting text
    String getItemText() {
        return this._item_text;
    }

    // setting name
    void setItemText(String text) {
        this._item_text = text;
    }

    // getting date
    String getItemDate() {
        return this._item_date;
    }

    // setting date
    void setItemDate(String date) {
        this._item_date = date;
    }
}
