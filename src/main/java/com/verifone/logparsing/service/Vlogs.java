package com.verifone.logparsing.service;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VERIFONE_LOGS_UPADTE", uniqueConstraints = { @UniqueConstraint(columnNames = { "GATEWAY_TRACE_ID" }) })
public class Vlogs implements Serializable {

	private static final long serialVersionUID = -944006380567234140L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	@JsonIgnore
	private int Id;

	@Column(name = "GATEWAY_TRACE_ID")
	private String gatewayTraceId;

	@Column(name = "TXN_TYPE")
	private String getTransactionTypeFromPdspInUpdate;

	@Column(name = "VALIDATION_UPDATE_TIME_TAKEN")
	private String validationUpdateTimeTaken;

	@Column(name = "DIMEBOX_CALL_UPDATE_TIME_TAKEN")
	private String dimeboxCallUpdateTimeTaken;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getGatewayTraceId() {
		return gatewayTraceId;
	}

	public void setGatewayTraceId(String gatewayTraceId) {
		this.gatewayTraceId = gatewayTraceId;
	}

	public String getGetTransactionTypeFromPdspInUpdate() {
		return getTransactionTypeFromPdspInUpdate;
	}

	public void setGetTransactionTypeFromPdspInUpdate(String getTransactionTypeFromPdspInUpdate) {
		this.getTransactionTypeFromPdspInUpdate = getTransactionTypeFromPdspInUpdate;
	}

	public String getValidationUpdateTimeTaken() {
		return validationUpdateTimeTaken;
	}

	public void setValidationUpdateTimeTaken(String validationUpdateTimeTaken) {
		this.validationUpdateTimeTaken = validationUpdateTimeTaken;
	}

	public String getDimeboxCallUpdateTimeTaken() {
		return dimeboxCallUpdateTimeTaken;
	}

	public void setDimeboxCallUpdateTimeTaken(String dimeboxCallUpdateTimeTaken) {
		this.dimeboxCallUpdateTimeTaken = dimeboxCallUpdateTimeTaken;
	}
	
	

	
}
