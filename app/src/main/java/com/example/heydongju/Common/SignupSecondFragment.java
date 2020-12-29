package com.example.heydongju.Common;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.heydongju.R;

public class SignupSecondFragment extends Fragment {

    private EditText phone_text;
    private EditText confirm;

    private TextView good;
    private TextView time;
    private ImageView continu;
    private ImageView resend;
    private ImageView send;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.common_signup_second, container, false);


        phone_text= (EditText) root.findViewById(R.id.phone);
        confirm= (EditText) root.findViewById(R.id.confirm);
        good= (TextView) root.findViewById(R.id.good);
        time= (TextView) root.findViewById(R.id.time);

        continu= (ImageView) root.findViewById(R.id.continu);
        resend= (ImageView) root.findViewById(R.id.resend);
        send= (ImageView) root.findViewById(R.id.send);


        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(millisUntilFinished / 1000+"초 안에 인증을 완료해주세요");
            }

            public void onFinish() {
                time.setText("시간이 종료되었습니다. 재전송 요청을 해주세요!");
            }
        }.start();

        // Navigation.findNavController(root).navigate(R.id.action_nav_second_to_nav_signup_third);

        return root;
    }

}
