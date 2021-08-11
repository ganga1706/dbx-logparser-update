package com.verifone.logparsing.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Read {
	@Autowired
	VlogsRepo vlogsRepo;

	public void dimeboxValidationTimeTakenFileWrite() throws IOException {
		
		 Scanner myObj = new Scanner(System.in);
		 System.out.println("please provide log file location:");
		 String filepath = myObj.nextLine(); 
		File files = new File(filepath);
		File[] filearray = files.listFiles();
		System.out.println("Number of Log files:" + filearray.length);
		for (File file : filearray) {
			if (file.isFile()) {
				try (BufferedReader fileContent = new BufferedReader(new FileReader(file))) {
					String line;
					while ((line = fileContent.readLine()) != null) {
						if (line.contains("Get transaction type from PDSP in UPDATE:")) {
							getTxnTypeTimeTaken(line);
						}
						if (line.contains("Validation UPDATE TIME TAKEN:")) {
							getValidationUpdateTimeTaken(line);
						}
						if (line.contains("Dimebox call UPDATE TIME TAKEN:")) {
							getDimeboxCallUpdateTIMETAKEN(line);
						}
					}

				}

			}

		}

		System.out.println("#################### end DB insertion ########################");

	}

	public String getTxnTypeTimeTaken(String txnType) {
		String strTxnType = null;
		if (txnType.contains("Get transaction type from PDSP in UPDATE:")) {
			String traceId = getTraceId(txnType);
			Vlogs findByTraceId = findByTraceId(traceId);
			String substring = txnType.substring(txnType.lastIndexOf("Get transaction type from PDSP in UPDATE:"));
			String[] split = substring.split(":");
			String[] string = split[1].split(",");
			strTxnType = string[0];
			if (findByTraceId == null) {
				System.out.println("SAVE-> Get transaction type from PDSP in UPDATE: " +traceId  + strTxnType);
				saveToDB(traceId, strTxnType, null, null);
			} else if (findByTraceId.getGetTransactionTypeFromPdspInUpdate() == null
					|| findByTraceId.getGetTransactionTypeFromPdspInUpdate().isEmpty()) {
				System.out.println("UPDATE-> Get transaction type from PDSP in UPDATE: " +traceId  + strTxnType);
				vlogsRepo.updateGetTransactionTypeFromPdspInUpdateByGatewayTraceId(strTxnType, traceId);
			}

			return strTxnType;

		}
		return strTxnType;
	}

	public String getValidationUpdateTimeTaken(String vutt) {
		String strVutt = null;
		if (vutt.contains("Validation UPDATE TIME TAKEN:")) {
			String traceId = getTraceId(vutt);
			Vlogs findByTraceId = findByTraceId(traceId);
			String substring = vutt.substring(vutt.lastIndexOf("Validation UPDATE TIME TAKEN:"));
			String[] split = substring.split(":");
			  String[] string = split[1].split(",");
			  strVutt = string[0];
			if (findByTraceId == null) {
				System.out.println("SAVE-> Validation UPDATE TIME TAKEN: " +traceId  + strVutt);
				saveToDB(traceId, null, strVutt, null);
			} else if (findByTraceId.getValidationUpdateTimeTaken() == null
					|| findByTraceId.getValidationUpdateTimeTaken().isEmpty()) {
				System.out.println("UPDATE-> Validation UPDATE TIME TAKEN: " +traceId  + strVutt);
				vlogsRepo.updateValidationTimeTakenByGatewayTraceId(strVutt, traceId);
			}

			return strVutt;
		}
		return strVutt;
	}

	public String getDimeboxCallUpdateTIMETAKEN(String dcutt) {
		String strDcutt = null;
		if (dcutt.contains("Dimebox call UPDATE TIME TAKEN:")) {
			String traceId = getTraceId(dcutt);
			Vlogs findByTraceId = findByTraceId(traceId);
			String substring = dcutt.substring(dcutt.lastIndexOf("Dimebox call UPDATE TIME TAKEN:"));
			String[] split = substring.split(":");
			String[] string = split[1].split(",");
			strDcutt =  string[0];
			if (findByTraceId == null) {
				System.out.println("SAVE-> Dimebox call UPDATE TIME TAKEN: " +traceId  + strDcutt);
				saveToDB(traceId, null, null, strDcutt);
			} else if (findByTraceId.getDimeboxCallUpdateTimeTaken() == null
					|| findByTraceId.getDimeboxCallUpdateTimeTaken().isEmpty()) {
				System.out.println("UPDATE-> Dimebox call UPDATE TIME TAKEN: " +traceId  + strDcutt);
				vlogsRepo.updateDimeboxCallUpdateTimeTakenByGatewayTraceId(strDcutt, traceId);
			}
			return strDcutt;
		}
		return strDcutt;
	}

	public String getTraceId(String str) {
		String[] split = str.split(" ");
		//System.out.println(split[1]);
		StringBuffer sb = new StringBuffer(split[7]);
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public void saveToDB(String traceId, String ttfpiu, String vutt, String dcutt) {
		Vlogs findByGatewayTraceId = vlogsRepo.findByGatewayTraceId(traceId);
		if (findByGatewayTraceId == null) {
			Vlogs vlogs = new Vlogs();
			vlogs.setGatewayTraceId(traceId);
			vlogs.setGetTransactionTypeFromPdspInUpdate(ttfpiu);
			vlogs.setValidationUpdateTimeTaken(vutt);
			vlogs.setDimeboxCallUpdateTimeTaken(dcutt);
			try {
				vlogsRepo.save(vlogs);
			} catch (Exception e) {
				System.err.println("duplicate traceId");
			}
		}
	}

	public Vlogs findByTraceId(String traceId) {
		Vlogs findByGatewayTraceId = null;
		try {
			findByGatewayTraceId = vlogsRepo.findByGatewayTraceId(traceId);
		} catch (Exception e) {
			return findByGatewayTraceId;
		}
		return findByGatewayTraceId;
	}

}