package com.example.administrator.jingjiren;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
   // public static Tag = "WEBVIEW";
   WebView webView ;
   String TAG= "webviewPage";
    boolean flag = false;
    private final int STOP_SPLASH = 0;
    private final int SPLASH_TIME = 3000;
    private LinearLayout splashLt;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
    private long timeGetTime = new Date().getTime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.main_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(false);
        webView.clearCache(true);
        webView.addJavascriptInterface(this, "youtubedown");
        WebSettings settings = webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.setLoadWithOverviewMode(true);                             // setUseWideViewPort方法设置webview推荐使用的窗口。setL
        settings.setUseWideViewPort(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName("UTF-8");
        WebView.setWebContentsDebuggingEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.d("webview", "onKeyDown: onProgressChanged="+newProgress);

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("webview", "onKeyDown: onReceivedTitle="+title);
                webView.loadUrl("javascript:var $jscomp=$jscomp||{};$jscomp.scope={};$jscomp.createTemplateTagFirstArg=function(a){return a.raw=a};$jscomp.createTemplateTagFirstArgWithRaw=function(a,b){a.raw=b;return a};\n" +
                        "var jsVarStr=\"[a-zA-Z_\\\\$][a-zA-Z_0-9]*\",jsSingleQuoteStr=\"'[^'\\\\\\\\]*(:?\\\\\\\\[\\\\s\\\\S][^'\\\\\\\\]*)*'\",jsDoubleQuoteStr='\"[^\"\\\\\\\\]*(:?\\\\\\\\[\\\\s\\\\S][^\"\\\\\\\\]*)*\"',jsQuoteStr=\"(?:\"+jsSingleQuoteStr+\"|\"+jsDoubleQuoteStr+\")\",jsKeyStr=\"(?:\"+jsVarStr+\"|\"+jsQuoteStr+\")\",jsPropStr=\"(?:\\\\.\"+jsVarStr+\"|\\\\[\"+jsQuoteStr+\"\\\\])\",jsEmptyStr=\"(?:''|\\\"\\\")\",reverseStr=\":function\\\\(a\\\\)\\\\{(?:return )?a\\\\.reverse\\\\(\\\\)\\\\}\",sliceStr=\":function\\\\(a,b\\\\)\\\\{return a\\\\.slice\\\\(b\\\\)\\\\}\",spliceStr=\":function\\\\(a,b\\\\)\\\\{a\\\\.splice\\\\(0,b\\\\)\\\\}\",\n" +
                        "swapStr=\":function\\\\(a,b\\\\)\\\\{var c=a\\\\[0\\\\];a\\\\[0\\\\]=a\\\\[b(?:%a\\\\.length)?\\\\];a\\\\[b(?:%a\\\\.length)?\\\\]=c(?:;return a)?\\\\}\",actionsObjRegexp=new RegExp(\"var (\"+jsVarStr+\")=\\\\{((?:(?:\"+jsKeyStr+reverseStr+\"|\"+jsKeyStr+sliceStr+\"|\"+jsKeyStr+spliceStr+\"|\"+jsKeyStr+swapStr+\"),?\\\\r?\\\\n?)+)\\\\};\"),actionsFuncRegexp=new RegExp(\"function(?: \"+jsVarStr+\")?\\\\(a\\\\)\\\\{a=a\\\\.split\\\\(\"+(jsEmptyStr+\"\\\\);\\\\s*((?:(?:a=)?\")+jsVarStr+jsPropStr+\"\\\\(a,\\\\d+\\\\);)+)return a\\\\.join\\\\(\"+(jsEmptyStr+\"\\\\)\\\\}\")),reverseRegexp=\n" +
                        "new RegExp(\"(?:^|,)(\"+jsKeyStr+\")\"+reverseStr,\"m\"),sliceRegexp=new RegExp(\"(?:^|,)(\"+jsKeyStr+\")\"+sliceStr,\"m\"),spliceRegexp=new RegExp(\"(?:^|,)(\"+jsKeyStr+\")\"+spliceStr,\"m\"),swapRegexp=new RegExp(\"(?:^|,)(\"+jsKeyStr+\")\"+swapStr,\"m\");\n" +
                        "function setParse(a){var b=actionsObjRegexp.exec(a),d=actionsFuncRegexp.exec(a);if(!b||!d)return null;a=b[1].replace(/\\$/g,\"\\\\$\");var e=b[2].replace(/\\$/g,\"\\\\$\");b=d[1].replace(/\\$/g,\"\\\\$\");var c=reverseRegexp.exec(e);console.log(\"boj bodey :\",e,c,\"------=\",c[1]);d=c&&c[1].replace(/\\$/g,\"\\\\$\").replace(/\\$|^'|^\"|'$|\"$/g,\"\");console.log(\"reverseKey=\",d);var f=(c=sliceRegexp.exec(e))&&c[1].replace(/\\$/g,\"\\\\$\").replace(/\\$|^'|^\"|'$|\"$/g,\"\");console.log(\"sliceKey=\",f);var g=(c=spliceRegexp.exec(e))&&c[1].replace(/\\$/g,\n" +
                        "\"\\\\$\").replace(/\\$|^'|^\"|'$|\"$/g,\"\");console.log(\"sliceKey=\",g);e=(c=swapRegexp.exec(e))&&c[1].replace(/\\$/g,\"\\\\$\").replace(/\\$|^'|^\"|'$|\"$/g,\"\");console.log(\"swapKey=\",e);c=\"(\"+[d,f,g,e].join(\"|\")+\")\";console.log(\"keys=\",c);a=new RegExp(\"(?:a=)?\"+a+\"(?:\\\\.\"+c+\"|\\\\['\"+c+\"'\\\\]|\\\\[\\\"\"+c+'\"\\\\])\\\\(a,(\\\\d+)\\\\)',\"g\");console.log(\"tokenizeRegexp=\",a);for(var h=[];null!==(c=a.exec(b));)switch(c[1]||c[2]||c[3]){case e:h.push(\"w\"+c[4]);break;case d:h.push(\"r\");break;case f:h.push(\"s\"+c[4]);break;case g:h.push(\"p\"+\n" +
                        "c[4])}return h}function setDownloadURL(a,b){if(a.url){var d=a.url;try{d=decodeURIComponent(d)}catch(c){return}d=getUrl(d);b&&(d.query[a.sp||\"signature\"]=b);b=\"\";var e=d.query;for(key in e)b=b?b+(\"&\"+key+\"=\"+e[key]):b+(key+\"=\"+e[key]);d=d.hostname+\"?\"+b;a.url=d;console.log(\"yurl:\",d);return d}}function querystringparse(a){a=a.split(\"&\");for(var b={},d=0;d<a.length;d++){var e=a[d].split(\"=\");b[e[0]]=e[1]}return b}var swapHeadAndPosition=function(a,b){var d=a[0];a[0]=a[b%a.length];a[b]=d;return a};\n" +
                        "function decipher(a,b){b=b.split(\"\");for(var d=0,e=a.length;d<e;d++){var c=a[d];switch(c[0]){case \"r\":b=b.reverse();break;case \"w\":c=~~c.slice(1);b=swapHeadAndPosition(b,c);break;case \"s\":c=~~c.slice(1);b=b.slice(c);break;case \"p\":c=~~c.slice(1),b.splice(0,c)}}return b.join(\"\")}\n" +
                        "function parseUrl(a){var b,d,e={};a=a?a.replace(/[^\\?]*\\?/,\"\"):\"\";var c=a.split(\"&\");var f=0;for(d=c.length;d>f;f++){a=c[f];var g=a.indexOf(\"=\");-1!==g&&(b=a.substr(g+1),b=decodeURIComponent(b),e[a.substr(0,g)]=b)}return e}function getUrl(a){var b=parseUrl(a);return{hostname:a.substring(0,a.indexOf(\"?\")),query:b}}\n" +
                        "var youtube={basejs:\"\",basejsurl:\"\",title:\"\",init:function(){console.log(\"-------init-----:\");console.log(\"-------document-----:\",document);document.getElementById(\"youtube_download\")||this.createEl()},createEl:function(){var a=document.createElement(\"div\"),b=this;a.onclick=function(){b.start()};a.id=\"youtube_download\";a.style.position=\"fixed\";a.style.right=\"30px\";a.style.bottom=\"30px\";a.style.width=\"50px\";a.style.height=\"50px\";a.style.background=\"#2196F3\";a.style.opacity=\"0.9\";a.style.borderRadius=\n" +
                        "\"50%\";a.style.zIndex=1E4;a.style.padding=\"10px\";a.style.fontSize=\"20px\";a.style.color=\"#fff\";a.style.textAlign=\"center\";a.style.boxShadow=\"1px 1px 10px 1px #000\";a.innerHTML=\"down\";document.body.appendChild(a)},ajaxFetch:function(a,b){var d=this,e=new XMLHttpRequest;e.open(\"GET\",a,!0);e.send();e.onreadystatechange=function(){4===e.readyState&&200===e.status&&b.call(d,e.response)}},start:function(){var a=window.location.href,b=this;console.log(\"start \\u5f00\\u59cb\\u89e3\\u6790:\",a);this.ajaxFetch(a,\n" +
                        "function(d){console.log(\"\\u9875\\u9762\\u89e3\\u6790\\u6210\\u529f:\",d);b.getBaseJS(d);b.getTitle(d);b.getInitResponse(d)})},getBaseJS:function(a){a=a.substring(a.indexOf(\"PLAYER_JS_URL\")+13+3);this.basejsurl=\"https://www.youtube.com\"+a.substr(0,a.indexOf(\"base.js\")+7)},getTitle:function(a){a=a.substring(a.indexOf(\"<title>\")+7);this.title=a.substr(0,a.indexOf(\"</title>\"));console.log(\"\\u5df2\\u7ecf\\u89e3\\u6790\\u51fa\\u6765 title:\",this.title)},getInitResponse:function(a){a=a.substring(a.indexOf(\"adaptiveFormats\")+\n" +
                        "15+2);a=a.substr(0,a.indexOf(\"]\")+1);this.adaptiveFormats=eval(a);var b=this;console.log(\"this.adaptiveFormats:\",this.adaptiveFormats);this.ajaxFetch(this.basejsurl,function(d){b.startparse(d)})},start_:function(){var a=this,b=new XMLHttpRequest;b.open(\"GET\",\"https://www.youtube.com\"+yt.config_.PLAYER_JS_URL,!0);b.send();b.onreadystatechange=function(){4===b.readyState&&200===b.status&&(a.basejs=b.response,a.startparse())}},startparse:function(a){var b=setParse(a),d=this;a=this.adaptiveFormats;Object.assign({},\n" +
                        "a);console.log(\"tokens:\",b);var e=[];a.forEach(function(c){console.log(\"format:\",c);c=Object.assign({},c);var f=c.signatureCipher||c.cipher;console.log(\"cipher---:\",f);f&&(Object.assign(c,querystringparse(f)),delete c.signatureCipher,delete c.cipher);f=b&&c.s?decipher(b,c.s):null;setDownloadURL(c,f);-1<c.mimeType.indexOf(\"audio\")&&e.push({name:d.title,url:c.url})});d.download(e)},download:function(a){console.log(\"param:\",a);try{pdata=JSON.stringify(a),window.youtubedown.testchanel(pdata)}catch(b){console.log(\"e:\",\n" +
                        "b)}}};setTimeout(function(){youtube.init()},1E3);");
            }
        });
        // webView.loadUrl("http://47.103.8.214:8890");

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("https://www.youtube.com/watch?v=VXCuvlbDCcc");

    }

    public void DownloadVideo (String url, String name) {
        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url));

        // 通过setAllowedNetworkTypes方法可以设置允许在何种网络下下载，
            // 也可以使用setAllowedOverRoaming方法，它更加灵活
        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

            // 此方法表示在下载过程中通知栏会一直显示该下载，在下载完成后仍然会显示，
            // 直到用户点击该通知或者消除该通知。还有其他参数可供选择
        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // 设置下载文件存放的路径，同样你可以选择以下方法存放在你想要的位置。
            // setDestinationUri
            // setDestinationInExternalPublicDir
        req.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "youtube");
        Toast.makeText(this, "开始下载", Toast.LENGTH_SHORT).show();
            // 设置一些基本显示信息
/*
        new AlertDialog.Builder(this)
                .setMessage("这是第二个提示")
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialoginterface, int i){
                                //按钮事件
                            }
                        })
                .show();
                */

        req.setTitle(name);
        req.setDescription("下载完后请点击打开");
        //req.setMimeType("application/vnd.android.package-archive");

        // Ok go!
        Log.d("webview", "onKeyDown: 开始下载视频:" + name);
        DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = dm.enqueue(req);
    }

    public boolean turl (String url) {
        URL urll;
        try {
            urll = new URL(url);
            InputStream in = urll.openStream();
            Log.d("webview", "turl 测试连接:" + url);
            return true;
            //DownloadVideo(url);
        } catch (Exception e1) {
            Log.d("webview", "onKeyDown: 连接不可以");
            //urll = null;
            return false;
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Log.d("webview", "onKeyDown: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxonReceivedErrorxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Log.d("webview", "onKeyDown: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxonReceivedErrorxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //Logger.e(TAG, "shouldOverrideUrlLoading---url=" + url);
            Log.d("webview", "onKeyDown: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxshouldOverrideUrlLoadingxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            //view.loadUrl(url);
            flag = false;
            return super.shouldOverrideUrlLoading(webView,url);
            //return  true;
        }

        @Override
        public void onPageStarted(final WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("webview", "onKeyDown: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxonPageStartedxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            //Logger.e(TAG, "onPageStarted---url=" + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //stopTiming();
            //webView.loadUrl("javascript:var mrg={init:function(){alert(\"ooo\")}}setTimeout(function(){mrg.init()}，1000)");
            //if(!flag) {

                Log.d("webview", "onKeyDown:------onPageFinished xx-------");
            //}
            flag = true;
            //Logger.e(TAG, "onPageFinished---url=" + url);
        }

    }

    public void setUmeng (){
        MobclickAgent.setDebugMode( true );
        //webView.loadUrl("http://192.168.10.226:8080");
        // 友盟消息 push  5ddcd1db0cafb290fe0000fc
        UMConfigure.init(this, "5dd35d003fc195f94e000705", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        //MobclickAgent.onEvent(this, "startApp");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        // System.exit(0);
        Log.d("webview", "start keydown");
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            //Toast.makeText("lyj_test","now_is_back_event",1).show();

            Log.d("webview", "onKeyDown: xxxxxxxxxxxxxxxxxxxxxxxxxxx");
            //MsgData msgData =new MsgData();
            //msgData.type = "exit";
            //JSONObject jsonObject = new JSONObject(msgData);
            //Log.d("webview", "onKeyDown: xxxxxxxxxxxxxxxxxxxxxxxxxxx:" + JSON.toJSONString(msgData));
            Log.d("webview", "onKeyDown: xxxxxxxxxxxxxxxxxxxxxxxxxxx:");
           // webView.loadUrl("javascript:window.msgChannel(" + msgData + ")");
            //webView.loadUrl("javascript:msg={init:function(){alert(\"kkkkkkk\")}},setTimeout(function(){msg.init()},1e3);");
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @JavascriptInterface

    public void testchanel (String param) {
        //Log.d("webview", "onKeyDown:" + param);
        boolean flag = true;
        try{
            JSONArray jsonObject = new JSONArray(param);
            //Log.d("webview", "onKeyDown:-------------" + jsonObject);
            for(int j=0; j<jsonObject.length(); j++){
                JSONObject JB = jsonObject.getJSONObject(j);
                final String  URL = JB.getString("url");
                final String  name = JB.getString("name");
                if(turl(URL)){
                    flag = false;
                    new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("要下载此次视频吗?")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    DownloadVideo(URL, name);
                                    //finish();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //取消按钮事件
                                }
                            })
                            .show();
                    break;
                }
                //turl(URL);
                //Log.d("webview", "onKeyDown:-------------:" + URL);
            }
            if(flag){
                new AlertDialog.Builder(this)
                        .setMessage("很遗憾没有匹配出来youtube视频")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialoginterface, int i){
                                        //按钮事件
                                    }
                                })
                        .show();
            }

            // 友盟统计


        }catch (JSONException e){
            e.printStackTrace();
        }


        //DownloadVideo(param);
       // Log.d(TAG, "msgChannel: test channel:" + param);
    }

    @JavascriptInterface

    public void msgChannel (String param){
       // Log.d("webview", "recieveMsg: recive message");
        Log.d(TAG, "msgChannel: yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy:" + param);
        try{
            JSONObject jsonObject = new JSONObject(param);
            String type = jsonObject.getString("type");
            Log.d("webview", "msgChanel:" + type);
            // 友盟统计
            if(TextUtils.equals(type, "um_statistics")){
                // exitApp()

                JSONObject data = jsonObject.getJSONObject("data");
                String event = data.getString("event");
                Log.d(TAG, "msgChannel: message :" + event);
                MobclickAgent.onEvent(this, event);
            }

            if(TextUtils.equals(type, "exit")){
                exitApp();
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
       // return true;
    }



    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }

    public void exitApp (){
        System.exit(0);
    }

}
