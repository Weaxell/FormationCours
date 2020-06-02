package com.example.formationcours;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<Villager> villagers;
    private MainActivity mainActivity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        View layout;
        TextView txtVillName;
        TextView txtVillBirthday;
        TextView txtVillPersonality;
        TextView txtVillSpecies;
        TextView txtVillIdNumber;
        ImageView iconImgView;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtVillName = (TextView) v.findViewById(R.id.textViewName);
            txtVillBirthday = (TextView) v.findViewById(R.id.textViewBirthday);
            txtVillPersonality = (TextView) v.findViewById(R.id.textViewPersonality);
            txtVillSpecies = (TextView) v.findViewById(R.id.textViewSpecies);
            txtVillIdNumber = (TextView) v.findViewById(R.id.textViewIdNumber);
            iconImgView = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void add(int position, Villager villagerItem) {
        villagers.add(position, villagerItem);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        villagers.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(ArrayList<Villager> myDataset, MainActivity pMainActivity) {
        villagers = myDataset;
        mainActivity = pMainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout2, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Villager villager = villagers.get(position);
        holder.txtVillName.setText(villager.getName().get("name-EUfr"));
        holder.txtVillName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove(position);
                mainActivity.displayVillager(villagers.get(position));
            }
        });

        holder.iconImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Villager vill = villagers.get(position);
                mainActivity.proceedFavorites(vill);
                notifyItemChanged(position);

            }
        });

        holder.txtVillBirthday.setText("Birthday: " + villager.getBirthday());
        holder.txtVillPersonality.setText("Personality: " + villager.getPersonality());
        holder.txtVillSpecies.setText("Species: " + villager.getSpecies());
        holder.txtVillIdNumber.setText("ID n°" + villager.getId());

        new DownloadImageTask(holder.iconImgView).execute(villager.getIcon_uri());
        if(mainActivity.isVillagerFavorite(villager))
            holder.iconImgView.setBackgroundDrawable(ContextCompat.getDrawable(mainActivity.getApplicationContext(), R.drawable.favorite_back));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return villagers.size();
    }

}