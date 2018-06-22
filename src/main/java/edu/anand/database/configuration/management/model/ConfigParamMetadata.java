package edu.anand.database.configuration.management.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

@Entity
@Table(name="CONFIG_PARAM_METADATA")
@Inheritance
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorOptions(force = true)
public class ConfigParamMetadata implements Serializable{

	@Id
	@GeneratedValue
	protected Long id;
	protected Long createdOn = System.currentTimeMillis();
	protected Long updatedOn = System.currentTimeMillis();
	protected String name;
	protected String defaultValue;
	protected DATATYPE datatype;
	
	
	public enum DATATYPE {
		STRING,INTEGER,BOOLEAN
	}

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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDefaultValue() {
		return defaultValue;
	}


	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}


	public DATATYPE getDatatype() {
		return datatype;
	}


	public void setDatatype(DATATYPE datatype) {
		this.datatype = datatype;
	}
	
	

}
