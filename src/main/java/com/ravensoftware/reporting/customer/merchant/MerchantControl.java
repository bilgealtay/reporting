package com.ravensoftware.reporting.customer.merchant;

import com.ravensoftware.reporting.model.entity.Merchant;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by bilga
 */
@Component
public class MerchantControl {

    private MerchantRepository repository;

    public MerchantControl(MerchantRepository repository) {
        this.repository = repository;
    }

    public Merchant save(Merchant merchant) {
        return repository.save(merchant);
    }

    public Merchant findById(Long id){
        Optional<Merchant> byId = repository.findById(id);
        if (!byId.isPresent()){
            return null;
        }
        return byId.get();
    }
}
