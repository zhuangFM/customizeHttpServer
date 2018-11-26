package cn.scau.zfm.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class CustomizeRequest {
    private String uri;
    private String method;
    private String content;
    private HashMap<String, String> requestHeaderParamMap = new HashMap<>();

    public CustomizeRequest(InputStream inputStream) throws IOException {
        String requestStr = "";
        byte[] requestStrBytes = new byte[1024];
        int requestStrLength = 0;
        if ((requestStrLength = inputStream.read(requestStrBytes)) > 0) {
            requestStr = new String(requestStrBytes, 0, requestStrLength);
        }
        this.content = requestStr;
//        char[] charArr = requestStr.toCharArray();
//        Pattern pattern = Pattern.compile("^[A-Za-z]+$");

//        int inx = 0;
//        for(char ch : charArr){
////            Matcher matcher = pattern.matcher(ch);
////            if(ch<'A'||(ch>'Z'&&ch<'a')||ch)
//            if(ch==' '||ch=='\n')
//                inx++;
//            else
//                break;
//        }
//        requestStr = requestStr.substring(inx,requestStr.length());

        if (!("").equals(requestStr)) {
            System.out.println(requestStr);
            String requestLine = requestStr.split("\n")[0];
            this.uri = requestLine.split("\\s")[1];
            this.method = requestLine.split("\\s")[0];
            String[] strArr = requestStr.split("\n");
            for (int i = 1;i<strArr.length;i++) {
                String str = strArr[i];
                if("".equals(str))
                    continue;
                this.requestHeaderParamMap.put(str.split(":")[0], str.split(":")[1]);
                System.out.println(str);
            }
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HashMap<String, String> getRequestHeaderParamMap() {
        return requestHeaderParamMap;
    }

    public void setRequestHeaderParamMap(HashMap<String, String> requestHeaderParamMap) {
        this.requestHeaderParamMap = requestHeaderParamMap;
    }

    @Override
    public String toString() {
        return "Request uri:[" + this.uri + "]" + "  method:[" + this.method + "]" + " content:[" + this.content + "]";
    }
}
