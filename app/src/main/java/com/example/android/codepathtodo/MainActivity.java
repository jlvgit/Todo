package com.example.android.codepathtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 20;
    ArrayList<TodoItem> items;
    TodoItemAdapter iAdapter;
    DatabaseHandler db = new DatabaseHandler(this);
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items = (ArrayList<TodoItem>) db.getAllItems();
        iAdapter = new TodoItemAdapter(this, items);

        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(iAdapter);


        setupListViewListener();
        setupEditViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        TodoItem itemAtPos = items.get(pos);
                        items.remove(pos);
                        db.deleteItem(itemAtPos);
                        iAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
    }

    private void setupEditViewListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editItemIntent = new Intent(MainActivity.this, EditItemActivity.class);

                TodoItem item = items.get(position);
                editItemIntent.putExtra("itemID", item.getID());
                editItemIntent.putExtra("itemText", item.getItemText());
                editItemIntent.putExtra("itemDate", item.getItemDate());
                editItemIntent.putExtra("itemPos", position);
                startActivityForResult(editItemIntent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String etText = data.getExtras().getString("editedText");
            String etDate = data.getExtras().getString("editedDate");
            int itemID = data.getIntExtra("itemID",0);
            int pos = data.getExtras().getInt("itemPos", 0);
            TodoItem etItem = new TodoItem(etText, etDate);

            if (pos > items.size()) {
                iAdapter.add(etItem);
                db.addItem(etItem);
            } else {
                TodoItem olditem = db.getItem(itemID);
                items.set(pos, etItem);
                db.updateItem(olditem, etText, etDate);
            }

            iAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_button) {
            Intent editItemIntent = new Intent(MainActivity.this, EditItemActivity.class);
            editItemIntent.putExtra("itemPos", items.size() + 1);
            startActivityForResult(editItemIntent, REQUEST_CODE);
            Toast.makeText(this, "Go to edit page", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
