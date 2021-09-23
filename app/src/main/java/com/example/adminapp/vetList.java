package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class vetList extends AppCompatActivity implements VeterinarianAdapter.VetClickInterface {

    private RecyclerView vetRV;
    private TextView vetList_TV;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<VeterinarianModel> veterinarianModelArrayList;
    private RelativeLayout bottomSheetRL;
    private VeterinarianAdapter veterinarianAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_list);
        vetRV = findViewById(R.id.idVet);
        vetList_TV = findViewById(R.id.TV_vetList);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Veterinarians");
        veterinarianModelArrayList = new ArrayList<>();
        veterinarianAdapter =  new VeterinarianAdapter(veterinarianModelArrayList,this,this);
        vetRV.setLayoutManager(new LinearLayoutManager(this));
        vetRV.setAdapter(veterinarianAdapter);
        bottomSheetRL = findViewById(R.id.idRLBSheet);

        getAllVeterinarians();

    }

    private void getAllVeterinarians(){

        veterinarianModelArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

               veterinarianModelArrayList.add(snapshot.getValue(VeterinarianModel.class));
               veterinarianAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                veterinarianAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                veterinarianAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                veterinarianAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onVetClick(int position) {
        displayBottomSheet(veterinarianModelArrayList.get(position));

    }

    private void displayBottomSheet(VeterinarianModel veterinarianModel){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView vetNameTV = layout.findViewById(R.id.idTVVetName);
        TextView addressTV = layout.findViewById(R.id.idTVAddress);
        TextView contactTV = layout.findViewById(R.id.idTVContact);
        TextView emailTV = layout.findViewById(R.id.idTVMail);
        Button editBtn = layout.findViewById(R.id.idBtnEdit);

        vetNameTV.setText("Dr. "+veterinarianModel.getVetName());
        addressTV.setText(veterinarianModel.getVetAddress());
        contactTV.setText(veterinarianModel.getVetContact());
        emailTV.setText(veterinarianModel.getVetEmail());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(vetList.this,EditVeterinarian.class);
                i.putExtra("veterinarian",veterinarianModel);
                startActivity(i);

            }
        });
    }
}