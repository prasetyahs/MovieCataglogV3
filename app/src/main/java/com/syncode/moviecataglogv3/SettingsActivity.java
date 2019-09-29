package com.syncode.moviecataglogv3;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.syncode.moviecataglogv3.localdata.SharedPreference;
import com.syncode.moviecataglogv3.receiver.DailyAlarm;
import com.syncode.moviecataglogv3.receiver.ReleaseAlarm;
import com.syncode.moviecataglogv3.reminder.Alarm;

public class SettingsActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    private SharedPreference sharedPreference;
    Switch dailyAlarm, switchRelease;

    private final static int REQUEST_CODE_DAILY = 10;
    private final static int REQUEST_CODE_RELEASE = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        sharedPreference = new SharedPreference(this);
        switchRelease = findViewById(R.id.switch_release);
        dailyAlarm = findViewById(R.id.switch_daily);
        radioGroup = findViewById(R.id.rb_group);
        if (sharedPreference.getReferencesLang("lang").toLowerCase().trim().equals("id")) {
            RadioButton rbBahasa = radioGroup.findViewById(R.id.rb_bahasa);
            rbBahasa.setChecked(true);
        } else {
            RadioButton rbEnglish = radioGroup.findViewById(R.id.rb_english);
            rbEnglish.setChecked(true);
        }
        dailyAlarm.setChecked(sharedPreference.getAlarmRelease("daily"));
        switchRelease.setChecked(sharedPreference.getAlarmRelease("release"));
        dailyAlarm.setOnCheckedChangeListener((compoundButton, b) -> {
            sharedPreference.setPrefAlarmDaily(b);
            if (b) {
                Alarm.setAlarmManager(SettingsActivity.this, 7, 0, 0, REQUEST_CODE_DAILY, DailyAlarm.class);
            } else {
                Alarm.cancelNotification(SettingsActivity.this, REQUEST_CODE_DAILY, DailyAlarm.class);
            }
        });

        switchRelease.setOnCheckedChangeListener((compoundButton, b) -> {
            sharedPreference.setPrefAlarmRelease(b);
            if (b) {
                Alarm.setAlarmManager(SettingsActivity.this, 8, 0, 0, REQUEST_CODE_RELEASE, ReleaseAlarm.class);
            } else {
                Alarm.cancelNotification(SettingsActivity.this, REQUEST_CODE_RELEASE, ReleaseAlarm.class);
            }
        });


        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rb_bahasa:
                    sharedPreference.setPrefLang("id", "id");
                    break;
                case R.id.rb_english:
                    sharedPreference.setPrefLang("en-US", "en");
                    break;
            }
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
