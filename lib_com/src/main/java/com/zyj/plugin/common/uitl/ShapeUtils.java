package com.zyj.plugin.common.uitl;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;

import static android.graphics.drawable.GradientDrawable.LINEAR_GRADIENT;

public class ShapeUtils {

    /**
     * 设置 shape 的颜色
     *
     * @param view
     * @param solidColor
     */
    private static void setShapeColor(View view, int solidColor) {
        if (view == null) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(solidColor);
        view.setBackgroundDrawable(gradientDrawable);
    }

    public static void setShapeColorRes(View view, int solidColor) {
        setShapeColor(view, ColorUtils.getColor(solidColor));
    }

    public static void setShapeColorStr(View view, String solidColor) {
        setShapeColor(view, ColorUtils.string2Int(solidColor));
    }

    /**
     * 设置shape倒角和颜色
     *
     * @param view
     * @param solidColor
     * @param corner
     */
    private static void setShapeCorner2Color(View view, int solidColor, float corner) {
        if (view == null) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(solidColor);
        gradientDrawable.setCornerRadius(corner);
        view.setBackgroundDrawable(gradientDrawable);
    }

    public static void setShapeCorner2ColorRes(View view, int solidColor, float corner) {
        setShapeCorner2Color(view, ColorUtils.getColor(solidColor), SizeUtils.dp2px(corner));
    }

    public static void setShapeCorner2ColorStr(View view, String solidColor, float corner) {
        setShapeCorner2Color(view, ColorUtils.string2Int(solidColor), SizeUtils.dp2px(corner));
    }

    /**
     * 设置shape倒角和颜色
     *
     * @param view
     * @param solidColor
     */
    private static void setShapeCorner2Color(View view, int solidColor, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        if (view == null) {
            return;
        }
        float[] radii = new float[]{
                topLeftRadius, topLeftRadius,
                topRightRadius, topRightRadius,
                bottomRightRadius, bottomRightRadius,
                bottomLeftRadius, bottomLeftRadius};
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(solidColor);
        gradientDrawable.setCornerRadii(radii);
        view.setBackgroundDrawable(gradientDrawable);
    }

    public static void setShapeCorner2ColorRes(View view, int solidColor, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        topLeftRadius = SizeUtils.dp2px(topLeftRadius);
        topRightRadius = SizeUtils.dp2px(topRightRadius);
        bottomRightRadius = SizeUtils.dp2px(bottomRightRadius);
        bottomLeftRadius = SizeUtils.dp2px(bottomLeftRadius);
        setShapeCorner2Color(view, ColorUtils.getColor(solidColor), topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
    }

    public static void setShapeCorner2ColorStr(View view, String solidColor, float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        topLeftRadius = SizeUtils.dp2px(topLeftRadius);
        topRightRadius = SizeUtils.dp2px(topRightRadius);
        bottomRightRadius = SizeUtils.dp2px(bottomRightRadius);
        bottomLeftRadius = SizeUtils.dp2px(bottomLeftRadius);
        setShapeCorner2Color(view, ColorUtils.string2Int(solidColor), topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius);
    }

    /**
     * 设置shape倒角 颜色 和描边颜色和大小
     *
     * @param view
     * @param solidColor
     * @param corner
     */
    private static void setShapeCorner2Color2Stroke(View view, int solidColor, float corner, int strokeColor, int strokeWidth) {
        if (view == null) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(solidColor);
        gradientDrawable.setCornerRadius(corner);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        view.setBackgroundDrawable(gradientDrawable);
    }

    public static void setShapeCorner2Color2StrokeStr(View view, String solidColor, float corner, String strokeColor, int strokeWidth) {
        setShapeCorner2Color2Stroke(view, ColorUtils.string2Int(solidColor), SizeUtils.dp2px(corner), ColorUtils.string2Int(strokeColor), SizeUtils.dp2px(strokeWidth));
    }

    /**
     * 设置矩形渐变，同时shape渐变类型只能是线性从上倒下，颜色数组中的顺序即是渐变顺序
     *
     * @param view
     * @param colors
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void setShapeGradient(View view, int[] colors) {
        if (view == null) {
            return;
        }
        if (colors.length > 3) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setGradientType(LINEAR_GRADIENT);
        gradientDrawable.setColors(colors);
        view.setBackgroundDrawable(gradientDrawable);
    }

}