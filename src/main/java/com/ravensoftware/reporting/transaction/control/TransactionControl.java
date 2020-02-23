package com.ravensoftware.reporting.transaction.control;

import com.ravensoftware.reporting.contact.control.AcquirerControl;
import com.ravensoftware.reporting.contact.control.CustomerInfoControl;
import com.ravensoftware.reporting.contact.control.MerchantControl;
import com.ravensoftware.reporting.contact.entity.CustomerInfo;
import com.ravensoftware.reporting.transaction.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by bilga on 21-02-2020
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
        if (transaction.getTxDateStr() != null){
            transaction.setTxDate(LocalDate.parse(transaction.getTxDateStr(), formatter));
        }
        return repository.save(transaction);
    }

    public Transaction findById(Long id){
        Optional<Transaction> byId = repository.findById(id);
        if (!byId.isPresent()){
            return null;
        }
        return byId.get();
    }

    public CustomerInfo getCustomerInfo(Long id) {
        Transaction transaction = findById(id);
        return transaction.getCustomerInfo();
    }

    public Page<Transaction> findAll(Pageable pageable, SearchParam searchParam) {
        return repository.findAll(prepareQuery(searchParam), pageable);
    }

    public ReportResponse prepareReport(SearchParam param) {
        List<Transaction> transactions = repository.findAll(prepareReportQuery(param));
        Map<String, TransactionReportResponse> currencyMap = new HashMap<>();

        transactions.forEach(t -> {
            TransactionReportResponse trr = currencyMap.get(t.getCurrency().toString());
            if (trr == null) {
                trr = new TransactionReportResponse();
            }
            trr.setCurrency(t.getCurrency().toString());
            trr.setTotal(trr.getTotal() + t.getAmount());
            trr.setCount(trr.getCount() + 1);
            currencyMap.put(t.getCurrency().toString(), trr);
        });

        ReportResponse rr = new ReportResponse();
        currencyMap.forEach((key, value) -> rr.getResponse().add(value));
        rr.setStatus(ReportStatus.APPROVED);
        return rr;
    }

    private Specification<Transaction> prepareQuery(SearchParam param) {

        Specification<Transaction> query = prepareReportQuery(param);

        if (param.getTransactionStatus() != null) {
            query = query.and(TransactionSpecification.status(param.getTransactionStatus()));
        }

        if (param.getOperation() != null) {
            query = query.and(TransactionSpecification.operation(param.getOperation()));
        }

        if (param.getPaymentMethod() != null) {
            query = query.and(TransactionSpecification.paymentMethod(param.getPaymentMethod()));
        }

        if (param.getErrorCode() != null) {
            query = query.and(TransactionSpecification.errorCode(param.getErrorCode()));
        }

        if (param.getFilterField() != null && param.getFilterValue() != null) {
            query = query.and(TransactionSpecification.filter(param.getFilterField(), param.getFilterValue()));
        }

        if (param.getEmail() != null) {
            long id = 0;
            CustomerInfo customerInfo = customerInfoControl.findByEmail(param.getEmail());
            if (customerInfo != null) {
                id = customerInfo.getId();
                query = query.and(TransactionSpecification.customer(id));
            }
        }

        return query;
    }

    public static Specification<Transaction> prepareReportQuery(SearchParam param) {

        Specification<Transaction> query = Specification.where(null);

        if (param.getFromDate() != null && param.getToDate() != null) {
            query = query.and(TransactionSpecification.fromTo(param.getFromDate(), param.getToDate()));
        }

        if (param.getMerchantId() != null) {
            query = query.and(TransactionSpecification.merchant(param.getMerchantId()));
        }

        if (param.getAcquirerId() != null) {
            query = query.and(TransactionSpecification.acquirer(param.getAcquirerId()));
        }

        return query;
    }
}
