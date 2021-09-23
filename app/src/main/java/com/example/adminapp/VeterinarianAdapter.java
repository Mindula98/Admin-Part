package com.example.adminapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class VeterinarianAdapter extends RecyclerView.Adapter<VeterinarianAdapter.ViewHolder> {
    int lasPos = -1;
    private ArrayList<VeterinarianModel> veterinarianModelArrayList;
    private Context context;
    private VetClickInterface vetClickInterface;

    public VeterinarianAdapter(ArrayList<VeterinarianModel> veterinarianModelArrayList, Context context, VetClickInterface vetClickInterface) {
        this.veterinarianModelArrayList = veterinarianModelArrayList;
        this.context = context;
        this.vetClickInterface = vetClickInterface;
    }

    @NonNull
    @Override
    public VeterinarianAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vetlist_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeterinarianAdapter.ViewHolder holder, int position) {
        VeterinarianModel veterinarianModel = veterinarianModelArrayList.get(position);
        holder.vetNameTV.setText("Dr."+veterinarianModel.getVetName());
        setAnimation(holder.itemView,position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vetClickInterface.onVetClick(position);
            }
        });

    }

    private void setAnimation(View itemView, int position){

        if (position>lasPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lasPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return veterinarianModelArrayList.size();
    }

    public interface VetClickInterface{
        void onVetClick(int position);
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {


        private TextView vetNameTV ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vetNameTV = itemView.findViewById(R.id.crdView_TV);

        }
    }
}
