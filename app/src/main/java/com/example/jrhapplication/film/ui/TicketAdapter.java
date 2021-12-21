package com.example.jrhapplication.film.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jrhapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.VH>{

    private List<String> tickets = new ArrayList<String>();



    public void setTickets(List<String> tickets) {
        this.tickets.clear();
        this.tickets.addAll(tickets);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket,null);
        return new VH(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }


    class VH extends RecyclerView.ViewHolder {

        public VH(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvTicketName);
            tvTime = itemView.findViewById(R.id.tvTicketTime);
        }

         TextView tvName,tvTime;


    }
}
