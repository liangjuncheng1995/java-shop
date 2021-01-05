package com.github.wxpay.sdk;

import java.io.InputStream;
import java.util.stream.Stream;

public class LinWxPayConfigSample extends WXPayConfig{
    public String getAppID() {//小程序的AppId
        return "wxdsdjskjd";
    }

    public String getMchID() { //商户平台获取 商户号
        return "1589111117";
    }

    public String getKey() {// 商户平台获取
        return "sdsdsds";
    }

    public InputStream getCertStream() {
        return null;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public IWXPayDomain getWXPayDomain(){
        return null;
    }
}
