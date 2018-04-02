package com.robby.lesson06_android_multi_layout_v2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robby.lesson06_android_multi_layout_v2.adapter.StudentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Robby Tan
 */

public class ListStudentFragment extends Fragment {

    @BindView(R.id.rv_students)
    RecyclerView rvStudents;
    private StudentAdapter studentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_student, container, false);
        ButterKnife.bind(this, rootView);
        rvStudents = rootView.findViewById(R.id.rv_students);
        rvStudents.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStudents.setAdapter(getStudentAdapter());
        return rootView;
    }

    public StudentAdapter getStudentAdapter() {
        if (studentAdapter == null) {
            studentAdapter = new StudentAdapter();
        }
        return studentAdapter;
    }
}
