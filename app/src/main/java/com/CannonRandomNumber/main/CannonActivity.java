package com.CannonRandomNumber.main;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class CannonActivity extends AppCompatActivity {

    TextView textView_result;
    Button button_shot;
    ImageView green_ball;
    SoundPool soundPool;
    ConstraintLayout constraintLayout;
    int soundID;
    private AdView mAdview; //애드뷰 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon);

        textView_result = (TextView) findViewById(R.id.TextView_result);
        button_shot = (Button) findViewById(R.id.Button_shot);
        green_ball = (ImageView) findViewById(R.id.greenball);
        constraintLayout = (ConstraintLayout) findViewById(R.id.cannon_background);


        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();//값 넘겨받기

        final int N = bundle.getInt("N");
        final boolean spanking = bundle.getBoolean("spanking");
        boolean night = bundle.getBoolean("night");
        final Random random = new Random();
        final int array[] = new int[N];
        final int[] A = {0};
        final Animation shot1 = AnimationUtils.loadAnimation(this,R.anim.ball_start);
        final int[] CHECK = {0};
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        soundID = soundPool.load(this,R.raw.cann_soun,0);

        if (night == true) {
            constraintLayout.setBackgroundResource(R.drawable.cannon_night);
        } else {
            constraintLayout.setBackgroundResource(R.drawable.cannon);
        }



        for (int i = 0; i <= N-1; i++) {
            array[i] = random.nextInt(N) + 1;
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j]) {
                    i--;
                }
            }
        }

        button_shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                green_ball.startAnimation(shot1);
                textView_result.setText("");
                soundPool.play(soundID,1f,1f,0,0,1f);


                if (CHECK[0] == A[0]) {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {//딜레이 넣을 공간

                        if (spanking == false) { //중복체거 체크가 해제되있을 경우
                            int result = random.nextInt(N)+1;
                            textView_result.setText("" + result);
                        } else { //중복제거 체크가 설정되있을 경우
                            if (A[0] <= N) {
                                textView_result.setText("" + array[A[0]]);
                            }else if (A[0] > N){
                                Intent intent = new Intent(CannonActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                        A[0]++;

                    }
                }, 1500); // 딜레이 시간
                } else {
                    A[0]++;
                }
                CHECK[0]++;

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