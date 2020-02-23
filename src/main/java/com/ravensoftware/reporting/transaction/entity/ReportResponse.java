package com.ravensoftware.reporting.transaction.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilga on 23-02-2020
 */
public class ReportResponse implements Serializable {

    private ReportStatus status;
    private List<TransactionReportResponse> response = new ArrayList<>();

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public List<TransactionReportResponse> getResponse() {
        return response;
    }

    public void setResponse(List<TransactionReportResponse> response) {
        this.response = response;
    }
}
