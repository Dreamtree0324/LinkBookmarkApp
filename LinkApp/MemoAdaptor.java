package com.example.administrator.dnf;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemoAdaptor extends RecyclerView.Adapter<MemoHolder>{
    Context context;
    int resId;
    ArrayList<MemoVO> datas;
    private int cardPosition = 0;
    private int lastPosition = -1;

    public MemoAdaptor(Context context, int resId, ArrayList<MemoVO> objects) {
        this.context = context;
        this.resId = resId;
        this.datas = objects;
    }

    @Override
    public MemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resId, parent, false);
        final MemoHolder holder = new MemoHolder(view);

        holder.moveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPosition = holder.getAdapterPosition();

                if(cardPosition != RecyclerView.NO_POSITION) {
                    int itemId = datas.get(cardPosition).getId();
                    String itemLink = datas.get(cardPosition).getLink();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemLink));

                    context.startActivity(intent);
                } else{
                Toast.makeText(context, "cardview.click : 존재하지 않는 아이템", Toast.LENGTH_SHORT).show();
            }
        }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPosition = holder.getAdapterPosition();
                if(cardPosition != RecyclerView.NO_POSITION){
                    int itemId = datas.get(cardPosition).getId();
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("id", itemId);
                    context.startActivity(intent);
                } else{
                    Toast.makeText(context, "cardview.click : 존재하지 않는 아이템", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cardPosition = holder.getAdapterPosition();
                if(cardPosition != RecyclerView.NO_POSITION) {
                    int itemId = datas.get(cardPosition).getId();

                    DBHelper helper = new DBHelper(context);
                    SQLiteDatabase db = helper.getWritableDatabase();

                    db.execSQL("DELETE FROM tb_link WHERE _id=?", new String[]{Integer.toString(itemId)});
                    db.close();
                    removeItemView(cardPosition);
                    Toast.makeText(context, "메모가 삭제됨", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(context, "cardview.longclick : 존재하지 않는 아이템", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        return holder;
    }

    @Override

    public void onBindViewHolder(MemoHolder holder, int position) {
        TextView titleView = holder.titleView;
        TextView contentView = holder.linkView;

        MemoVO vo = datas.get(position);


        titleView.setText(vo.getTitle());
        contentView.setText(vo.getLink());
        setAnimation(holder.cardView, position);
    }

    private void setAnimation(CardView cardView, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            cardView.startAnimation(animation);
            lastPosition = position;
        } else{
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            cardView.startAnimation(animation);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private void removeItemView(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, datas.size());
    }
}
