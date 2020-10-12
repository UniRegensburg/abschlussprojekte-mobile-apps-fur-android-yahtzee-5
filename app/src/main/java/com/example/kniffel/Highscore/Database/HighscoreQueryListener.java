package com.example.kniffel.Highscore.Database;

import com.example.kniffel.Highscore.HighscoreItem;


import java.util.List;

public interface HighscoreQueryListener {

    void onHighscoreQueryResult(List<HighscoreItem> highscoreList);
}
