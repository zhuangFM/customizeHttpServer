package cn.scau.zfm.servlet;

import cn.scau.zfm.base.CustomizeRequest;
import cn.scau.zfm.base.CustomizeResponse;

public abstract class AbstractServlet {
    public abstract void doGet(CustomizeRequest request, CustomizeResponse response);
    public abstract void doPost(CustomizeRequest request, CustomizeResponse response);
    public abstract void doHead(CustomizeRequest request, CustomizeResponse response);

    public void service(CustomizeRequest request,CustomizeResponse response){
        if("get".equalsIgnoreCase(request.getMethod())){
            this.doGet(request,response);
        }
        else if("post".equalsIgnoreCase(request.getMethod())){
            this.doPost(request,response);
        }
        else if("head".equalsIgnoreCase(request.getMethod())){
            this.doHead(request,response);
        }
    }

}
