package com.example.igclone3.model;

public class Transaction {
    private String id;
    private String transactionDate;
    private String transactionType;
    private String transactionCategory;
    private int transactionAmount;
    private String transactionDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public Transaction(String id, String transactionDate, String transactionType, String transactionCategory, int transactionAmount, String transactionDescription) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionCategory = transactionCategory;
        this.transactionAmount = transactionAmount;
        this.transactionDescription = transactionDescription;
    }
}
