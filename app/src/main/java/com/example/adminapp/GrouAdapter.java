package com.example.adminapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class GrouAdapter extends FirebaseRecyclerAdapter<GroupsModel,GrouAdapter.groupHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public GrouAdapter(@NonNull FirebaseRecyclerOptions<GroupsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull groupHolder holder, int position, @NonNull GroupsModel model) {
        holder.groupName.setText(model.getName());
        holder.details.setText(model.getDetails());

        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.labrador_retriever)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.image);
    }

    @NonNull
    @Override
    public groupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_holder, parent,false);
        return new groupHolder(view);
    }

    class groupHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView groupName;
        TextView details;

        public groupHolder(@NonNull View itemView) {
            super(itemView);

            image = (CircleImageView)itemView.findViewById(R.id.groupImage);
            groupName = (TextView)itemView.findViewById(R.id.groupName);
            details = (TextView)itemView.findViewById(R.id.groupDetails);
        }
    }

}
