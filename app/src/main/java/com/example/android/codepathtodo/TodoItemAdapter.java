package com.example.android.codepathtodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    TodoItemAdapter(Context context, List<TodoItem> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        TodoItem item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Lookup view for data population
        TextView itemText = (TextView) convertView.findViewById(R.id.item_text);
        TextView itemDate = (TextView) convertView.findViewById(R.id.date_text);

        // Populate the data into the template view using the data object
        itemText.setText(item.getItemText());
        itemDate.setText(item.getItemDate());

        // Return the completed view to render on screen
        return convertView;
    }
}

