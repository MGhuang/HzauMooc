package com.feidian.george.hzaumooc.Adapter.Suggestion;

public class Talk {
    public static final int TYPE_RECEIVED=0;
    public  static final int TYPE_SENT=1;
    private String content;
    private int type;
    public Talk(String content, int type) {
        super();
        this.content = content;
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}