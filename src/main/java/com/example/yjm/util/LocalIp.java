package com.example.yjm.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalIp {
    public static String ip() {
        String ipAddress = "";
        try {
            // 获取本机的 InetAddress 对象
            InetAddress localHost = InetAddress.getLocalHost();

            // 获取本机的 IP 地址
            ipAddress = localHost.getHostAddress();

            System.out.println("本机 IP 地址: " + ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ipAddress;
    }
}