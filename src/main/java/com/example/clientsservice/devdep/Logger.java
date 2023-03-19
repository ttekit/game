package com.example.clientsservice.devdep;


public class Logger {
    private static final String infoColor = "\u001B[34m";
    private static final String fixColor = "\u001B[32m";
    private static final String errorColor = "\u001B[31m";

    private static final String whiteColor = "\u001B[0m";

    public static void printInfo(Object info) {
        System.out.printf("%s[%sINFO%s]%s\n", whiteColor, infoColor, whiteColor, info.toString());
    }

    public static void printInInfoColor(Object info) {
        System.out.printf("%s%s%s\n", infoColor, info, whiteColor);
    }
    public static void printInFixColor(Object info) {
        System.out.printf("%s%s%s\n", fixColor, info, whiteColor);
    }
    public static void printInErrorColor(Object info) {
        System.out.printf("%s%s%s\n", errorColor, info, whiteColor);
    }
    public static void printFix(Object fix) {
        System.out.printf("%s[%sFIX%s]%s\n", whiteColor, fixColor, whiteColor, fix.toString());
    }
    public static void printError(Object error) {
        System.out.printf("%s[%sERROR%s]%s\n", whiteColor, errorColor, whiteColor, error.toString());
    }
    public static void print(String str){
        System.out.println(str);
    }

    public static void cls(){
        System.out.println(System.lineSeparator().repeat(70));
    }
}