package com.example.kniffel;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kniffel.InsertNumberOfPlayers.InsertNumberOfPlayers;

import pl.droidsonroids.gif.GifImageView;


public class MainActivity extends AppCompatActivity {

    /**
     * Dauer bis der SplashScreen automatisch beendet wird
     */
    private final static int SPLASH_SCREEN = 5000;


    Animation topAnim,
            bottomAnimLeft,
            bottomAnimRight;
    GifImageView gif;
    TextView logo,
            slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAnimation();
        initUI();
        switchToInsertNameActivity();
    }

    //Actionbar wird immernoch angezeigt

    /**
     * Der ActionBar wird hier erstellt, bzw. auch hier ausgeblendet
     *
     * @return kein menu wird zurückgegeben
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        hideBars();
        return super.onCreateOptionsMenu(menu);
    }

    private void hideBars() {
        /**
         * Statusbar wird versteckt
         */
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //ActionBar zu auszublenden funktioniert so leider nicht
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * Animationen für drei verschiedene Richtungen werden initialisiert
     */
    private void initAnimation() {
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimLeft = AnimationUtils.loadAnimation(this, R.anim.bottom_animation_left);
        bottomAnimRight = AnimationUtils.loadAnimation(this, R.anim.bottom_animation_right);

    }

    /**
     * Gif und zwei Textviews werden initialisiert und bekommen jeweils eine Animation zugewiesen
     */
    private void initUI() {
        gif = findViewById(R.id.gifImageView);
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);

        gif.setAnimation(topAnim);
        logo.setAnimation(bottomAnimLeft);
        slogan.setAnimation(bottomAnimRight);
    }


    /**
     * Nach 5 Sekunden wechselt der SplashScreen zu InsertNumberOfPlayersActivity
     */
    private void switchToInsertNameActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentToInsertNumberOfPlayers = new Intent(getApplicationContext(), InsertNumberOfPlayers.class);
                startActivity(intentToInsertNumberOfPlayers);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}



