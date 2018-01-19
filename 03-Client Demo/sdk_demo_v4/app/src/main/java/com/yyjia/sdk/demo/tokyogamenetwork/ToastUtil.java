package com.yyjia.sdk.demo.tokyogamenetwork;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Toast弹出提示框工具类，用于简化Toast提示代码
 * 
 */
public class ToastUtil {

	private static Toast toast;
	private static View view;

	private ToastUtil() {
	}

	@SuppressLint("ShowToast")
	private static void getToast(Context context) {
		if (toast == null) {
			toast = new Toast(context);
		}
		if (view == null) {
			view = Toast.makeText(context, "", Toast.LENGTH_SHORT).getView();
		}
		toast.setView(view);
	}

	public static void showShortToast(Context context, CharSequence msg) {
		showToast(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
	}

	public static void showShortToast(Context context, int resId) {
		showToast(context.getApplicationContext(), resId, Toast.LENGTH_SHORT);
	}

	public static void showLongToast(Context context, CharSequence msg) {
		showToast(context.getApplicationContext(), msg, Toast.LENGTH_LONG);
	}

	public static void showLongToast(Context context, int resId) {
		showToast(context.getApplicationContext(), resId, Toast.LENGTH_LONG);
	}

	private static void showToast(Context context, CharSequence msg,
			int duration) {
		try {
			getToast(context);
			toast.setText(msg);
			toast.setDuration(duration);
			toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 100);
			toast.show();
		} catch (Exception e) {
			// Utils.E(e.getMessage());
		}
	}

	private static void showToast(Context context, int resId, int duration) {
		try {
			if (resId == 0) {
				return;
			}
			getToast(context);
			toast.setText(resId);
			toast.setDuration(duration);
			// toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 100);
			toast.show();
		} catch (Exception e) {
			// Utils.E(e.getMessage());
		}
	}

	// /**
	// * 默认方式显示Toast
	// *
	// * @param context
	// * 指定的Context实例
	// * @param message
	// * Toast显示的文本信息
	// */
	// public static void showToastBottom(Context context, String message) {
	// Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
	// toast.setGravity(Gravity.CENTER, 0, 0);
	// toast.show();
	// }
	//
	// /**
	// * 默认方式显示Toast
	// *
	// * @param context
	// * 指定的Context实例
	// * @param resId
	// * Toast显示的文本信息字符串资源
	// */
	// public static void showToastBottom(Context context, int resId) {
	// Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
	// toast.setGravity(Gravity.CENTER, 0, 0);
	// toast.show();
	// }
	//
	// /**
	// * 显示Toast
	// *
	// * @param context
	// * 指定的Context实例
	// * @param resId
	// * Toast显示的文本信息字符串资源
	// * @param time
	// * 设置Toast停留的时间
	// */
	// public static void showToastBottom(Context context, int resId, int time)
	// {
	// Toast toast = Toast.makeText(context, resId, time);
	// toast.setGravity(Gravity.CENTER, 0, 0);
	// toast.show();
	// }

	// /**
	// * 默认方式显示Toast
	// *
	// * @param context
	// * 指定的Context实例
	// * @param message
	// * Toast显示的文本信息
	// */
	// public static void showToastBottom(Context context, String message) {
	// Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
	// toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 100);
	// toast.show();
	// }
	//
	// /**
	// * 默认方式显示Toast
	// *
	// * @param context
	// * 指定的Context实例
	// * @param resId
	// * Toast显示的文本信息字符串资源
	// */
	// public static void showToastBottom(Context context, int resId) {
	// Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
	// toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 100);
	// toast.show();
	// }
	//
	// /**
	// * 显示Toast
	// *
	// * @param context
	// * 指定的Context实例
	// * @param resId
	// * Toast显示的文本信息字符串资源
	// * @param time
	// * 设置Toast停留的时间
	// */
	// public static void showToastBottom(Context context, int resId, int time)
	// {
	// Toast toast = Toast.makeText(context, resId, time);
	// toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 100);
	// toast.show();
	// }

}