package com.umang_rathod.editorx.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.umang_rathod.editorx.R;
import com.umang_rathod.editorx.backend.ApiCalls;
import com.umang_rathod.editorx.backend.Colorization;
import com.umang_rathod.editorx.backend.ColorizationPost;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class ShowImage extends AppCompatActivity {

    StorageReference storageReference;
    Uri imageUri;
    ProgressDialog progressDialog;
    private String uploadedImageUrl;
    private final String BASE_URL = "https://techhk.aoscdn.com/api/tasks/visual/";
    private ApiCalls apiCalls;
    TextView debugHere;
    ImageView showImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        debugHere = findViewById(R.id.debug_here);

        setLogoAnimation();

        Intent intent = getIntent();
        String message = intent.getStringExtra("IMAGE_URI");
        showImage = findViewById(R.id.show_image);
        imageUri = Uri.parse(message);
        Glide.with(this)
                .load(imageUri)
                .into(showImage);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCalls = retrofit.create(ApiCalls.class);




        MaterialButton submit = findViewById(R.id.submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               uploadImage();

//                File imageFile = new File(getPath(imageUri));
//                debugHere.setText(imageFile.toString());
//                getColoredmage(imageFile);
//
//                Response response = Request.Post("https://api.remove.bg/v1.0/removebg")
//                        .addHeader("X-Api-Key", "INSERT_YOUR_API_KEY_HERE")
//                        .body(
//                                MultipartEntityBuilder.create()
//                                        .addBinaryBody("image_file", new File("/path/to/file.jpg"))
//                                        .addTextBody("size", "auto")
//                                        .build()
//                        ).execute();
//                response.saveContent(new File("no-bg.png"));


            }
        });

    }

    public String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
    }

    private void getColoredmage(String imageFile) {
        Toast.makeText(this, "GET ENHANCED", Toast.LENGTH_SHORT).show();
        
        Call<Colorization> call = apiCalls.getColoredImage("wxrwy9l7h6hsyscre", imageFile, 1);


        call.enqueue(new Callback<Colorization>() {

            @Override
            public void onResponse(Call<Colorization> call, Response<Colorization> response) {
                debugHere.setText("ENTERED TO CALL...");
                if (!response.isSuccessful()){
                    debugHere.setText(response.code());
                    return;
                }
                Colorization received = response.body();

            }

            @Override
            public void onFailure(Call<Colorization> call, Throwable t) {
                debugHere.setText(t.toString());
            }
        });


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

    private void uploadImage(){

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Uploading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/" + fileName);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(ShowImage.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }

                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                uploadedImageUrl = uri.toString();
                                debugHere.setText(uploadedImageUrl);
                            }
                        });
//                        getColoredmage(uploadedImageUrl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ShowImage.this, "Upload failed", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}