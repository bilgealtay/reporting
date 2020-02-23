package com.ravensoftware.reporting.transaction.boundary;

import com.ravensoftware.reporting.transaction.entity.ReportResponse;
import com.ravensoftware.reporting.transaction.entity.SearchParam;
import com.ravensoftware.reporting.contact.entity.CustomerInfo;
import com.ravensoftware.reporting.transaction.control.TransactionControl;
import com.ravensoftware.reporting.transaction.entity.Transaction;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by bilga on 21-02-2020
 */
@RestController
@RequestMapping(path = "/transactions")
public class TransactionResources {

    private TransactionControl transactionControl;

    public TransactionResources(TransactionControl transactionControl) {
        this.transactionControl = transactionControl;
    }

    @ApiOperation(value = "Save transaction to the System ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping
    public ResponseEntity save(@ApiParam(value = "Transaction save a database table", required = true) @Valid @RequestBody Transaction transaction) {
        Transaction save  = transactionControl.save(transaction);
        return ResponseEntity.ok(save);
    }

    @ApiOperation(value = "Find Transaction" , response = Transaction.class)
    @GetMapping("/transaction")
    public ResponseEntity getTransaction(@ApiParam(value = "Id for get transaction", required = true) @org.springframework.web.bind.annotation.RequestParam("transactionId") @NotNull Long id) {
        Transaction transaction = transactionControl.findById(id);
        return ResponseEntity.ok(transaction);
    }

    @ApiOperation(value = "Find Customer Info of Transaction" , response = CustomerInfo.class)
    @GetMapping("/client")
    public ResponseEntity getCustomerInfo(@ApiParam(value = "Transaction id for get customer info of transaction", required = true) @org.springframework.web.bind.annotation.RequestParam("transactionId") @NotNull Long id) {
        CustomerInfo customerInfo = transactionControl.getCustomerInfo(id);
        return ResponseEntity.ok(customerInfo);
    }

    @ApiOperation(value = "Get Transaction list ")
    @PostMapping("/list")
    public ResponseEntity findAll(Pageable pageable, @RequestBody(required = false) SearchParam searchParam) {
        Page<Transaction> transactions = transactionControl.findAll(pageable, searchParam);
        return ResponseEntity.ok(transactions);
    }

    @ApiOperation(value = "Get Transaction Report ")
    @PostMapping("/report")
    public ResponseEntity getReport(@RequestBody(required = false) SearchParam searchParam) {
        ReportResponse reportResponse = transactionControl.prepareReport(searchParam);
        return ResponseEntity.ok(reportResponse);
    }
}
