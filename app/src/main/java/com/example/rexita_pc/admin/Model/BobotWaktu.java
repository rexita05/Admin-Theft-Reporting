package com.example.rexita_pc.admin.Model;

import java.io.Serializable;

public class BobotWaktu implements Serializable {

    private float Waktu;
    private String Key;

    public BobotWaktu(float waktu){
        Waktu = waktu;
    }

    public float getWaktu() {
        return Waktu;
    }

    public void setWaktu(float waktu) {
        Waktu = waktu;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getKey() {
        return Key;
    }
}
