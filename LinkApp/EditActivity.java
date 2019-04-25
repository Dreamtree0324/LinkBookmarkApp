package com.example.administrator.dnf;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    Button addSave;
    EditText addTitle,addLink;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        addTitle = (EditText)findViewById(R.id.add_title);
        addLink = (EditText)findViewById(R.id.add_link);
        addSave = (Button)findViewById(R.id.save_btn);
        addSave.setOnClickListener(this);

        Intent intent = getIntent();
        itemId = intent.getIntExtra("id", 0);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT title, link FROM tb_link WHERE _id=?", new String[]{Integer.toString(itemId)});
        while(rs.moveToNext()){
            addTitle.setText(rs.getString(0));
            addLink.setText(rs.getString(1));
        }
        db.close();
    }

    @Override
    public void onClick(View v) {
        String title = addTitle.getText().toString();
        String link = addLink.getText().toString();


        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (itemId == 0) {
            db.execSQL("INSERT INTO tb_link(title, link) VALUES(?, ?)", new String[]{title, link});
            Toast.makeText(getApplicationContext(), "메모가 추가됨", Toast.LENGTH_SHORT).show();
        } else {
            db.execSQL("UPDATE tb_link SET title=?, link=? WHERE _id=?", new String[]{title, link, Integer.toString(itemId)});
            Toast.makeText(getApplicationContext(), "메모가 수정됨", Toast.LENGTH_SHORT).show();
        }
        db.close();

        finish();
    }
}
