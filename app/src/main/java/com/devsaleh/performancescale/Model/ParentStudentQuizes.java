package com.devsaleh.performancescale.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentStudentQuizes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("studentQuizes")
    @Expose
    private List<StudentQuize> studentQuizes = null;
    @SerializedName("allQuizesTime")
    @Expose
    private String allQuizesTime;

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

    public List<StudentQuize> getStudentQuizes() {
        return studentQuizes;
    }

    public void setStudentQuizes(List<StudentQuize> studentQuizes) {
        this.studentQuizes = studentQuizes;
    }

    public String getAllQuizesTime() {
        return allQuizesTime;
    }

    public void setAllQuizesTime(String allQuizesTime) {
        this.allQuizesTime = allQuizesTime;
    }
}
