package com.umang_rathod.editorx.screens;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.umang_rathod.editorx.R;

import java.util.Date;


public class Signin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        CardView openCal = findViewById(R.id.openCalendar);
        MaterialButton nextBtn = findViewById(R.id.submit_btn);

        setLogoAnimation();

        openCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Date birthDate = openCal();
                    Log.d("BD", birthDate.toString());
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), OtpVerification.class);
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);

                if(true){
                    Toast.makeText(Signin.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                } else{

                    Toast.makeText(Signin.this, "FAILURE", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Date openCal(){
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        final int[] year = {calendar.get(Calendar.YEAR)};
        final int[] month = {calendar.get(Calendar.MONTH)};
        final int[] day = {calendar.get(Calendar.DAY_OF_MONTH)};

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                R.style.CustomDatePickerDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int _year, int _month, int _dayOfMonth) {
                        year[0] = _year;
                        month[0] = _month;
                        day[0] = _dayOfMonth;
                    }
                }, year[0], month[0], day[0]);

        datePickerDialog.show();

        String dateString = "" + year[0] + "-" + (month[0]) + "-" + day[0];
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateString);

        TextView showBD = findViewById(R.id.birthdate);
        showBD.setText("" + day[0] + " / " + (month[0]+1) + " / " + year[0]);

        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException | java.text.ParseException e) {
            e.printStackTrace();
        }


        return date;
    }

    void setLogoAnimation(){
        final TextView appName = findViewById(R.id.app_name);
        int[] colors = {Color.RED, Color.GREEN, Color.BLUE};
        ValueAnimator colorAnim = ObjectAnimator.ofInt(appName, "textColor", colors);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.start();
    }

}