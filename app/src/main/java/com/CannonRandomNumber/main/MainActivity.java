package com.CannonRandomNumber.main;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    EditText editText_N;
    Button button_next,button_night,easter_egg;
    CheckBox checkBox_spanking;
    ConstraintLayout constraintLayout;
    boolean flag = false;
    private AdView mAdview; //애드뷰 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_N = (EditText) findViewById(R.id.EditText_N);
        button_next = (Button) findViewById(R.id.Button_next);
        checkBox_spanking = (CheckBox) findViewById(R.id.Checkbox_spanking);
        button_night = (Button) findViewById(R.id.Button_night);
        constraintLayout = (ConstraintLayout) findViewById(R.id.lobby_background);
        easter_egg = (Button) findViewById(R.id.easter_egg);


        easter_egg.setOnClickListener(new View.OnClickListener() {
            int easter = 0;
            @Override
            public void onClick(View view) {
                easter++;;
                if (easter == 3) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cjmpm.tistory.com/category/%EB%8C%80%ED%8F%AC%EB%BD%91%EA%B8%B0%20%EB%AA%A8%EB%B0%94%EC%9D%BC"));
                    startActivity(intent);
                }
            }
        });

        button_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == false) {
                    constraintLayout.setBackgroundResource(R.drawable.lobby_night);
                    flag = true;
                } else {
                    constraintLayout.setBackgroundResource(R.drawable.lobby);
                    flag = false;
                }
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
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
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해 주세요", Toast.LENGTH_SHORT);
                }
            }
        });


        MobileAds.initialize(this, new OnInitializationCompleteListener() { //광고 초기화
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdview = findViewById(R.id.adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER); //광고 사이즈는 배너 사이즈로 설정
        adView.setAdUnitId("\n" + "ca-app-pub-3940256099942544~3347511713");
    }
}