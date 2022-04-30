package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devsaleh.performancescale.Model.ParentAllGrader;
import com.devsaleh.performancescale.Model.ParentItem;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.Ui.StudentFragment;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.ActivityAddStudentMarksBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

public class addStudentMarks extends AppCompatActivity {
    ActivityAddStudentMarksBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String token;
    private int user_id;
    ProgressBar progressBar;
    private String y1_ar, y1_en, y1_ma, y1_ch, y1_ph, y1_bi, y1_re, y1_so_st, y1_co, y1_sp_ar, y1_ge, y1_hi, y1_in_dr, y1_in_sc;
    private String y2_ar, y2_en, y2_ma, y2_ch, y2_ph, y2_bi, y2_re, y2_so_st, y2_co, y2_sp_ar, y2_ge, y2_hi, y2_in_dr, y2_in_sc;
    private String y3_ar, y3_en, y3_ma, y3_ch, y3_ph, y3_bi, y3_re, y3_so_st, y3_co, y3_sp_ar, y3_ge, y3_hi, y3_in_dr, y3_in_sc;
    private String y4_ar, y4_en, y4_ma, y4_ch, y4_ph, y4_bi, y4_re, y4_so_st, y4_co, y4_sp_ar, y4_ge, y4_hi, y4_in_dr, y4_in_sc;
    private String y5_ar, y5_en, y5_ma, y5_ch, y5_ph, y5_bi, y5_re, y5_so_st, y5_co, y5_sp_ar, y5_ge, y5_hi, y5_in_dr, y5_in_sc;
    private String y6_ar, y6_en, y6_ma, y6_ch, y6_ph, y6_bi, y6_re, y6_so_st, y6_co, y6_sp_ar, y6_ge, y6_hi, y6_in_dr, y6_in_sc;
    private String grades1, grades2, grades3, grades4, grades5, grades6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStudentMarksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        user_id = intent.getIntExtra(StudentFragment.STUDENT_ID, 1);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        editor.apply();
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite ThreeBounce1 = new Circle();
        progressBar.setIndeterminateDrawable(ThreeBounce1);
            /*
        Arabic 1
        English 2
        Maths 3
        Chemistry 4
        Physics 5
        Biology 6
        Religion 7
        Social Studies 8
        Computer 9
        Special Arabic 10
        Geography 11
        History 12
        Industrial Drawing 13
        sciences Drawing 14
         */


        //--- get marks
        postViewModel.getStudentAllGrader(token, String.valueOf(user_id));
        postViewModel.MldParentAllGrader.observe(addStudentMarks.this, new Observer<ParentAllGrader>() {
            @Override
            public void onChanged(ParentAllGrader parentAllGrader) {
                if (parentAllGrader.getStatus()) {
                    if (parentAllGrader.getYear1().size() != 0) {
                        binding.marksEtY1Ar.setText(String.valueOf(parentAllGrader.getYear1().get(0).getGrade()));
                        binding.marksEtY1En.setText(String.valueOf(parentAllGrader.getYear1().get(1).getGrade()));
                        binding.marksEtY1Ma.setText(String.valueOf(parentAllGrader.getYear1().get(2).getGrade()));
                        binding.marksEtY1Ch.setText(String.valueOf(parentAllGrader.getYear1().get(3).getGrade()));
                        binding.marksEtY1Ph.setText(String.valueOf(parentAllGrader.getYear1().get(4).getGrade()));
                        binding.marksEtY1Bi.setText(String.valueOf(parentAllGrader.getYear1().get(5).getGrade()));
                        binding.marksEtY1Re.setText(String.valueOf(parentAllGrader.getYear1().get(6).getGrade()));
                        binding.marksEtY1SoS.setText(String.valueOf(parentAllGrader.getYear1().get(7).getGrade()));
                        binding.marksEtY1Co.setText(String.valueOf(parentAllGrader.getYear1().get(8).getGrade()));
                        binding.marksEtY1SpAr.setText(String.valueOf(parentAllGrader.getYear1().get(9).getGrade()));
                        if (parentAllGrader.getYear1().size() > 10) {
                            binding.marksEtY1Ge.setText(String.valueOf(parentAllGrader.getYear1().get(10).getGrade()));
                            binding.marksEtY1Hi.setText(String.valueOf(parentAllGrader.getYear1().get(11).getGrade()));
                            binding.marksEtY1InDr.setText(String.valueOf(parentAllGrader.getYear1().get(12).getGrade()));
                            binding.marksEtY1ScDr.setText(String.valueOf(parentAllGrader.getYear1().get(13).getGrade()));
                        }

                    }
                    if (parentAllGrader.getYear2().size() != 0) {
                        binding.marksEtY2Ar.setText(String.valueOf(parentAllGrader.getYear2().get(0).getGrade()));
                        binding.marksEtY2En.setText(String.valueOf(parentAllGrader.getYear2().get(1).getGrade()));
                        binding.marksEtY2Ma.setText(String.valueOf(parentAllGrader.getYear2().get(2).getGrade()));
                        binding.marksEtY2Ch.setText(String.valueOf(parentAllGrader.getYear2().get(3).getGrade()));
                        binding.marksEtY2Ph.setText(String.valueOf(parentAllGrader.getYear2().get(4).getGrade()));
                        binding.marksEtY2Bi.setText(String.valueOf(parentAllGrader.getYear2().get(5).getGrade()));
                        binding.marksEtY2Re.setText(String.valueOf(parentAllGrader.getYear2().get(6).getGrade()));
                        binding.marksEtY2SoS.setText(String.valueOf(parentAllGrader.getYear2().get(7).getGrade()));
                        binding.marksEtY2Co.setText(String.valueOf(parentAllGrader.getYear2().get(8).getGrade()));
                        binding.marksEtY2SpAr.setText(String.valueOf(parentAllGrader.getYear2().get(9).getGrade()));
                        if (parentAllGrader.getYear2().size() > 10) {
                            binding.marksEtY2Ge.setText(String.valueOf(parentAllGrader.getYear2().get(10).getGrade()));
                            binding.marksEtY2Hi.setText(String.valueOf(parentAllGrader.getYear2().get(11).getGrade()));
                            binding.marksEtY2InDr.setText(String.valueOf(parentAllGrader.getYear2().get(12).getGrade()));
                            binding.marksEtY2ScDr.setText(String.valueOf(parentAllGrader.getYear2().get(13).getGrade()));
                        }

                    }
                    if (parentAllGrader.getYear3().size() != 0) {
                        binding.marksEtY3Ar.setText(String.valueOf(parentAllGrader.getYear3().get(0).getGrade()));
                        binding.marksEtY3En.setText(String.valueOf(parentAllGrader.getYear3().get(1).getGrade()));
                        binding.marksEtY3Ma.setText(String.valueOf(parentAllGrader.getYear3().get(2).getGrade()));
                        binding.marksEtY3Ch.setText(String.valueOf(parentAllGrader.getYear3().get(3).getGrade()));
                        binding.marksEtY3Ph.setText(String.valueOf(parentAllGrader.getYear3().get(4).getGrade()));
                        binding.marksEtY3Bi.setText(String.valueOf(parentAllGrader.getYear3().get(5).getGrade()));
                        binding.marksEtY3Re.setText(String.valueOf(parentAllGrader.getYear3().get(6).getGrade()));
                        binding.marksEtY3SoS.setText(String.valueOf(parentAllGrader.getYear3().get(7).getGrade()));
                        binding.marksEtY3Co.setText(String.valueOf(parentAllGrader.getYear3().get(8).getGrade()));
                        binding.marksEtY3SpAr.setText(String.valueOf(parentAllGrader.getYear3().get(9).getGrade()));
                        if (parentAllGrader.getYear3().size() > 10) {
                            binding.marksEtY3Ge.setText(String.valueOf(parentAllGrader.getYear3().get(10).getGrade()));
                            binding.marksEtY3Hi.setText(String.valueOf(parentAllGrader.getYear3().get(11).getGrade()));
                            binding.marksEtY3InDr.setText(String.valueOf(parentAllGrader.getYear3().get(12).getGrade()));
                            binding.marksEtY3ScDr.setText(String.valueOf(parentAllGrader.getYear3().get(13).getGrade()));
                        }

                    }
                    if (parentAllGrader.getYear4().size() != 0) {
                        binding.marksEtY4Ar.setText(String.valueOf(parentAllGrader.getYear4().get(0).getGrade()));
                        binding.marksEtY4En.setText(String.valueOf(parentAllGrader.getYear4().get(1).getGrade()));
                        binding.marksEtY4Ma.setText(String.valueOf(parentAllGrader.getYear4().get(2).getGrade()));
                        binding.marksEtY4Ch.setText(String.valueOf(parentAllGrader.getYear4().get(3).getGrade()));
                        binding.marksEtY4Ph.setText(String.valueOf(parentAllGrader.getYear4().get(4).getGrade()));
                        binding.marksEtY4Bi.setText(String.valueOf(parentAllGrader.getYear4().get(5).getGrade()));
                        binding.marksEtY4Re.setText(String.valueOf(parentAllGrader.getYear4().get(6).getGrade()));
                        binding.marksEtY4SoS.setText(String.valueOf(parentAllGrader.getYear4().get(7).getGrade()));
                        binding.marksEtY4Co.setText(String.valueOf(parentAllGrader.getYear4().get(8).getGrade()));
                        binding.marksEtY4SpAr.setText(String.valueOf(parentAllGrader.getYear4().get(9).getGrade()));
                        if (parentAllGrader.getYear4().size() > 10) {
                            binding.marksEtY4Ge.setText(String.valueOf(parentAllGrader.getYear4().get(10).getGrade()));
                            binding.marksEtY4Hi.setText(String.valueOf(parentAllGrader.getYear4().get(11).getGrade()));
                            binding.marksEtY4InDr.setText(String.valueOf(parentAllGrader.getYear4().get(12).getGrade()));
                            binding.marksEtY4ScDr.setText(String.valueOf(parentAllGrader.getYear4().get(13).getGrade()));
                        }

                    }
                    if (parentAllGrader.getYear5().size() != 0) {
                        binding.marksEtY5Ar.setText(String.valueOf(parentAllGrader.getYear5().get(0).getGrade()));
                        binding.marksEtY5En.setText(String.valueOf(parentAllGrader.getYear5().get(1).getGrade()));
                        binding.marksEtY5Ma.setText(String.valueOf(parentAllGrader.getYear5().get(2).getGrade()));
                        binding.marksEtY5Ch.setText(String.valueOf(parentAllGrader.getYear5().get(3).getGrade()));
                        binding.marksEtY5Ph.setText(String.valueOf(parentAllGrader.getYear5().get(4).getGrade()));
                        binding.marksEtY5Bi.setText(String.valueOf(parentAllGrader.getYear5().get(5).getGrade()));
                        binding.marksEtY5Re.setText(String.valueOf(parentAllGrader.getYear5().get(6).getGrade()));
                        binding.marksEtY5SoS.setText(String.valueOf(parentAllGrader.getYear5().get(7).getGrade()));
                        binding.marksEtY5Co.setText(String.valueOf(parentAllGrader.getYear5().get(8).getGrade()));
                        binding.marksEtY5SpAr.setText(String.valueOf(parentAllGrader.getYear5().get(9).getGrade()));
                        if (parentAllGrader.getYear5().size() > 10) {
                            binding.marksEtY5Ge.setText(String.valueOf(parentAllGrader.getYear5().get(10).getGrade()));
                            binding.marksEtY5Hi.setText(String.valueOf(parentAllGrader.getYear5().get(11).getGrade()));
                            binding.marksEtY5InDr.setText(String.valueOf(parentAllGrader.getYear5().get(12).getGrade()));
                            binding.marksEtY5ScDr.setText(String.valueOf(parentAllGrader.getYear5().get(13).getGrade()));
                        }


                    }
                    if (parentAllGrader.getYear6().size() != 0) {
                        binding.marksEtY6Ar.setText(String.valueOf(parentAllGrader.getYear6().get(0).getGrade()));
                        binding.marksEtY6En.setText(String.valueOf(parentAllGrader.getYear6().get(1).getGrade()));
                        binding.marksEtY6Ma.setText(String.valueOf(parentAllGrader.getYear6().get(2).getGrade()));
                        binding.marksEtY6Ch.setText(String.valueOf(parentAllGrader.getYear6().get(3).getGrade()));
                        binding.marksEtY6Ph.setText(String.valueOf(parentAllGrader.getYear6().get(4).getGrade()));
                        binding.marksEtY6Bi.setText(String.valueOf(parentAllGrader.getYear6().get(5).getGrade()));
                        binding.marksEtY6Re.setText(String.valueOf(parentAllGrader.getYear6().get(6).getGrade()));
                        binding.marksEtY6SoS.setText(String.valueOf(parentAllGrader.getYear6().get(7).getGrade()));
                        binding.marksEtY6Co.setText(String.valueOf(parentAllGrader.getYear6().get(8).getGrade()));
                        binding.marksEtY6SpAr.setText(String.valueOf(parentAllGrader.getYear6().get(9).getGrade()));
                        if (parentAllGrader.getYear6().size() > 10) {
                            binding.marksEtY6Ge.setText(String.valueOf(parentAllGrader.getYear6().get(10).getGrade()));
                            binding.marksEtY6Hi.setText(String.valueOf(parentAllGrader.getYear6().get(11).getGrade()));
                            binding.marksEtY6InDr.setText(String.valueOf(parentAllGrader.getYear6().get(12).getGrade()));
                            binding.marksEtY6ScDr.setText(String.valueOf(parentAllGrader.getYear6().get(13).getGrade()));
                        }


                    }

                } else
                    Toast.makeText(addStudentMarks.this, parentAllGrader.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        //--- post marks
        binding.addStudentMarksBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.addStudentMarksBtnSave.setEnabled(false);
                //-------------------------------------------------------- Year 1
                y1_ar = binding.marksEtY1Ar.getText().toString();
                y1_en = binding.marksEtY1En.getText().toString();
                y1_ma = binding.marksEtY1Ma.getText().toString();
                y1_ch = binding.marksEtY1Ch.getText().toString();
                y1_ph = binding.marksEtY1Ph.getText().toString();
                y1_bi = binding.marksEtY1Bi.getText().toString();
                y1_re = binding.marksEtY1Re.getText().toString();
                y1_so_st = binding.marksEtY1SoS.getText().toString();
                y1_co = binding.marksEtY1Co.getText().toString();
                y1_sp_ar = binding.marksEtY1SpAr.getText().toString();
                y1_ge = binding.marksEtY1Ge.getText().toString();
                y1_hi = binding.marksEtY1Hi.getText().toString();
                y1_in_dr = binding.marksEtY1InDr.getText().toString();
                y1_in_sc = binding.marksEtY1ScDr.getText().toString();
                grades1 = y1_ar + "," + y1_en + "," + y1_ma + "," + y1_ch + "," + y1_ph + "," + y1_bi + "," + y1_re + "," + y1_so_st + "," + y1_co
                        + "," + y1_sp_ar + "," + y1_ge + "," + y1_hi + "," + y1_in_dr + "," + y1_in_sc;

                //-------------------------------------------------------- Year 2
                y2_ar = binding.marksEtY2Ar.getText().toString();
                y2_en = binding.marksEtY2En.getText().toString();
                y2_ma = binding.marksEtY2Ma.getText().toString();
                y2_ch = binding.marksEtY2Ch.getText().toString();
                y2_ph = binding.marksEtY2Ph.getText().toString();
                y2_bi = binding.marksEtY2Bi.getText().toString();
                y2_re = binding.marksEtY2Re.getText().toString();
                y2_so_st = binding.marksEtY2SoS.getText().toString();
                y2_co = binding.marksEtY2Co.getText().toString();
                y2_sp_ar = binding.marksEtY2SpAr.getText().toString();
                y2_ge = binding.marksEtY2Ge.getText().toString();
                y2_hi = binding.marksEtY2Hi.getText().toString();
                y2_in_dr = binding.marksEtY2InDr.getText().toString();
                y2_in_sc = binding.marksEtY2ScDr.getText().toString();
                grades2 = y2_ar + "," + y2_en + "," + y2_ma + "," + y2_ch + "," + y2_ph + "," + y2_bi + "," + y2_re + "," + y2_so_st + "," + y2_co
                        + "," + y2_sp_ar + "," + y2_ge + "," + y2_hi + "," + y2_in_dr + "," + y2_in_sc;

                //-------------------------------------------------------- Year 3
                y3_ar = binding.marksEtY3Ar.getText().toString();
                y3_en = binding.marksEtY3En.getText().toString();
                y3_ma = binding.marksEtY3Ma.getText().toString();
                y3_ch = binding.marksEtY3Ch.getText().toString();
                y3_ph = binding.marksEtY3Ph.getText().toString();
                y3_bi = binding.marksEtY3Bi.getText().toString();
                y3_re = binding.marksEtY3Re.getText().toString();
                y3_so_st = binding.marksEtY3SoS.getText().toString();
                y3_co = binding.marksEtY3Co.getText().toString();
                y3_sp_ar = binding.marksEtY3SpAr.getText().toString();
                y3_ge = binding.marksEtY3Ge.getText().toString();
                y3_hi = binding.marksEtY3Hi.getText().toString();
                y3_in_dr = binding.marksEtY3InDr.getText().toString();
                y3_in_sc = binding.marksEtY3ScDr.getText().toString();
                grades3 = y3_ar + "," + y3_en + "," + y3_ma + "," + y3_ch + "," + y3_ph + "," + y3_bi + "," + y3_re + "," + y3_so_st + "," + y3_co
                        + "," + y3_sp_ar + "," + y3_ge + "," + y3_hi + "," + y3_in_dr + "," + y3_in_sc;

                //-------------------------------------------------------- Year 4
                y4_ar = binding.marksEtY4Ar.getText().toString();
                y4_en = binding.marksEtY4En.getText().toString();
                y4_ma = binding.marksEtY4Ma.getText().toString();
                y4_ch = binding.marksEtY4Ch.getText().toString();
                y4_ph = binding.marksEtY4Ph.getText().toString();
                y4_bi = binding.marksEtY4Bi.getText().toString();
                y4_re = binding.marksEtY4Re.getText().toString();
                y4_so_st = binding.marksEtY4SoS.getText().toString();
                y4_co = binding.marksEtY4Co.getText().toString();
                y4_sp_ar = binding.marksEtY4SpAr.getText().toString();
                y4_ge = binding.marksEtY4Ge.getText().toString();
                y4_hi = binding.marksEtY4Hi.getText().toString();
                y4_in_dr = binding.marksEtY4InDr.getText().toString();
                y4_in_sc = binding.marksEtY4ScDr.getText().toString();
                grades4 = y4_ar + "," + y4_en + "," + y4_ma + "," + y4_ch + "," + y4_ph + "," + y4_bi + "," + y4_re + "," + y4_so_st + "," + y4_co
                        + "," + y4_sp_ar + "," + y4_ge + "," + y4_hi + "," + y4_in_dr + "," + y4_in_sc;

                //-------------------------------------------------------- Year 5
                y5_ar = binding.marksEtY5Ar.getText().toString();
                y5_en = binding.marksEtY5En.getText().toString();
                y5_ma = binding.marksEtY5Ma.getText().toString();
                y5_ch = binding.marksEtY5Ch.getText().toString();
                y5_ph = binding.marksEtY5Ph.getText().toString();
                y5_bi = binding.marksEtY5Bi.getText().toString();
                y5_re = binding.marksEtY5Re.getText().toString();
                y5_so_st = binding.marksEtY5SoS.getText().toString();
                y5_co = binding.marksEtY5Co.getText().toString();
                y5_sp_ar = binding.marksEtY5SpAr.getText().toString();
                y5_ge = binding.marksEtY5Ge.getText().toString();
                y5_hi = binding.marksEtY5Hi.getText().toString();
                y5_in_dr = binding.marksEtY5InDr.getText().toString();
                y5_in_sc = binding.marksEtY5ScDr.getText().toString();
                grades5 = y5_ar + "," + y5_en + "," + y5_ma + "," + y5_ch + "," + y5_ph + "," + y5_bi + "," + y5_re + "," + y5_so_st + "," + y5_co
                        + "," + y5_sp_ar + "," + y5_ge + "," + y5_hi + "," + y5_in_dr + "," + y5_in_sc;

                //-------------------------------------------------------- Year 6
                /*
                y6_ar = binding.marksEtY6Ar.getText().toString();
                y6_en = binding.marksEtY6En.getText().toString();
                y6_ma = binding.marksEtY6Ma.getText().toString();
                y6_ch = binding.marksEtY6Ch.getText().toString();
                y6_ph = binding.marksEtY6Ph.getText().toString();
                y6_bi = binding.marksEtY6Bi.getText().toString();
                y6_re = binding.marksEtY6Re.getText().toString();
                y6_so_st = binding.marksEtY6SoS.getText().toString();
                y6_co = binding.marksEtY6Co.getText().toString();
                y6_sp_ar = binding.marksEtY6SpAr.getText().toString();
                y6_ge = binding.marksEtY6Ge.getText().toString();
                y6_hi = binding.marksEtY6Hi.getText().toString();
                y6_in_dr = binding.marksEtY6InDr.getText().toString();
                y6_in_sc = binding.marksEtY6ScDr.getText().toString();
                grades6 = y6_ar + "," + y6_en + "," + y6_ma + "," + y6_ch + "," + y6_ph + "," + y6_bi + "," + y6_re + "," + y6_so_st + "," + y6_co
                        + "," + y6_sp_ar + "," + y6_ge + "," + y6_hi + "," + y6_in_dr + "," + y6_in_sc;
*/
                //-------------------------------------------------------- Request
                postViewModel.enterGradesByTeacher(token, String.valueOf(user_id), grades1, grades2, grades3, grades4, grades5, grades6);
                postViewModel.MldParentItem.observe(addStudentMarks.this, new Observer<ParentItem>() {
                    @Override
                    public void onChanged(ParentItem parentItem) {
                        if (parentItem.getStatus()) {
                            Toast.makeText(addStudentMarks.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                            binding.addStudentMarksBtnSave.setEnabled(true);
                            finish();
                        } else {
                            Toast.makeText(addStudentMarks.this, parentItem.getMessage(), Toast.LENGTH_SHORT).show();
                            binding.addStudentMarksBtnSave.setEnabled(true);
                        }
                    }
                });


            }
        });

    }
}