package com.example.degreeschedulerapp1;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.degreeschedulerapp1.databinding.ActivityDownloadBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


public class Download extends AppCompatActivity {
    //Initializing variables
    ActivityDownloadBinding binding;
    private static final int PERMISSION_STORAGE_CODE = 100;
    Button downloadBtn, loadBtn;
    TextView termCond, classView;
    CheckBox consent;
    String fileName = "Schedule";
    String content = "";
    //String yourFilePath = context.getFilesDir() + "/" + "File.txt";
    //File textUrl = new File( yourFilePath );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDownloadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //initialize views with xml
        downloadBtn = findViewById(R.id.download_Btn);
        loadBtn = findViewById(R.id.load_Btn);
        termCond = findViewById(R.id.termsConditions);
        consent = findViewById(R.id.consentBox);
        classView = (TextView)findViewById(R.id.classText);
        classView.setMovementMethod(new ScrollingMovementMethod());

        binding.termsConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_dialog();
            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readText();
            }
        });
        //handle button click
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(classView.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Click the Load button to enter class information", Toast.LENGTH_SHORT).show();
                }
                else {
                    String content = classView.getText().toString();
                    if (consent.isChecked()) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                                //permission denied, request it
                                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                                requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                            } else {
                                //permission already granted
                                saveText(fileName, content);
                            }
                        } else {
                            //system os is less than marshmallow
                            saveText(fileName, content);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Check the box to proceed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    //handle permission result
    private void readText(){
        File file = new File( "/data/data/com.example.degreescheduler/files/File.txt");
        if (!file.exists()){
            Toast.makeText(getApplicationContext(), "Please add classes using create schedule", Toast.LENGTH_SHORT).show();
        }
        String line = null;
        //Read text from file
        //StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error when reading the file", Toast.LENGTH_SHORT).show();
        }
        loadText(line);
    }


    private void loadText(String line){
        if(line.length()>0){
            classView.setText(line);
        }
    }

    private void saveText(String fileName, String content){
        String filename = fileName + ".txt";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);

        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this,"Saved!",Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this,"File not found!",Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Error saving the file!",Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                    //permission granted from popup, performed download
                    saveText(fileName, content);
                }
                else{
                    //permission denied from popup, show error message
                    Toast.makeText(this, "Permission denied..!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void show_dialog()
    {
        AlertDialog.Builder alert= new AlertDialog.Builder(this);

        WebView wv = new WebView(this);
        wv.loadUrl("file:///android_asset/TermsCondition.html");
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
