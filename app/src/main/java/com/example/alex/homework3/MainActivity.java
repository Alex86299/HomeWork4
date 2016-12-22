package com.example.alex.homework3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAct = (Button) findViewById(R.id.btnAct);
        ImageButton btnCall = (ImageButton) findViewById(R.id.btnCall);
        ImageButton btnSms = (ImageButton) findViewById(R.id.btnSms);

        btnAct.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnSms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnAct:
                intent = callNewAct();
                break;
            case R.id.btnCall:
                intent = callNewDial();
                break;
            case R.id.btnSms:
                intent = callNewSms();
                break;
        }
        startActivity(intent);
    }

    @NonNull
    private Intent callNewDial() {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        return intent;
    }

    @NonNull
    private Intent callNewAct() {
        Intent intent = new Intent(this, StartSecondActivity.class);
        return intent;
    }

    @NonNull
    private Intent callNewSms() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
