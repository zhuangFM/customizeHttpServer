package cn.scau.zfm.mapping;

public class ServletMapping {
    private String uri;
    private String className;

    public ServletMapping(String uri,String className){
        this.className = className;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
