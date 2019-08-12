package com.zyj.plugin.login.web;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zyj.plugin.common.mvp.dialog.BaseNorDialogFragment;
import com.zyj.plugin.common.uitl.ShapeUtils;
import com.zyj.plugin.login.R;
import com.zyj.plugin.login.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareFragment extends BaseNorDialogFragment {

    @BindView(R2.id.cancel_tv)
    TextView cancel_tv;
    @BindView(R2.id.bottom_layout)
    LinearLayout bottom_layout;
    ShareAdapter shareAdapter;
    List<Integer> resIds;
    String url = "";
    String title = "";
    String picUrl = "";
    String description = "";
    String[] storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    public int onBindLayout() {
        return R.layout.fragment_share;
    }

    public static ShareFragment getInstance(String title, String description, String url, String picUrl) {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        args.putString("url", url);
        args.putString("picUrl", picUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View view) {
        ShapeUtils.setShapeCorner2ColorStr(cancel_tv, "#F3F3F3", 3);
        ShapeUtils.setShapeCorner2ColorRes(bottom_layout, R.color.white, 20, 20, 0, 0);
        initRecyclerView();
    }

    private void initRecyclerView() {
        resIds = getResList();
        shareAdapter = new ShareAdapter(R.layout.item_image_view, resIds);
        initRecyclerView(R.id.module_rv, shareAdapter, new GridLayoutManager(mActivity, 5));
    }

    @OnClick({R2.id.cancel_tv})
    public void onClick(View view) {
        if (view.getId() == R.id.cancel_tv) {
            dismiss();
        }
    }


    @Override
    public void initListener() {
        shareAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    shareProcess(SHARE_MEDIA.QQ);
                    break;
                case 1:
                    shareProcess(SHARE_MEDIA.WEIXIN);
                    break;
                case 2:
                    shareProcess(SHARE_MEDIA.QZONE);
                    break;
                case 3:
                    shareProcess(SHARE_MEDIA.WEIXIN_CIRCLE);
                    break;
            }
            dismiss();
        });
    }

    private void shareProcess(SHARE_MEDIA var1) {
//        UMWeb web = new UMWeb(url);
//        web.setTitle(title);//标题
//        if (StringUtils.isEmpty(picUrl)) {
//            web.setThumb(new UMImage(mActivity, R.mipmap.ic_launcher));  //缩略图
//        } else {
//            web.setThumb(new UMImage(mActivity, picUrl));  //缩略图
//        }
//        web.setDescription(description);//描述
//        new ShareAction(mActivity)
//                .setPlatform(var1)//传入平台
//                .withMedia(web)
//                .setCallback(shareListener)//回调监听器
//                .share();
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        url = bundle.getString("url");
        title = bundle.getString("title");
        picUrl = bundle.getString("picUrl");
        description = bundle.getString("description");
    }

    public List<Integer> getResList() {
        List<Integer> resIds = new ArrayList<>();
        resIds.add(R.drawable.umeng_socialize_qq);
        resIds.add(R.drawable.umeng_socialize_wechat);
        resIds.add(R.drawable.umeng_socialize_qzone);
        resIds.add(R.drawable.umeng_socialize_wxcircle);
        return resIds;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (PermissionUtils.isGranted(storagePermission)) {
            super.show(manager, tag);
        } else {
            requestPermission();
        }
    }

    /**
     * 获取权限
     */
    private void requestPermission() {
        PermissionUtils.permission(storagePermission).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {
                //SpanUtils.with()
            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                ToastUtils.showShort("拨号权限被禁止");
            }
        }).theme(activity -> ScreenUtils.setFullScreen(activity)).request();
    }

//    private UMShareListener shareListener = new UMShareListener() {
//        /**
//         * @descrption 分享开始的回调
//         * @param platform 平台类型
//         */
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//
//        }
//
//        /**
//         * @descrption 分享成功的回调
//         * @param platform 平台类型
//         */
//        @Override
//        public void onResult(SHARE_MEDIA platform) {
//            showToast("成功了");
//        }
//
//        /**
//         * @descrption 分享失败的回调
//         * @param platform 平台类型
//         * @param t 错误原因
//         */
//        @Override
//        public void onError(SHARE_MEDIA platform, Throwable t) {
//            showToast("失败" + t.getMessage());
//        }
//
//        /**
//         * @descrption 分享取消的回调
//         * @param platform 平台类型
//         */
//        @Override
//        public void onCancel(SHARE_MEDIA platform) {
//            showToast("取消了");
//        }
//    };

}
