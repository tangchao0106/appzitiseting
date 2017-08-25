package com.xc.theme;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends BaseThemeActivity implements View.OnClickListener {
    Button btnSmall;
    Button btnNormal;
    Button btnLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSmall = (Button) findViewById(R.id.btnSmall);
        btnSmall.setOnClickListener(this);
        btnNormal = (Button) findViewById(R.id.btnNormal);
        btnNormal.setOnClickListener(this);
        btnLarge = (Button) findViewById(R.id.btnLarge);
        btnLarge.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSmall) {
            ThemeManager.setTheme(this, R.style.AppTheme_Smallsize, false);
            btnSmall.setTextSize(12);
            btnNormal.setTextSize(14);
            btnLarge.setTextSize(16);
        } else if (view == btnNormal) {
            ThemeManager.setTheme(this, R.style.AppTheme_NormalSize, false);
            btnSmall.setTextSize(16);
            btnNormal.setTextSize(18);
            btnLarge.setTextSize(20);
        } else if (view == btnLarge) {
            ThemeManager.setTheme(this, R.style.AppTheme_LargeSize, false);
            btnSmall.setTextSize(20);
            btnNormal.setTextSize(22);
            btnLarge.setTextSize(24);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
