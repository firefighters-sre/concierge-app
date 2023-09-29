package com.redhat.quarkus.service;

import com.redhat.quarkus.model.RegistroAcesso;
import com.redhat.quarkus.repository.RegistroAcessoRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AccessService {

    @Inject
    RegistroAcessoRepository repository;

    public void addEntry(RegistroAcesso registroAcesso) {
        repository.persist(registroAcesso);
    }

    public void addExit(RegistroAcesso registroAcesso) {
        // Assuming the entry has already been created and now we're updating the exit time
        RegistroAcesso existingEntry = repository.findById(registroAcesso.registroId);
        if (existingEntry != null) {
            existingEntry.horaSaída = registroAcesso.horaSaída;
            repository.persist(existingEntry);
        }
    }
}
