package com.example.kniffel.Highscore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.kniffel.GameOver.GameFinishedActivity;
import com.example.kniffel.Highscore.Database.HighscoreDatabaseHelper;
import com.example.kniffel.InsertNumberOfPlayers.InsertNumberOfPlayers;
import com.example.kniffel.InsertResults.TableActivity;
import com.example.kniffel.R;
import com.example.kniffel.Rules.Rules;
import com.example.kniffel.Tutorial.Tutorial;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * nicht fertig
 */

/**
 * In dieser Activity werden alle Highscore Einträge in einer Liste angezeigt.
 */

public class HighscoreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public final float END_SCALE = 0.7f;
    /**
     * Views für Adapter und Liste in den Highscores
     */
    private HighscoreDatabaseHelper dbHelper;
    private ArrayList<HighscoreItem> highscoreList;
    private HighscoreListAdapter adapterDB;
    private ListView highscoreListView;
    private TextView sortModeStatus;

    private String playerName;
    private int playerScore;

    private Button sortByName;
    private Button sortByScore;

    /**
     * Alle Views und Layouts für das Burgermenu
     */
    DrawerLayout drawerLayout;
    ConstraintLayout contentView;
    NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_list_layout);
        initMenu();
        initUI();
        initDB();
        initHighscoreItem();
        getExtrasFromIntent();
        initNavigationDrawer();
        sortHighscoresByName();
        sortHighscoresByScore();
    }

    private void initDB() {
        dbHelper = new HighscoreDatabaseHelper(this);
    }

    private void initHighscoreItem() {
        highscoreList = new ArrayList<>();
        adapterDB = new HighscoreListAdapter(this, highscoreList);
        highscoreListView.setAdapter(adapterDB);
    }

    private void initUI() {
        highscoreListView = findViewById(R.id.highscore_list);
        sortModeStatus = findViewById(R.id.sort_mode_status);
        sortModeStatus.setText("Score");
        sortByName = findViewById(R.id.buttonSortHighscoresByName);
        sortByScore = findViewById(R.id.buttonSortHighscoresByScore);
    }

    private void getExtrasFromIntent() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            playerName = extras.getString(GameFinishedActivity.EXTRA_KEY_PLAYER_NAME);
            playerScore = extras.getInt(GameFinishedActivity.EXTRA_KEY_PLAYER_SCORE);
            addHighscoreItemFromIntent();
        }
    }

    private void addHighscoreItemFromIntent(){
        HighscoreItem player = new HighscoreItem(playerName, playerScore);
        highscoreList.add(player);
        dbHelper.addHighscoreToDatabase(player);
        adapterDB.notifyDataSetChanged();
    }

    private void sortHighscoresByName(){
        sortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(highscoreList);
                adapterDB.notifyDataSetChanged();
            }
        });
    }

    private void sortHighscoresByScore(){
        sortByScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(highscoreList, new Comparator<HighscoreItem>() {
                    @Override
                    public int compare(HighscoreItem highscoreItem, HighscoreItem t1) {
                        return highscoreItem.getScore() - t1.getScore();
                    }
                });
                sortModeStatus.setText("Alphabet");
                adapterDB.notifyDataSetChanged();
            }
        });
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
    //Funktionalität des Burgermenüs

    /**
     * Initialisiert das DrawerLayout und NavigationView mit findViewById, beides wird für das
     * Burgermenu gebraucht
     */
    private void initMenu() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.constraint_highscores);
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

/**
 * Das muss am besten passieren, sobald das Spiel beendet wird. Das heißt wenn auf den "Game-Over-Button"
 * geklickt wird, muss überprüft werden ob einer der Spieler einen neuen Highscore erreicht hat, nur
 * dann wird ein Datenbankaufruf benötigt.
 * <p>
 * GameFinishedButton.setOnClickListener(new View.OnClickListener(){
 *
 * @Override public void onClick(View v) {
 * String playerName = muss ausgelesen werden
 * int score = muss aus einem Feld in der TabellenActivity geholt werden
 * date = ?
 * if(!task.isEmpty() && !date.isEmpty() && !score.isEmpty(){
 * HighscoreItem highscoreItem = new HighscoreItem(task, date, score);
 * highscoreList.add(highscoreItem);
 * adapter.notifyDataSetChanged();
 * <p>
 * }
 * dbHelper.addHighscoreToDatabase(highscoreItem);
 * }
 * });
 */

