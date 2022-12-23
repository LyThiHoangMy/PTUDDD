package com.example.cva.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cva.Model.Bill;
import com.example.cva.R;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>{
    List<Bill> bills;

    public BillAdapter(List<Bill> bills) {
        this.bills = bills;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        holder.billDate.setText(bills.get(position).getDate());
        holder.billName.setText(bills.get(position).getName());
        holder.billFee.setText(bills.get(position).getFee());
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    class BillViewHolder extends RecyclerView.ViewHolder{
        TextView billDate, billName,billFee;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);

            billDate = itemView.findViewById(R.id.tvBillDate);
            billName = itemView.findViewById(R.id.tvBillName);
            billFee = itemView.findViewById(R.id.tvBillFee);
        }
    }
}
