package com.securityevaluators.burp2vegeta;

public class Header {
    private final String name;
    private final String content;

    public Header(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public static Header Parse(String header) {
        if (header.contains(": ")) {
            String[] split = header.split(": ", 2);
            return new Header(split[0], split[1]);
        }
        else return new Header(header, null);
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}

