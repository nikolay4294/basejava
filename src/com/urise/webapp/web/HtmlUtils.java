package com.urise.webapp.web;

public class HtmlUtils {

    public String createHtmlHeader(String title) {
        return "<HTML><HEAD><TITLE> " + title + " </TITLE></HEAD><BODY>";
    }

    public String getTableHead(String align, int border) {
        return "<TABLE align=" + align + " border=" + border + ">";
    }

    public String getTH(String align, String value) {
        return "<TH align=" + align + "> " + value + " </TH>";
    }
}
