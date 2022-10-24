package com.ravensoftware.reporting.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ravensoftware.reporting.model.enums.ReportStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilga on 23-02-2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportResponse implements Serializable {

    private ReportStatus status;
    private List<TransactionReportResponse> response = new ArrayList<>();
    private String errorMessage;

}
