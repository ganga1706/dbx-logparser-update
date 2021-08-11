package com.verifone.logparsing.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VlogsRepo extends JpaRepository<Vlogs, Integer> {

	Vlogs findByGatewayTraceId(String gatewayTraceId);

	@Modifying
	@Transactional
	@Query("update Vlogs v set v.getTransactionTypeFromPdspInUpdate = ?1  where v.gatewayTraceId = ?2")
	void updateGetTransactionTypeFromPdspInUpdateByGatewayTraceId(String getTransactionTypeFromPdspInUpdate, String gatewayTraceId);

	@Modifying
	@Transactional
	@Query("update Vlogs v set v.validationUpdateTimeTaken = ?1  where v.gatewayTraceId = ?2")
	void updateValidationTimeTakenByGatewayTraceId(String validationUpdateTimeTaken, String gatewayTraceId);

	@Modifying
	@Transactional
	@Query("update Vlogs v set v.dimeboxCallUpdateTimeTaken = ?1  where v.gatewayTraceId = ?2")
	void updateDimeboxCallUpdateTimeTakenByGatewayTraceId(String dimeboxCallUpdateTimeTaken, String gatewayTraceId);
}
