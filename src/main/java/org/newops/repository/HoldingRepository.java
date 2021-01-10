package org.newops.repository;

import org.newops.model.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface HoldingRepository extends JpaRepository<Holding, Void>, JpaSpecificationExecutor<Holding> {
        List<Holding> findByFundcodeAndAsofdate(String fundcode, Date asofdate);
}