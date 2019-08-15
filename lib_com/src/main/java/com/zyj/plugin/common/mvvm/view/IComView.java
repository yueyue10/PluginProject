package com.zyj.plugin.common.mvvm.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface IComView {
    void setBack(int resId);

    void enableTitleStatusBar(boolean title, boolean status);

    void setRightImage(int resId);

    void setRightText(String rightText);

    void setRightClick(int resId, View.OnClickListener onClickListener);

    void setRightClick(String rightText, View.OnClickListener onClickListener);

    void initRecyclerView();

    RecyclerView initRecyclerView(int resId, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager);

    RecyclerView initRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager);

    RecyclerView initRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager, RecyclerView.ItemDecoration divider);
}
