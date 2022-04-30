package com.devsaleh.performancescale.ViewModel;

import com.devsaleh.performancescale.Model.ExpectedGpa;
import com.devsaleh.performancescale.Model.Parent;
import com.devsaleh.performancescale.Model.ParentAllGrader;
import com.devsaleh.performancescale.Model.ParentAverage;
import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.Model.ParentQuestion;
import com.devsaleh.performancescale.Model.ParentQuiz;
import com.devsaleh.performancescale.Model.ParentQuizDetails;
import com.devsaleh.performancescale.Model.ParentQuizesHomeScreen;
import com.devsaleh.performancescale.Model.ParentSignUpUsers;
import com.devsaleh.performancescale.Model.ParentSpecializedQuizes;
import com.devsaleh.performancescale.Model.ParentStudent;
import com.devsaleh.performancescale.Model.ParentStudentQuizes;
import com.devsaleh.performancescale.Model.ParentUserAnswerQuiz;
import com.devsaleh.performancescale.Model.Performance;
import com.devsaleh.performancescale.Model.Question;
import com.devsaleh.performancescale.Model.StudentQuizQuestions;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface PostInterface {

    @POST("signUpUsers")
    @FormUrlEncoded
    Call<ParentSignUpUsers> signUpUsers(@Field("name") String name,
                                        @Field("gender") String gender,
                                        @Field("school_type") String school_type,
                                        @Field("school_name") String school_name,
                                        @Field("specialized") String specialized,
                                        @Field("type") String type,
                                        @Field("user_id") String user_id);

    @POST("loginForUsers")
    @FormUrlEncoded
    Call<ParentSignUpUsers> loginForUsers(@Field("user_id") String user_id,
                                          @Field("fcm_token") String fcm_token,
                                          @Field("device_type") String device_type);

    @GET("quizesHomeScreen")
    Call<ParentQuizesHomeScreen> quizesHomeScreen(@Header("Authorization") String authHeader);

    @GET("getStudentQuizes")
    Call<ParentStudentQuizes> getStudentQuizes(@Header("Authorization") String authHeader);

    @GET("getPerformanceScale")
    Call<ParentStudent> getPerformanceScale(@Header("Authorization") String authHeader,
                                            @Query("specialized") String specialized,
                                            @Query("gender") String gender,
                                            @Query("direction") String direction);

    @GET("getQuizesBySpecialized")
    Call<ParentSpecializedQuizes> getQuizesBySpecialized(@Header("Authorization") String authHeader,
                                                         @Query("specialized") String specialized);

    @POST("enterUserGrade")
    @FormUrlEncoded
    Call<ParentItem> enterUserGrade(@Header("Authorization") String authHeader,
                                    @Field("year") String year,
                                    @Field("grades") String grade);


    @POST("userAnswerQuiz")
    @FormUrlEncoded
    Call<ParentUserAnswerQuiz> userAnswerQuiz(@Header("Authorization") String authHeader,
                                              @FieldMap Map<String, String> data);

    @POST("userAnswerQuiz")
    @FormUrlEncoded
    Call<ParentUserAnswerQuiz> userAnswerQuiz0000(@Header("Authorization") String authHeader,
                                                  @Field("question_ids[0]") String question_ids,
                                                  @Field("quiz_answers[0]") String quiz_answers);

    @GET("deleteUser")
    Call<Parent> deleteUser(@Header("Authorization") String authHeader,
                            @Query("user_id") String user_id);

    @GET("deleteQuiz")
    Call<Parent> deleteQuiz(@Header("Authorization") String authHeader,
                            @Query("quiz_id") String quiz_id);

    @POST("addStudentByTeacher")
    @FormUrlEncoded
    Call<ParentSignUpUsers> addStudentByTeacher(@Header("Authorization") String authHeader,
                                                @Field("name") String name,
                                                @Field("gender") String gender,
                                                @Field("school_type") String school_type,
                                                @Field("school_name") String school_name,
                                                @Field("specialized") String specialized,
                                                @Field("type") String type,
                                                @Field("user_id") String user_id);

    @POST("enterGradesByTeacher")
    @FormUrlEncoded
    Call<ParentItem> enterGradesByTeacher(@Header("Authorization") String authHeader,
                                          @Field("user_id") String user_id,
                                          @Field("grades1") String grades1,
                                          @Field("grades2") String grades2,
                                          @Field("grades3") String grades3,
                                          @Field("grades4") String grades4,
                                          @Field("grades5") String grades5,
                                          @Field("grades6") String grades6);

    @POST("createNewqQuiz")
    @FormUrlEncoded
    Call<ParentQuiz> createNewqQuiz(@Header("Authorization") String authHeader,
                                    @FieldMap Map<String, String> data);


    @POST("enterQuizQuestions")
    @FormUrlEncoded
    Call<ParentQuestion> enterQuizQuestions(@Header("Authorization") String authHeader,
                                            @Field("quiz_id") String quiz_id,
                                            @Field("question") String question,
                                            @Field("answers") String answers);


    @GET("getQuizDetails")
    Call<ParentQuizDetails> getQuizDetails(@Header("Authorization") String authHeader,
                                           @Query("quiz_id") String quiz_id);


    @GET("deleteQuestion")
    Call<Parent> deleteQuestion(@Header("Authorization") String authHeader,
                                @Query("question_id") String question_id);


    @POST("updateQuestion")
    @FormUrlEncoded
    Call<ParentQuestion> updateQuestion(@Header("Authorization") String authHeader,
                                        @Field("question_id") String question_id,
                                        @Field("question") String question,
                                        @Field("answers") String answers);


    @GET("getStudentAllGrader")
    Call<ParentAllGrader> getStudentAllGrader(@Header("Authorization") String authHeader,
                                              @Query("user_id") String user_id);

    @GET("averageAllSubjects")
    Call<ParentAverage> averageAllSubjects(@Header("Authorization") String authHeader,
                                           @Query("user_id") int user_id);


    @POST("changeQuizeTime")
    @FormUrlEncoded
    Call<Parent> changeQuizeTime(@Header("Authorization") String authHeader,
                                 @Field("quiz_id") String quiz_id,
                                 @Field("time") String time);


    @Multipart
    @POST("uploadContent")
    Call<Parent> uploadContent(@Header("Authorization") String authHeader,
                               @Part MultipartBody.Part image_user);


    /////////////////////////////////////////////////////////////////////////////
    //////////////////////////      Result       ////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    @GET("expected_gpa")
    Call<ExpectedGpa> expected_gpa(@Query("arabic") String arabic,
                                   @Query("english") String english,
                                   @Query("religion") String religion,
                                   @Query("history") String history,
                                   @Query("math") String math,
                                   @Query("physics") String physics,
                                   @Query("chemistry") String chemistry,
                                   @Query("biology") String biology,
                                   @Query("computer") String computer,
                                   @Query("social") String social);

    @GET("performance")
    Call<Performance> performance(@Query("gpa") String gpa,
                                  @Query("assessment") String assessment);

}