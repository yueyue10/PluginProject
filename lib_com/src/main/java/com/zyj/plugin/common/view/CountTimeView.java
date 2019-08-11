package com.zyj.plugin.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zyj.plugin.common.R;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @作者 zhouchao
 * @日期 2019/4/19
 * @描述
 */
public class CountTimeView extends AppCompatTextView {
    // handler的Message
    private static final int COUNTTIME = 1;

    // 提供默认的设置
    private static final String INITTEXT = "获取验证码";
    private static final String PREFIXRUNTEXT = "";
    private static final String SUFFIXRUNTEXT = "S后重发";
    private static final String FINISHTEXT = "点击重新获取";
    private static final int TOTALTIME = 60 * 1000;
    private static final int STEP = 1000;
    private static final int COLOR = Color.WHITE;
    // 来自布局文件中的属性设置
    private String mInitText;// 初始化文本
    private String mPrefixRunText;// 运行时的文本前缀
    private String mSuffixRunText;// 运行时的文本后缀
    private String mFinishText;// 完成倒计时后的文本显示
    private int mTotalTime;// 倒计时的总时间
    private int mStepTime;// 一次时间
    private int mColor;
    // 实际使用的总时间
    private int totalTime;
    // 判断是否在倒计时中，防止多次点击
    private boolean isRun;
    // 是否允许倒计时
    private boolean isAllowRun;
    // 处理倒计时的方法
    private Timer mTimer;
    private TimerTask mTimerTask;
    private Handler mHandler = new MyHandler(this);

    public CountTimeView(Context context) {
        this(context, null);
    }

    public CountTimeView(Context context, AttributeSet attrs) {
        // 如果不写android.R.attr.textViewStyle会丢失很多属性
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public CountTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 1. 在布局文件中提供设置
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountTimeView);
        mInitText = ta.getString(R.styleable.CountTimeView_inittext);
        mPrefixRunText = ta.getString(R.styleable.CountTimeView_prefixruntext);
        mSuffixRunText = ta.getString(R.styleable.CountTimeView_suffixruntext);
        mFinishText = ta.getString(R.styleable.CountTimeView_finishtext);
        mTotalTime = ta.getInteger(R.styleable.CountTimeView_totaltime, TOTALTIME);
        mStepTime = ta.getInteger(R.styleable.CountTimeView_onetime, STEP);
        mColor = ta.getColor(R.styleable.CountTimeView_timecolor, COLOR);
        ta.recycle();
        // 2.代码设置值
        // 3.如果布局和代码都没有设置，则给予默认值
        initData();
        initTimer();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 如果为空，则设置默认的值

        if (TextUtils.isEmpty(mInitText)) {
            mInitText = INITTEXT;
        }
        if (TextUtils.isEmpty(mPrefixRunText)) {
            mPrefixRunText = PREFIXRUNTEXT;
        }
        if (TextUtils.isEmpty(mSuffixRunText)) {
            mSuffixRunText = SUFFIXRUNTEXT;
        }
        if (TextUtils.isEmpty(mFinishText)) {
            mFinishText = FINISHTEXT;
        }
        if (mTotalTime < 0) {
            mTotalTime = TOTALTIME;
        }
        if (mStepTime < 0) {
            mStepTime = STEP;
        }
        CountTimeView.this.setText(mInitText);
    }

    /**
     * 初始化时间
     */
    private void initTimer() {
        totalTime = mTotalTime;
        mTimer = new Timer();
        mTimerTask = new TimerTask() {

            @Override
            public void run() {
                mHandler.sendEmptyMessage(COUNTTIME);
            }
        };
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isAllowRun || isRun) {
            return false;
        }
        return super.onTouchEvent(event);
    }

    public void startRun() {
        if (isAllowRun && !isRun) {
            initTimer();
            mTimer.schedule(mTimerTask, 0, mStepTime);
            setTextColor(Color.WHITE);
            isRun = true;
        }
    }

    public boolean isRun() {
        return isRun;
    }

    /**
     * 清除时间
     */
    private void clearTimer() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    /**
     * 设置初始化的文字
     *
     * @param mInittext
     * @return
     */
    public CountTimeView setInitText(String mInittext) {
        this.mInitText = mInittext;
        CountTimeView.this.setText(mInittext);
        return this;
    }

    /**
     * 设置运行时的文字前缀
     *
     * @param mPrefixRuntext
     * @return
     */
    public CountTimeView setPrefixRunText(String mPrefixRuntext) {
        this.mPrefixRunText = mPrefixRuntext;
        return this;
    }

    /**
     * 设置运行时的文字后缀
     *
     * @param mSuffixRuntext
     * @return
     */
    public CountTimeView setSuffixRunText(String mSuffixRuntext) {
        this.mSuffixRunText = mSuffixRuntext;
        return this;
    }

    /**
     * 设置结束的文字
     *
     * @param mFinishtext
     * @return
     */
    public CountTimeView setFinishText(String mFinishtext) {
        this.mFinishText = mFinishtext;
        return this;
    }

    /**
     * 设置倒计时的总时间
     *
     * @param mTotaltime
     * @return
     */
    public CountTimeView setTotalTime(int mTotaltime) {
        this.mTotalTime = mTotaltime;
        return this;
    }

    /**
     * 设置一次倒计时的时间
     *
     * @param mStepTime
     * @return
     */
    public CountTimeView setStepTime(int mStepTime) {
        this.mStepTime = mStepTime;
        return this;
    }

    /**
     * 设置默认倒计时秒数的颜色
     *
     * @param mColor
     * @return
     */
    public CountTimeView setTimeColor(int mColor) {
        this.mColor = mColor;
        return this;
    }

    /**
     * 窗口销毁时，倒计时停止
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearTimer();
    }

    /**
     * 是否允许倒计时
     */
    public void setAllowRun(Boolean isAllowRun) {
        this.isAllowRun = isAllowRun;
    }

    /**
     * 是否允许倒计时
     */
    public void setAllowRun(Boolean isAllowRun, int color) {
        this.isAllowRun = isAllowRun;
        if (!isRun)
            setTextColor(color);
    }

    private static class MyHandler extends Handler {
        private WeakReference<CountTimeView> activityWeakReference;

        public MyHandler(CountTimeView timeView) {
            activityWeakReference = new WeakReference<CountTimeView>(timeView);
        }

        @Override
        public void handleMessage(Message msg) {
            CountTimeView timeView = activityWeakReference.get();
            if (timeView != null) {
                switch (msg.what) {
                    case COUNTTIME:
                        // 对秒数进行格式化
                        DecimalFormat df = new DecimalFormat("#00");
                        String strTotalTime = df.format(timeView.totalTime / 1000);
                        String runtimeText = timeView.mPrefixRunText + strTotalTime + timeView.mSuffixRunText;

                        // 对秒数进行颜色设置
                        Spannable spannable = new SpannableString(runtimeText);
                        ForegroundColorSpan redSpan = new ForegroundColorSpan(timeView.mColor);
                        spannable.setSpan(redSpan, timeView.mPrefixRunText.length(), timeView.mPrefixRunText.length() + strTotalTime.length(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        timeView.setText(spannable);
                        timeView.totalTime -= timeView.mStepTime;
                        if (timeView.totalTime < 0) {
                            timeView.setText(timeView.mFinishText);
                            if (timeView.isAllowRun) {
                                timeView.setTextColor(Color.WHITE);
                                timeView.setBackgroundResource(R.color.bg_button_orange);
                            } else {
                                timeView.setTextColor(Color.WHITE);
                                timeView.setBackgroundResource(R.color.bg_button_orange_30);
                            }
                            timeView.isRun = false;
                            timeView.clearTimer();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }


}
