package com.devsaleh.performancescale.ViewModel;

import com.devsaleh.performancescale.Model.ExpectedGpa;
import com.devsaleh.performancescale.Model.Parent;
import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.Model.ParentQuiz;
import com.devsaleh.performancescale.Model.ParentQuizesHomeScreen;
import com.devsaleh.performancescale.Model.ParentSignUpUsers;
import com.devsaleh.performancescale.Model.ParentSpecializedQuizes;
import com.devsaleh.performancescale.Model.ParentStudent;
import com.devsaleh.performancescale.Model.ParentStudentQuizes;
import com.devsaleh.performancescale.Model.ParentUserAnswerQuiz;
import com.devsaleh.performancescale.Model.Performance;
import com.devsaleh.performancescale.Model.StudentQuizQuestions;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient2 {
    private static final String BASE_URL2 = "https://performancescale.herokuapp.com/";
    private final PostInterface postInterface;
    private static PostsClient2 INSTANCE;

    public PostsClient2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL2)
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

    public static PostsClient2 getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PostsClient2();
        }
        return INSTANCE;
    }


    public Call<ExpectedGpa> expected_gpa(String arabic, String english, String religion, String history, String math, String physics
            , String chemistry, String biology, String computer, String social) {
        return postInterface.expected_gpa(arabic, english, religion, history, math, physics, chemistry, biology, computer, social);
    }


    public Call<Performance> performance(String gpa, String assessment) {
        return postInterface.performance(gpa, assessment);
    }

}