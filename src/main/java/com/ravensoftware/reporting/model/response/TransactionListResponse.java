package com.ravensoftware.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilga on 22-10-2022
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionListResponse {
    private List<TransactionResponse> transactionResponseList = new ArrayList<>();
}
