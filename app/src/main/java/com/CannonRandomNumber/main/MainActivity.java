package com.CannonRandomNumber.main;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    EditText editText_N;
    Button button_next,button_night;
    CheckBox checkBox_spanking;
    RelativeLayout relativeLayout;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_N = findViewById(R.id.EditText_N);
        button_next = findViewById(R.id.Button_next);
        checkBox_spanking = findViewById(R.id.Checkbox_spanking);
        button_night = findViewById(R.id.Button_night);
        relativeLayout = findViewById(R.id.lobby_background);

        button_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == false) {
                    relativeLayout.setBackgroundResource(R.drawable.lobby_night);
                    flag = true;
                } else {
                    relativeLayout.setBackgroundResource(R.drawable.lobby);
                    flag = false;
                }
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int N = Integer.parseInt(editText_N.getText().toString());

                Intent intent = new Intent(MainActivity.this, CannonActivity.class);
                intent.putExtra("N", N);//숫자값 보내주기
                intent.putExtra("night", flag);

                if (checkBox_spanking.isChecked() == true) { //중복제거 값을 보내주기
                    intent.putExtra("spanking", true);
                } else {
                    intent.putExtra("spanking", false);
                }

                startActivity(intent);//화면전환
            }
        });
    }
}