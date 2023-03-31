package com.iracingui.ui.input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.iracingui.R;
import com.iracingui.databinding.FragmentNotificationsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputScreenFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NavController navController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://iracingai-default-rtdb.europe-west1.firebasedatabase.app/");



        View view = inflater.inflate(R.layout.input_layout, container, false);

        TextView textView1 = view.findViewById(R.id.textView1);
        EditText editText1 = view.findViewById(R.id.editText1);
        TextView textView2 = view.findViewById(R.id.textView2);
        EditText editText2 = view.findViewById(R.id.editText2);
        TextView textView3 = view.findViewById(R.id.textView3);
        EditText editText3 = view.findViewById(R.id.editText3);
        TextView textView4 = view.findViewById(R.id.textView4);
        EditText editText4 = view.findViewById(R.id.editText4);
        TextView textView5 = view.findViewById(R.id.textView5);
        EditText editText5 = view.findViewById(R.id.editText5);
        TextView textView6 = view.findViewById(R.id.textView6);
        EditText editText6 = view.findViewById(R.id.editText6);


        // initial text for the TextViews
        textView1.setText("Race Length");
        textView2.setText("AVG. Lap Time");
        textView3.setText("Fuel MAX");
        textView4.setText("Fuel per Lap");
        textView5.setText("Pit Stop Delta");
        textView6.setText("Number of dipshits signed up");

        // Navigation button to Input-screen and save data to firebase
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        Button notificButton = view.findViewById(R.id.notificButton);
        notificButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference raceLengthRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/race_duration");
                raceLengthRef.setValue(editText1.getText().toString());
                DatabaseReference avgLapRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/avg_lap_time");
                avgLapRef.setValue(editText2.getText().toString());
                DatabaseReference fuelMaxRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/fuel_tank_size");
                fuelMaxRef.setValue(editText3.getText().toString());
                DatabaseReference fuelLapRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/fuel_per_lap");
                fuelLapRef.setValue(editText4.getText().toString());
                DatabaseReference pitRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/pit_stop_lost_time");
                pitRef.setValue(editText5.getText().toString());
                // Repeat this for each EditText

                // Navigate to NotificationsFragment
                navController.navigate(R.id.navigation_notifications);
            }
        });

        DatabaseReference raceLengthRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/race_duration");
        raceLengthRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String race_duration = snapshot.getValue(String.class);
                editText1.setText(race_duration);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here
            }
        });

        DatabaseReference avgLapRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/avg_lap_time");
        avgLapRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String avg_lap_time = snapshot.getValue(String.class);
                editText2.setText(avg_lap_time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here
            }
        });

        DatabaseReference fuelMaxRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/fuel_tank_size");
        fuelMaxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fuel_tank_size = snapshot.getValue(String.class);
                editText3.setText(fuel_tank_size);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here
            }
        });


        DatabaseReference fuelLapRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/fuel_per_lap");
        fuelLapRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fuel_per_lap = snapshot.getValue(String.class);
                editText4.setText(fuel_per_lap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here
            }
        });

        DatabaseReference pitRef = firebaseDatabase.getReference("Team 1/Race 1/prerace/pit_stop_lost_time");
        pitRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pit_stop_lost_time = snapshot.getValue(String.class);
                editText5.setText(pit_stop_lost_time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here
            }
        });

        return view;
    }








}
