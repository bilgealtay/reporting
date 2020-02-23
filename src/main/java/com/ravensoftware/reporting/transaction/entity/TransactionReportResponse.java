package com.ravensoftware.reporting.transaction.entity;

import java.io.Serializable;

/**
 * Created by bilga on 23-02-2020
 */
public class TransactionReportResponse implements Serializable {

    private long count;
    private long total;
    private String currency;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
