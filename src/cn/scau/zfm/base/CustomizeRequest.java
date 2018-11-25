package cn.scau.zfm.base;

import java.io.IOException;
import java.io.InputStream;

public class CustomizeRequest {
    private String uri;
    private String method;
    private String content;

    public CustomizeRequest(InputStream inputStream) throws IOException {
        String requestStr = "";
        byte[] requestStrBytes = new byte[1024];
        int requestStrLength = 0;
        if ((requestStrLength = inputStream.read(requestStrBytes)) > 0) {
            requestStr = new String(requestStrBytes, 0, requestStrLength);
        }
        this.content = requestStr;
        String httpHead = requestStr.split("\n")[0];
        this.uri = httpHead.split("\\s")[1];
        this.method = httpHead.split("\\s")[0];

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

    @Override
    public String toString() {
        return "Request uri:[" + this.uri + "]" + "  method:[" + this.method + "]" + " content:[" + this.content + "]";
    }
}
