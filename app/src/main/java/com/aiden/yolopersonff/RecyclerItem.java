package com.aiden.yolopersonff;

public class RecyclerItem {
    private String concordancerate;
    private String location;
    private String time;

    public RecyclerItem (String concordancerate, String location, String time){
        this.concordancerate = concordancerate;
        this.location = location;
        this.time = time;
    }

    public String getConcordancerate() {
        return concordancerate;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }
}