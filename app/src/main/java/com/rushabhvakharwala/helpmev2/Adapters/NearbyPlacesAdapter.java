package com.rushabhvakharwala.helpmev2.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.NearbyPlaces;
import com.rushabhvakharwala.helpmev2.R;

import java.util.List;

public class NearbyPlacesAdapter extends RecyclerView.Adapter<NearbyPlacesAdapter.NearbyPlacesViewHolder>{

    private Context mCtx;
    private List<NearbyPlaces> placesList;

    public NearbyPlacesAdapter(Context mCtx, List<NearbyPlaces> placesList) {
        this.mCtx = mCtx;
        this.placesList = placesList;
    }

    @Override
    public NearbyPlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.nearbycards,null);
        NearbyPlacesViewHolder holder = new NearbyPlacesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NearbyPlacesViewHolder holder, int position) {
        NearbyPlaces nearbyPlace = placesList.get(position);

        //Setters
        holder.circleLogo.setImageBitmap(nearbyPlace.getBitmap());
        holder.name.setText(nearbyPlace.getName());
        holder.address.setText(nearbyPlace.getVicinity());
        holder.description.setText(nearbyPlace.getDescription());
        holder.storeImage.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.image_7,null));
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    class NearbyPlacesViewHolder extends RecyclerView.ViewHolder {

        com.mikhaellopez.circularimageview.CircularImageView circleLogo;
        TextView name;
        TextView address;
        TextView description;
        ImageView storeImage;

        public NearbyPlacesViewHolder(View itemView) {
            super(itemView);

            circleLogo = itemView.findViewById(R.id.logo_circle_cards);
            name = itemView.findViewById(R.id.name_cards);
            address = itemView.findViewById(R.id.address_cards);
            description = itemView.findViewById(R.id.descripion_cards);
            storeImage = itemView.findViewById(R.id.image_cards);
        }
    }


}
