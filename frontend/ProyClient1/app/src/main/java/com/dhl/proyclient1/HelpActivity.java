/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dhl.proyclient1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class HelpActivity extends AppCompatActivity {

    WebView myWebView ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar  toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSeDfaL2yGeH2_6wFmbEz6T8cquEIUnHtmXGGPFPAR3UEAtdAA/viewform?c=0&w=1");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
}
