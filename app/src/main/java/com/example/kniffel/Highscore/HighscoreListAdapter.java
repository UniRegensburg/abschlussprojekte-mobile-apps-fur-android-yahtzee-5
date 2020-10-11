package com.example.kniffel.Highscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kniffel.R;

import java.util.ArrayList;

/**
 * eigener Adapter um Highscore-Einträge in einer Liste anzuzeigen.
 */

public class HighscoreListAdapter extends ArrayAdapter<HighscoreItem> {
    private ArrayList<HighscoreItem> highscoreList;
    private Context context;

    public HighscoreListAdapter(Context context, ArrayList<HighscoreItem> highscoreList) {
        super(context, R.layout.highscore_list_item, highscoreList);
        this.highscoreList = highscoreList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.highscore_list_item, null);

        }
        HighscoreItem highscoreItem = highscoreList.get(position);
        if (highscoreItem != null) {
            TextView tvHighscoreItemPlayerName = v.findViewById(R.id.highscore_player_name);
            TextView tvHighscoreItemDate = v.findViewById(R.id.highscore_date);
            TextView tvHighscoreItemScore = v.findViewById(R.id.highscore_score);

            tvHighscoreItemPlayerName.setText(highscoreItem.getPlayerName());
            tvHighscoreItemDate.setText(highscoreItem.getFormattedDate());
            tvHighscoreItemScore.setText(String.valueOf(highscoreItem.getScore()));
        }
        return v;
    }
}
