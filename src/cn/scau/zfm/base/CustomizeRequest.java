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
        if (!("").equals(requestStr)) {
            System.out.println(requestStr);
            String requestLine = requestStr.split("\n")[0];
            this.uri = requestLine.split("\\s")[1];
            this.method = requestLine.split("\\s")[0];
            String[] strArr = requestStr.split("\n");
            if ("post".equalsIgnoreCase(method)) {
                for (int i = 1; i < strArr.length; i++) {
                    String str = strArr[i];
                    if (str.length() <= 1)
                        continue;
                    if (i != strArr.length - 1)
                        this.requestHeaderParamMap.put(str.split(":")[0], str.split(":")[1]);
                    else {
                        String[] param = str.split("&");
                        for (String item : param) {
                            this.requestHeaderParamMap.put(item.split("=")[0], item.split("=")[1]);
                        }
                    }
                }
            } else if ("get".equalsIgnoreCase(method)) {
                if (this.uri.indexOf("?") > -1) {
                    String param = this.uri.split("\\?")[1];
                    this.uri = this.uri.split("\\?")[0];
                    for (String item : param.split("&")) {
                        this.requestHeaderParamMap.put(item.split("=")[0], item.split("=")[1]);
                    }
                }
                for (int i = 1; i < strArr.length; i++) {
                    String str = strArr[i];
                    if (str.length() <= 1)
                        continue;
                    this.requestHeaderParamMap.put(str.split(":")[0], str.split(":")[1]);
                }
            } else {
                for (int i = 1; i < strArr.length; i++) {
                    String str = strArr[i];
                    if (str.length() <= 1)
                        continue;
                    this.requestHeaderParamMap.put(str.split(":")[0], str.split(":")[1]);
                }
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
