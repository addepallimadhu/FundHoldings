package org.newops.repository;

import org.newops.model.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HoldingRepository extends JpaRepository<Holding, Void>, JpaSpecificationExecutor<Holding> {

}