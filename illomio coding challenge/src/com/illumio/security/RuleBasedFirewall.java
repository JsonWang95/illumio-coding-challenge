package com.illumio.security;
import java.io.*;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RuleBasedFirewall implements Firewall {

	// Direction, Protocol -> Sorted list by portStartRange
	// portStartRange <= request_port
	private Map<IndexKey, List<Rule>> ruleIndex;

	public RuleBasedFirewall(String filePath) throws IOException {
		this.ruleIndex = new HashMap<IndexKey, List<Rule>>();
		List<Rule> rules = CsvRulesReader.read(filePath);
		
		for (Rule rule : rules) {
			IndexKey key = new IndexKey(rule.getDirection(), rule.getProtocol());
			ruleIndex.putIfAbsent(key, new ArrayList<Rule>());
			ruleIndex.get(key).add(rule);
		}
		
		for (Map.Entry<IndexKey, List<Rule>> keyValue : ruleIndex.entrySet()) {
			Collections.sort(keyValue.getValue(), (r1, r2) -> Integer.compare(r1.getPortStartRange(), r2.getPortStartRange()));
		}
	}
	
	public boolean acceptPacket(String directionString, String protocolString, int port, String ipAddress) {
		Direction direction = Direction.valueOf(directionString.toUpperCase());
		Protocol protocol = Protocol.valueOf(protocolString.toUpperCase());
		IpAddress ip = new IpAddress(ipAddress);
		
		IndexKey key = new IndexKey(direction, protocol);
		for (Rule rule : ruleIndex.get(key)) {
			if (rule.getPortStartRange() > port) break;
			if (rule.getPortEndRange() < port) continue;
			if (rule.getIpAddressStart().compareTo(ip) > 0 || rule.getIpAddressEnd().compareTo(ip) < 0) continue;
			return true;
		}
		return false;
	}
	
	
	private static class IndexKey {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((direction == null) ? 0 : direction.hashCode());
			result = prime * result + ((protocol == null) ? 0 : protocol.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IndexKey other = (IndexKey) obj;
			if (direction != other.direction)
				return false;
			if (protocol != other.protocol)
				return false;
			return true;
		}
		private Direction direction;
		public IndexKey(Direction direction, Protocol protocol) {
			this.direction = direction;
			this.protocol = protocol;
		}
		private Protocol protocol;
	}
}
	
