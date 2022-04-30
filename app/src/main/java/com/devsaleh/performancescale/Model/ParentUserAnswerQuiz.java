package com.devsaleh.performancescale.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentUserAnswerQuiz {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("questions_count")
    @Expose
    private Integer questions_count;
    @SerializedName("final_percent")
    @Expose
    private Integer final_percent;

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

    public Integer getQuestions_count() {
        return questions_count;
    }

    public void setQuestions_count(Integer questions_count) {
        this.questions_count = questions_count;
    }

    public Integer getFinal_percent() {
        return final_percent;
    }

    public void setFinal_percent(Integer final_percent) {
        this.final_percent = final_percent;
    }
}
