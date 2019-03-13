package com.wsw.bean;

/**
 * @author wsw
 *
 */
public class URL {

	private String host;
	private Integer prot;
	
	public URL(String host, Integer prot) {
		super();
		this.host = host;
		this.prot = prot;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getProt() {
		return prot;
	}
	public void setProt(Integer prot) {
		this.prot = prot;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((prot == null) ? 0 : prot.hashCode());
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
		URL other = (URL) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (prot == null) {
			if (other.prot != null)
				return false;
		} else if (!prot.equals(other.prot))
			return false;
		return true;
	}
	
}
