package com.template.msgtemplate;

import android.graphics.drawable.Drawable;

/**
 * Created by Mohammad Arda on 11/1/2017.
 */

public class KeyMessage {

    private String key;
    private String data;

    public KeyMessage(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
