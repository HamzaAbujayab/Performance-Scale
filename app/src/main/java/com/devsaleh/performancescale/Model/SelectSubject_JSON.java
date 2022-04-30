package com.devsaleh.performancescale.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectSubject_JSON {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sub1_group")
    @Expose
    private String sub1Group;
    @SerializedName("sub2_group")
    @Expose
    private String sub3Group;
    @SerializedName("sub3_group")
    @Expose
    private String sub3Group;
    @SerializedName("sub2_group")

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

    public String getSub1Group() {
        return sub1Group;
    }

    public void setSub1Group(String sub1Group) {
        this.sub1Group = sub1Group;
    }



}

