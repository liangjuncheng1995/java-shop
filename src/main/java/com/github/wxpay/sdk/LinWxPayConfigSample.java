package com.github.wxpay.sdk;

import java.io.InputStream;
import java.util.stream.Stream;

public class LinWxPayConfigSample extends WXPayConfig{
    public String getAppID() {//小程序的AppId
        return "wx8397f8696b538317";
    }

    public String getMchID() { //商户平台获取 商户号
        return "1473426802";
    }

    public String getKey() {// 商户平台获取
        return "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";
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
        IWXPayDomain  iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API,true);
            }
        };
        return iwxPayDomain;
    }
}
