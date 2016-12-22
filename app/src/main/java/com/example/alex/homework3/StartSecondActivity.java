package com.example.alex.homework3;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartSecondActivity extends ListActivity {
private RecyclerView appRecView;
    private RecyclerView.Adapter adapter;
    private Filter filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_second);

      appRecView = (RecyclerView) findViewById(R.id.my_recycler_view);
        appRecView.setHasFixedSize(true);
        onClickList(null);

        PackageManager pm = this.getPackageManager();
        List<ApplicationInfo> applicationInfos = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        FilteredListAdapter filteredListAdapter = new FilteredListAdapter(this, pm, applicationInfos);
        filter = filteredListAdapter.getFilter();
        appRecView.setAdapter(filteredListAdapter);

        EditText appFilter = (EditText) findViewById(R.id.edit);
        appFilter.addTextChangedListener(textWatcher);

        Button listButton = (Button) findViewById(R.id.list_button);
        Button gridButton = (Button) findViewById(R.id.grid_button);

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickList(v);
            }
        });
        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGridList(v);
            }
        });
    }

TextWatcher textWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) { filter.filter(s);    }
};
    public void onClickList(View view){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        appRecView.setLayoutManager(layoutManager);
    }
    public void onGridList(View view){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        appRecView.setLayoutManager(layoutManager);
    }

}

