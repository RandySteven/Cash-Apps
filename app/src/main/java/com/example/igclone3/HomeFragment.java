package com.example.igclone3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.igclone3.adapter.TransactionsAdapter;
import com.example.igclone3.model.Transaction;
import com.example.igclone3.model.User;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Transaction> transactions;
    User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return viewGroup;
    }

    private void init(){
        user = (User) getActivity().getIntent().getSerializableExtra("user");
        transactions = new ArrayList<>();
//        transactions.add(new Transaction())
    }

    ListView lvDaftarTransaksi;
    TransactionsAdapter transactionsAdapter;
    private void listView(){
        lvDaftarTransaksi = viewGroup.findViewById(R.id.lvDaftarTransaksi);
        transactionsAdapter = new TransactionsAdapter(getContext(), transactions);
        lvDaftarTransaksi.setAdapter(transactionsAdapter);
    }

}