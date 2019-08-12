package com.zyj.plugin.main;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zyj.plugin.R;

public class MainPopWindow extends PopupWindow implements View.OnClickListener {

    protected Activity context;

    public MainPopWindow(Activity context, int width, int height) {
        super(width, height);
        this.context = context;
        setWindowAlpha();
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        initView();
    }

    /**
     * 设置背景透明度
     */
    public void setWindowAlpha() {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.4f;
        context.getWindow().setAttributes(lp);
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // 加上这句
        setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 1f;
                context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
            }
        });
    }

    private void initView() {
        View circleExpandView = LayoutInflater.from(context)
                .inflate(R.layout.pop_window_main, null, false);
        RelativeLayout main_layout = circleExpandView.findViewById(R.id.main_layout);
        circleExpandView.setOnClickListener(v -> dismiss());
        ImageView booking_space_iv = main_layout.findViewById(R.id.booking_space_iv);
        ImageView online_payment_iv = main_layout.findViewById(R.id.online_payment_iv);
        ImageView reverse_for_car_iv = main_layout.findViewById(R.id.reverse_for_car_iv);
        online_payment_iv.setOnClickListener(this);
        reverse_for_car_iv.setOnClickListener(this);
        booking_space_iv.setOnClickListener(this);
        setContentView(circleExpandView);
//        main_layout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Point center = new Point();
//                if (main_layout.getMain() == null)
//                    return;
//                center.set((int) main_layout.getMain().getX(), (int) main_layout.getMain().getY());
//                DefaultAnimationHandler defaultAnimationHandler = new DefaultAnimationHandler();
//                defaultAnimationHandler.setMenu(main_layout);
//                defaultAnimationHandler.animateMenuOpening(center);
//            }
//        },1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.booking_space_iv:
                onClickListener.onClick("booking_space");
                break;
            case R.id.online_payment_iv:
                onClickListener.onClick("online_payment");
                break;
            case R.id.reverse_for_car_iv:
                onClickListener.onClick("reverse_for_car");
                break;
        }
        dismiss();
    }

    private OnClickListener onClickListener;

    public interface OnClickListener {
        void onClick(String positionTag);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
