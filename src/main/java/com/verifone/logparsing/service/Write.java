package com.verifone.logparsing.service;

import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

@Service
public class Write {

	@Autowired
	VlogsRepo vlogsRepo;

	public void csvwriter() {
		 Scanner myObj = new Scanner(System.in);
		 System.out.println("please provide CSV file location:");
		 String filepath = myObj.nextLine(); 

		try (CSVWriter dimeboxcsvWrite = new CSVWriter(new FileWriter(filepath));) {

			String dimeboxheader[] = { "Gateway_Trace_Id", "Get transaction type from PDSP in UPDATE", "Validation UPDATE TIME TAKEN",
					"Dimebox call UPDATE TIME TAKEN" };
			dimeboxcsvWrite.writeNext(dimeboxheader);
			List<Vlogs> findAllVlogs = vlogsRepo.findAll();
			String[] csvData = new String[4];
			for (Vlogs vlogs : findAllVlogs) {
				csvData[0] = vlogs.getGatewayTraceId();
				csvData[1] = vlogs.getGetTransactionTypeFromPdspInUpdate();
				csvData[2] = vlogs.getValidationUpdateTimeTaken();
				csvData[3] = vlogs.getDimeboxCallUpdateTimeTaken();
				dimeboxcsvWrite.writeNext(csvData);
			}

			System.out.println("#################### close CSV write ################");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
