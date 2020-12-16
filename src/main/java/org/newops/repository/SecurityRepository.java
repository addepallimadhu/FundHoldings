package org.newops.repository;

import org.newops.model.Fund;
import org.newops.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SecurityRepository extends JpaRepository<Security, Void>, JpaSpecificationExecutor<Security> {

}