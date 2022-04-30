package com.devsaleh.performancescale.Ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.devsaleh.performancescale.Activity.addStudent;
import com.devsaleh.performancescale.Activity.addStudentMarks;
import com.devsaleh.performancescale.Activity.login;
import com.devsaleh.performancescale.Activity.result;
import com.devsaleh.performancescale.Adapter.StudentAdapter;
import com.devsaleh.performancescale.Model.Parent;
import com.devsaleh.performancescale.Model.ParentStudent;
import com.devsaleh.performancescale.Model.Student;
import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.ViewModel.PostViewModel;
import com.devsaleh.performancescale.databinding.FragmentStudentBinding;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;


public class StudentFragment extends Fragment {
    public static final String STUDENT_ID = "student_id";
    public static final String STUDENT_NAME = "student_name";
    public static final String STUDENT_SPECIALIZED = "student_sp";
    public static final String STUDENT_SCHOOL_NAME = "student_school_name";
    FragmentStudentBinding binding;
    PostViewModel postViewModel;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String user_id, token;
    private StudentAdapter studentAdapter;
    ProgressBar progressBar;
    private RadioButton rb_scientific, rb_literary, rb_industrial, rb_right, rb_false, rb_male, rb_female;
    private String section, direction, gender;
    private String stringFile = Environment.getExternalStorageDirectory().getPath() + File.separator + "Test.pdf";

    private static final int PIK_IMAGE_REQ_CODE_IMAGE_USER = 4;
    private String path;
    int req1 = 0; // 0 do not add image from studio
    MultipartBody.Part image_user_part;
    public static final String[] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStudentBinding.inflate(inflater, container, false);

        requestPermissions(StudentFragment.PERMISSION_STORAGE, 500);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sp.edit();
        token = sp.getString(login.TOKEN, "");
        editor.apply();
        progressBar = (ProgressBar) binding.spinKit;
        Sprite ThreeBounce1 = new Circle();
        progressBar.setIndeterminateDrawable(ThreeBounce1);
        binding.performanceScaleRvStudents.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.performanceScaleRvStudents.setHasFixedSize(true);
        getStudentMethod(null, null, null);
        /*
        binding.performanceScaleBtnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), addStudent.class));
            }
        });
        binding.performanceScaleBtnAddStudentMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), addStudentMarks.class));
            }
        });
        */
        binding.performanceScaleBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.performanceScaleBtnAddStudent1.setVisibility(View.VISIBLE);
                binding.performanceScaleBtnUploadStudent1.setVisibility(View.VISIBLE);
            }
        });
        binding.performanceScaleBtnAddStudent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), addStudent.class));
            }
        });
        //----------------------------------------------------------------------------------
        /*
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        */
        //----------------------------------------------------------------------------------
        binding.performanceScaleBtnUploadStudent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSharaFile();
            }
        });

        binding.performanceScaleBtnTvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogThem);
                View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_filter, (LinearLayout) requireActivity().findViewById(R.id.bottom_sheet_container));
                rb_scientific = bottomSheetView.findViewById(R.id.bottom_filter_rb_scientific);
                rb_literary = bottomSheetView.findViewById(R.id.bottom_filter_rb_literary);
                rb_industrial = bottomSheetView.findViewById(R.id.bottom_filter_rb_industrial);
                rb_right = bottomSheetView.findViewById(R.id.bottom_filter_rb_right);
                rb_false = bottomSheetView.findViewById(R.id.bottom_filter_rb_false);
                rb_male = bottomSheetView.findViewById(R.id.bottom_filter_rb_male);
                rb_female = bottomSheetView.findViewById(R.id.bottom_filter_rb_female);
                bottomSheetView.findViewById(R.id.bottom_filter_btn_save_change).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (rb_scientific.isChecked())
                            section = "scientific";
                        if (rb_literary.isChecked())
                            section = "literary";
                        if (rb_industrial.isChecked())
                            section = "industrial";
                        if (rb_right.isChecked())
                            direction = "right";
                        if (rb_false.isChecked())
                            direction = "false";
                        if (rb_male.isChecked())
                            gender = "male";
                        if (rb_female.isChecked())
                            gender = "female";
                        getStudentMethod(section, gender, direction);
                        bottomSheetDialog.dismiss();


                    }
                });
                bottomSheetView.findViewById(R.id.bottom_filter_btn_rest).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getStudentMethod(null, null, null);
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        return binding.getRoot();

    }

    private void getStudentMethod(String specialized, String gender, String direction) {
        postViewModel.getPerformanceScale(token, specialized, gender, direction);
        postViewModel.MldStudents.observe(getViewLifecycleOwner(), new Observer<ParentStudent>() {
            @Override
            public void onChanged(ParentStudent parentStudent) {
                if (parentStudent.getStatus()) {
                    progressBar.setVisibility(View.GONE);
                    studentAdapter = new StudentAdapter(parentStudent.getStudents(), getContext());
                    studentAdapter.setStudent_listener(new StudentAdapter.OnClickItemListener() {
                        @Override
                        public void onClick(Student student) {
                            //  Intent intent = new Intent(getContext(), addStudentMarks.class);
                            Intent intent = new Intent(getContext(), result.class);
                            intent.putExtra(STUDENT_ID, student.getId());
                            intent.putExtra(STUDENT_NAME, student.getName());
                            intent.putExtra(STUDENT_SPECIALIZED, student.getSpecialized());
                            intent.putExtra(STUDENT_SCHOOL_NAME, student.getSchoolName());
                            startActivity(intent);
                        }
                    });
                    studentAdapter.setMark_listener(new StudentAdapter.OnClickImageListener() {
                        @Override
                        public void onClick(Student student) {
                           // Intent intent = new Intent(getContext(), addStudentMarks.class);
                          //  intent.putExtra(STUDENT_ID, student.getId());
                           // startActivity(intent);
                        }
                    });
                    binding.performanceScaleRvStudents.setAdapter(studentAdapter);
                } else
                    Toast.makeText(getContext(), parentStudent.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void buttonSharaFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), PIK_IMAGE_REQ_CODE_IMAGE_USER);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
        //------------------------

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PIK_IMAGE_REQ_CODE_IMAGE_USER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            // path = uri.getPath();

            //-------------------------------
            String selectedImagePath = getPath(uri);
            if (selectedImagePath == null) {
                selectedImagePath = getImageFilePath(uri);
            }
            if (selectedImagePath.equals("null")) {
                selectedImagePath = getImageFilePath(uri);
            }
            File file2 = new File(selectedImagePath);
            //-------------------------------
            /*
            File path2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file = new File(path2, path);
            try {
                // Make sure the Pictures directory exists.
                file2.mkdirs();
                @SuppressLint("ResourceType") InputStream is = getResources().openRawResource(R.drawable.add_q11);
                OutputStream os = new FileOutputStream(file);
                byte[] data1 = new byte[is.available()];
                is.read(data1);
                os.write(data1);
                is.close();
                os.close();

                // Tell the media scanner about the new file so that it is
                // immediately available to the user.
                MediaScannerConnection.scanFile(getContext(),
                        new String[] { file.toString() }, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("ExternalStorage", "Scanned " + path + ":");
                                Log.i("ExternalStorage", "-> uri=" + uri);
                            }
                        });
            } catch (IOException e) {
                // Unable to create file, likely because external storage is
                // not currently mounted.
                Log.w("ExternalStorage", "Error writing " + file, e);
            }
            */
            //-------------------------------
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
            image_user_part = MultipartBody.Part.createFormData("uploaded_file", file2.getName(), requestFile);

            postViewModel.uploadContent(token, image_user_part);
            postViewModel.uploadContent.observe(getViewLifecycleOwner(), new Observer<Parent>() {
                @Override
                public void onChanged(Parent parent) {
                    // TODO
                    // post excel file
                    //Toast.makeText(getContext(), parent.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "$$", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(getContext(), "You haven't picked File", Toast.LENGTH_LONG).show();
        }
    }

    // UPDATED!
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    // UPDATED!
    public String getImageFilePath(Uri uri) {
        String imagePath = "";
        File file = new File(uri.getPath());
        String[] filePath = file.getPath().split(":");
        String image_id = filePath[filePath.length - 1];
        Cursor cursor = getActivity().getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            imagePathList.add(imagePath);
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagePath;
    }

}