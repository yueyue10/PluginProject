package com.zyj.plugin.login.web;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;

import com.zyj.plugin.common.Constants;
import com.zyj.plugin.common.mvp.BaseActivity;
import com.zyj.plugin.common.view.WebViewBox;
import com.zyj.plugin.login.R;
import com.zyj.plugin.login.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R2.id.webView)
    public WebViewBox webView;

    public String title = "详情界面";
    public String url = "http://www.ocdc.com.cn";
    public String picUrl = null;
    public String description = null;
    public ShareFragment shareFragment;

    @Override
    public int onBindLayout() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        description = getString(R.string.share_content);
    }

    @Override
    public void initData() {
        //单纯的网页链接
        if (getIntent().getStringExtra("url") != null)
            url = getIntent().getStringExtra("url");
        if (getIntent().getStringExtra("title") != null)
            title = getIntent().getStringExtra("title");
        if (getIntent().getStringExtra("picUrl") != null)
            picUrl = getIntent().getStringExtra("picUrl");
        if (getIntent().getStringExtra("description") != null)
            description = getIntent().getStringExtra("description");
        if (getIntent().getBooleanExtra("canshare", true))
            setRight(R.mipmap.transmit);
        setTitleBack(title);
        if (url.equals(Constants.PATH_AGREENEMT_MLF)) {
            webView.setTextSizeLarger();
        }
        webView.loadUrl(url);
    }

    @OnClick({R2.id.iv_right})
    public void onClick(View view) {
        if (view.getId() == R.id.iv_right) {
            if (shareFragment == null) {
                shareFragment = ShareFragment.getInstance(title, description, url, picUrl);
            }
            shareFragment.show(getSupportFragmentManager(), "ShareFragment");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webView.goBack()) {
                return true;
            } else {
                finish();
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        UMShareAPI.get(this).release();
    }


    private void getPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }
}
