package com.example.demo;

public class UczenObecnoscSprawdzianyLekcje {
    String pon;
    String wt;
    String sr;
    String czw;
    String pt;

    public UczenObecnoscSprawdzianyLekcje(String pon, String wt, String sr, String czw, String pt) {
        this.pon = pon;
        this.wt = wt;
        this.sr = sr;
        this.czw = czw;
        this.pt = pt;
    }

    public String getPon() {
        return pon;
    }

    public void setPon(String pon) {
        this.pon = pon;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getCzw() {
        return czw;
    }

    public void setCzw(String czw) {
        this.czw = czw;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    @Override
    public String toString() {
        return "\nUczenObecnoscSprawdzianyLekcje{" +
                "pon='" + pon + '\'' +
                ", wt='" + wt + '\'' +
                ", sr='" + sr + '\'' +
                ", czw='" + czw + '\'' +
                ", pt='" + pt + '\'' +
                '}';
    }
}
