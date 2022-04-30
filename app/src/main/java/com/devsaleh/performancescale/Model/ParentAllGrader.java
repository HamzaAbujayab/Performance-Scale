package com.devsaleh.performancescale.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentAllGrader {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("year1")
    @Expose
    private List<Year> year1 = null;
    @SerializedName("year2")
    @Expose
    private List<Year> year2 = null;
    @SerializedName("year3")
    @Expose
    private List<Year> year3 = null;
    @SerializedName("year4")
    @Expose
    private List<Year> year4 = null;
    @SerializedName("year5")
    @Expose
    private List<Year> year5 = null;
    @SerializedName("year6")
    @Expose
    private List<Year> year6 = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Year> getYear1() {
        return year1;
    }

    public void setYear1(List<Year> year1) {
        this.year1 = year1;
    }

    public List<Year> getYear2() {
        return year2;
    }

    public void setYear2(List<Year> year2) {
        this.year2 = year2;
    }

    public List<Year> getYear3() {
        return year3;
    }

    public void setYear3(List<Year> year3) {
        this.year3 = year3;
    }

    public List<Year> getYear4() {
        return year4;
    }

    public void setYear4(List<Year> year4) {
        this.year4 = year4;
    }

    public List<Year> getYear5() {
        return year5;
    }

    public void setYear5(List<Year> year5) {
        this.year5 = year5;
    }

    public List<Year> getYear6() {
        return year6;
    }

    public void setYear6(List<Year> year6) {
        this.year6 = year6;
    }

}
