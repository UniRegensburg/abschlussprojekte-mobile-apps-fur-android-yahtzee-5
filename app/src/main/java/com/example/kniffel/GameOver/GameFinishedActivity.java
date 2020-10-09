package com.example.kniffel.GameOver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.kniffel.Highscore.HighscoreActivity;
import com.example.kniffel.InsertNumberOfPlayers.InsertNumberOfPlayers;
import com.example.kniffel.InsertResults.TableActivity;
import com.example.kniffel.R;
import com.example.kniffel.Rules.Rules;
import com.example.kniffel.Tutorial.Tutorial;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class GameFinishedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public final float END_SCALE = 0.7f;


    /**
     * Alle Views und Layouts für das Burgermenu
     * sind die public oder private? -Q
     */
    DrawerLayout drawerLayout;
    ConstraintLayout contentView;
    NavigationView navigationView;

    /**
     * Views für GameFinished
     */
    private Button btnNewGame, btnHighscores;

    /**
     * playerNames enthält die Namen der Spieler, in endScore stehen die finalen Punktzahlen drin playerNames[0] gehört
     * zu endScores[0] die Werte werden aus dem Intent der TableActivity zugewiesen
     */
    private String[] playerNames;
    private int[] endScores;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getExtrasFromIntent();
        initViews();
        initUi();
        initMenu();
        initNavigationDrawer();
    }

    /**
     * weißt playerNames, endScores aus den Daten des Intents zu
     */
    private void getExtrasFromIntent() {
        Bundle extra = getIntent().getExtras();
        playerNames = extra.getStringArray(TableActivity.EXTRA_KEY_PLAYER_NAMES);
        endScores = extra.getIntArray(TableActivity.EXTRA_KEY_FINAL_POINTS);
    }

    /**
     * Actionbar wird mit Icon erstellt, rechts oben allerdings noch sehr dunkel
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Wenn auf das Icon geklickt wird, öffnet sich das Burgermenü
     * Wenn das Burgermenü bereits geöffnet ist schließt es sich wieder
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.burgermenu_icon == item.getItemId()) {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else drawerLayout.openDrawer(GravityCompat.START);
        }
        animateNavigationDrawer();
        return super.onOptionsItemSelected(item);
    }

    //Funktionalität der Game Finished Activity

    private void initViews() {
        btnHighscores = findViewById(R.id.buttonToHighscores);
        btnNewGame = findViewById(R.id.buttonNewGame);
    }

    private void initUi() {
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGame();
            }
        });
        btnHighscores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHighscores();
            }
        });
    }

    private void startNewGame() {
        Intent startNewGame = new Intent(this, InsertNumberOfPlayers.class);
        startActivity(startNewGame);
    }

    private void showHighscores() {
        Intent showHighscores = new Intent(this, HighscoreActivity.class);
        startActivity(showHighscores);
    }


    //Funktionalität des Burgermenüs

    /**
     * Initialisiert das DrawerLayout und NavigationView mit findViewById, beides wird für das
     * Burgermenu gebraucht
     */
    private void initMenu() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.game_over_constraint);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
    }

    /**
     * NavigationDrawer Funktionen
     */
    private void initNavigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.highscore_burgermenu_item);
    }

    /**
     * Layout um die Anzahl der Spieler anzugeben verschiebt sich um die Breite des Burgermenus nach
     * rechts, wenn dieses ausgezogen wird
     */
    @SuppressLint("ResourceAsColor")
    private void animateNavigationDrawer() {
        //Um das Layout einzufärben
        drawerLayout.setScrimColor(getResources().getColor(R.color.light_blue, getTheme()));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaleOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaleOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaleOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.highscore_burgermenu_item:
                Intent intentToStartHighscoreActivity = new Intent(this, HighscoreActivity.class);
                startActivity(intentToStartHighscoreActivity);
                break;
            case R.id.tutorial_burgermenu_item:
                Intent intentToStartTutorialActivity = new Intent(this, Tutorial.class);
                startActivity(intentToStartTutorialActivity);
                break;
            case R.id.rules_burgermenu_item:
                Intent intentToStartRulesActivity = new Intent(this, Rules.class);
                startActivity(intentToStartRulesActivity);
                break;

            case R.id.end_game_burgermenu_item:
                Intent intentToGameFinishedActivity = new Intent(this, GameFinishedActivity.class);
                startActivity(intentToGameFinishedActivity);
                break;
            case R.id.start_new_game_burgermenu_item:
                Intent intentToStartNewGame = new Intent(this, InsertNumberOfPlayers.class);
                startActivity(intentToStartNewGame);
                break;
        }
        return true;
    }

    /**
     * Burgermenu schließt sich, wenn auf den Hintergrund geklickt wird
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }
}
