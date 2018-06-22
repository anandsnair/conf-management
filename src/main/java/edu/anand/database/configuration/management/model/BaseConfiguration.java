package edu.anand.database.configuration.management.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="BASE_CONFIGURATION")
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseConfiguration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7311513214531408645L;
	@Id
	@GeneratedValue
	protected Long id;
	protected Long createdOn = System.currentTimeMillis();
	protected Long updatedOn = System.currentTimeMillis();
	

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="baseConfig")
	protected List<ConfigParam> configParams;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}
	public Long getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ConfigParam> getConfigParams() {
		return configParams;
	}
	public void setConfigParams(List<ConfigParam> configParams) {
		this.configParams = configParams;
	}

}
