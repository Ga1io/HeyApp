package com.example.heydongju.Common;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.heydongju.FrontActivity;
import com.example.heydongju.R;
import com.example.heydongju.Server.ApiInterface;
import com.example.heydongju.Server.HttpClient;
import com.example.heydongju.Data.JoinData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinFragment extends Fragment {

    private EditText id_text;
    private EditText pw_text;
    private EditText email_text;
    private EditText pw_confirm_text;
    private EditText name_text;
    private String birth_text;
    private TextView birth;
    private EditText cauid_text;
    private EditText phone_text;
    Spinner spinner = null;
    RelativeLayout join = null;
    Button btnYearMonthPicker;
    ApiInterface api;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            birth_text=year+"-"+monthOfYear+"-"+dayOfMonth;
            Log.d("TedPark", birth_text);
            btnYearMonthPicker.setText(birth_text);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sign_in, container, false);
        id_text = (EditText) root.findViewById(R.id.id);
        pw_text= (EditText) root.findViewById(R.id.pw);
        pw_confirm_text = (EditText) root.findViewById(R.id.pw_confirm);
        name_text = (EditText) root.findViewById(R.id.name);
        cauid_text = (EditText) root.findViewById(R.id.cauid);
        email_text=(EditText) root.findViewById(R.id.email);
        phone_text=(EditText) root.findViewById(R.id.phone);
        spinner = (Spinner)root.findViewById(R.id.spinner);
        api = HttpClient.getRetrofit().create( ApiInterface.class );
        join = (RelativeLayout) root.findViewById(R.id.join);



        btnYearMonthPicker = root.findViewById(R.id.btn_year_month_picker);


        btnYearMonthPicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyYearMonthPickerDialog pd = new MyYearMonthPickerDialog();
                pd.setListener(d);
                pd.show(getActivity().getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id=id_text.getText().toString();
                String user_pw=pw_text.getText().toString();
                String pw_confirm=pw_confirm_text.getText().toString();
                String userCauId=cauid_text.getText().toString();
                String userEmail= email_text.getText().toString();
                String userName=name_text.getText().toString();
                String userBirth=birth_text;
                String userPhone=phone_text.getText().toString();

                requestPost(view, id, user_pw, pw_confirm, userName, userCauId, userBirth, userEmail, userPhone);
            }
        });
        return root;
    }

    public void requestPost(View view, String id, String userPw, String pwConfirm, String userName, String userCauId, String userBirth, String userEmail, String userPhone) {
        JoinData joinData = new JoinData(id, userPw, pwConfirm, userName, userCauId, userBirth, userEmail, userPhone);
        Call<JoinData> call = api.requestJoin(joinData);
        Log.d("id", id);
        Log.d("userPw", userPw);
        Log.d("pwConfirm", pwConfirm);
        Log.d("userName", userName);
        Log.e("userCauId",userCauId);
        Log.d("userBirth", userBirth);
        Log.d("userEmail", userEmail);
        Log.d("phoneNum", userPhone);


        call.enqueue( new Callback<JoinData>() {
            @Override
            public void onResponse(Call<JoinData> call, Response<JoinData> response) {

                if(response.code()==200){
                    Toast.makeText(getContext().getApplicationContext(), "회원가입 성공입니다. 가입하신 이메일로 인증 후에 로그인 가능합니다.", Toast.LENGTH_LONG).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("join", "join");
                    Navigation.findNavController(view).navigate(R.id.action_nav_sign_to_nav_login,bundle);

                }else if(response.code()==401){
                    Toast.makeText(getContext().getApplicationContext(), "아이디 혹은 이메일 중복입니다.", Toast.LENGTH_LONG).show();
                    Log.d("error1",String.valueOf(response.body()));
                }
                else if(response.code()==402){
                    Toast.makeText(getContext().getApplicationContext(), "비밀번호가 비밀번호 재입력과 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    Log.d("error2", String.valueOf(response.body()));
                }
                else{
                    Toast.makeText(getContext().getApplicationContext(), "올바르지 않은 정보입니다..", Toast.LENGTH_LONG).show();
                    Log.d("error3", String.valueOf(response.body()));

                }
            }

            @Override
            public void onFailure(Call<JoinData> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(), "네트워크 연결을 확인해주세요.", Toast.LENGTH_LONG).show();
            }
        } );
    }




}
