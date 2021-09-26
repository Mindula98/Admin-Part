package com.example.adminapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

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
    protected void onBindViewHolder(@NonNull groupHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull GroupsModel model) {
        holder.groupName.setText(model.getName());
        holder.details.setText(model.getDetails());

        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.labrador_retriever)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.image);


        //Update Process
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1350)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.upd_grpName);
                EditText img = view.findViewById(R.id.upd_grpImage);
                EditText dtls = view.findViewById(R.id.upd_grpDetails);

                Button btnUpdate = view.findViewById(R.id.btn_update);

                name.setText(model.getName());
                img.setText(model.getImage());
                dtls.setText(model.getDetails());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("Name",name.getText().toString());
                        map.put("Image",img.getText().toString());
                        map.put("Details",dtls.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Groups")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.groupName.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.groupName.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        //Delete Process
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.groupName.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("This Process Cannot be Undone");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Groups")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.groupName.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }




    @NonNull
    @Override
    public groupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_holder, parent,false);
        return new groupHolder(view);
    }

    class groupHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView groupName, details;

        Button btnEdit, btnDelete;

        public groupHolder(@NonNull View itemView) {
            super(itemView);

            image = (CircleImageView)itemView.findViewById(R.id.groupImage);
            groupName = (TextView)itemView.findViewById(R.id.groupName);
            details = (TextView)itemView.findViewById(R.id.groupDetails);

            btnEdit = (Button)itemView.findViewById(R.id.btn_groupEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btn_groupDelete);
        }
    }

}
