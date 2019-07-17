package com.example.rexita_pc.admin.Model;

import java.io.Serializable;

public class BobotKerugian implements Serializable {

    private float Kerugian;
    private String Key;

    public BobotKerugian(float kerugian){
        Kerugian = kerugian;
    }

    public float getKerugian() {
        return Kerugian;
    }

    public void setKerugian(float kerugian) {
        Kerugian = kerugian;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getKey() {
        return Key;
    }
}
