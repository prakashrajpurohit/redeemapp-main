package com.mercury.redeem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ChangepassActivity extends AppCompatActivity {

    EditText edConfrimPass,edPass;
    LinearLayout lvlChangePass;
    TextView txtSubmit;
    SavePref savePref;
    public BindingService videoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        edPass=findViewById(R.id.edPass);
        edConfrimPass=findViewById(R.id.edConfrimPass);
        lvlChangePass =findViewById(R.id.linearlay);
        txtSubmit=findViewById(R.id.txtSubmit);
        savePref=new SavePref(ChangepassActivity.this);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);



        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edPass.getText().toString().equalsIgnoreCase(edConfrimPass.getText().toString())){
                    changepassapi();
                }else{
                    Toast.makeText(ChangepassActivity.this, "Please Enter Correct Password", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }


    public void changepassapi(){
        lvlChangePass.setVisibility(View.VISIBLE);
        try {
            callloginApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {


                    ArrayList<SuccessModel.Suc_Model_Inner> arrayList= response.body().getJSON_DATA();

                    String msg;
                    msg=arrayList.get(0).getMsg();
                    Toast.makeText(ChangepassActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                    if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")){

                        Intent i=new Intent(ChangepassActivity.this,LoginActivity.class);
                        startActivity(i);
                    }else {
                        lvlChangePass.setVisibility(View.GONE);
                    }


                }

                @Override
                public void onFailure(Call<SuccessModel> call, Throwable t) {
                    lvlChangePass.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private Call<SuccessModel> callloginApi() {
        return videoService.change_password(savePref.getUserPhone(),edPass.getText().toString());
    }

}