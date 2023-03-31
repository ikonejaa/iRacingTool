package com.iracingui.ui.home;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.iracingui.R;
import com.iracingui.databinding.FragmentHomeBinding;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView timerText;
    private Button startButton;
    private CountDownTimer countDownTimer;
    private long startingTimeMillis;
    private long timeLeftInMillis;
    private String startingTimeStr;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        timerText = root.findViewById(R.id.timerText);
        startButton = root.findViewById(R.id.startButton);

        firebaseDatabase = FirebaseDatabase.getInstance("https://iracingai-default-rtdb.europe-west1.firebasedatabase.app/");

        // Read data from the database
        DatabaseReference stintRef = firebaseDatabase.getReference("Team 1/Race 1/backend/stint_length");
        stintRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stint_length = snapshot.getValue(String.class);
                long stintLength = Long.parseLong(stint_length);
                timerText.setText(String.valueOf(stintLength));
                startingTimeMillis = stintLength * 60 * 1000;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        // timer setup
        countDownTimer = new CountDownTimer(startingTimeMillis, 60000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timerText.setText("PIT");
            }
        };

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
            }
        });

        return root;
    }

    private void startTimer() {
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        timerText.setText(String.format(Locale.getDefault(), "%02d", minutes));
    }

    private void resetTimer() {
        countDownTimer.cancel();
        timeLeftInMillis = startingTimeMillis;
        updateCountDownText();
    }
}



