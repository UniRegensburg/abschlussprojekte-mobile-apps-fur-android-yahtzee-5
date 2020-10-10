package com.example.kniffel.InsertName;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.kniffel.GameOver.GameFinishedActivity;
import com.example.kniffel.Highscore.HighscoreActivity;
import com.example.kniffel.InsertNumberOfPlayers.InsertNumberOfPlayers;
import com.example.kniffel.InsertResults.TableActivity;
import com.example.kniffel.R;
import com.example.kniffel.Rules.Rules;
import com.example.kniffel.Tutorial.Tutorial;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class InsertNameActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Key um die Spieler Namen an die TableActivity weiterzugeben
     */
    public static final String EXTRA_KEY_PLAYER_NAMES_ARRAY = "PLAYERS_NAMES";
    public final float END_SCALE = 0.7f;


    /**
     * Alle Views und Layouts für das Burgermenu
     */
    DrawerLayout drawerLayout;
    ConstraintLayout contentView;
    NavigationView navigationView;


    private EditText editTextForPlayerNames;
    private Button buttonToConfirmPlayerNames;
    private String[] playerNames;
    private int counterHowMuchNamesAlreadyEntered = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_name);
        getExtrasFromIntent();
        initViews();
        initClickListener();
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

    //Funktionalität des Namen Eintagens

    /**
     * speichter die Anzahl der Spieler die man über den Intent ausließt in numberOfPlayers und erstellt das playerNames Array
     */
    private void getExtrasFromIntent() {
        Bundle extras = getIntent().getExtras();
        int numberOfPlayers = extras.getInt(InsertNumberOfPlayers.EXTRA_KEY_NUMBER_OF_PLAYERS);
        playerNames = new String[numberOfPlayers];
    }

    /**
     * Initialisiert den EditText für die Namen der Spieler und den Button zum Bestätigen
     */
    private void initViews() {
        editTextForPlayerNames = findViewById(R.id.editTextForPlayersNames);
        buttonToConfirmPlayerNames = findViewById(R.id.buttonAcceptPlayerName);
    }

    /**
     * Initialisiert den Listener für den Button, der die initPlayerNames Methode aufruft
     */
    private void initClickListener() {
        buttonToConfirmPlayerNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPlayerNames();
            }
        });
    }

    /**
     * aus dem Edittext wird der derzeitig eingegebene Name ausgelesen und in das playerNames Array
     * an der richtigen Stelle zugewiesen der Edittext wird danach wieder leer gemacht
     * der Zähler counterHowMuchNamesAlreadyEntered wird eins hochgezählt damit in der if Abfrage danach der Intent zur
     * RollTheDice Activity aufgerufen werden kann sobald alle Namen eingetragen sind playerNames.length ist dabei die Spieleranzahl
     */
    private void initPlayerNames() {
        String currentName = editTextForPlayerNames.getText().toString();
        playerNames[counterHowMuchNamesAlreadyEntered] = currentName;
        editTextForPlayerNames.setText("");
        editTextForPlayerNames.setHint(R.string.next_player_hint);
        counterHowMuchNamesAlreadyEntered++;

        if (counterHowMuchNamesAlreadyEntered == playerNames.length) {
            createIntentToStartTableActivity();
        }

    }

    /**
     * Intent um die RollTheDiceActivity aufzurufen wird erstellt
     */
    private void createIntentToStartTableActivity() {
        Intent intentToStartTableActivity = new Intent(this, TableActivity.class);

        intentToStartTableActivity.putExtra(EXTRA_KEY_PLAYER_NAMES_ARRAY, playerNames);
        startActivity(intentToStartTableActivity);
    }

    //Funktionalität des Burgermenüs

    /**
     * Initialisiert das DrawerLayout und NavigationView mit findViewById, beides wird für das
     * Burgermenu gebraucht
     */
    private void initMenu() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        contentView = findViewById(R.id.insert_name_of_player_constraint);
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
                Intent intentToGameOverActivity = new Intent(this, GameFinishedActivity.class);
                startActivity(intentToGameOverActivity);
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
