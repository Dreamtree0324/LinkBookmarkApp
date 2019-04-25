package com.example.administrator.dnf;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadDBActivity extends AppCompatActivity {
    TextView titleView;
    TextView linkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_db);

        titleView = (TextView)findViewById(R.id.read_title);
        linkView = (TextView)findViewById(R.id.read_link);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select title, link from tb_link order by _id desc limit 1", null);
        while(cursor.moveToNext()){
            titleView.setText(cursor.getString(0));
            linkView.setText(cursor.getString(1));
        }

        db.close();
    }
}
