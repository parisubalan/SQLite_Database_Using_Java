package com.parisubalan.sqlDatabase.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parisubalan.sqlDatabase.adapter.Adapter;
import com.parisubalan.sqlDatabase.pojo.PojoClass;
import com.parisubalan.sqlDatabase.R;
import com.parisubalan.sqlDatabase.database.SqliteDatabase;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText etFind;
    RecyclerView recyclerView;
    SqliteDatabase db;
    ArrayList<PojoClass> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        initialization();
        filter();
    }

    public void initialization()
    {
        etFind = findViewById(R.id.et_search);
        recyclerView = findViewById(R.id.recycler_view);
        arrayList =  new ArrayList<>();
        db = new SqliteDatabase(this);
    }

    public void filter()
    {
        etFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayList = db.readData(etFind.getText().toString());
                adapterSet();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void adapterSet()
    {
        Adapter adapter = new Adapter(getApplicationContext(), arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}
