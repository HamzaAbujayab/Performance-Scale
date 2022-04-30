
package com.devsaleh.performancescale.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.devsaleh.performancescale.R;
import com.devsaleh.performancescale.Ui.ExamsFragment;
import com.devsaleh.performancescale.Ui.ProfileFragment;
import com.devsaleh.performancescale.Ui.StudentFragment;
import com.devsaleh.performancescale.databinding.ActivityHomeBinding;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class home extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_stu3));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_exams3));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_profile3));

        binding.bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {//WorkerProfile
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new StudentFragment();
                        break;
                    case 2:
                        fragment = new ExamsFragment();
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        break;

                }
                loadFragment(fragment);
            }
        });

       // binding.bottomNavigation.setCount(3, "");
        binding.bottomNavigation.show(1, true);
        binding.bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(HomeActivity.this, "You Clicked:"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(HomeActivity.this, "You Reslected:"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

}