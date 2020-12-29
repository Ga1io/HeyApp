package com.example.heydongju.Common;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.heydongju.R;

import org.w3c.dom.Text;

public class SignupFirstFragment extends Fragment {

    private EditText id_text;
    private EditText pw_text;
    private EditText pw_confirm_text;
    private TextView good;
    private ImageView confirm;
    private ImageView continu;
    private boolean confirmed;
    private boolean pw_confirmed;
    String strColor = "#FE2E2E";
    String strColor2 = "#7EA6C6";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.common_siginup_first, container, false);

        id_text = (EditText) root.findViewById(R.id.id);
        pw_text= (EditText) root.findViewById(R.id.pw);
        good= (TextView) root.findViewById(R.id.good);
        pw_confirm_text = (EditText) root.findViewById(R.id.pw_confirm);

        confirmed=false;

        confirm= (ImageView) root.findViewById(R.id.confirm);
        continu= (ImageView) root.findViewById(R.id.continu);

        pw_confirm_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(pw_text.getText().toString().equals(pw_confirm_text.getText().toString())&&pw_confirm_text.getText().toString().length()!=0){
                    good.setText("비밀 번호와 비밀 번호 확인이 일치합니다!");
                    good.setTextColor(Color.parseColor(strColor2));
                    pw_confirmed=true;
                }else{
                    good.setText("비밀 번호와 비밀 번호 확인이 일치하지않습니다.");
                    good.setTextColor(Color.parseColor(strColor));
                    pw_confirmed=false;

                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                if(pw_text.getText().toString().equals(pw_confirm_text.getText().toString())&&pw_confirm_text.getText().toString().length()!=0){
                    good.setText("비밀 번호와 비밀 번호 확인이 일치합니다!");
                    good.setTextColor(Color.parseColor(strColor2));
                    pw_confirmed=true;

                }else{
                    good.setText("비밀 번호와 비밀 번호 확인이 일치하지않습니다.");
                    good.setTextColor(Color.parseColor(strColor));
                    pw_confirmed=false;

                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                good.setText("비밀 번호 확인 해주세요.");
                good.setTextColor(Color.parseColor(strColor2));
                pw_confirmed=false;

            }
        });
        pw_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(pw_text.getText().toString().equals(pw_confirm_text.getText().toString())&&pw_confirm_text.getText().toString().length()!=0){
                    good.setText("비밀 번호와 비밀 번호 확인이 일치합니다!");
                    good.setTextColor(Color.parseColor(strColor2));
                    pw_confirmed=true;


                }else{
                    good.setText("비밀 번호와 비밀 번호 확인이 일치하지않습니다.");
                    good.setTextColor(Color.parseColor(strColor));
                    pw_confirmed=false;

                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                if(pw_text.getText().toString().equals(pw_confirm_text.getText().toString())&&pw_confirm_text.getText().toString().length()!=0){
                    good.setText("비밀 번호와 비밀 번호 확인이 일치합니다!");
                    good.setTextColor(Color.parseColor(strColor2));
                    pw_confirmed=true;


                }else{
                    good.setText("비밀 번호와 비밀 번호 확인이 일치하지않습니다.");
                    good.setTextColor(Color.parseColor(strColor));
                    pw_confirmed=false;

                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                good.setText("비밀 번호 확인 해주세요.");
                pw_confirmed=false;

                good.setTextColor(Color.parseColor(strColor2));
            }
        });



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //중복 검사 테스트를 만들어야함(서버랑 통신해서 하는거)
                //일단 그냥 confirmed를 바꾸게 더미


                confirmed=true;
            }
        });

        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmed&&pw_confirmed){
                    Bundle bundle = new Bundle();
                    String id=id_text.getText().toString();
                    String user_pw=pw_text.getText().toString();
                    bundle.putString("id", id);
                    bundle.putString("user_pw", user_pw);
                    Navigation.findNavController(root).navigate(R.id.action_nav_first_to_nav_signup_second,bundle);
                }
                else if(!confirmed){
                    Toast.makeText(getContext().getApplicationContext(), "아이디 중복 검사를 해주세요.", Toast.LENGTH_LONG).show();
                }
                else if(!pw_confirmed){
                    Toast.makeText(getContext().getApplicationContext(), "비밀 번호가 비밀 번호 확인과 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext().getApplicationContext(), "아이디와 비밀 번호의 검사를 해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });


        return root;
    }

}
