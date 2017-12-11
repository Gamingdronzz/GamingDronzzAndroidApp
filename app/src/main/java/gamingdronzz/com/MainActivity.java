package gamingdronzz.com;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    final String removeMenu = "document.getElementsByClassName('navbar-toggle collapsed')[0].style.display=\"none\"; ";
    final String removeContact = "document.getElementsByClassName('w3l_contact')[0].style.display=\"none\"; ";
    final String removeFooter = "document.getElementsByClassName('footer ')[0].style.display=\"none\"; ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        setupWebview();
        webView.loadUrl("http://www.gamingdronzz.com/");
    }

    private void setupWebview() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @RequiresApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    Log.d("Url", url);
                    view.loadUrl(url);
                }
                return true;
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(getApplicationContext(), "We are getting things fixed..", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });

        webView.setWebChromeClient
                (
                        new WebChromeClient() {
                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("javascript:");
                                stringBuilder.append("(function() { ");

                                stringBuilder.append(removeMenu);
                                stringBuilder.append(removeContact);
                                stringBuilder.append(removeFooter);
                                stringBuilder.append("} ) ()");
                                view.loadUrl(stringBuilder.toString());

                            }

                        }
                );
    }

}
