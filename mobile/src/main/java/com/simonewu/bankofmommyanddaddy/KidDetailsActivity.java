package com.simonewu.bankofmommyanddaddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simonewu.bankofmommyanddaddy.database.AppDatabase;
import com.simonewu.bankofmommyanddaddy.database.Kid;
import com.simonewu.bankofmommyanddaddy.database.SingletonLocalDatabase;

import java.util.List;

public class KidDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_details);
        final String id = getIntent().getExtras().getString("id");
        AppDatabase db = SingletonLocalDatabase.getInstance(getApplicationContext());
        Kid currentKid = db.kidDao().loadById(id);
        Log.i("KidDetailsActivity", "looking for kid by ID " + id);
        if (currentKid != null) {
            Log.i("KidDetailsActivity", "Found! " + currentKid.toString());
        } else {
            Log.i("KidDetailsActivity", "...null :(");
        }

        ((TextView) findViewById(R.id.kidName)).setText(currentKid.getFirstName());
        ((TextView) findViewById(R.id.kidBalance)).setText(currentKid.getBalanceString());

        Button button = findViewById(R.id.edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), EditKidActivity.class);
                i.putExtra("id", id);
                view.getContext().startActivity(i);
                finish();
            }
        });

        Button button2 = findViewById(R.id.backToList);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListKidsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
