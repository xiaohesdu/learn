package com.learn;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gonghe.hogan
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        String zkServerUrl = "10.128.114.87:12181, 10.128.114.88:12181, 10.128.114.89:12181";
        String[] addrsSplit = zkServerUrl.split(",");
        Map<String, String> messages = new HashMap<>();
        boolean down = false;
        for (String addrs : addrsSplit) {
            if (addrs != null && !addrs.trim().isEmpty()) {
                String[] split = addrs.split(":");
                InetSocketAddress socketAddress = new InetSocketAddress(split[0], Integer.valueOf(split[1]));
                try (Socket socket = new Socket()) {
                    socket.connect(socketAddress, 1000);
                    messages.put(addrs, "up");
                } catch (Exception e) {
                    down = true;
                    log.error(e.getMessage(), e);
                    messages.put(addrs, e.getMessage());
                }
            }
        }
    }

public void copy(){



}
}
