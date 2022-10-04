package com.accenture.bars.repository;

import com.accenture.bars.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {
    public Billing findByBillingCycleAndStartDateAndEndDate(
    		int billingCycle
    		, LocalDate startDate
    		, LocalDate endDate);


}
