package cn.scau.zfm.servlet;

import cn.scau.zfm.base.CustomizeRequest;
import cn.scau.zfm.base.CustomizeResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class IndexServlet extends AbstractServlet {

    @Override
    public void doGet(CustomizeRequest request, CustomizeResponse response) {
        try {
            HashMap<String, String> paramMap = request.getRequestHeaderParamMap();
            if (!paramMap.containsKey("Cookie")) {
                HashMap<String, String> redirectMap = new HashMap<>();
                redirectMap.put("Location", "http://localhost:8081/login");
                response.setStatusCode("301");
                response.setCodeDesc("Moved Permanently");
                response.write("", redirectMap);
            } else {
                response.write(getHtmlContent(), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(CustomizeRequest request, CustomizeResponse response) {
        try {
            HashMap<String, String> paramMap = request.getRequestHeaderParamMap();
            if (!paramMap.containsKey("Cookie")) {
                HashMap<String, String> redirectMap = new HashMap<>();
                redirectMap.put("Location", "http://localhost:8081/login");
                response.setStatusCode("301");
                response.setCodeDesc("Moved Permanently");
                response.write("", redirectMap);
            } else {
                response.write(getHtmlContent(), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doHead(CustomizeRequest request, CustomizeResponse response) {
        try {
            response.write("", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRedirectStr() {
        StringBuilder builder = new StringBuilder("");

        return null;

    }

    private String getHtmlContent() throws IOException {
        String path = this.getClass().getClassLoader().getResource("cn/scau/zfm/html/index.html").getPath();

        File file = new File(path);

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        String htmlStr = "";
        int fileLength = 0;
        if((fileLength=fileInputStream.read(bytes))>0){
            htmlStr = new String (bytes,0,fileLength);
        }
        return htmlStr ;
    }
}
