package com.example.igclone3.dao;

import com.example.igclone3.model.Transaction;

import java.util.List;

public interface TransactionDAO {
    public void createTransaction(Transaction transaction);
    public List<Transaction> getAllTransactions(String userId);
    public Transaction getTransactionDetail(String transactionId);
    public void updateTransaction(String transactionId, Transaction transaction);
    public void deleteTransaction(String transactionId);
}
