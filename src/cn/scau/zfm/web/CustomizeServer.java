package cn.scau.zfm.web;

import cn.scau.zfm.base.CustomizeRequest;
import cn.scau.zfm.base.CustomizeResponse;
import cn.scau.zfm.servlet.AbstractServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomizeServer {
    private final static int PORT = 8081;
    private static HashMap<String, String> servletMappingMap;
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    static {
        servletMappingMap = new HashMap<>();
        servletMappingMap.put("/index", "cn.scau.zfm.servlet.IndexServlet");
        servletMappingMap.put("/login", "cn.scau.zfm.servlet.LoginServlet");
        servletMappingMap.put("/default", "cn.scau.zfm.servlet.DefaultServlet");
    }


    public void execute() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = socket.getInputStream();
                            OutputStream outputStream = socket.getOutputStream();
                            CustomizeRequest request = new CustomizeRequest(inputStream);
                            CustomizeResponse response = new CustomizeResponse(outputStream);
                            dispatch(request, response);
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
//                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dispatch(CustomizeRequest request, CustomizeResponse response) {
        String uri = request.getUri();
        String className = servletMappingMap.get("/default");
        if (null != uri && servletMappingMap.containsKey(uri)) {
            className = servletMappingMap.get(uri);
        }
        try {
            Class clazz = Class.forName(className);
            AbstractServlet servlet = (AbstractServlet) clazz.newInstance();
            servlet.service(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
