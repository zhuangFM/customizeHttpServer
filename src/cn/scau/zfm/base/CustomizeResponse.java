package cn.scau.zfm.base;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class CustomizeResponse {
    private OutputStream outputStream;
    private String statusCode = "200";
    private String codeDesc = "OK";

    public CustomizeResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content, HashMap<String, String> paramMap) throws IOException {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("HTTP/1.1 " + this.statusCode + " " + codeDesc + "\n")
                .append("Content-Type:text/html\n");
        if (null != paramMap) {
            paramMap.keySet().forEach((key) -> {
                String val = paramMap.get(key);
                stringBuffer.append(key + ":" + val + "\n");
            });
        }
        stringBuffer.append("\r\n")
                .append(content);
        outputStream.write(stringBuffer.toString().getBytes());
        outputStream.close();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
