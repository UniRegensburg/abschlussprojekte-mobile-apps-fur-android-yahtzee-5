package com.example.kniffel.InsertResults;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kniffel.R;

import java.util.ArrayList;

public class TableEntryAdapter extends RecyclerView.Adapter<TableEntryAdapter.TableEntryViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    /**
     * Lister der Spieler, die über den Adapter verwaltet werden
     */
    private ArrayList<Player> players;
    private Context context;

    /**
     * Instanzen von TableEntryViewHolder
     * werden vom Adapter an den RecyclerView weitergegeben um die im Adapter gespeicherten Daten
     * im User Interface anzuzeigen. Jeder Spieler wird durch einen eigenen ViewHolder dargestelt.
     */
    public static class TableEntryViewHolder extends RecyclerView.ViewHolder {

        int viewType;
        public View entryView;

        public TableEntryViewHolder(View v, int viewType) {
            super(v);
            if (viewType == TYPE_ITEM) {
                entryView = v;
                viewType = 1;
            } else if (viewType == TYPE_HEADER) {
                entryView = v;
                viewType = 0;
            }

        }
    }


    /**
     * Konstruktor des Adapters
     */
    public TableEntryAdapter(ArrayList<Player> players, Context context) {
        this.players = players;
        this.context = context;
    }

    /**
     * Ersetzt die aktuell im Adapter gespeicherten Daten (Player-Liste) und informiert das
     * angeschlossene RecyclerView
     */
    public void setPlayerEntries(ArrayList<Player> players) {
        this.players = players;
        this.notifyDataSetChanged();
    }


    /**
     * Erstellt bei Bedarf einen neuen ViewHolder für das angeschlossene RecyclerView
     *
     * @return
     */

    @Override
    public TableEntryAdapter.TableEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        TableEntryViewHolder vh;
        if (viewType == TYPE_ITEM) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_player, parent, false);
            vh = new TableEntryViewHolder(v, viewType);
            return vh;
        } else if (viewType == TYPE_HEADER) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_head, parent, false);
            vh = new TableEntryViewHolder(v, viewType);
            return vh;
        }
        return null;
    }

    /**
     * Trägt bei Bedarf die Informationen (Spielername und die geworfenen Ergebnisse) eines Spielers
     * in den vorbereiteten Viewholder ein
     */
    @Override
    public void onBindViewHolder( TableEntryAdapter.TableEntryViewHolder holder, int position) {
        Player player;
        if (holder.viewType == TYPE_ITEM) {
            player = players.get(position - 1);


        } else if (holder.viewType == TYPE_HEADER) {
            TextView toss = holder.entryView.findViewById(R.id.table_head_toss);
            TextView ones = holder.entryView.findViewById(R.id.table_head_toss_ones);
            TextView twosome = holder.entryView.findViewById(R.id.table_head_toss_twosome);
            TextView threesome = holder.entryView.findViewById(R.id.table_head_toss_threesome);
            TextView foursome = holder.entryView.findViewById(R.id.table_head_toss_foursome);
            TextView fivesome = holder.entryView.findViewById(R.id.table_head_toss_fivesome);
            TextView sixsome = holder.entryView.findViewById(R.id.table_head_toss_sixsome);
            TextView subtotals = holder.entryView.findViewById(R.id.table_head_toss_subtotal);
            TextView bonus = holder.entryView.findViewById(R.id.table_head_toss_bonus);
            TextView threeDoubles = holder.entryView.findViewById(R.id.table_head_toss_three_doubles);
            TextView fourDoubles = holder.entryView.findViewById(R.id.table_head_toss_four_doubles);
            TextView fullHouse = holder.entryView.findViewById(R.id.table_head_toss_full_house);
            TextView smallStreet = holder.entryView.findViewById(R.id.table_head_toss_small_street);
            TextView bigStreet = holder.entryView.findViewById(R.id.table_head_toss_big_street);
            TextView kniffel = holder.entryView.findViewById(R.id.table_head_toss_kniffel);
            TextView chance = holder.entryView.findViewById(R.id.table_head_toss_chance);
            TextView totalSum =holder.entryView.findViewById(R.id.table_head_end_sum);
        }
    }


    @Override
    public int getItemCount() {
        return players.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

}
