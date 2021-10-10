package com.nova.antaeul;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewDiv_height extends RecyclerView.ItemDecoration {

    private final int divHeight;

    public RecyclerViewDiv_height(int divHeight) {
        this.divHeight = divHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = divHeight;//아이템 하단 높이 설정
        outRect.top = divHeight;//아이템 상단 높이 설정
    }
}