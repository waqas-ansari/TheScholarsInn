package com.arktech.waqasansari.thescholarsinn;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ActivityTweets extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        WebView tweetView = (WebView) findViewById(R.id.tweetView);
        tweetView.getSettings().setDomStorageEnabled(true);
        tweetView.getSettings().setJavaScriptEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progress);

        tweetView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(ActivityTweets.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        String summary = "<a class=\"twitter-timeline\" href=\"https://twitter.com/thescholarsinn\">Tweets by thescholarsinn</a> <script async src=\"//platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";
        tweetView.loadDataWithBaseURL("https://twitter.com", summary, "text/html", "UTF-8", null);

    }
}
