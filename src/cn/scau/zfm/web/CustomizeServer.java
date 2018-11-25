package cn.scau.zfm.web;

import java.io.IOException;
import java.net.ServerSocket;

public class CustomizeServer {
    private final static int PORT = 80;

    public void execute(){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
