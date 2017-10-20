package rw.techbuzz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class GenericDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Column(name = "VERSION")
	@Version
	private Long version = 1L;

	@Column(name = "STATE", nullable = false)
	private boolean state;

	
	public Long getVersion() {
		return version;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}


}
