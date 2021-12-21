package com.example.jrhapplication.film.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jrhapplication.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.VH> {

    private List<Ticket> tickets = new ArrayList<Ticket>();

    Map<Integer, Boolean> checkedMap = new HashMap<Integer, Boolean>();


    public void setTickets(List<Ticket> tickets) {
        this.tickets.clear();
        this.tickets.addAll(tickets);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, null);
        return new VH(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        int index = holder.getAdapterPosition();
        Ticket item = tickets.get(index);
        holder.tvName.setText(item.getName());

        holder.checkBox.setChecked(item.getChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extracted(index, item);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extracted(index, item);

            }
        });
    }

    private void extracted(int index, Ticket item) {
        item.setChecked(!item.getChecked());
        notifyDataSetChanged();
    }


    public void getCheckBoxPositions() {
        for (Map.Entry<Integer, Boolean> entry : checkedMap.entrySet()) {
            System.out.println("entry=" + entry);
        }
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
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        TextView tvName, tvTime;
        CheckBox checkBox;


    }
}
