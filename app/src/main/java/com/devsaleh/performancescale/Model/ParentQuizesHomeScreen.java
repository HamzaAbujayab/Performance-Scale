package com.devsaleh.performancescale.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentQuizesHomeScreen {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("quizzesCount")
    @Expose
    private Integer quizzesCount;
    @SerializedName("questionsCount")
    @Expose
    private Integer questionsCount;
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

    public Integer getQuizzesCount() {
        return quizzesCount;
    }

    public void setQuizzesCount(Integer quizzesCount) {
        this.quizzesCount = quizzesCount;
    }

    public Integer getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(Integer questionsCount) {
        this.questionsCount = questionsCount;
    }

    public String getAllQuizesTime() {
        return allQuizesTime;
    }

    public void setAllQuizesTime(String allQuizesTime) {
        this.allQuizesTime = allQuizesTime;
    }
}
