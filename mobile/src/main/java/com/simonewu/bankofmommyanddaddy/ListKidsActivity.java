package com.simonewu.bankofmommyanddaddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.simonewu.bankofmommyanddaddy.database.AppDatabase;
import com.simonewu.bankofmommyanddaddy.database.Kid;
import com.simonewu.bankofmommyanddaddy.database.SingletonLocalDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListKidsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kids);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppDatabase db = SingletonLocalDatabase.getInstance(getApplicationContext());
        final List<Kid> kids = db.kidDao().getAll();
        List<String> kidStrings = new ArrayList<>();
        for (Kid k : kids) {
            Log.i("ListKidsActivity", "Adding kid to string list: " + k.toString());
            kidStrings.add(k.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, kidStrings);

        ListView kidList = findViewById(R.id.kidListView);
        kidList.setAdapter(adapter);

        AdapterView.OnItemClickListener mMessageClickedHandler =
            new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent i = new Intent(v.getContext(), KidDetailsActivity.class);
                    Log.i("ListKidsActivity",
                            "Getting details for ID " + kids.get(position).getUid());
                    i.putExtra("id", kids.get(position).getUid());
                    v.getContext().startActivity(i);
                    finish();
                }
            };

        kidList.setOnItemClickListener(mMessageClickedHandler);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createKid(view);
            }
        });
    }

    public void createKid(View view) {
        Intent intent = new Intent(this, CreateKidsActivity.class);
        startActivity(intent);
    }

}
