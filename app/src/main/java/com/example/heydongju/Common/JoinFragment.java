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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
    private int country_no;
    private EditText spicy_text;
    Spinner spinner = null;
    ImageButton join = null;
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
        spicy_text = (EditText) root.findViewById(R.id.cauid);
        email_text=(EditText) root.findViewById(R.id.email);
        spinner = (Spinner)root.findViewById(R.id.spinner);
        api = HttpClient.getRetrofit().create( ApiInterface.class );
        join = (ImageButton) root.findViewById(R.id.join);

        spicy_text.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "5")});


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
                String userEmail= email_text.getText().toString();
                String userName=name_text.getText().toString();
                String userBirth=birth_text;
                int userSpicy=0;

                char tmp;
                boolean digit=true;
                for(int i = 0 ; i < spicy_text.getText().toString().length() ; i++) { //입력받은 문자열인 input의 길이만큼 반복
                    tmp = spicy_text.getText().toString().charAt(i); //한글자씩 검사하기 위해서 char형 변수인 tmp에 임시저장
                    if(Character.isDigit(tmp) == false) { //문자열이 숫자가 아닐 경우
                        digit = false; //output의 값을 false로 바꿈
                    }
                }
                if(spicy_text.getText().toString().length()==0){
                    digit=false;
                }
                if(!digit){
                    userSpicy=0;
                }
                else{
                    userSpicy=Integer.parseInt(spicy_text.getText().toString());
                }
                int country_no;
                String country=spinner.getSelectedItem().toString();
                if(country=="America"){
                    country_no=1;
                }else{
                    country_no=2;
                }

                requestPost(id, user_pw, pw_confirm, userEmail, userName, userBirth, userSpicy, country_no);
            }
        });
        return root;
    }

    public void requestPost(String id, String userPw, String pwConfirm, String userEmail, String userName, String userBirth, int userSpicy, int countryNo) {
        JoinData joinData = new JoinData(id, userPw, pwConfirm, userEmail, userName, userBirth, userSpicy, countryNo);
        Call<JoinData> call = api.requestJoin( joinData );
        Log.d("id", id);
        Log.d("userPw", userPw);
        Log.d("pwConfirm", pwConfirm);
        Log.d("userEmail", userEmail);
        Log.d("userName", userName);
        Log.d("userBirth", userBirth);
        Log.d("userSpicy", String.valueOf(userSpicy));
        Log.d("countryNo", String.valueOf(countryNo));


/*
- 아이디 혹은 이메일 중복: JsonResponse({"message": "INVALID_FORM"}, status=401)
- 비밀번호 확인 틀림: JsonResponse({"message": "INVALID_PASSWORD"}, status=402)
- 로그인 성공: JsonResponse({"message": "SUCCESS"}, status=200)
 */
        call.enqueue( new Callback<JoinData>() {
            @Override
            public void onResponse(Call<JoinData> call, Response<JoinData> response) {

                if(response.code()==200){
                    Toast.makeText(getContext().getApplicationContext(), "회원가입 성공입니다. 가입하신 이메일로 인증 후에 로그인 가능합니다.", Toast.LENGTH_LONG).show();


                    FrontActivity activity = (FrontActivity) getActivity();
                    activity.jointoLogin();
                }else if(response.code()==401){
                    Toast.makeText(getContext().getApplicationContext(), "아이디 혹은 이메일 중복입니다.", Toast.LENGTH_LONG).show();
                }
                else if(response.code()==402){
                    Toast.makeText(getContext().getApplicationContext(), "비밀번호가 비밀번호 재입력과 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext().getApplicationContext(), "올바르지 않은 정보입니다..", Toast.LENGTH_LONG).show();
                    Log.d("error", String.valueOf(response.body()));
                    Log.d("error", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<JoinData> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(), "네트워크 연결을 확인해주세요.", Toast.LENGTH_LONG).show();
            }
        } );
    }




}
