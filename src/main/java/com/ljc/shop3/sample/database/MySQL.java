package com.ljc.shop3.sample.database;

import com.ljc.shop3.sample.IConnect;
import org.springframework.stereotype.Component;


public class MySQL implements IConnect {

    private String ip = "localhost";
    private Integer port = 3306;

    public MySQL(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public MySQL() {

    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public void connect() {
        System.out.println(this.port + ":" + this.ip);
    }
}
