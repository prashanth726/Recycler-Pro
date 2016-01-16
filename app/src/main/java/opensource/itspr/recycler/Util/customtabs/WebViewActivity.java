package opensource.itspr.recycler.Util.customtabs;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import opensource.itspr.recycler.MyApplication;
import opensource.itspr.recycler.R;

public class WebViewActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "extra.url";
  private ProgressBar progressBar;
  private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String url = getIntent().getStringExtra(EXTRA_URL);
      progressBar = (ProgressBar) findViewById(R.id.progressBar);
      progressBar.setMax(100);
        webView = (WebView)findViewById(R.id.web_view);
       // webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
      webSettings.setDomStorageEnabled(true);
      webView.setWebViewClient(new WebViewClientDemo());
      webView.setWebChromeClient(new WebChromeClientDemo());
        setTitle(url);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

  private class WebViewClientDemo extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      view.loadUrl(url);
      return true;
    }
    @Override
    public void onPageFinished(WebView view, String url) {
      super.onPageFinished(view, url);
      progressBar.setVisibility(View.GONE);
      progressBar.setProgress(100);
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      super.onPageStarted(view, url, favicon);
      progressBar.setVisibility(View.VISIBLE);
      Toast.makeText(MyApplication.getAppContext(), "Loading..", Toast.LENGTH_SHORT).show();
      progressBar.setProgress(0);
    }
  }

  private class WebChromeClientDemo extends WebChromeClient {

    public void onProgressChanged(WebView view, int progress) {
      progressBar.setProgress(progress);

    }
  }


  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
      webView.goBack();
      return true;
    }
    else {
      finish();
    }
    return super.onKeyDown(keyCode, event);
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return true;
  }
}