package com.example.kniffel.InsertNumberOfPlayers;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.kniffel.Highscore.HighscoreActivity;
import com.example.kniffel.InsertName.InsertNameActivity;
import com.example.kniffel.R;
import com.example.kniffel.Rules.Rules;
import com.google.android.material.navigation.NavigationView;

public class InsertNumberOfPlayers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    /**
     * Key um die Spieler Anzahl an die InsertNameActivity weiterzugeben
     */
    public static final String EXTRA_KEY_NUMBER_OF_PLAYERS = "PLAYERS_NUMBER";
    public final float END_SCALE = 0.7f;


    /**
     * Alle Views und Layouts für das Burgermenu
     */
    DrawerLayout drawerLayout;
    ConstraintLayout contentView;
    NavigationView navigationView;


    private Button buttonForTwoPlayers;
    private Button buttonForThreePlayers;
    private Button buttonForFourPlayers;
    private int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_number_of_players);
        initViews();
        initUi();
        initMenu();
        initNavigationDrawer();
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

    //Funktionalität für InsertNumberOfPlayer Aytivity

    /**
     * Initialisiert die drei Buttons mit findViewById
     */
    private void initViews() {
        buttonForTwoPlayers = findViewById(R.id.buttonStartGameWith2Players);
        buttonForThreePlayers = findViewById(R.id.buttonStartGameWith3Players);
        buttonForFourPlayers = findViewById(R.id.buttonStartGameWith4Players);
    }

    /**
     * Weißt den drei Buttons setOnClicklistener zu sodass damit ein Intent erstellt werden kann
     */
    private void initUi() {
        buttonForTwoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInsertNameActivity(2);
            }
        });
        buttonForThreePlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInsertNameActivity(3);
            }
        });
        buttonForFourPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInsertNameActivity(4);
            }
        });
    }

    /**
     * die OnClickListener starten diese Methode um einen Intent zur InsertNameActivity zu kommen
     *
     * @param i ist die Anzahl der Spieler
     */
    private void startInsertNameActivity(int i) {
        numberOfPlayers = i;
        Intent intentToStartInsertNameActivity = new Intent(this, InsertNameActivity.class);
        intentToStartInsertNameActivity.putExtra(EXTRA_KEY_NUMBER_OF_PLAYERS, numberOfPlayers);
        startActivity(intentToStartInsertNameActivity);
    }

    //Funktionalität des Burgermenüs

    /**
     * Initialisiert das DrawerLayout und NavigationView mit findViewById, beides wird für das
     * Burgermenu gebraucht
     */
    private void initMenu() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.insert_number_of_player_constraint);

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
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
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
                //
                break;
            case R.id.rules_burgermenu_item:
                Intent intentToStartRulesActivity = new Intent(this, Rules.class);
                startActivity(intentToStartRulesActivity);
                break;
            case R.id.settings_burgermenu_item:
                //
                break;
            case R.id.end_game_burgermenu_item:
                //
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

