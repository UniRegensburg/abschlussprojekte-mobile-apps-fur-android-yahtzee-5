package com.example.kniffel.Tutorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kniffel.InsertNumberOfPlayers.InsertNumberOfPlayers;
import com.example.kniffel.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tutorial extends AppCompatActivity {

    private ViewPager screenPager;
    TutorialViewPagerAdapter tutorialViewPagerAdapter;
    List<ScreenItem> mList = new ArrayList<>();
    TabLayout tabIndicator;
    Button buttonNext;
    int position = 0;
    Button buttonStart;
    Animation buttonAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Activity im FullScreen-Modus*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        /** ActionBar verstecken*/
        getSupportActionBar().hide();
        setContentView(R.layout.tutorial_actvitiy);
        initUI();
        initButton();
    }



    private void initUI() {
        /** Tab Views initialisieren*/
        tabIndicator = findViewById(R.id.tab_indicator);


        fillScreenList();

        /** Viewpager einrichten*/
        screenPager = findViewById(R.id.pager);
        tutorialViewPagerAdapter = new TutorialViewPagerAdapter(this, mList);
        screenPager.setAdapter(tutorialViewPagerAdapter);

        /** TabLayout mit Viewpager verbinden*/
        tabIndicator.setupWithViewPager(screenPager);
    }

    /**
     * ListScreen befüllen
     */
    private void fillScreenList() {
        mList.add(new ScreenItem(R.string.tutorial_title_menu_player, R.string.tutorial_description_menu_player, R.drawable.tutorial_main));
        mList.add(new ScreenItem(R.string.tutorial_title_burger_menu, R.string.tutorial_description_burger_menu, R.drawable.tutorial_burger_menu));
        mList.add(new ScreenItem(R.string.tutorial_title_player_name, R.string.tutorial_description_player_name, R.drawable.tutorial_insert_name));
        mList.add(new ScreenItem(R.string.tutorial_title_dice_activity, R.string.tutorial_description_dice_activity, R.drawable.tutorial_dice_activity));
        mList.add(new ScreenItem(R.string.tutorial_title_dice_activity_selected, R.string.tutorial_description_dice_activity_selected, R.drawable.tutorial_dice_acrtivity_selected));
        mList.add(new ScreenItem(R.string.tutorial_table_results, R.string.tutorial_description_table_results, R.drawable.tutorial_table_results));
    }

    private void initButton() {
        buttonNext = findViewById(R.id.button_next_tutorial);
        buttonStart = findViewById(R.id.button_start_tutorial);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** aktuelle View-Position wird ermittelt */
                position = screenPager.getCurrentItem();
                /** ausgehend von der aktuellen Position wird der nächste Screen beim Click auf den Next-Button angezeigt*/
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                /** wenn der letzte Screen erreicht ist*/
                if (position == mList.size() - 1) {
                    /** wird der "Start-Button" angezeigt und man wird zur MainActivity mit der Auswahl der Spieleranzahl geleitet*/
                    loadLastScreen();
                }
            }
        });

        /** TabLayout ChangeListener hinzufügen*/
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * "Spiel start" wird angzeigt und der "Weiter" Button wird ausgeblendet
     */
    private void loadLastScreen() {
        buttonNext.setVisibility(View.INVISIBLE);
        buttonStart.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        /** Animation zu "Spiel starten" hinzufügen*/
        buttonAnimation();

        /** ClickListener auf StartButton registrieren*/
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /** InsertNumberOfPlayers-Activity öffnen*/
                Intent intentToInsertNumberOfPlayers = new Intent(getApplicationContext(), InsertNumberOfPlayers.class);
                startActivity(intentToInsertNumberOfPlayers);
            }
        });
    }


    private void buttonAnimation() {
        buttonAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tutorial_button_animation);
        buttonStart.setAnimation(buttonAnim);
    }

}
