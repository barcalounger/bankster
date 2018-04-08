package com.simonewu.bankofmommyanddaddy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simonewu.bankofmommyanddaddy.database.AppDatabase;
import com.simonewu.bankofmommyanddaddy.database.Kid;
import com.simonewu.bankofmommyanddaddy.database.SingletonLocalDatabase;
import com.simonewu.bankofmommyanddaddy.database.TransactionRecord;

import java.math.BigDecimal;
import java.util.Date;

public class EditKidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String id = getIntent().getExtras().getString("id");
        final AppDatabase db = SingletonLocalDatabase.getInstance(getApplicationContext());
        final Kid currentKid = db.kidDao().loadById(id);

        ((EditText) findViewById(R.id.kidName)).setText(currentKid.getFirstName());
        ((EditText) findViewById(R.id.kidBalance))
                .setText(currentKid.getBalanceStringWithoutCurrencySign());

        Button doneButton = findViewById(R.id.done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editKidInLocalStorage(currentKid);
            }
        });

        Button deleteKid = findViewById(R.id.deleteKid);

        deleteKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                // Add the buttons
                builder.setMessage(
                        "Permanently delete " + currentKid.getFirstName()+ "'s balance?");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        db.kidDao().delete(currentKid);
                        Intent intent = new Intent(view.getContext(), ListKidsActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Intent intent = new Intent(view.getContext(), KidDetailsActivity.class);
                        intent.putExtra("id", currentKid.getUid());
                        view.getContext().startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    private void editKidInLocalStorage(Kid kid) {
        Log.i("EditKidsActivity", "trying to edit kid");
        EditText kidName = findViewById(R.id.kidName);
        kid.setFirstName(kidName.getText().toString());
        Log.i("EditKidsActivity", "with name: " + kidName.getText().toString());

        String balanceStr = ((TextView)findViewById(R.id.kidBalance)).getText().toString();
        if (!balanceStr.equals(kid.getBalanceStringWithoutCurrencySign())) {
            BigDecimal balance;
            try {
                balance = new BigDecimal(balanceStr);
            } catch (NumberFormatException e) {
                Log.i("EditKidsActivity",
                        "exception parsing decimal: " + balanceStr);
                balance = new BigDecimal(0);
            }
            int dollars = balance.intValue();
            kid.setDollars(dollars);

            int cents = balance.subtract(new BigDecimal(dollars)).scaleByPowerOfTen(2).intValue();
            kid.setCents(cents);

            AppDatabase db = SingletonLocalDatabase.getInstance(getApplicationContext());
            db.kidDao().update(kid);
            TransactionRecord tr = new TransactionRecord();
            tr.setTid(java.util.UUID.randomUUID().toString());
            tr.setAmount(balanceStr);
            tr.setKidId(kid.getUid());
            tr.setTimestamp(System.currentTimeMillis());
            tr.setNote("");
            db.transactionRecordDao().insertAll(tr);
        }
        Intent intent = new Intent(this, ListKidsActivity.class);
        startActivity(intent);
    }



}
