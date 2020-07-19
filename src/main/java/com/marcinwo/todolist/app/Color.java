package com.marcinwo.todolist.app;

public enum Color {
    GREEN("#51720e"), PINK("#ab2a57"), BLUE("#72a1e7"), RED("#fe6c46");

    private String hexValue;

    Color(String hexValue) {
        this.hexValue = hexValue;
    }

    public String getHexValue() {
        return hexValue;
    }
}
