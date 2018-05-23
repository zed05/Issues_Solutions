package com.example.zed.issues_solutions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

public class NewsDetailActivity extends AppCompatActivity {

    WebView wv;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);



        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        wv = findViewById(R.id.wvNews);

        Intent intent = getIntent();
        String url = intent.getStringExtra("newsLink");

        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient()); // Hiển thị web ngay trên app, người dùng có thể click link khác mà không thoát khỏi app

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true); // Cho phép chạy JS trên app
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
