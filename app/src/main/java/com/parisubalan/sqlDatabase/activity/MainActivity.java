package com.parisubalan.sqlDatabase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parisubalan.sqlDatabase.pojo.PojoClass;
import com.parisubalan.sqlDatabase.R;
import com.parisubalan.sqlDatabase.database.SqliteDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText etName, etMobile, etStandard, etSection;
    Button saveBtn, updateBtn, deleteBtn, searchBtn;
    SqliteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    public void initialization()
    {
        etName = findViewById(R.id.et_name);
        etMobile = findViewById(R.id.et_mobile);
        etStandard = findViewById(R.id.et_standred);
        etSection = findViewById(R.id.et_section);
        saveBtn = findViewById(R.id.save_btn);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        searchBtn = findViewById(R.id.search_btn);

        saveBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        db = new SqliteDatabase(this);

    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.save_btn)
        {
            if (emptyChecking()) {
                db.addDetail(new PojoClass(etName.getText().toString(), etMobile.getText().toString(), etStandard.getText().toString(), etSection.getText().toString()));
                if (db != null) {
                    Toast.makeText(this, "Record was added", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if (v.getId() == R.id.update_btn)
        {
            if (emptyChecking()) {
                db.updateDetail(new PojoClass(etName.getText().toString(), etMobile.getText().toString(), etStandard.getText().toString(), etSection.getText().toString()));
                Toast.makeText(this, "Item Updated", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId() == R.id.delete_btn)
        {
            db.deleteDetail(new PojoClass(etName.getText().toString(), etMobile.getText().toString(), etStandard.getText().toString(), etSection.getText().toString()));
            Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.search_btn)
        {
            Intent i = new Intent(this, SearchActivity.class);
            startActivity(i);
        }
    }

    public boolean emptyChecking()
    {
        if (etName.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter Your Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (etMobile.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (etSection.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter Your Mobile Section", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (etStandard.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Enter Your Mobile Standard", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
