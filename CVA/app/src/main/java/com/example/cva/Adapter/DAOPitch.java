package com.example.cva.Adapter;

import com.example.cva.Model.PitchModelAd;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOPitch {
    private DatabaseReference databaseReference;

    public DAOPitch()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(PitchModelAd.class.getSimpleName());
    }

    public Task<Void> add(PitchModelAd pit)
    {
        return databaseReference.push().setValue(pit);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public  Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }
}
