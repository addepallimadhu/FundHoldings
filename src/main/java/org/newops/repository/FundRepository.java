package org.newops.repository;

import org.newops.model.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FundRepository extends JpaRepository<Fund, Void>, JpaSpecificationExecutor<Fund> {

}