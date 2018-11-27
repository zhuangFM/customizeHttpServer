package cn.scau.zfm.servlet;

import cn.scau.zfm.base.CustomizeRequest;
import cn.scau.zfm.base.CustomizeResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

public class LoginServlet extends AbstractServlet {

    @Override
    public void doGet(CustomizeRequest request, CustomizeResponse response) {
        try {
            HashMap<String,String> paramMap = request.getRequestHeaderParamMap();
            if(paramMap.containsKey("user")&&paramMap.containsKey("password")){
                String user = paramMap.get("user");
                String pasw = paramMap.get("password");
                if("123".equals(user)&&"123".equals(pasw)){
                    //登陆成功 跳转到主页 index.html 并且设置cookie
                    HashMap<String,String> redirectMap = new HashMap<>();
                    redirectMap.put("Set-Cookie","login=true");
                    response.write(getHtmlContent("index.html"), redirectMap);
                }
                else{
                    //密码或者用户名错误 返回登陆界面 login.html
                    response.write(getHtmlContent("login.html")+"<span style:'color:red;'>用户名或者密码不正确</span>", null);
                }
            }
            else{
                response.write(getHtmlContent("login.html"), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(CustomizeRequest request, CustomizeResponse response) {
        try {
            HashMap<String,String> paramMap = request.getRequestHeaderParamMap();
            if(paramMap.containsKey("user")&&paramMap.containsKey("password")){
                String user = paramMap.get("user");
                String pasw = paramMap.get("password");
                if("123".equals(user)&&"123".equals(pasw)){
                    //登陆成功 跳转到主页 index.html 并且设置cookie
                    HashMap<String,String> redirectMap = new HashMap<>();
                    redirectMap.put("Set-Cookie","login=true");
                    response.write(getHtmlContent("index.html"), redirectMap);
                }
                else{
                    //密码或者用户名错误 返回登陆界面 login.html
                    response.write(getHtmlContent("login.html")+"<span style:'color:red;'>用户名或者密码不正确</span>", null);
                }
            }
            else{
                response.write(getHtmlContent("login.html"), null);
            }
        }catch (IOException e) {
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

    private String getHtmlContent(String htmlName) throws IOException {
        String path = this.getClass().getClassLoader().getResource("cn/scau/zfm/html/"+htmlName).getPath();
        path = URLDecoder.decode(path, "utf-8");

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
