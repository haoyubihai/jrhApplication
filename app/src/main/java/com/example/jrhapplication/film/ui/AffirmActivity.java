package com.example.jrhapplication.film.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jrhapplication.R;
import com.example.jrhapplication.widgets.PlusMinusView;

import java.util.ArrayList;
import java.util.List;

public class AffirmActivity extends Activity {

    private TextView tvName,tvDesc,tvTip,btnSure,btnCancel;
    private PlusMinusView plusMinusView;
    private RecyclerView rcList;
    private Boolean hasTickets = false;
    /**
     * 0 身份证 1 二维码票据 2 人员二维码
     */
    private int type = 0;
    private List<Ticket> tickets = new ArrayList<Ticket>();
    private TicketAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affirm);
        initViews();
        initRcList();
        requestInfo();
    }

    private void requestInfo() {
    }

    private void initRcList() {
        mAdapter = new TicketAdapter();

        rcList.setAdapter(mAdapter);
        rcList.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i <200 ; i++) {
            Ticket t = new Ticket();
            t.setName("i="+i);
            tickets.add(t);
        }
        mAdapter.setTickets(tickets);
        mAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        tvName = findViewById(R.id.tvName);
        tvDesc = findViewById(R.id.tvDesc);
        tvTip = findViewById(R.id.tvTip);
        btnSure = findViewById(R.id.btnSure);
        btnCancel = findViewById(R.id.btnCancel);
        plusMinusView = findViewById(R.id.tvPlusMinus);
        rcList = findViewById(R.id.rcList);
        
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sure();
            }
        });
        
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }




    private void showPlusMinusView() {
        tvTip.setText("请输入核销数目");
        plusMinusView.setVisibility(View.VISIBLE);
        rcList.setVisibility(View.GONE);
    }

    private void showTickets(){
        tvTip.setText("请选择票据");
        plusMinusView.setVisibility(View.GONE);
        rcList.setVisibility(View.VISIBLE);
    }
    

    private void cancel() {
    }

    private void sure() {

        mAdapter.getCheckBoxPositions();
    }
}