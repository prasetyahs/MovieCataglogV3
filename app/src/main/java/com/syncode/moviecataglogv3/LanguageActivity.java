package com.syncode.moviecataglogv3;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.syncode.moviecataglogv3.localdata.SharedPreference;

public class LanguageActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        sharedPreference = new SharedPreference(this);
        radioGroup = findViewById(R.id.rb_group);
        if (sharedPreference.getReferences("lang").toLowerCase().trim().equals("id")) {
            RadioButton rbBahasa = radioGroup.findViewById(R.id.rb_bahasa);
            rbBahasa.setChecked(true);
        } else {
            RadioButton rbEnglish = radioGroup.findViewById(R.id.rb_english);
            rbEnglish.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_bahasa:
                        sharedPreference.setPref("id", "id");
                        break;
                    case R.id.rb_english:
                        sharedPreference.setPref("en-US", "en");
                        break;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
