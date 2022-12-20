package com.example.cva.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cva.Model.PitchModel;
import com.example.cva.R;

public class ShowDetailPitchActivity extends AppCompatActivity {
    private ImageView pic;
    private TextView title, price, description;
    private Button btnDeposit, btnPay;
    private PitchModel object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_pitch);

        pic = findViewById(R.id.pitchPic);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        description = findViewById(R.id.Description);
        btnDeposit = findViewById(R.id.btnDeposit);
        btnPay = findViewById(R.id.btnPay);

        object = (PitchModel) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(pic);

        title.setText(object.getTitle());
        price.setText(object.getFee() + "đ");
        description.setText(object.getDes());

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowDetailPitchActivity.this, "Pay 50% of the booking fee!", Toast.LENGTH_SHORT).show();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowDetailPitchActivity.this, "Pay 100% of the booking fee!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}