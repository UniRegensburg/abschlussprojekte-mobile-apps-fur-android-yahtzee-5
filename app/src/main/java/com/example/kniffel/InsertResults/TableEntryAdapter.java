package com.example.kniffel.InsertResults;

import android.content.Context;
import android.util.Log;
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
     * Konstruktor des Adapters
     */
    public TableEntryAdapter(Context context, ArrayList<Player> players) {
        this.context = context;
        this.players = players;
    }

    /**
     * Instanzen von TableEntryViewHolder
     * werden vom Adapter an den RecyclerView weitergegeben um die im Adapter gespeicherten Daten
     * im User Interface anzuzeigen. Jeder Spieler wird durch einen eigenen ViewHolder dargestelt.
     */
    public static class TableEntryViewHolder extends RecyclerView.ViewHolder {

        public View entryView;
        public int viewType;


        public TableEntryViewHolder(View itemView, int viewType) {
            super(itemView);
            entryView = itemView;
            this.viewType = viewType;

        }
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
            if (viewType == TYPE_ITEM) {
                View v;
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_player, parent, false);
                Log.d("onCreateItemView", "itemView mit Player erstellt");
                return new ViewHolderItem(v, TYPE_ITEM);

            } else if (viewType == TYPE_HEADER) {
                View v;
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_head, parent, false);
                Log.d("onCreateHeaderView", "headerView wird erstellt");
                return new ViewHolderHeader(v, TYPE_HEADER);
            }
            throw new RuntimeException("no type");
        }

        /**
         * Trägt bei Bedarf die Informationen (Spielername und die geworfenen Ergebnisse) eines Spielers
         * in den vorbereiteten Viewholder ein
         */
        @Override
        public void onBindViewHolder(TableEntryAdapter.TableEntryViewHolder holder, int position) {
            if (holder instanceof ViewHolderItem) {
                Player player = players.get(position-1);
                ViewHolderItem holderItem = (ViewHolderItem) holder;
                holderItem.name.setText(player.getName());
                holderItem.ones.setText(player.getOnes());
                holderItem.twosome.setText(player.getTwosome());
                holderItem.threesome.setText(player.getThreesome());
                holderItem.foursome.setText(player.getFoursome());
                holderItem.fivesome.setText(player.getFivesome());
                holderItem.sixsome.setText(player.getSixsome());
                holderItem.subtotals.setText(player.getSubtotals());
                holderItem.bonus.setText(player.getBonus());
                holderItem.threeDoubles.setText(player.getThreeDoubles());
                holderItem.fourDoubles.setText(player.getFourDoubles());
                holderItem.fullHouse.setText(player.getFullHouse());
                holderItem.smallStreet.setText(player.getSmallStreet());
                holderItem.bigStreet.setText(player.getBigStreet());
                holderItem.kniffel.setText(player.getKniffel());
                holderItem.chance.setText(player.getChance());
                holderItem.totalsum.setText(player.getTotalSum());
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

        public class ViewHolderItem extends TableEntryAdapter.TableEntryViewHolder {
            TextView name;
            TextView ones;
            TextView twosome;
            TextView threesome;
            TextView foursome;
            TextView fivesome;
            TextView sixsome;
            TextView subtotals;
            TextView bonus;
            TextView threeDoubles;
            TextView fourDoubles;
            TextView fullHouse;
            TextView smallStreet;
            TextView bigStreet;
            TextView kniffel;
            TextView chance;
            TextView totalsum;

            public ViewHolderItem(View itemView, int viewType) {
                super(itemView, viewType);
                View v = itemView;
                name = v.findViewById(R.id.column_player_name);
                ones = v.findViewById(R.id.column_player_toss_one);
                twosome = v.findViewById(R.id.column_player_toss_two);
                threesome = v.findViewById(R.id.column_player_toss_three);
                foursome = v.findViewById(R.id.column_player_toss_four);
                fivesome = v.findViewById(R.id.column_player_toss_five);
                sixsome = v.findViewById(R.id.column_player_toss_six);
                subtotals = v.findViewById(R.id.column_player_toss_subtotals);
                bonus = v.findViewById(R.id.column_player_toss_bonus);
                threeDoubles = v.findViewById(R.id.column_player_toss_three_doubles);
                fourDoubles = v.findViewById(R.id.column_player_toss_four_doubles);
                fullHouse = v.findViewById(R.id.column_player_toss_full_house);
                smallStreet = v.findViewById(R.id.column_player_toss_little_street);
                bigStreet = v.findViewById(R.id.column_player_toss_big_street);
                kniffel = v.findViewById(R.id.column_player_toss_kniffel);
                chance = v.findViewById(R.id.column_player_toss_chance);
                totalsum = v.findViewById(R.id.column_player_toss_end_summary);
            }
        }

        public class ViewHolderHeader extends TableEntryAdapter.TableEntryViewHolder {
            public ViewHolderHeader(View itemView, int viewType) {
                super(itemView, viewType);
            }
        }
    }
