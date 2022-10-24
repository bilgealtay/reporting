package com.ravensoftware.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionListResponse {
    private List<TransactionResponse> transactionResponseList = new ArrayList<>();
}
