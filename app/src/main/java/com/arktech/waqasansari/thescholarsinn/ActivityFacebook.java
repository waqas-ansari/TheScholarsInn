package com.arktech.waqasansari.thescholarsinn;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class ActivityFacebook extends AppCompatActivity {
    ProgressBar progressBar;
    WebView facebookView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);


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

        progressBar = (ProgressBar) findViewById(R.id.progress);

        facebookView = (WebView) findViewById(R.id.tweetView);
        facebookView.getSettings().setDomStorageEnabled(true);
        facebookView.getSettings().setJavaScriptEnabled(true);

        facebookView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_SCROLL;
            }
        });



        facebookView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(ActivityFacebook.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        /*
        String summary = "<div id=\"fb-root\"></div>\n" +
                "<script>(function(d, s, id) {\n" +
                "  var js, fjs = d.getElementsByTagName(s)[0];\n" +
                "  if (d.getElementById(id)) return;\n" +
                "  js = d.createElement(s); js.id = id;\n" +
                "  js.src = \"//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.6\";\n" +
                "  fjs.parentNode.insertBefore(js, fjs);\n" +
                "}(document, 'script', 'facebook-jssdk'));</script><div class=\"fb-page\" data-href=\"https://facebook.com/thescholarsinn\" data-tabs=\"timeline\" data-small-header=\"false\" data-adapt-container-width=\"true\" data-hide-cover=\"false\" data-show-facepile=\"false\"><blockquote cite=\"https://facebook.com/thescholarsinn\" class=\"fb-xfbml-parse-ignore\"><a href=\"https://facebook.com/thescholarsinn\">Scholars&#039; Inn Coaching System</a></blockquote></div>";
        facebookView.loadDataWithBaseURL("https://facebook.com", summary, "text/html", "UTF-8", null);
        */
        facebookView.loadUrl("https://touch.facebook.com/thescholarsinn");

    }


    @Override
    public void onBackPressed() {
        if(facebookView.canGoBack())
            facebookView.goBack();
        else super.onBackPressed();
    }
}
