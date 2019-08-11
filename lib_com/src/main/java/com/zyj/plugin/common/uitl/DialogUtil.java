package com.zyj.plugin.common.uitl;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @作者 zhouchao
 * @日期 2019/4/26
 * @描述
 */
public class DialogUtil {

    public static ProgressDialog showProgress(Context context, String message) {
        ProgressDialog mProgress = new ProgressDialog(context);
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.setMessage(message);
        mProgress.setCancelable(true);
        mProgress.setIndeterminate(false);
        mProgress.show();
        return mProgress;
    }

    public static void showNotifyDialog(Context context, DialogInterface.OnClickListener positiveListener,
                                        DialogInterface.OnClickListener negativeListener) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(context);
        dialog.setTitle("友情提示");
        dialog.setMessage("您有任务未提交，请及时提交，确保任务完成");
        dialog.setPositiveButton("前往提交",
                positiveListener);
        dialog.setNegativeButton("暂不提交",
                negativeListener);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showPhoneDialog(Context context, DialogInterface.OnClickListener positiveListener,
                                       DialogInterface.OnClickListener negativeListener) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(context);
        dialog.setTitle("友情提示");
        dialog.setMessage("您有任务未提交，请及时提交，确保任务完成");
        dialog.setPositiveButton("前往提交",
                positiveListener);
        dialog.setNegativeButton("暂不提交",
                negativeListener);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showNotifyDialog(Context context, String message) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(context);
        dialog.setTitle("提示");
        dialog.setMessage(message);
        dialog.setPositiveButton("确定",
                (dialog1, which) -> dialog1.dismiss());
        dialog.setCancelable(false);
        dialog.show();
    }



    public static void showNotifyDialog(Context context,String title, String message) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("确定",
                (dialog1, which) -> dialog1.dismiss());
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showNotifyDialog(Context context,String title, String message,
                                        DialogInterface.OnClickListener positiveListener) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("确定",positiveListener);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showDialog(Context context,
                                  String title,
                                  String message,
                                  String positiveText, String NegativeText,
                                  DialogInterface.OnClickListener positiveListener,
                                  DialogInterface.OnClickListener negativeListener) {
        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(positiveText, positiveListener);
        dialog.setNegativeButton(NegativeText, negativeListener);
        dialog.setCancelable(false);
        dialog.show();
    }
}
