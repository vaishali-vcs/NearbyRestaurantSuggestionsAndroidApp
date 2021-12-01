package com.example.vaishali_tatsat_COMP304Sec001_Lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> {

    private Context context;
    private ArrayList<RestaurantCard> restaurants;
    private OnItemClickListener listener;

    public RestaurantAdapter(Context context, ArrayList<RestaurantCard> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
        RestaurantCard restaurant = restaurants.get(position);
        holder.txtaddress.setText(restaurant.getAddress());
        holder.txtcuisine.setText(restaurant.getType());
        holder.txtName.setText(restaurant.getName());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    class RestaurantHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtcuisine;
        private TextView txtaddress;

        RestaurantHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtname);
            txtcuisine = itemView.findViewById(R.id.txttype);
            txtaddress = itemView.findViewById(R.id.txtaddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(restaurants.get(position));
                }
            });
        }

        void SetDetails(RestaurantCard restaurant) {
            txtName.setText(restaurant.getName());
            txtcuisine.setText(restaurant.getType());
            txtaddress.setText(restaurant.getAddress());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RestaurantCard restaurant);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
