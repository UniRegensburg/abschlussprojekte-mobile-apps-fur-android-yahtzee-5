package com.example.kniffel.GameOver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.kniffel.Highscore.Database.HighscoreDatabaseHelper;
import com.example.kniffel.Highscore.HighscoreActivity;
import com.example.kniffel.Highscore.HighscoreItem;
import com.example.kniffel.Highscore.HighscoreListAdapter;
import com.example.kniffel.InsertNumberOfPlayers.InsertNumberOfPlayers;
import com.example.kniffel.InsertResults.Player;
import com.example.kniffel.InsertResults.TableActivity;
import com.example.kniffel.R;
import com.example.kniffel.Rules.Rules;
import com.example.kniffel.Tutorial.Tutorial;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class GameFinishedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public final float END_SCALE = 0.7f;
    public static final String EXTRA_KEY_PLAYER_NAME = "PLAYERS_NAME";
    public static final String EXTRA_KEY_PLAYER_SCORE = "PLAYER_SCORE";

    /**
     * Views für Adapter und Liste in der GameOverActivity
     */
    private ListView gameOverListView;
    private GameOverListAdapter adapter;
    private ArrayList<Player> gameOverList;
    private ArrayList<Player> players;


    /**
     * Alle Views und Layouts für das Burgermenu
     */
    private DrawerLayout drawerLayout;
    private ConstraintLayout contentView;
    private NavigationView navigationView;


    /**
     * Views für GameFinished
     */
    private Button btnNewGame, btnHighscores;
    private Button btnStoreWinner;

    /**
     * playerNames enthält die Namen der Spieler, in endScore stehen die finalen Punktzahlen drin playerNames[0] gehört
     * zu endScores[0] die Werte werden aus dem Intent der TableActivity zugewiesen
     */
    private String[] playerNames;
    private int[] endScores;

    /**
     * Deklaration eines einzelnen Spielers
     */
    Player player;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getExtrasFromIntent();
        initViews();
        initUi();
        initMenu();
        initNavigationDrawer();
        showGameOverList();
        onSaveDataButtonClicked();
    }

    /**
     * weißt playerNames, endScores aus den Daten des Intents zu
     */
    private void getExtrasFromIntent() {
        Bundle extra = getIntent().getExtras();
        assert extra != null;
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
        btnStoreWinner = findViewById(R.id.addWinnerToHighscores);
        gameOverListView = findViewById(R.id.player_lv);
        gameOverList = new ArrayList<>();
        adapter = new GameOverListAdapter(this, gameOverList);
        View v = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.game_over_list_item, null);
        gameOverListView.setAdapter(adapter);
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

    /**
     * Es wird eine Liste mit allen Spielern erstellt
     *
     * @param playerNames ist das String Array aus dem Intent
     * @return eine Arraylist vom Typen Spieler, jeder Spieler erhält einen Namen und die
     * dazugehörige Punktzahl aus dem Spiel
     */
    private ArrayList<Player> getPlayerList(String[] playerNames, int[] endScores) {
        players = new ArrayList<Player>();
        for (int i = 0; i < playerNames.length; i++) {
            player = new Player(playerNames[i], endScores[i]);
            players.add(player);
        }
        return players;
    }

    private void showGameOverList() {
        ArrayList<Player> players = getPlayerList(playerNames, endScores);
        gameOverList.addAll(players);
        adapter.notifyDataSetChanged();
    }


    /**Der höchste Punktestand des Spiels wird ermittelt*/
    private int getHighestScore() {
        int highestScore = 0; //größte Zahl
        for (int i = 0; i < endScores.length; i++) {
            if (endScores[i] > highestScore) {
                highestScore = endScores[i];
            }
        }
        return highestScore;
    }

    /**bester Spieler wird vorbereitet in die Datenbank gespeichert zu werden*/
    private Player getPlayerForDatabase() {
        String name = "";
        int score = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() == getHighestScore()) {
                name = players.get(i).getName();
                score = players.get(i).getScore();
            }
        }
        return new Player(name, score);
    }

    private void setDataForDatabase(){
        Player highscorePlayer = getPlayerForDatabase();
        String name = highscorePlayer.getName();
        int score = highscorePlayer.getScore();
        Intent playerData = new Intent(this, HighscoreActivity.class);
        playerData.putExtra(EXTRA_KEY_PLAYER_NAME, name);
        playerData.putExtra(EXTRA_KEY_PLAYER_SCORE, score);
        startActivity(playerData);
    }


    private void onSaveDataButtonClicked(){
        btnStoreWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataForDatabase();
            }
        });
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
