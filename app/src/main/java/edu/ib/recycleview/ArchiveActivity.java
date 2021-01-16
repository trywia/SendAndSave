package edu.ib.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class ArchiveActivity extends AppCompatActivity {
    public static ArrayList<Contact> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvMessages);
        Intent intent = getIntent();
        list = (ArrayList<Contact>) intent.getSerializableExtra("list");
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(list);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBtnPreviousClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}