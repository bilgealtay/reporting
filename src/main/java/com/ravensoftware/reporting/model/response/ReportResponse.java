package com.ravensoftware.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ravensoftware.reporting.model.enums.ReportStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportResponse implements Serializable {

    private ReportStatus status;
    private List<TransactionReportResponse> response = new ArrayList<>();
    private String errorMessage;

}
