package com.devsaleh.performancescale.ViewModel;

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
import com.devsaleh.performancescale.Model.StudentQuizQuestions;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {
    private static final String BASE_URL = "https://performance.drwafi.be/public/api/";
    private final PostInterface postInterface;
    private static PostsClient INSTANCE;

    public PostsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addNetworkInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request.Builder builder = chain.request().newBuilder();
                                builder.addHeader("Accept-Language", "en");
                                Request request = builder.build();
                                Response response = chain.proceed(request);
                                return response;
                            }
                        }).build())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static PostsClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PostsClient();
        }
        return INSTANCE;
    }


    public Call<ParentSignUpUsers> signUpUsers(String name, String gender, String school_type, String school_name, String specialized, String type, String user_id) {
        return postInterface.signUpUsers(name, gender, school_type, school_name, specialized, type, user_id);
    }

    public Call<ParentSignUpUsers> loginForUsers(String user_id, String fcm_token, String device_type) {
        return postInterface.loginForUsers(user_id, fcm_token, device_type);
    }

    public Call<ParentQuizesHomeScreen> quizesHomeScreen(String authHeader) {
        return postInterface.quizesHomeScreen(authHeader);
    }

    public Call<ParentStudentQuizes> getStudentQuizes(String authHeader) {
        return postInterface.getStudentQuizes(authHeader);
    }

    public Call<ParentStudent> getPerformanceScale(String authHeader, String specialized, String gender, String direction) {
        return postInterface.getPerformanceScale(authHeader, specialized, gender, direction);
    }

    public Call<ParentSpecializedQuizes> getQuizesBySpecialized(String authHeader, String specialized) {
        return postInterface.getQuizesBySpecialized(authHeader, specialized);
    }

    public Call<ParentItem> enterUserGrade(String authHeader, String year, String grade) {
        return postInterface.enterUserGrade(authHeader, year, grade);
    }

    public Call<ParentUserAnswerQuiz> userAnswerQuiz(String authHeader, Map<String, String> data) {
        return postInterface.userAnswerQuiz(authHeader, data);
    }

    public Call<Parent> deleteUser(String authHeader, String user_id) {
        return postInterface.deleteUser(authHeader, user_id);
    }

    public Call<Parent> deleteQuiz(String authHeader, String user_id) {
        return postInterface.deleteQuiz(authHeader, user_id);
    }

    public Call<ParentSignUpUsers> addStudentByTeacher(String authHeader, String name, String gender, String school_type, String school_name, String specialized, String type, String user_id) {
        return postInterface.addStudentByTeacher(authHeader, name, gender, school_type, school_name, specialized, type, user_id);
    }

    public Call<ParentItem> enterGradesByTeacher(String authHeader, String user_id, String grades1, String grades2, String grades3, String grades4, String grades5, String grades6) {
        return postInterface.enterGradesByTeacher(authHeader, user_id, grades1, grades2, grades3, grades4, grades5, grades6);
    }

    public Call<ParentQuiz> createNewqQuiz(String authHeader, Map<String, String> data) {
        return postInterface.createNewqQuiz(authHeader, data);
    }

    public Call<ParentQuestion> enterQuizQuestions(String authHeader, String quiz_id, String question, String answers) {
        return postInterface.enterQuizQuestions(authHeader, quiz_id, question, answers);
    }

    public Call<ParentQuizDetails> getQuizDetails(String authHeader, String quiz_id) {
        return postInterface.getQuizDetails(authHeader, quiz_id);
    }

    public Call<Parent> deleteQuestion(String authHeader, String question_id) {
        return postInterface.deleteQuestion(authHeader, question_id);
    }

    public Call<ParentQuestion> updateQuestion(String authHeader, String question_id, String question, String answers) {
        return postInterface.updateQuestion(authHeader, question_id, question, answers);
    }

    public Call<ParentAllGrader> getStudentAllGrader(String authHeader, String user_id) {
        return postInterface.getStudentAllGrader(authHeader, user_id);
    }

    public Call<ParentAverage> averageAllSubjects(String authHeader, int user_id) {
        return postInterface.averageAllSubjects(authHeader, user_id);
    }

    public Call<Parent> changeQuizeTime(String authHeader, String quiz_id, String time) {
        return postInterface.changeQuizeTime(authHeader, quiz_id, time);
    }

    public Call<Parent> uploadContent(String authHeader, MultipartBody.Part image_user) {
        return postInterface.uploadContent(authHeader, image_user);
    }


}