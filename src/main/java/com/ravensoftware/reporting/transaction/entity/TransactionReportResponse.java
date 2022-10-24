package com.ravensoftware.reporting.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bilga on 23-02-2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionReportResponse implements Serializable {

    private long count;
    private BigDecimal total = BigDecimal.ZERO;
    private String currency;

}
