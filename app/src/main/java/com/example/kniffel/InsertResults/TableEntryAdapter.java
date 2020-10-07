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
                final Player player = players.get(position-1);
                ViewHolderItem holderItem = (ViewHolderItem) holder;
                holderItem.name.setText(player.getName());

                holderItem.ones.setText(player.getOnes());
                if (player.getClickable() && player.getOnes().equals("")) {
                    holderItem.ones.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setOnes();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.twosome.setText(player.getTwosome());
                if (player.getClickable() && player.getTwosome().equals("")) {
                    holderItem.twosome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("Testen", "onItemClick in Adapter Aufgerufen");
                            player.setTwosome();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.threesome.setText(player.getThreesome());
                if (player.getClickable() && player.getThreesome().equals("")) {
                    holderItem.threesome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setThreesome();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.foursome.setText(player.getFoursome());
                if (player.getClickable() && player.getFoursome().equals("")) {
                    holderItem.foursome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setFoursome();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.fivesome.setText(player.getFivesome());
                if (player.getClickable() && player.getFivesome().equals("")) {
                    holderItem.fivesome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setFivesome();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.sixsome.setText(player.getSixsome());
                if (player.getClickable() && player.getSixsome().equals("")) {
                    holderItem.sixsome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setSixsome();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.threeDoubles.setText(player.getThreeDoubles());
                if (player.getClickable() && player.getThreeDoubles().equals("")) {
                    holderItem.threeDoubles.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setThreeDoubles();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.fourDoubles.setText(player.getFourDoubles());
                if (player.getClickable() && player.getFourDoubles().equals("")) {
                    holderItem.fourDoubles.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setFourDoubles();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.fullHouse.setText(player.getFullHouse());
                if (player.getClickable() && player.getFullHouse().equals("")) {
                    holderItem.fullHouse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setFullHouse();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.smallStreet.setText(player.getSmallStreet());
                if (player.getClickable() && player.getSmallStreet().equals("")) {
                    holderItem.smallStreet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setSmallStreet();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.bigStreet.setText(player.getBigStreet());
                if (player.getClickable() && player.getBigStreet().equals("")) {
                    holderItem.bigStreet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setBigStreet();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.kniffel.setText(player.getKniffel());
                if (player.getClickable() && player.getKniffel().equals("")) {
                    holderItem.kniffel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setKniffel();
                            setPlayerEntries(players);
                        }
                    });
                }

                holderItem.chance.setText(player.getChance());
                if (player.getClickable() && player.getChance().equals("")) {
                    holderItem.chance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            player.setChance();
                            setPlayerEntries(players);
                        }
                    });
                }


                holderItem.subtotals.setText(player.getSubtotals());
                holderItem.bonus.setText(player.getBonus());
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
