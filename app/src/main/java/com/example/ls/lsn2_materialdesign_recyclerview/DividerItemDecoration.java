package com.example.ls.lsn2_materialdesign_recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 路很长~ on 2017/7/30.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mDividerHight = 1;
    private Paint mColorPaint;
    private final int[] attr = new int[]{android.R.attr.listDivider};
    private int mOrignation;
    private Drawable mDiveder;

    public DividerItemDecoration(Context context, int mOrignation) {
        TypedArray array = context.obtainStyledAttributes(attr);
        mDiveder = array.getDrawable(0);
        array.recycle();
        getOrignation(mOrignation);
        //绘制颜色分割线的画笔
       // mColorPaint = new Paint();
       // mColorPaint.setColor(Color.RED);

    }

    private void getOrignation(int mOrignation) {
        if (mOrignation != LinearLayoutManager.HORIZONTAL && mOrignation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("兄弟呀！你这个是方向枚举设置么");
        }
        this.mOrignation = mOrignation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrignation == LinearLayoutManager.VERTICAL) {
            setDrawVertical(c, parent);
        } else if (mOrignation == LinearLayoutManager.HORIZONTAL) {
            setDrawHorizontal(c, parent);
        }

    }

    private void setDrawHorizontal(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        int top=parent.getPaddingTop();
        int bottom= parent.getHeight()-parent.getPaddingBottom();
        for (int i = 0; i < count; i++) {
            View childer = parent.getChildAt(i);
            RecyclerView.LayoutParams parames = (RecyclerView.LayoutParams) parent.getChildAt(i).getLayoutParams();
            int left=childer.getRight()+parames.rightMargin+ Math.round(ViewCompat.getTranslationX(childer));;
            int right=left+mDiveder.getIntrinsicWidth();

            mDiveder.setBounds(left,top,right,bottom);
            mDiveder.draw(c);
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }

        }
    }

    private void setDrawVertical(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < count; i++) {
            View childer = parent.getChildAt(i);
            RecyclerView.LayoutParams parames = (RecyclerView.LayoutParams) parent.getChildAt(i).getLayoutParams();
            int top = childer.getBottom() + parames.bottomMargin + Math.round(ViewCompat.getTranslationY(childer));
            int bottom = top + mDiveder.getIntrinsicHeight();
            mDiveder.setBounds(left, top, right, bottom);
            mDiveder.draw(c);
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //如果是垂直防线话表格
        if(mOrignation==LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0,mDiveder.getIntrinsicHeight());
        }else{
            outRect.set(0,mDiveder.getIntrinsicWidth(),0,0);
        }

    }
}
