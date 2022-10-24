package com.ravensoftware.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ravensoftware.reporting.model.entity.Acquirer;
import com.ravensoftware.reporting.model.entity.CustomerInfo;
import com.ravensoftware.reporting.model.entity.Merchant;
import com.ravensoftware.reporting.model.entity.Transaction;
import lombok.Data;

/**
 * Created by bilga
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponse {

    private FX fx;

    private CustomerInfo customerInfo;

    private Acquirer acquirerTransactions;

    private Merchant merchant;

    private Transaction transactions;
}
