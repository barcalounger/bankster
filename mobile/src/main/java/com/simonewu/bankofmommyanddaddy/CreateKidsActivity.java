package com.simonewu.bankofmommyanddaddy;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.simonewu.bankofmommyanddaddy.database.AppDatabase;
import com.simonewu.bankofmommyanddaddy.database.Kid;
import com.simonewu.bankofmommyanddaddy.database.SingletonLocalDatabase;

import java.math.BigDecimal;

public class CreateKidsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_kids);

        Button button = (Button) findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createKidInLocalStorage(view);
            }
        });
    }

    private void createKidInLocalStorage(View view) {
        Kid newKid = new Kid();
        EditText kidName = findViewById(R.id.kidName);
        newKid.setFirstName(kidName.getText().toString());
        EditText balanceStr = findViewById(R.id.kidOpeningBalance);
        BigDecimal balance;
        try {
            balance = new BigDecimal(balanceStr.getText().toString());
        } catch (NumberFormatException e) {
            balance = new BigDecimal(0);
        }
        int dollars = balance.intValue();
        newKid.setDollars(dollars);

        int cents = balance.subtract(new BigDecimal(dollars)).scaleByPowerOfTen(2).intValue();
        newKid.setCents(cents);

        newKid.setUid(java.util.UUID.randomUUID().toString());
        AppDatabase db = SingletonLocalDatabase.getInstance(getApplicationContext());
        db.kidDao().insertAll(newKid);
    }
}
