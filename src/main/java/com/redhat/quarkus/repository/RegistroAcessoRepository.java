
package com.redhat.quarkus.repository;

import com.redhat.quarkus.model.RegistroAcesso;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistroAcessoRepository implements PanacheRepository<RegistroAcesso> {
}
