package com.example.rexita_pc.admin.Model;

import java.io.Serializable;

public class DataKecamatan implements Serializable {
    private String Kecamatan;
    private String Key;

    public DataKecamatan(String kecamatan) {

        Kecamatan = kecamatan;
    }

    public String getKecamatan() {
        return Kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        Kecamatan = kecamatan;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
