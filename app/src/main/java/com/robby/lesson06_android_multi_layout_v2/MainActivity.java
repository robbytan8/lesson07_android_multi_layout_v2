package com.robby.lesson06_android_multi_layout_v2;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robby.lesson06_android_multi_layout_v2.adapter.StudentAdapter;
import com.robby.lesson06_android_multi_layout_v2.entity.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Robby Tan
 */

public class MainActivity extends AppCompatActivity implements StudentAdapter.StudentDataClickedListener {

    private ListStudentFragment listStudentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_list_container, getListStudentFragment());
        populateStudentsData();
        transaction.commit();
    }

    public ListStudentFragment getListStudentFragment() {
        if (listStudentFragment == null) {
            listStudentFragment = new ListStudentFragment();
            listStudentFragment.getStudentAdapter().setStudentDataClickedListener(this);
        }
        return listStudentFragment;
    }

    private void populateStudentsData() {
        try {
            InputStream inputStream = getAssets().open("students.json");
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<Student> students = new ArrayList<>();
            students.addAll(Arrays.asList(objectMapper.readValue(inputStream, Student[].class)));
            getListStudentFragment().getStudentAdapter().setStudents(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStudentDataClicked(Student student) {
        if (findViewById(R.id.fragment_data) != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(StudentDataFragment.ARG_STUDENT, student);
            StudentDataFragment fragment = new StudentDataFragment();
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_data, fragment);
            transaction.commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelable(StudentDataFragment.ARG_STUDENT, student);
            StudentDataFragment fragment = new StudentDataFragment();
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_list_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
