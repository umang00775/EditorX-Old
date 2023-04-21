package com.umang_rathod.editorx;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umang_rathod.editorx.screens.Dashboard;
import com.umang_rathod.editorx.screens.Signin;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tv = findViewById(R.id.parent_company);
        Shader textShader=new LinearGradient(0, 0, tv.getPaint().measureText(tv.getText().toString()), tv.getTextSize(),
                new int[]{Color.RED,Color.MAGENTA},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        tv.getPaint().setShader(textShader);

        Animation animate = AnimationUtils.loadAnimation(this,R.anim.animation);
        TextView appName = findViewById(R.id.app_name);
        appName.setAnimation(animate);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Signin.class);
                Pair pair = new Pair<View, String>(appName, "transition");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pair);
                    startActivity(intent, options.toBundle());
                }
            }
        },SPLASH_SCREEN);
    }
}
