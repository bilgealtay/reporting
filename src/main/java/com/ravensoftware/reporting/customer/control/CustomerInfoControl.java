package com.ravensoftware.reporting.customer.control;

import com.ravensoftware.reporting.model.entity.CustomerInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by bilga on 21-02-2020
 */
@Component
public class CustomerInfoControl {

    private CustomerInfoRepository repository;

    public CustomerInfoControl(CustomerInfoRepository repository) {
        this.repository = repository;
    }

    public CustomerInfo save(CustomerInfo customerInfo) {
        return repository.save(customerInfo);
    }

    public CustomerInfo findById(Long id){
        Optional<CustomerInfo> byId = repository.findById(id);
        if (!byId.isPresent()){
            return null;
        }
        return byId.get();
    }

    public CustomerInfo findByEmail(String email){
        return repository.findByEmail(email);
    }
}
