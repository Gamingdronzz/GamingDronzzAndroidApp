package gamingdronzz.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        bindVIews();
        //ShowNextActivity();
        StartAnimations();
    }

    private void bindVIews() {

        imageView = (ImageView) findViewById(R.id.imageSplash);
    }

    private void StartAnimations() {
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        final Animation animationBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        anim.reset();
        animationBounce.reset();


        imageView.clearAnimation();
        imageView.startAnimation(animationBounce);

        animationBounce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LoadNextActivity();
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void LoadNextActivity() {


        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}

