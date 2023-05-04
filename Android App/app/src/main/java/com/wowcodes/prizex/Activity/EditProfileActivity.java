package com.mercury.redeem.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;
import com.nabinbhandari.android.permissions.PermissionHandler;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.mercury.redeem.Constants.imageurl;

public class EditProfileActivity extends AppCompatActivity {


    RequestBody id;
    RequestBody name;
    RequestBody email;
    RequestBody pass;
    RequestBody phonenumber;
    MultipartBody.Part partImage;
    RequestBody image;
    String imgPart;
    ImageView imageAdd;
    CircleImageView imageProfile;
    int PICK_IMAGE_REQUEST = 111;
    BindingService videoService;
    Uri FilePath;
    LinearLayout lvlEprofile;
    Boolean imgselected = false;
    ProgressDialog dialog;
    private Button buttonUpdate;
    private EditText edPass;
    private TextView txtTittle, txtPassword, txtYourName, txtEmailId, txtPhoneNumb;
    private ImageView imgBackk;
    private SavePref savePref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        savePref = new SavePref(EditProfileActivity.this);

        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);


        txtTittle = findViewById(R.id.txtAucname);
        edPass = findViewById(R.id.edPass);
        imgBackk = findViewById(R.id.imgBackk);
        lvlEprofile = findViewById(R.id.linearlay);


        txtTittle.setText("Profile Update");
        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buttonUpdate = findViewById(R.id.buttonUpdate);
        imageAdd = findViewById(R.id.imageAdd);
        imageProfile = findViewById(R.id.imageProfile);
        txtPhoneNumb = findViewById(R.id.txtPhoneNumb);
        txtYourName = findViewById(R.id.edt_fname);
        txtEmailId = findViewById(R.id.txtEmailId);


        getprofile();

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkWriteExternalPermission()) {
                    openGallery();
                } else {
                    checkPermissionOfStorage();
                }

            }
        });


        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {


                    if (imgPart != null) {

                        File imagefile = new File(imgPart);
                        image = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                        partImage = MultipartBody.Part.createFormData("image", "abc", image);

                    }

                    id = RequestBody.create(MediaType.parse("multipart/form-data"), savePref.getUserId());
                    name = RequestBody.create(MediaType.parse("multipart/form-data"), txtYourName.getText().toString());
                    email = RequestBody.create(MediaType.parse("multipart/form-data"), txtEmailId.getText().toString());
                    pass = RequestBody.create(MediaType.parse("multipart/form-data"), edPass.getText().toString());
                    phonenumber = RequestBody.create(MediaType.parse("multipart/form-data"), txtPhoneNumb.getText().toString());


                    imageAddapi();

                }
            }
        });


    }

    private void openGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri dataimage = data.getData();
            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(dataimage, imageprojection, null, null, null);

            imgselected = true;
            if (cursor != null) {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                imgPart = cursor.getString(indexImage);


                if (imgPart != null) {
                    File image = new File(imgPart);
                    imageProfile.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                }
            }
        }
    }


    private boolean validation() {

        try {
            if (txtEmailId.getText().toString().equalsIgnoreCase(" ") || !isValidMail(txtEmailId.getText().toString())) {
                Toast.makeText(EditProfileActivity.this, "Please Enter Valid Email", Toast.LENGTH_LONG).show();
                return false;
            } else if (TextUtils.isEmpty(txtYourName.getText().toString())) {
                Toast.makeText(EditProfileActivity.this, "Please Enter Valid Name", Toast.LENGTH_LONG).show();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public void getprofile() {
        lvlEprofile.setVisibility(View.VISIBLE);
        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {

                    try {
                        lvlEprofile.setVisibility(View.GONE);
                        ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();

                        txtYourName.setText(arrayList.get(0).getName());
                        txtEmailId.setText(arrayList.get(0).getEmail());
                        txtPhoneNumb.setText(arrayList.get(0).getPhone());
                        edPass.setText(arrayList.get(0).getPassword());
                        if (arrayList.get(0).getImage().equalsIgnoreCase("")) {

                            try {

                                Glide.with(EditProfileActivity.this)
                                        .load(R.drawable.img_user)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .fitCenter()
                                        .into(imageProfile);

                            }catch (Exception e){
                                e.printStackTrace();

                            }


                        } else {
                            try {
                                Glide.with(EditProfileActivity.this)
                                        .load(imageurl + arrayList.get(0).getImage())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .placeholder(R.drawable.img_user)
                                        .fitCenter()
                                        .into(imageProfile);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlEprofile.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                    lvlEprofile.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<UserProfile> callgetApi() {
        return videoService.getUserProfile(savePref.getUserId());
    }


    public void imageAddapi() {
        lvlEprofile.setVisibility(View.VISIBLE);

        callimageAddapi().enqueue(new Callback<SuccessModel>() {
            @Override
            public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                try {
                    ArrayList<SuccessModel.Suc_Model_Inner> arrayList = new ArrayList<>();
                    arrayList = response.body().getJSON_DATA();


                    Toast.makeText(EditProfileActivity.this, "" + response.body().getJSON_DATA().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                    if (response.body().getJSON_DATA().get(0).getSuccess().equalsIgnoreCase("1")) {
                        Intent i = new Intent(EditProfileActivity.this, MainActivity.class);
                        i.putExtra("comefrom", "activity_editprofile");
                        startActivity(i);
                        finish();
                    } else {
                        lvlEprofile.setVisibility(View.GONE);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<SuccessModel> call, Throwable t) {

                lvlEprofile.setVisibility(View.GONE);


            }
        });
    }


    private Call<SuccessModel> callimageAddapi() {
        return videoService.postUserProfileUpdate(name, email, phonenumber, partImage, id, pass);
    }


    public void checkPermissionOfStorage() {

        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        String rationale = getString(R.string.msg_rationale);
        com.nabinbhandari.android.permissions.Permissions.Options options = new com.nabinbhandari.android.permissions.Permissions.Options()
                .setRationaleDialogTitle(getString(R.string.txt_info))
                .setSettingsDialogTitle(getString(R.string.txt_warning));
        com.nabinbhandari.android.permissions.Permissions.check(EditProfileActivity.this/*context*/, permissions, rationale, options, new PermissionHandler() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onGranted() {

                openGallery();
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {


            }
        });

    }


    private boolean checkWriteExternalPermission() {
        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = EditProfileActivity.this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

}

