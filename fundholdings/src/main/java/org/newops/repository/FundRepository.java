package org.newops.repository;

import org.newops.model.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FundRepository extends JpaRepository<Fund, Void>, JpaSpecificationExecutor<Fund> {

        List<Fund> findBySchemeName(String SchemeName);

        Fund findByamfiCode(Integer AMFICode);


}