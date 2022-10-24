package com.ravensoftware.reporting.transaction;

import com.ravensoftware.reporting.base.Currency;
import com.ravensoftware.reporting.customer.acquirer.AcquirerControl;
import com.ravensoftware.reporting.customer.customerInfo.CustomerInfoControl;
import com.ravensoftware.reporting.customer.merchant.MerchantControl;
import com.ravensoftware.reporting.exception.ReportingApiException;
import com.ravensoftware.reporting.model.entity.CustomerInfo;
import com.ravensoftware.reporting.model.entity.Transaction;
import com.ravensoftware.reporting.model.enums.ReportStatus;
import com.ravensoftware.reporting.model.response.FX;
import com.ravensoftware.reporting.model.response.FXMerchant;
import com.ravensoftware.reporting.model.response.TransactionResponse;
import com.ravensoftware.reporting.model.request.ReportRequest;
import com.ravensoftware.reporting.model.response.ReportResponse;
import com.ravensoftware.reporting.model.request.SearchParam;
import com.ravensoftware.reporting.model.response.TransactionReportResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by bilga
 */
@Component
public class TransactionControl {

    private TransactionRepository repository;
    private AcquirerControl acquirerControl;
    private MerchantControl merchantControl;
    private CustomerInfoControl customerInfoControl;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.UK);

    public TransactionControl(TransactionRepository repository, AcquirerControl acquirerControl, MerchantControl merchantControl, CustomerInfoControl customerInfoControl) {
        this.repository = repository;
        this.acquirerControl = acquirerControl;
        this.merchantControl = merchantControl;
        this.customerInfoControl = customerInfoControl;
    }

    public Transaction save(Transaction transaction) {
        if (transaction.getAcquirer().getId() != null){
            transaction.setAcquirer(acquirerControl.findById(transaction.getAcquirer().getId()));
        }
        if (transaction.getMerchant().getId() != null){
            transaction.setMerchant(merchantControl.findById(transaction.getMerchant().getId()));
        }
        if (transaction.getCustomerInfo().getId() != null){
            transaction.setCustomerInfo(customerInfoControl.findById(transaction.getCustomerInfo().getId()));
        }
        transaction.setTransactionDate(LocalDate.now());

        return repository.save(transaction);
    }

    public Transaction findById(Long id){
        Optional<Transaction> byId = repository.findById(id);
        if (!byId.isPresent()){
            return null;
        }
        return byId.get();
    }

    public TransactionResponse getTransaction(Long id) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(new ReportingApiException("TransactionId",
                        HttpStatus.BAD_REQUEST));
        return getTransactionResponse(transaction);
    }

    private TransactionResponse getTransactionResponse(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setCustomerInfo(transaction.getCustomerInfo());
        transactionResponse.setFx(createFx(transaction.getAmount(), transaction.getCurrency()));
        transactionResponse.setAcquirerTransactions(transaction.getAcquirer());
        transactionResponse.setMerchant(transaction.getMerchant());
        transactionResponse.setTransactions(transaction);
        return transactionResponse;
    }

    private FX createFx(BigDecimal amount, Currency currency) {
        FX fx = new FX();
        fx.setMerchant(new FXMerchant(amount, currency));
        return fx;
    }

    public CustomerInfo getCustomerInfo(Long id) {
        Transaction transaction = repository.findByIdFetchCustomerInfo(id)
                .orElseThrow(new ReportingApiException("TransactionId", HttpStatus.BAD_REQUEST));
        return transaction.getCustomerInfo();
    }

    public Page<TransactionResponse> findAll(Pageable pageable, SearchParam searchParam) {
        Page<Transaction> transactions = repository.findAll(prepareQuery(searchParam), pageable);
        return transactions.map(this::getTransactionResponse);
    }

    public ReportResponse prepareReport(ReportRequest param) {
        ReportResponse reportResponse = new ReportResponse();
        try {
            List<Transaction> transactions = repository.findAll(prepareReportQuery(param));
            Map<String, TransactionReportResponse> currencyMap = new HashMap<>();

            transactions.forEach(t -> {
                TransactionReportResponse response = currencyMap.get(t.getCurrency().toString());
                if (response == null) {
                    response = new TransactionReportResponse();
                }
                response.setCurrency(t.getCurrency().toString());
                response.setTotal(response.getTotal().add(t.getAmount()));
                response.setCount(response.getCount() + 1);
                currencyMap.put(t.getCurrency().toString(), response);
            });
            currencyMap.forEach((key, value) -> reportResponse.getResponse().add(value));
            reportResponse.setStatus(ReportStatus.APPROVED);
        } catch (Exception e) {
            reportResponse.setErrorMessage(e.getCause().getMessage());
        }
        return reportResponse;
    }

    private Specification<Transaction> prepareQuery(SearchParam param) {

        Specification<Transaction> query = Specification.where(null);

        if (Optional.ofNullable(param.getFromDate()).isPresent()) {
            query = query.and(TransactionSpecification.fromDate(param.getFromDate()));
        }

        if (Optional.ofNullable(param.getToDate()).isPresent()) {
            query = query.and(TransactionSpecification.toDate(param.getToDate()));
        }

        if (Optional.ofNullable(param.getMerchantId()).isPresent()) {
            query = query.and(TransactionSpecification.merchant(param.getMerchantId()));
        }

        if (Optional.ofNullable(param.getAcquirerId()).isPresent()) {
            query = query.and(TransactionSpecification.acquirer(param.getAcquirerId()));
        }

        if (Optional.ofNullable(param.getTransactionStatus()).isPresent()) {
            query = query.and(TransactionSpecification.status(param.getTransactionStatus()));
        }

        if (Optional.ofNullable(param.getOperation()).isPresent()) {
            query = query.and(TransactionSpecification.operation(param.getOperation()));
        }

        if (Optional.ofNullable(param.getPaymentMethod()).isPresent()) {
            query = query.and(TransactionSpecification.paymentMethod(param.getPaymentMethod()));
        }

        if (Optional.ofNullable(param.getErrorCode()).isPresent()) {
            query = query.and(TransactionSpecification.errorCode(param.getErrorCode()));
        }

        if (Optional.ofNullable(param.getFilterField()).isPresent()) {
            query = query.and(TransactionSpecification.filter(param.getFilterField(), param.getFilterValue()));
        }

        if (Optional.ofNullable(param.getEmail()).isPresent()) {
            long id = 0;
            CustomerInfo customerInfo = customerInfoControl.findByEmail(param.getEmail());
            if (customerInfo != null) {
                id = customerInfo.getId();
                query = query.and(TransactionSpecification.customer(id));
            }
        }

        return query;
    }

    public static Specification<Transaction> prepareReportQuery(ReportRequest param) {

        Specification<Transaction> query = Specification.where(null);

        if (Optional.ofNullable(param.getFromDate()).isPresent()) {
            query = query.and(TransactionSpecification.fromDate(param.getFromDate()));
        }

        if (Optional.ofNullable(param.getToDate()).isPresent()) {
            query = query.and(TransactionSpecification.toDate(param.getToDate()));
        }

        if (Optional.ofNullable(param.getMerchantId()).isPresent()) {
            query = query.and(TransactionSpecification.merchant(param.getMerchantId()));
        }

        if (Optional.ofNullable(param.getAcquirerId()).isPresent()) {
            query = query.and(TransactionSpecification.acquirer(param.getAcquirerId()));
        }

        return query;
    }
}
