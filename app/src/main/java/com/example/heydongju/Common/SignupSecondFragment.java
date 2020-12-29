package com.example.heydongju.Common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.heydongju.R;

public class SignupSecondFragment extends Fragment {

    private EditText id_text;
    private EditText pw_text;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.common_signup_second, container, false);

       // Navigation.findNavController(root).navigate(R.id.action_nav_second_to_nav_signup_third);

        return root;
    }

}
