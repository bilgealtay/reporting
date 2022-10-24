package com.ravensoftware.reporting.transaction.resources;

import com.ravensoftware.reporting.model.entity.CustomerInfo;
import com.ravensoftware.reporting.model.entity.Transaction;
import com.ravensoftware.reporting.model.response.TransactionResponse;
import com.ravensoftware.reporting.transaction.control.TransactionControl;
import com.ravensoftware.reporting.transaction.entity.ReportRequest;
import com.ravensoftware.reporting.transaction.entity.ReportResponse;
import com.ravensoftware.reporting.transaction.entity.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by bilga on 21-02-2020
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transactions")
public class TransactionResources {

    private final TransactionControl transactionControl;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Transaction transaction) {
        Transaction save  = transactionControl.save(transaction);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/transaction")
    public ResponseEntity<TransactionResponse> getTransaction(@RequestParam(name = "transactionId") Long id) {
        return ResponseEntity.ok(transactionControl.getTransaction(id));
    }

    @GetMapping("/client")
    public ResponseEntity getCustomerInfo(@RequestParam(name = "transactionId") Long id) {
        CustomerInfo customerInfo = transactionControl.getCustomerInfo(id);
        return ResponseEntity.ok(customerInfo);
    }

    @PostMapping("/list")
    public ResponseEntity findAll(Pageable pageable, @RequestBody(required = false) SearchParam searchParam) {
        Page<TransactionResponse> transactions = transactionControl.findAll(pageable, searchParam);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/report")
    public ResponseEntity getReport(@RequestBody ReportRequest reportRequest) {
        ReportResponse reportResponse = transactionControl.prepareReport(reportRequest);
        return ResponseEntity.ok(reportResponse);
    }
}
