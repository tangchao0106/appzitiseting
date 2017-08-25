package com.xc.theme;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/***
 * 所有需要跟随主题改变的类都继承此基类
 **/
public class BaseThemeActivity extends AppCompatActivity {

    /**
     * 保存当前使用的主题ID
     */
    private int mCurrentThemeId;

    /**
     * 此方法会在onCreate方法之前被系统执行
     *
     * @param resid
     */
    @Override
    public void setTheme(@StyleRes int resid) {
        int savedTheme = ThemeManager.getTheme(this);
        if (savedTheme > 0 && savedTheme != resid) {
            resid = savedTheme;
        }
        Log.e("print","setTheme before onCreate");
        mCurrentThemeId = resid;
        super.setTheme(resid);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            /**不依赖外部调用,通过系统回调函数当从设置页面返回就会自动调用*/
            ThemeManager.recreateIfThemeChanged(this, mCurrentThemeId);
        }
    }

    public static class ThemeManager {

        public static SharedPreferences getThemeSp(Activity context) {
            return context.getSharedPreferences("themes", MODE_PRIVATE);
        }

        public static int getTheme(Activity context) {
            return getThemeSp(context).getInt("savedTheme", -1);
        }

        /**
         * @param context    Activity对象
         * @param resid      主题的资源id
         * @param isRecreate 保存设置的主题后是否需要重新启动
         */
        public static void setTheme(Activity context, @StyleRes final int resid, boolean isRecreate) {
            if (resid > 0) {
                getThemeSp(context).edit().putInt("savedTheme", resid).commit();
                if (isRecreate) {
                    context.recreate();
                }
            }
        }

        public static void recreateIfThemeChanged(Activity context, int mCurrentThemeId) {
            int savedTheme = getTheme(context);
            if (savedTheme > 0 && savedTheme != mCurrentThemeId) {
                context.recreate();
            }
        }
    }
}
