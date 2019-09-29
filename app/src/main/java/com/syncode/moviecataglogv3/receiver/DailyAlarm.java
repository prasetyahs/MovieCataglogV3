package com.syncode.moviecataglogv3.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.syncode.moviecataglogv3.R;
import com.syncode.moviecataglogv3.reminder.Alarm;

public class DailyAlarm extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Alarm.showNotification("Daily", context, context.getResources().getString(R.string.message1), 1, "Alarm", "ch_1",3);
    }
}
