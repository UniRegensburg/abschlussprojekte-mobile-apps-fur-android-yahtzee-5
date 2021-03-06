package com.example.kniffel.GameOver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.kniffel.InsertResults.Player;
import com.example.kniffel.R;

import java.util.ArrayList;
import java.util.Collections;

public class GameOverListAdapter extends ArrayAdapter<Player> {
    private ArrayList<Player> gameOverList;
    private Context context;

    public GameOverListAdapter(Context context, ArrayList<Player> gameOverList) {
        super(context, R.layout.game_over_list_item, gameOverList);
        this.gameOverList = gameOverList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.game_over_list_item, null);

        }
        TextView tvGameOverPlayerName = v.findViewById(R.id.game_over_player_name);
        TextView tvGameOverScore = v.findViewById(R.id.game_over_score);

        Player player = gameOverList.get(position);

        tvGameOverPlayerName.setText(player.getName());
        tvGameOverScore.setText(String.valueOf(player.getScore()));
        return v;
    }


}
