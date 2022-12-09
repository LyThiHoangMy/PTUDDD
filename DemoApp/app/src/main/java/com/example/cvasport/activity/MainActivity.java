package com.example.cvasport.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.cvasport.R;
import com.example.cvasport.adapter.LoaiSanPhamAdapter;
import com.example.cvasport.model.LoaiSanPham;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewHome;
    NavigationView navigationView;
    ListView listViewHome;
    DrawerLayout drawerLayout;
    LoaiSanPhamAdapter loaiSanPhamAdapter;
    List<LoaiSanPham> listLoaiSP;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping(); //AnhXa()
        ActionBar();
        if(isConnected(this))
        {
            Toast.makeText(getApplicationContext(), "Have Internet", Toast.LENGTH_SHORT).show();
            ActionViewFlipper();
        }
        else {
            Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void ActionViewFlipper() {
        List<String> arrayQuangCao = new ArrayList<>();
        arrayQuangCao.add("https://ducminhads.com/media/1667/gia-lam-san-bong-da-co-nhan-tao-mini-1.jpg");
        arrayQuangCao.add("https://tse3.mm.bing.net/th?id=OIP.f24VU1yH_Hb0bxerGkGyEAHaFj&pid=Api&P=0");
        arrayQuangCao.add("https://tse4.mm.bing.net/th?id=OIP.Fuvs_jm7RIM_574gAnEZewHaET&pid=Api&P=0");
        arrayQuangCao.add("https://tse2.mm.bing.net/th?id=OIP.5Yo3Q8F-VBbw7o0D1ppvGwHaDZ&pid=Api&P=0");
        for(int i = 0; i< arrayQuangCao.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrayQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right_out);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Mapping() {
        toolbar = findViewById(R.id.toolbarHome);
        viewFlipper = findViewById(R.id.viewFlipperHome);
        recyclerViewHome = findViewById(R.id.rcvHome);
        navigationView = findViewById(R.id.navigViewHome);
        listViewHome = findViewById(R.id.lvHome);
        drawerLayout = findViewById(R.id.drawerLayout);

        //Khoi tao adapter LoaiSanPham
        listLoaiSP = new ArrayList<>();
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(getApplicationContext(), listLoaiSP);
        listViewHome.setAdapter(loaiSanPhamAdapter);
    }

    private boolean isConnected(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected()))
        {
            return true;
        }
        else {
            return false;
        }
    }
}