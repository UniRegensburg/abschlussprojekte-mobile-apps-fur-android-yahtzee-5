package com.example.kniffel;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.kniffel.Tutorial.Tutorial;

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

    /** Mittels "shared preferences" booleschen Wert im Speicher speichern, damit beim nächsten Ausführen der Anwendung bekannt ist,
     * dass der Benutzer die Aktivität des Tutorials bereits durchlaufen hat */
    private void savePrefsData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("tutorialPrefs", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putBoolean("isTutorialOpened", true);
        prefEditor.commit();
    }

    /** Methode prüft, ob die TutorialActivit schon einmal geöffnet wurde*/
    private boolean restorePrefData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("tutorialPrefs", MODE_PRIVATE);
        Boolean isTutorialActivityOpenendBefore = preferences.getBoolean("isTutorialOpened", false);
        return isTutorialActivityOpenendBefore;
    }
    /**
     * Nach 5 Sekunden wechselt der SplashScreen zu InsertNumberOfPlayersActivity
     */
    private void switchToInsertNameActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /** kurz bevor die TutorialActivity gestartet wird, wird überprüft ob sie vorher schon einmal geöffnet wurde*/
                if(!restorePrefData()){
                    /** wurde sie noch nicht geöffnet, wird sie geöffnet*/
                    Intent intentOpenTutorial = new Intent(getApplicationContext(), Tutorial.class);
                    startActivity(intentOpenTutorial);
                    savePrefsData();
                    finish();
                }
                /** wurde die TuturialActivity bereits in der MainActivity geöffnet, wird die insertNumberOfPlayerActivity geöffnet
                 * die TutorialActivity ist dann nur noch über das Burgermenu aufrufbar*/
                else {
                    Intent intentToInsertNumberOfPlayers = new Intent(getApplicationContext(), InsertNumberOfPlayers.class);
                    startActivity(intentToInsertNumberOfPlayers);
                    finish();
                }
            }
        }, SPLASH_SCREEN);
    }
}



