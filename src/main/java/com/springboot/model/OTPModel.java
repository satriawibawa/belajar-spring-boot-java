package com.springboot.model;

public class OTPModel {

    private Integer msisdn;

    public OTPModel(Integer msisdn) {
        super();
        this.msisdn = msisdn;
    }

    public Integer getMsisdn() {
        return msisdn;
    }

    @Override
    public String toString() {
        return "OTPModel{" + "msisdn=" + msisdn + "}";
    }
}
