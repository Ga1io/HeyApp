package com.example.heydongju.Common;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.heydongju.R;
import com.example.heydongju.Server.ApiInterface;
import com.example.heydongju.Data.ReqData;
import com.example.heydongju.Data.ResData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    ViewGroup root;
    ImageButton login = null;
    ImageButton join = null;
    ImageButton without = null;
    TextView findid = null;
    TextView findpw = null;
    ApiInterface api;


    EditText mEmailView;
    EditText mPasswordView;
    String mode;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.common_login, container, false);

        mode = getArguments().getString("mode");

        mEmailView = (EditText) root.findViewById(R.id.id);
        mPasswordView = (EditText) root.findViewById(R.id.pw);

        mEmailView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }

        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    //attemptLogin();
                    return true;
                }
                return false;
            }
        });


        login = (ImageButton) root.findViewById(R.id.login);
        join = (ImageButton) root.findViewById(R.id.join);
        findid = (TextView) root.findViewById(R.id.findId);
        findpw = (TextView) root.findViewById(R.id.findPw);


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.equals("deliver")){
                //    Navigation.findNavController(view).navigate(R.id.action_nav_login_to_nav_deliver_home);
                }else{
                //    Navigation.findNavController(view).navigate(R.id.action_nav_login_to_nav_deliver_home);

                }

                //  activity.requestPostLogin(userID,userPW);
              /*  String id, pw;
                id = mEmailView.getText().toString();
                pw= mPasswordView.getText().toString();
                requestPost(id, pw);*/
            }
        });

        return root;
    }
    public void requestPost(String id, String pw) {
        ReqData reqData = new ReqData( id, pw);
        Call<ResData> call = api.requestPostLogin( reqData );
        Log.e("sex","뭐냐고?");

        Log.e("sex",id);
        Log.e("sex",pw);
        // 비동기로 백그라운드 쓰레드로 동작
        call.enqueue( new Callback<ResData>() {
            // 통신성공 후 텍스트뷰에 결과값 출력

            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                if(response.code()==200){
                    Log.e("sex","뭐노?c");

                }
                else if(response.code()==202){


                    Log.e("sex","뭐노?cc");

                    Log.e("sex", String.valueOf(response.body().getId()));
                    Log.e("sex", String.valueOf(response.body().getPassword()));

                }


                else if(response.code()==401){
                    Log.e("sex","뭐노?");

                    Toast.makeText(getContext().getApplicationContext(), "없는 아이디입니다.", Toast.LENGTH_LONG).show();
                }
                else if(response.code()==402){
                    Log.e("sex","뭐노?");

                    Toast.makeText(getContext().getApplicationContext(), "계정이 비활성 상태이거나 비밀번호가 틀립니다.", Toast.LENGTH_LONG).show();

                }
                else{
                    Log.e("serwerwqerweqrewqex", String.valueOf(response.code()));

                    Toast.makeText(getContext().getApplicationContext(), "잘못된 아이디나 비밀번호입니다.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(), "네트워크 연결을 확인해주세요.", Toast.LENGTH_LONG).show();
                Log.e("penis", t.getLocalizedMessage());
                Log.e("bozi", t.getMessage());
                t.printStackTrace();

            }
        } );
    }

}
