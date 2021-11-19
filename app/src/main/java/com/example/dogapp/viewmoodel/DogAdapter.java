package com.example.dogapp.viewmoodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.view.ListFragment;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {

    private ArrayList<DogBreed> dogList;
    private Context context;
    private FragmentViewHolder fragmentViewHolder;

    public DogAdapter(ArrayList<DogBreed> dogList) {
        this.dogList = dogList;
    }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.tvName.setText(dogList.get(position).getName());
        holder.tvDetails.setText(dogList.get(position).getBredFor());
        Picasso.get().load(dogList.get(position).getUrl()).into(holder.imgDog);
    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDog;
        TextView tvName, tvDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDog = itemView.findViewById(R.id.img_Dog);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDetails = itemView.findViewById(R.id.tv_details);
        }
    }
}
