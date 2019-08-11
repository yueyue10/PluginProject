package com.zyj.plugin.home.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.home.news.NewsListActivity;
import com.zyj.plugin.home.news.PickedListActivity;
import com.zyj.plugin.home.news.detail.NewsDetailActivity;
import com.zyj.plugin.home.news.picture.ShowPictureAc;
import com.zyj.plugin.login.web.WebViewActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class JudgeUtils {

    public static void startShowPictureAc(Context mActivity, ArrayList<String> urls, int position) {
        mActivity.startActivity(new Intent(mActivity, ShowPictureAc.class)
                .putExtra("data", urls)
                .putExtra("position", position));
    }

    public static void startNewsListAc(Context mActivity, String id) {
        mActivity.startActivity(new Intent(mActivity, NewsListActivity.class)
                .putExtra("data", id));
    }

    public static void startPickedListActivity(Context mActivity, List<NewsBean> newsBeans) {
        mActivity.startActivity(new Intent(mActivity, PickedListActivity.class)
                .putParcelableArrayListExtra("newsBeans", (ArrayList<? extends Parcelable>) newsBeans));
    }

    public static void startNewsDetailAc(Context mActivity, int id) {
        mActivity.startActivity(new Intent(mActivity, NewsDetailActivity.class)
                .putExtra("newsId", id));
    }

    public static void startWebViewAc(Context mActivity, String url, String title) {
        startWebViewAc(mActivity, url, title, null, null, true);
    }

    public static void startWebViewAc(Context mActivity, String url, String title, String picUrl, String description, boolean canshare) {
        mActivity.startActivity(new Intent(mActivity, WebViewActivity.class)
                .putExtra("url", url).putExtra("title", title)
                .putExtra("picUrl", picUrl).putExtra("description", description)
                .putExtra("canshare", canshare));
    }

}
