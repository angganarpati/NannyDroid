package com.neti.model;

public class Ranking {
    private String nama;
    private String ktp;
    private String status;
    private float total;
    
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getKtp() {
        return ktp;
    }
    public void setKtp(String ktp) {
        this.ktp = ktp;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public float getTotal(){
    	return total;
    }
    public void setTotal(float total){
    	this.total = total;
    }
    
}
