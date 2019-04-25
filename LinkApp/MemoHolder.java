package com.example.administrator.dnf;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MemoHolder  extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView titleView;
    public TextView linkView;
    public Button moveBtn;

    public MemoHolder(View root) {
        super(root);
        cardView = root.findViewById(R.id.cardview);
        titleView = (TextView) root.findViewById(R.id.link_title);
        linkView = (TextView) root.findViewById(R.id.link_item);
        moveBtn = (Button) root.findViewById(R.id.moveBtn);
    }
}
