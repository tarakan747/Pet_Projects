package com.mikhailhurski.cityguide.Common;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mikhailhurski.cityguide.HelperClasses.SliderAdapter;
import com.mikhailhurski.cityguide.R;
import com.mikhailhurski.cityguide.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letGetStarted;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letGetStarted = findViewById(R.id.get_started_btn);

        //Call Adapter
        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }


    public void skip(View view){
        startActivity(new Intent(this, UserDashboard.class));
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem(++currentPosition);
    }

    private void addDots(int position) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;
            switch (position){
                case 0:
                    letGetStarted.setVisibility(View.INVISIBLE);
                case 1:
                    letGetStarted.setVisibility(View.INVISIBLE);
                case 2:
                    letGetStarted.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_btn_anim);
                    letGetStarted.setAnimation(animation);
                    letGetStarted.setVisibility(View.VISIBLE);

                    break;

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}