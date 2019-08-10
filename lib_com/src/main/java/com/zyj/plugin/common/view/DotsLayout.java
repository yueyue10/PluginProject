package com.zyj.plugin.common.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * @作者 zhouchao
 * @日期 2019/4/1
 * @描述
 */
public class DotsLayout extends LinearLayout {
    private static final int MAX_SHOW_DOTS = 14;
    private Drawable normalDrawable;
    private Drawable selectedDrawable;
    private int mCurrDot = -1;
    private int mTotalDots;
    private int selectedColor = Color.WHITE;
    private int normalColor = Color.GRAY;
    private LayoutParams params;

    public DotsLayout(Context context) {
        super(context);
        init();
    }

    public DotsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }
        setOrientation(HORIZONTAL);

        GradientDrawable dot_normal = new GradientDrawable();
        dot_normal.setSize(DensityUtil.dp2px(12), DensityUtil.dp2px(3));
        dot_normal.setShape(GradientDrawable.RECTANGLE);
        dot_normal.setCornerRadius(4);
        dot_normal.setColor(normalColor);
        dot_normal.setStroke(DensityUtil.dp2px(0.5f), normalColor);
        normalDrawable = dot_normal;

        GradientDrawable dot_focus = new GradientDrawable();
        dot_focus.setColor(selectedColor);
        dot_focus.setShape(GradientDrawable.RECTANGLE);
        dot_focus.setCornerRadius(4);
        dot_focus.setSize(DensityUtil.dp2px(12), DensityUtil.dp2px(3));
        selectedDrawable = dot_focus;

        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = DensityUtil.dp2px(3);
        params.rightMargin = DensityUtil.dp2px(3);
    }

    public void setDotRes(int normalResId, int selectedResId) {
        normalDrawable = getResources().getDrawable(normalResId);
        selectedDrawable = getResources().getDrawable(selectedResId);
        invalidate();
    }

    public void setDot(int currDot, int totalDots) {
        if (totalDots <= 1) {
            return;
        }
        ImageView imgView = null;
        if (totalDots > MAX_SHOW_DOTS) {
            if (currDot > MAX_SHOW_DOTS - 1) {
                if (mCurrDot == MAX_SHOW_DOTS - 1) {
                    return;
                }
                imgView = (ImageView) getChildAt(mCurrDot);
                imgView.setImageDrawable(normalDrawable);

                mCurrDot = MAX_SHOW_DOTS - 1;
                imgView = (ImageView) getChildAt(mCurrDot);
                imgView.setImageDrawable(selectedDrawable);
            } else {
                if (currDot != mCurrDot) {
                    imgView = (ImageView) getChildAt(mCurrDot);
                    imgView.setImageDrawable(normalDrawable);
                    imgView = (ImageView) getChildAt(currDot);
                    imgView.setImageDrawable(selectedDrawable);
                }
                mCurrDot = currDot;
            }
            mTotalDots = MAX_SHOW_DOTS;
            return;
        }

        for (int i = mTotalDots; i < totalDots; i++) {
            imgView = new ImageView(getContext());
            imgView.setImageDrawable(normalDrawable);
            addView(imgView, params);
        }
        if (currDot != mCurrDot) {
            if (mCurrDot != -1) {
                imgView = (ImageView) getChildAt(mCurrDot);
                imgView.setImageDrawable(normalDrawable);
            }

            imgView = (ImageView) getChildAt(currDot);
            imgView.setImageDrawable(selectedDrawable);
        }

        mCurrDot = currDot;
        mTotalDots = totalDots;
    }
}
