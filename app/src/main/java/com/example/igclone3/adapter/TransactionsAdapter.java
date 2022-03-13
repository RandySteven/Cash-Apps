package com.example.igclone3.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.igclone3.R;
import com.example.igclone3.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends ArrayAdapter<Transaction> {

    private ArrayList<Transaction> transactions;
    private Context context;

    public TransactionsAdapter(Context context, ArrayList<Transaction> transactions){
        super(context, 0, transactions);
        this.transactions = transactions;
        this.context = context;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return transactions.size();
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        Transaction transaction = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.transaction_item, parent, false);
        }
        TextView tvTransactionDate, tvTransactionType, tvTransactionCategory, tvTransactionAmount, tvTransactionDescription;
        tvTransactionDate = convertView.findViewById(R.id.tvTransactionDate);
        tvTransactionCategory = convertView.findViewById(R.id.tvTransactionCategory);
        tvTransactionType = convertView.findViewById(R.id.tvTransactionType);
        tvTransactionAmount = convertView.findViewById(R.id.tvTransactionAmount);
        tvTransactionDescription = convertView.findViewById(R.id.tvTransactionDescription);

        tvTransactionDate.setText(transaction.getTransactionDate());
        tvTransactionAmount.setText(transaction.getTransactionAmount()+"");
        tvTransactionCategory.setText(transaction.getTransactionCategory());
        tvTransactionType.setText(transaction.getTransactionType());
        tvTransactionDescription.setText(transaction.getTransactionDescription());

        return convertView;
    }
}
