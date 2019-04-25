package com.example.administrator.dnf;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Item extends AppCompatActivity implements View.OnClickListener {

    Button moveBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_item);

       moveBtn = (Button)findViewById(R.id.moveBtn);

       moveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == moveBtn) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"));
            startActivity(intent);
        }
    }
}
