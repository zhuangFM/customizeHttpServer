package cn.scau.zfm.servlet;

import cn.scau.zfm.base.CustomizeRequest;
import cn.scau.zfm.base.CustomizeResponse;

import java.io.IOException;

public class LoginServlet extends AbstractServlet {

    @Override
    public void doGet(CustomizeRequest request, CustomizeResponse response) {
        try {
            
            response.write("[GET]login.html", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(CustomizeRequest request, CustomizeResponse response) {
        try {
            response.write("[POST]login.html", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
