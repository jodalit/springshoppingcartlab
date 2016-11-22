package com.formationinterne.projets.springmvc.datamodels;

import java.util.Objects;

public class Client extends Person {
	private Long clientId;
	private String clientType; //{wholesaler, retailer}
	private boolean isLocalClient; //{client of our region, other client of other region}
	
	public Client() {
		super();
	}

	public Client(Long clientId, String clientType, boolean isLocalClient) {
		super();
		this.clientId = clientId;
		this.clientType = clientType;
		this.isLocalClient = isLocalClient;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public boolean isLocalClient() {
		return isLocalClient;
	}

	public void setLocalClient(boolean isLocalClient) {
		this.isLocalClient = isLocalClient;
	}

	@Override
	public String toString() {
		return "Client [\n" + super.toString() + ",\n clientId=" + clientId + ", clientType=" + clientType + ", isLocalClient=" + isLocalClient
				+ "\n]";
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result + Objects.hash(clientId, clientType, isLocalClient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (clientType == null) {
			if (other.clientType != null)
				return false;
		} else if (!clientType.equals(other.clientType))
			return false;
		if (isLocalClient != other.isLocalClient)
			return false;
		return true;
	}
	
	
}
