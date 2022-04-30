package com.devsaleh.performancescale.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpectedGpa {


    @SerializedName("Scientific")
    @Expose
    private String Scientific;
    @SerializedName("industrial")
    @Expose
    private String industrial;
    @SerializedName("literary")
    @Expose
    private String literary;

    public String getScientific() {
        return Scientific;
    }

    public void setScientific(String scientific) {
        Scientific = scientific;
    }

    public String getIndustrial() {
        return industrial;
    }

    public void setIndustrial(String industrial) {
        this.industrial = industrial;
    }

    public String getLiterary() {
        return literary;
    }

    public void setLiterary(String literary) {
        this.literary = literary;
    }
}
