package com.ravensoftware.reporting.contact.control;

import com.ravensoftware.reporting.contact.entity.Acquirer;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by bilga on 21-02-2020
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
