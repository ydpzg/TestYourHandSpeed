package com.dukelight.testyourhandspeed;

import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YUDAPEI on 17/3/9.
 */

public class ViewHelper {
    private static Map<Integer, Integer> sClickCountMap = new HashMap<Integer, Integer>();
    private static Map<Integer, Long> sClickTimeMap = new HashMap<Integer, Long>();

    /**
     * 为某个view设置点击多少次做动作，神奇事件
     *
     * @param view  界面
     * @param count 点击次数
     * @param interval 相隔的毫秒数
     */
    public static void addViewClickMagicalEvent(View view, final int count, final int interval, final Runnable runnable) {
        sClickCountMap.put(view.getId(), 0);
        sClickTimeMap.put(view.getId(), 0L);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        int tmpCount = sClickCountMap.get(view.getId());
                        long tmpTime = sClickTimeMap.get(view.getId());
                        long nowTime = System.currentTimeMillis();
                        if (tmpTime < nowTime - interval) {
                            sClickCountMap.put(view.getId(), 1);
                            tmpCount = 1;
                        } else {
                            if (tmpCount < count) {
                                tmpCount++;
                            }
                            if (tmpCount >= count) {
                                runnable.run();
                                tmpCount = 0;
                            }
                        }
                        sClickCountMap.put(view.getId(), tmpCount);
                        sClickTimeMap.put(view.getId(), nowTime);

                        break;
                    default:
                }
                return false;
            }
        });
    }
}
