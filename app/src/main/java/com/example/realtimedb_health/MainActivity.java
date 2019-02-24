package com.example.realtimedb_health;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private DatabaseReference mRootReference = firebaseDatabase.getReference();

    private DatabaseReference mDiseaseReference = mRootReference.child("Disease");

    private DatabaseReference mSymptomsReference = mRootReference.child("Symptoms");

    private EditText diseaseInput, sympInput;

    private TextView readData, readSymp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diseaseInput = findViewById(R.id.inputEditText);

        readData = findViewById(R.id.displayText);

        sympInput = findViewById(R.id.sympEditText);

        readSymp = findViewById(R.id.sympText);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
    /*
        if(dataSnapshot.child("Disease").getValue(String.class) != null){
            String key = dataSnapshot.child("Disease").getKey();

            if(key.equals("AIDS")){

                String disease = dataSnapshot.child("Disease").child("AIDS").getValue(String.class);

                readData.setText(disease);
            }


            else if (key.equals("Tuberculosis")){

                String symptoms = dataSnapshot.child("Disease").child("Tuberculosis").getValue(String.class);

                readSymp.setText(symptoms);
            }

        } */


    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        mDiseaseReference.addValueEventListener(this);

        mSymptomsReference.addValueEventListener(this);
    }

    public void submitHeading(View view) {

        String disease = diseaseInput.getText().toString();

        mDiseaseReference.setValue(disease);

        String symptoms = sympInput.getText().toString();

        mSymptomsReference.setValue(symptoms);

        diseaseInput.setText("");

        sympInput.setText("");

    }

    public void getBtn(View view) {

        mDiseaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                readData.setText(dataSnapshot.child("AIDS").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        mSymptomsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                readSymp.setText(dataSnapshot.child("Fiver").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
