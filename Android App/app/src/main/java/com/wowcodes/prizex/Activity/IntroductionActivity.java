package com.mercury.redeem.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mercury.redeem.R;

public class IntroductionActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private static final String MyPREFERENCES = "DoctorPrefrance";
    LinearLayout layoutCraeteAcc,layoutSignIn;
    TextView txtTC;
    TextView txtPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_intoduction);

        mViewPager = findViewById(R.id.viewpager);
        txtTC =findViewById(R.id.txtTC);
        txtPrivacy = findViewById(R.id.txtPrivacy);
        layoutCraeteAcc=findViewById(R.id.layoutCreateAcc);
        layoutSignIn=findViewById(R.id.layoutSignIn);
        lazyadapter la = new lazyadapter();
        mViewPager.setAdapter(la);
        boolean user = true;
        SharedPreferences.Editor sp = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
        sp.putBoolean("userfirsttime", user);
        sp.apply();

        txtTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntroductionActivity.this,WebViewActivity.class);
                intent.putExtra("url","http://wowcodes.in/terms");
                intent.putExtra("from","intro");
                intent.putExtra("title","1");

                startActivity(intent);
            }
        });
        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntroductionActivity.this,WebViewActivity.class);
                intent.putExtra("url","http://wowcodes.in/privacy");
                intent.putExtra("from","intro");
                intent.putExtra("title","2");

                startActivity(intent);
            }
        });

        layoutSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntroductionActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        layoutCraeteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntroductionActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    class lazyadapter extends PagerAdapter {

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {

            TextView title1;
            TextView title2;
            TextView title3;

            ImageView sliderimage;
            RelativeLayout mainlayout;
            ImageView p1, p2, p3,p4,p5;
            LayoutInflater inflater = LayoutInflater.from(IntroductionActivity.this);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_onboarding, container, false);

            title1 = layout.findViewById(R.id.txt_tittle1);
            title2 = layout.findViewById(R.id.txt_tittle2);
            Button btn_skip = layout.findViewById(R.id.btn_skip);
            title3 = layout.findViewById(R.id.txt_tittle3);
            Button btn_continue = layout.findViewById(R.id.btn_continue);
            sliderimage = layout.findViewById(R.id.img);

            p1 = layout.findViewById(R.id.p1);
            p2 = layout.findViewById(R.id.p2);
            p3 = layout.findViewById(R.id.p3);
            p4 = layout.findViewById(R.id.p4);
            p5 = layout.findViewById(R.id.p5);

            mainlayout = layout.findViewById(R.id.main_rel);




            switch (position) {
                case 0:

                    title1.setText(R.string.slidetitle1);
                    title2.setText(R.string.slidetitle2);
                    title3.setText(R.string.slidetitle3);

                    sliderimage.setImageResource(R.drawable.bazaar);
                    mainlayout.setBackgroundColor(getResources().getColor(R.color.white));
                    p1.setImageResource(R.drawable.img_selected);
                    p2.setImageResource(R.drawable.img_notselected);
                    p3.setImageResource(R.drawable.img_notselected);
                    p4.setImageResource(R.drawable.img_notselected);
                    p5.setImageResource(R.drawable.img_notselected);

                    // btn_skip.setVisibility(View.VISIBLE);
                    break;

                case 1:

                    title1.setText(R.string.secondtittle1);
                    title2.setText(R.string.secondtittle2);
                    title3.setText(R.string.secondtittle3);

                    sliderimage.setImageResource(R.drawable.auction);
                    mainlayout.setBackgroundColor(getResources().getColor(R.color.white));
                    p1.setImageResource(R.drawable.img_notselected);
                    p2.setImageResource(R.drawable.img_selected);
                    p3.setImageResource(R.drawable.img_notselected);
                    p4.setImageResource(R.drawable.img_notselected);
                    p5.setImageResource(R.drawable.img_notselected);
                    //  btn_skip.setVisibility(View.VISIBLE);
                    break;
                case 2:

                    title1.setText(R.string.thirdtittle1);
                    title2.setText(R.string.thirdtittle2);
                    title3.setText(R.string.thirdtittle3);

                    sliderimage.setImageResource(R.drawable.draw);
                    mainlayout.setBackgroundColor(getResources().getColor(R.color.white));
                    p1.setImageResource(R.drawable.img_notselected);
                    p2.setImageResource(R.drawable.img_notselected);
                    p3.setImageResource(R.drawable.img_selected);
                    p4.setImageResource(R.drawable.img_notselected);
                    p5.setImageResource(R.drawable.img_notselected);
                    //btn_skip.setVisibility(View.VISIBLE);
                    break;
                case 3:

                    title1.setText(R.string.fourthtittle1);
                    title2.setText(R.string.fourthtittle2);
                    title3.setText(R.string.fourthtittle3);

                    sliderimage.setImageResource(R.drawable.c);
                    mainlayout.setBackgroundColor(getResources().getColor(R.color.white));
                    p1.setImageResource(R.drawable.img_notselected);
                    p2.setImageResource(R.drawable.img_notselected);
                    p3.setImageResource(R.drawable.img_notselected);
                    p4.setImageResource(R.drawable.img_selected);
                    p5.setImageResource(R.drawable.img_notselected);
                    //    btn_skip.setVisibility(View.VISIBLE);
                    break;

                case 4:

                    title1.setText(R.string.fifthtittle1);
                    title2.setText(R.string.fifthtittle2);
                    title3.setText(R.string.fifthtittle3);

                    sliderimage.setImageResource(R.drawable.d);
                    mainlayout.setBackgroundColor(getResources().getColor(R.color.white));
                    p1.setImageResource(R.drawable.img_notselected);
                    p2.setImageResource(R.drawable.img_notselected);
                    p3.setImageResource(R.drawable.img_notselected);
                    p4.setImageResource(R.drawable.img_notselected);
                    p5.setImageResource(R.drawable.img_selected);
                    btn_continue.setText(R.string.gotit_txt);
                    // btn_skip.setVisibility(View.GONE);
                    break;
            }

            btn_skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(IntroductionActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            });
            btn_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 2) {
                        Intent i = new Intent(IntroductionActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                }
            });


            container.addView(layout);
            return layout;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
