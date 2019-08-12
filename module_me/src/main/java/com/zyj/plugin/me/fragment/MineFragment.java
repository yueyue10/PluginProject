package com.zyj.plugin.me.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zyj.plugin.common.Constants;
import com.zyj.plugin.common.data.API;
import com.zyj.plugin.common.data.bean.ResourceBean;
import com.zyj.plugin.common.data.bean.UserInfo;
import com.zyj.plugin.common.data.local.SpManager;
import com.zyj.plugin.common.mvp.BaseMvpFragment;
import com.zyj.plugin.common.view.CircleImageView;
import com.zyj.plugin.login.utils.LoginUtils;
import com.zyj.plugin.login.web.ShareFragment;
import com.zyj.plugin.me.R;
import com.zyj.plugin.me.R2;
import com.zyj.plugin.me.order.OrderListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseMvpFragment<MinePresenter> implements MineContract.View {

    @BindView(R2.id.user_name_tv)
    TextView user_name_tv;
    @BindView(R2.id.user_icon_iv)
    CircleImageView user_icon_iv;
    List<ResourceBean> resourceBeans;
    MineModuleAdapter mineModuleAdapter;
    public ShareFragment shareFragment;
    private String download_url;

    public static Fragment newInstance() {
        return new MineFragment();
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        resourceBeans = mPresenter.getResourcesData();
        mineModuleAdapter = new MineModuleAdapter(R.layout.item_mine_module, resourceBeans);
        initRecyclerView(R.id.user_module_rv, mineModuleAdapter, new LinearLayoutManager(mActivity));
        initListener();
    }

    @Override
    public void initData() {
        download_url = API.BASE_URL + "mlf/linkLF.html";

    }

    @SuppressLint("MissingPermission")
    public void initListener() {
        mineModuleAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (resourceBeans.get(position).getName()) {
                case "我的订单":
                    if (LoginUtils.isLogin(mActivity, Constants.REQUEST_CODE_LOGIN)) {
                        startActivity(new Intent(mActivity, OrderListActivity.class));
                    }
                    break;
                case "在线缴费记录":
//                    if (LoginUtils.isLogin(mActivity, Constants.REQUEST_CODE_LOGIN)) {
//                        startActivity(new Intent(mActivity, PayOnLineHistoryActivity.class));
//                    }
                    break;
                case "推荐给好友":
                    if (shareFragment == null) {
                        shareFragment = ShareFragment.getInstance(getString(R.string.app_name),
                                getString(R.string.share_content), download_url, null);
                    }
                    shareFragment.show(mActivity.getSupportFragmentManager(), "ShareFragment");
                    break;
                case "常用信息":
//                    if (LoginUtils.isLogin(mActivity, Constants.REQUEST_CODE_LOGIN)) {
//                        startActivity(new Intent(mActivity, ComInfoActivity.class));
//                    }
                    break;
                case "意见反馈":
//                    startActivity(new Intent(mActivity, FeedbackActivity.class));
                    break;
                case "关于梦廊坊":
//                    Intent intent_about = new Intent(getContext(), AboutUsActivity.class);
//                    startActivity(intent_about);
                    break;
                case "设置":
//                    startActivity(new Intent(mActivity, SettingActivity.class));
                    break;
            }
        });
    }

    @OnClick({R2.id.user_icon_iv, R2.id.user_name_tv})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.user_icon_iv) {
            showToast("我的1");
        } else if (i == R.id.user_name_tv) {
            showToast("我的2");
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfo userInfo) {
        user_name_tv.setText(userInfo.getNickNameStr());
        Glide.with(mActivity).load(userInfo.getHeadImg()).apply(new RequestOptions()
                .error(R.mipmap.ic_launcher)).into(user_icon_iv);
    }

    @Override
    public void getNoPayInfoSuccess(int noPayNum) {
        if (noPayNum > 0) {
            resourceBeans.get(0).setOrderStatus(noPayNum + "个待付款");
        } else {
            resourceBeans.get(0).setOrderStatus(null);
        }
        mineModuleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        String picUrl = null;
        if (!TextUtils.isEmpty(SpManager.getInstance().getToken())) {
            mPresenter.getUserInfo();
            user_name_tv.setText(SpManager.getInstance().getUserUserInfo().getNickNameStr());
            picUrl = SpManager.getInstance().getUserUserInfo().getHeadImg();
        } else {
            user_name_tv.setText(getString(R.string.hint_not_login));
        }
        mPresenter.getNoPayInfo();
        Glide.with(mActivity).load(picUrl).apply(new RequestOptions()
                .error(R.mipmap.ic_launcher)).into(user_icon_iv);
    }
}
