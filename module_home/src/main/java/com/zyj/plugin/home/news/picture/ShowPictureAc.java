package com.zyj.plugin.home.news.picture;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyj.plugin.common.mvp.BaseActivity;
import com.zyj.plugin.common.uitl.ShapeUtils;
import com.zyj.plugin.common.uitl.StatusBarUtil;
import com.zyj.plugin.home.R;
import com.zyj.plugin.home.fragment.PageChangeListener;

import java.util.ArrayList;

import butterknife.BindView;

public class ShowPictureAc extends BaseActivity {

    @BindView(R.id.ll_title_content)
    RelativeLayout ll_title_content;
    @BindView(R.id.picture_vp)
    ViewPager picture_vp;
    @BindView(R.id.tv_number)
    TextView tv_number;
    int mPosition = 0;
    SamplePagerAdapter pagerAdapter;
    ArrayList<String> picture_list = new ArrayList<>();

    @Override
    public int onBindLayout() {
        return R.layout.activity_showpicture;
    }

    @Override
    public void initView() {
        setBack(R.mipmap.back);
        ShapeUtils.setShapeCorner2ColorStr(tv_number, "#FF000000", 5);
        StatusBarUtil.immersiveTitle(ShowPictureAc.this, ContextCompat.getColor(mActivity, R.color.black), ll_title_content);
        ll_title_content.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.black));
    }

    @Override
    public void initData() {
        if (getIntent().getStringArrayListExtra("data") != null)
            picture_list = getIntent().getStringArrayListExtra("data");
        mPosition = getIntent().getIntExtra("position", 0);
        tv_number.setText(String.format("%d/%d", mPosition + 1, picture_list.size()));
        pagerAdapter = new SamplePagerAdapter(mActivity, picture_list);
        picture_vp.setAdapter(pagerAdapter);
        picture_vp.setCurrentItem(mPosition);
        picture_vp.setOnPageChangeListener(new PageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (picture_list.size() > 0) {
                    tv_number.setText(String.format("%d/%d", position + 1, picture_list.size()));
                }
            }
        });
    }


}

