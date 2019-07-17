package com.example.rexita_pc.admin.Model;

import android.icu.util.DateInterval;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class DataPencurian implements Serializable  {

    private String Waktu;
    private String Tanggal;
    private String Kejadian;
    private String Lokasi;
    private String Kecamatan;
    private String Kerugian;
    private String Interval;
    private int Status;
    private String Key;

    public DataPencurian() {
    }

    public DataPencurian(String waktu, String tanggal, String kejadian, String lokasi, String kecamatan,
                         String kerugian,String interval, int status, String key) {
        Waktu = waktu;
        Tanggal = tanggal;
        Kejadian = kejadian;
        Lokasi = lokasi;
        Kecamatan = kecamatan;
        Kerugian = kerugian;
        Interval = interval;
        Status = status;
        Key = key;
    }

    public String getWaktu() {
        return Waktu;
    }

    public void setWaktu(String waktu) {
        Waktu = waktu;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getKejadian() {
        return Kejadian;
    }

    public void setKejadian(String kejadian) {
        Kejadian = kejadian;
    }

    public String getLokasi() {
        return Lokasi;
    }

    public void setLokasi(String lokasi) {
        Lokasi = lokasi;
    }

    public String getKecamatan() {
        return Kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        Kecamatan = kecamatan;
    }

    public String getKerugian() {
        return Kerugian;
    }

    public void setKerugian(String kerugian) {
        Kerugian = kerugian;
    }

    public String getInterval() {
        return Interval;
    }

    public void setInterval(String interval) {
        Interval = interval;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
