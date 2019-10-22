package com.love.juae.drumexersie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ArrayList<View> bitlist = new ArrayList<>();
    TextView tv_speed;
    Button btn_up, btn_down;
    int bpm = 60, second = 1000;
    //(BPM / 기준 BPM) * (Tempo / 기준 Temp), 이 식으로 얻은 값은 BPM을 박자로 나눈 초 값이며,
    // 제작하는 프로그램에서는 값 만큼 초를 움직여주면 된다. 대개 기준 BPM은 60을 잡는 것이 편하다. 60BPM, 4/4박자 = 1초
    private TimerTask mTask;
    private Timer mTimer;
    private int note = 4;
    private int task_num = 0;
    private ArrayList<View> view_list = new ArrayList<>();
    private boolean timer_start = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bitlist.add((View) findViewById(R.id.bit_1));
        bitlist.add((View) findViewById(R.id.bit_2));
        bitlist.add((View) findViewById(R.id.bit_3));
        bitlist.add((View) findViewById(R.id.bit_4));

        tv_speed = (TextView) findViewById(R.id.tv_speed);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_down = (Button) findViewById(R.id.btn_down);
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bpm += 5;
                second -= 83.33;
                tv_speed.setText(String.valueOf(bpm));
                mTimer = new Timer();
                if(timer_start) {
                    mTask.cancel();
                }else{
                    timer_start = true;
                }
                start_newtask();
            }
        });

        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bpm > 5) {
                    bpm -= 5;
                    second += 83.33;
                    tv_speed.setText(String.valueOf(bpm));
                    mTimer = new Timer();
                    if(timer_start) {
                        mTask.cancel();
                    }else{
                        timer_start = true;
                    }
                    start_newtask();
                }
            }
        });

   /*     new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for(i = 0; i<=100; i++) {
                    // 현재 UI 스레드가 아니기 때문에 메시지 큐에 Runnable을 등록 함
                    runOnUiThread(new Runnable() {
                        public void run() {
                            // 메시지 큐에 저장될 메시지의 내용
                            bitlist.get(0).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    });
                }
            }
        }).start();
*/
    }

    private void start_newtask() {
        task_num = 0;
        mTask = new TimerTask() {
            @Override
            public void run() {
                if (task_num == note) {
                    task_num = 0;

                }
                Log.e("", "task_num : " + task_num);
                int bef_num;
                if (task_num == 0) {
                    bef_num = note - 1;
                } else {
                    bef_num = task_num - 1;
                }
                bitlist.get(bef_num).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bitlist.get(task_num).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                task_num += 1;
            }
        };
        mTimer.schedule(mTask, 0, second);
    }
}
