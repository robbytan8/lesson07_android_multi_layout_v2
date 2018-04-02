package com.robby.lesson06_android_multi_layout_v2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robby.lesson06_android_multi_layout_v2.entity.Student;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Robby Tan
 */

public class StudentDataFragment extends Fragment {

    final static String ARG_STUDENT = "parcel_student";

    @BindView(R.id.tv_id)
    TextView lblId;
    @BindView(R.id.tv_name)
    TextView lblName;
    @BindView(R.id.tv_address)
    TextView lblAddress;
    @BindView(R.id.tv_phone)
    TextView lblPhone;
    @BindView(R.id.tv_department)
    TextView lblDepartment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null && getArguments().containsKey(StudentDataFragment.ARG_STUDENT)) {
            Student student = getArguments().getParcelable(StudentDataFragment.ARG_STUDENT);
            lblId.setText(student.getId());
            lblName.setText(student.getFullName());
            lblAddress.setText(student.getAddress());
            lblPhone.setText(student.getPhone());
            lblDepartment.setText(student.getDepartment());
        }
    }

}
