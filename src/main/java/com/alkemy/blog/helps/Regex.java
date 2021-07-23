package com.alkemy.blog.helps;

public class Regex {
    public static boolean isImageValidUrl(String url) {
        String reg = "((http(s?):)|([/|.|\\w|\\s])*.)*(?:jpg|jpeg|png)";
        return url.matches(reg);
    }
}
