package com.example.aditya.browsera;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urledit;
    Button go, forward, back, refresh, clear;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        brow = (WebView) findViewById(R.id.wv_brow);
        urledit = (EditText) findViewById(R.id.et_url);
        go = (Button) findViewById(R.id.btn_go);
        forward = (Button) findViewById(R.id.btn_fwd);
        back = (Button) findViewById(R.id.btn_back);
        refresh = (Button) findViewById(R.id.btn_ref);
        clear = (Button) findViewById(R.id.btn_clr);

        brow.setWebViewClient(new ourViewClient());
        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress ==100) {
                    progressBar.setVisibility(View.GONE );

                }else{progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        WebSettings websetting = brow.getSettings();
        websetting.setJavaScriptEnabled(true);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editextValue = urledit.getText().toString();
                if (!editextValue.startsWith("https://"))
                    editextValue = "https://" + editextValue;


                String url = editextValue;
                brow.loadUrl(url);

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (brow.canGoForward())
                    brow.goForward();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (brow.canGoBack())
                    brow.goBack();

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brow.reload();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brow.clearHistory();

            }
        });

    }
}
