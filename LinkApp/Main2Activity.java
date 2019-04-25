package com.example.administrator.dnf;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    Button add_list;
    RecyclerView recyclerView;
    Button moveBtn;

    ArrayList<MemoVO> datas;
    MemoAdaptor mAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("즐겨찾는 링크");

        add_list = (Button)findViewById(R.id.add_list);
        recyclerView = findViewById(R.id.main_list);

        add_list.setOnClickListener(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        datas = new ArrayList<>();
        getAllMemos();

        mAdapter = new MemoAdaptor(this, R.layout.custom_item, datas);
        recyclerView.setAdapter(mAdapter);

        int offset = 5;
        recyclerView.addItemDecoration(new MemoItemDecoration(offset));


    }

    private void getAllMemos() {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor rs = db.rawQuery("select _id,title,link from tb_link order by _id", null);

        while(rs.moveToNext()){
            MemoVO vo = new MemoVO();

            vo.setId(rs.getInt(0));
            vo.setTitle(rs.getString(1));
            vo.setLink(rs.getString(2));
            datas.add(vo);
        }
        db.close();
    }

    @Override
    public void onClick(View v) {
        if(v == add_list) {
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            intent.putExtra("id", 0);
            startActivity(intent);
        }
    }

    protected void onResume() {
        super.onResume();

        datas.clear();
        getAllMemos();
        mAdapter.notifyDataSetChanged();
    }

    private class MemoItemDecoration extends RecyclerView.ItemDecoration {
        private int offset;

        public MemoItemDecoration(int offset) {
            this.offset = offset;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();


            ViewCompat.setElevation(view, 20.0f);
        }
    }
}
