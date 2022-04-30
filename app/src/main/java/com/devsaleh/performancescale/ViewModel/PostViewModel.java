package com.devsaleh.performancescale.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
import com.devsaleh.performancescale.Model.StudentQuizQuestions;

import java.io.IOException;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    public MutableLiveData<ParentSignUpUsers> MldSignUpUsers = new MutableLiveData<>();
    public MutableLiveData<ParentQuizesHomeScreen> MldQuizesHomeScreen = new MutableLiveData<>();
    public MutableLiveData<ParentStudentQuizes> MldStudentQuizes = new MutableLiveData<>();
    public MutableLiveData<ParentStudent> MldStudents = new MutableLiveData<>();
    public MutableLiveData<ParentSpecializedQuizes> MldSpecializedQuizes = new MutableLiveData<>();
    public MutableLiveData<ParentItem> MldParentItem = new MutableLiveData<>();
    public MutableLiveData<ParentUserAnswerQuiz> MldUserAnswerQuiz = new MutableLiveData<>();
    public MutableLiveData<Parent> MldParent = new MutableLiveData<>();
    public MutableLiveData<ParentQuiz> MldParentQuiz = new MutableLiveData<>();
    public MutableLiveData<ParentQuestion> MldStudentQuizQuestions = new MutableLiveData<>();
    public MutableLiveData<ExpectedGpa> MldExpectedGpa = new MutableLiveData<>();
    public MutableLiveData<Performance> MldPerformance = new MutableLiveData<>();
    public MutableLiveData<ParentQuizDetails> MldParentQuizDetails = new MutableLiveData<>();
    public MutableLiveData<ParentQuestion> MldPParentQuestion = new MutableLiveData<>();
    public MutableLiveData<ParentAllGrader> MldParentAllGrader = new MutableLiveData<>();
    public MutableLiveData<ParentAverage> MldParentAverage = new MutableLiveData<>();
    public MutableLiveData<Parent> changeQuizeTime = new MutableLiveData<>();
    public MutableLiveData<Parent> uploadContent = new MutableLiveData<>();


    public void signUpUsers(String name, String gender, String school_type, String school_name, String specialized, String type, String user_id) {
        PostsClient.getINSTANCE().signUpUsers(name, gender, school_type, school_name, specialized, type, user_id).enqueue(new Callback<ParentSignUpUsers>() {
            @Override
            public void onResponse(Call<ParentSignUpUsers> call, Response<ParentSignUpUsers> response) {
                if (response.isSuccessful()) {
                    MldSignUpUsers.setValue(response.body());
                    Log.d("TAG", "onResponse: 111");
                } else
                    Log.d("TAG", "onResponse: 222");
            }

            @Override
            public void onFailure(Call<ParentSignUpUsers> call, Throwable t) {
                Log.d("TAG", "onResponse: 333 " + t.getMessage());
            }
        });
    }


    public void loginForUsers(String user_id, String fcm_token, String device_type) {
        PostsClient.getINSTANCE().loginForUsers(user_id, fcm_token, device_type).enqueue(new Callback<ParentSignUpUsers>() {
            @Override
            public void onResponse(Call<ParentSignUpUsers> call, Response<ParentSignUpUsers> response) {
                if (response.isSuccessful()) {
                    MldSignUpUsers.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentSignUpUsers> call, Throwable t) {
            }
        });
    }

    public void quizesHomeScreen(String authHeader) {
        PostsClient.getINSTANCE().quizesHomeScreen("Bearer " + authHeader).enqueue(new Callback<ParentQuizesHomeScreen>() {
            @Override
            public void onResponse(Call<ParentQuizesHomeScreen> call, Response<ParentQuizesHomeScreen> response) {
                if (response.isSuccessful()) {
                    MldQuizesHomeScreen.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentQuizesHomeScreen> call, Throwable t) {
            }
        });
    }

    public void getStudentQuizes(String authHeader) {
        PostsClient.getINSTANCE().getStudentQuizes("Bearer " + authHeader).enqueue(new Callback<ParentStudentQuizes>() {
            @Override
            public void onResponse(Call<ParentStudentQuizes> call, Response<ParentStudentQuizes> response) {
                if (response.isSuccessful()) {
                    MldStudentQuizes.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentStudentQuizes> call, Throwable t) {
            }
        });
    }

    public void getPerformanceScale(String authHeader, String specialized, String gender, String direction) {
        PostsClient.getINSTANCE().getPerformanceScale("Bearer " + authHeader, specialized, gender, direction).enqueue(new Callback<ParentStudent>() {
            @Override
            public void onResponse(Call<ParentStudent> call, Response<ParentStudent> response) {
                if (response.isSuccessful()) {
                    MldStudents.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentStudent> call, Throwable t) {
            }
        });
    }

    public void getQuizesBySpecialized(String authHeader, String specialized) {
        PostsClient.getINSTANCE().getQuizesBySpecialized("Bearer " + authHeader, specialized).enqueue(new Callback<ParentSpecializedQuizes>() {
            @Override
            public void onResponse(Call<ParentSpecializedQuizes> call, Response<ParentSpecializedQuizes> response) {
                if (response.isSuccessful()) {
                    MldSpecializedQuizes.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentSpecializedQuizes> call, Throwable t) {
            }
        });
    }

    public void enterUserGrade(String authHeader, String year, String grade) {
        PostsClient.getINSTANCE().enterUserGrade("Bearer " + authHeader, year, grade).enqueue(new Callback<ParentItem>() {
            @Override
            public void onResponse(Call<ParentItem> call, Response<ParentItem> response) {
                if (response.isSuccessful()) {
                    MldParentItem.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentItem> call, Throwable t) {
            }
        });
    }

    public void userAnswerQuiz(String authHeader, Map<String, String> data) {
        PostsClient.getINSTANCE().userAnswerQuiz("Bearer " + authHeader, data).enqueue(new Callback<ParentUserAnswerQuiz>() {
            @Override
            public void onResponse(Call<ParentUserAnswerQuiz> call, Response<ParentUserAnswerQuiz> response) {
                if (response.isSuccessful()) {
                    MldUserAnswerQuiz.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentUserAnswerQuiz> call, Throwable t) {
            }
        });
    }


    public void deleteUser(String authHeader, String user_id) {
        PostsClient.getINSTANCE().deleteUser("Bearer " + authHeader, user_id).enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if (response.isSuccessful()) {
                    MldParent.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {
            }
        });
    }

    public void deleteQuiz(String authHeader, String user_id) {
        PostsClient.getINSTANCE().deleteQuiz("Bearer " + authHeader, user_id).enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if (response.isSuccessful()) {
                    MldParent.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {
            }
        });
    }

    public void addStudentByTeacher(String authHeader, String name, String gender, String school_type, String school_name, String specialized, String type, String user_id) {
        PostsClient.getINSTANCE().addStudentByTeacher("Bearer " + authHeader, name, gender, school_type, school_name, specialized, type, user_id).enqueue(new Callback<ParentSignUpUsers>() {
            @Override
            public void onResponse(Call<ParentSignUpUsers> call, Response<ParentSignUpUsers> response) {
                if (response.isSuccessful()) {
                    MldSignUpUsers.setValue(response.body());
                    Log.d("TAG", "onResponse: 111");
                } else
                    Log.d("TAG", "onResponse: 222");
            }

            @Override
            public void onFailure(Call<ParentSignUpUsers> call, Throwable t) {
                Log.d("TAG", "onResponse: 333 " + t.getMessage());
            }
        });
    }

    public void enterGradesByTeacher(String authHeader, String user_id, String grades1, String grades2, String grades3, String grades4, String grades5, String grades6) {
        PostsClient.getINSTANCE().enterGradesByTeacher("Bearer " + authHeader, user_id, grades1, grades2, grades3, grades4, grades5, grades6).enqueue(new Callback<ParentItem>() {
            @Override
            public void onResponse(Call<ParentItem> call, Response<ParentItem> response) {
                if (response.isSuccessful()) {
                    MldParentItem.setValue(response.body());
                    Log.d("TAG", "onResponse: 111");
                } else
                    Log.d("TAG", "onResponse: 222 " + call.request());
            }

            @Override
            public void onFailure(Call<ParentItem> call, Throwable t) {
                Log.d("TAG", "onResponse: 333 " + t.getMessage());
            }
        });
    }

    public void createNewqQuiz(String authHeader, Map<String, String> data) {
        PostsClient.getINSTANCE().createNewqQuiz("Bearer " + authHeader, data).enqueue(new Callback<ParentQuiz>() {
            @Override
            public void onResponse(Call<ParentQuiz> call, Response<ParentQuiz> response) {
                if (response.isSuccessful()) {
                    MldParentQuiz.setValue(response.body());
                    Log.d("TAG", "onResponse: 111 " + call.request());
                }
            }

            @Override
            public void onFailure(Call<ParentQuiz> call, Throwable t) {
            }
        });
    }

    public void enterQuizQuestions(String authHeader, String quiz_id, String question, String answers) {
        PostsClient.getINSTANCE().enterQuizQuestions("Bearer " + authHeader, quiz_id, question, answers).enqueue(new Callback<ParentQuestion>() {
            @Override
            public void onResponse(Call<ParentQuestion> call, Response<ParentQuestion> response) {
                if (response.isSuccessful()) {
                    MldStudentQuizQuestions.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentQuestion> call, Throwable t) {
            }
        });
    }


    public void getQuizDetails(String authHeader, String user_id) {
        PostsClient.getINSTANCE().getQuizDetails("Bearer " + authHeader, user_id).enqueue(new Callback<ParentQuizDetails>() {
            @Override
            public void onResponse(Call<ParentQuizDetails> call, Response<ParentQuizDetails> response) {
                if (response.isSuccessful()) {
                    MldParentQuizDetails.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentQuizDetails> call, Throwable t) {
            }
        });
    }

    public void deleteQuestion(String authHeader, String user_id) {
        PostsClient.getINSTANCE().deleteQuestion("Bearer " + authHeader, user_id).enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if (response.isSuccessful()) {
                    MldParent.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {
            }
        });
    }


    public void updateQuestion(String authHeader, String question_id, String question, String answers) {
        PostsClient.getINSTANCE().updateQuestion("Bearer " + authHeader, question_id, question, answers).enqueue(new Callback<ParentQuestion>() {
            @Override
            public void onResponse(Call<ParentQuestion> call, Response<ParentQuestion> response) {
                if (response.isSuccessful()) {
                    MldPParentQuestion.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ParentQuestion> call, Throwable t) {
            }
        });
    }


    public void getStudentAllGrader(String authHeader, String user_id) {
        PostsClient.getINSTANCE().getStudentAllGrader("Bearer " + authHeader, user_id).enqueue(new Callback<ParentAllGrader>() {
            @Override
            public void onResponse(Call<ParentAllGrader> call, Response<ParentAllGrader> response) {
                if (response.isSuccessful()) {
                    MldParentAllGrader.setValue(response.body());
                    Log.d("TAG", "onResponse: 111");
                } else
                    Log.d("TAG", "onResponse: 222" + response.errorBody() + " /// " + response.body());
            }

            @Override
            public void onFailure(Call<ParentAllGrader> call, Throwable t) {
                Log.d("TAG", "onResponse: 333" + t.getMessage());
            }
        });
    }


    public void averageAllSubjects(String authHeader, int user_id) {
        PostsClient.getINSTANCE().averageAllSubjects("Bearer " + authHeader, user_id).enqueue(new Callback<ParentAverage>() {
            @Override
            public void onResponse(Call<ParentAverage> call, Response<ParentAverage> response) {
                if (response.isSuccessful()) {
                    MldParentAverage.setValue(response.body());
                    Log.d("TAG", "onResponse: 111");
                } else
                    Log.d("TAG", "onResponse: 222" + call.request() + " /// " + response.body());

            }

            @Override
            public void onFailure(Call<ParentAverage> call, Throwable t) {
                Log.d("TAG", "onResponse: 333" + t.getMessage());

            }
        });
    }

    public void changeQuizeTime(String authHeader, String question_id, String question) {
        PostsClient.getINSTANCE().changeQuizeTime("Bearer " + authHeader, question_id, question).enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if (response.isSuccessful()) {
                    changeQuizeTime.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {
            }
        });
    }

    public void uploadContent(String authHeader,  MultipartBody.Part image_user) {
        PostsClient.getINSTANCE().uploadContent("Bearer " + authHeader, image_user).enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if (response.isSuccessful()) {
                    uploadContent.setValue(response.body());
                    Log.d("TAG11", "111onResponse: " + call.request());
                } else {
                    try {
                        // MldErrorLogin.setValue(response.body().getSuccess());
                        Log.d("TAG11", "222onResponse: " + response.errorBody().string() + response.message());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {
                Log.d("TAG11", "333onResponse: " + t.getMessage() );
            }
        });
    }


    /////////////////////////////////////////////////////////////////////////////
    //////////////////////////      Result       ////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    public void expected_gpa(String arabic, String english, String religion, String history, String math, String physics
            , String chemistry, String biology, String computer, String social) {
        PostsClient2.getINSTANCE().expected_gpa(arabic, english, religion, history, math, physics, chemistry, biology, computer, social).enqueue(new Callback<ExpectedGpa>() {
            @Override
            public void onResponse(Call<ExpectedGpa> call, Response<ExpectedGpa> response) {
                if (response.isSuccessful()) {
                    MldExpectedGpa.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ExpectedGpa> call, Throwable t) {
            }
        });
    }

    public void performance(String gpa, String assessment) {
        PostsClient2.getINSTANCE().performance(gpa, assessment).enqueue(new Callback<Performance>() {
            @Override
            public void onResponse(Call<Performance> call, Response<Performance> response) {
                if (response.isSuccessful()) {
                    MldPerformance.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Performance> call, Throwable t) {
            }
        });
    }


}