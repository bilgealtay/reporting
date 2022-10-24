package com.ravensoftware.reporting.customer.acquirer;

import com.ravensoftware.reporting.model.entity.Acquirer;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by bilga
 */
@Component
public class AcquirerControl {

    private AcquirerRepository repository;

    public AcquirerControl(AcquirerRepository repository) {
        this.repository = repository;
    }

    public Acquirer save(Acquirer acquirer) {
        return repository.save(acquirer);
    }

    public Acquirer findById(Long id){
        Optional<Acquirer> byId = repository.findById(id);
        if (!byId.isPresent()){
            return null;
        }
        return byId.get();
    }
}
