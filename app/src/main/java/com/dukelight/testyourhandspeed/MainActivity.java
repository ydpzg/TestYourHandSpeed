package com.dukelight.testyourhandspeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTvClickArea;
    private TextView mTvCount;
    private TextView mTvInterval;
    private SeekBar mSbCount;
    private SeekBar mSbInterval;

    // 默认值
    private int mCount = 5;
    private int mInterval = 300;

    public static final int COUNT_MIN_VALUE = 5;
    public static final int INTERVAL_MIN_VALUE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvClickArea = findViewById(R.id.tv_click_area);
        mTvCount = findViewById(R.id.tv_count);
        mTvInterval = findViewById(R.id.tv_interval);
        mSbCount = findViewById(R.id.sb_count);
        mSbInterval = findViewById(R.id.sb_interval);


        mSbCount.setMax(10);
        mSbCount.setProgress(5);
        mCount = COUNT_MIN_VALUE + 5;
        mTvCount.setText(String.format("%d次", mCount));

        mSbCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mCount = seekBar.getProgress();
                mCount = COUNT_MIN_VALUE + seekBar.getProgress();
                mTvCount.setText(String.format("%d次", mCount));
                setMagicEvent();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbInterval.setMax(400);
        mSbInterval.setProgress(200);
        mInterval = INTERVAL_MIN_VALUE + 200 * 2;
        mTvInterval.setText(String.format("%d毫秒", mInterval));

        mSbInterval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mInterval = seekBar.getProgress();
                mInterval = INTERVAL_MIN_VALUE + seekBar.getProgress() * 2;
                mTvInterval.setText(String.format("%d毫秒", mInterval));
                setMagicEvent();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setMagicEvent();
    }

    private void setMagicEvent() {
        ViewHelper.addViewClickMagicalEvent(mTvClickArea, mCount, mInterval, new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "恭喜你，触发成功!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
