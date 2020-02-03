package com.illumio.security;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvRulesReader {
	
	public static List<Rule> read(String filePath) throws NumberFormatException, IOException {
		List<Rule> rules = new ArrayList<Rule>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String ruleLine;
		while((ruleLine = reader.readLine()) != null) {
			String[] ruleArr = ruleLine.split(",");
			Direction direction = Direction.valueOf(ruleArr[0].toUpperCase());
			Protocol protocol = Protocol.valueOf(ruleArr[1].toUpperCase());
			int portStartRange, portEndRange;
			if (ruleArr[2].contains("-")) {
				portStartRange = Integer.parseInt(ruleArr[2].split("-")[0]);
				portEndRange = Integer.parseInt(ruleArr[2].split("-")[1]);
			} else {
				portStartRange = portEndRange = Integer.parseInt(ruleArr[2]);
			}
			String ipStartRange, ipEndRange;
			if (ruleArr[3].contains("-")) {
				ipStartRange = ruleArr[3].split("-")[0];
				ipEndRange = ruleArr[3].split("-")[1];
			} else {
				ipStartRange = ipEndRange = ruleArr[3];
			}
			rules.add(new Rule(direction, protocol, portStartRange, portEndRange, ipStartRange, ipEndRange));
		}
		reader.close();
		return rules;
	}

}
