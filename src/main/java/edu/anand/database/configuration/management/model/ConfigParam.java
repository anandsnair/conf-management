package edu.anand.database.configuration.management.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Entity
@Table(name = "CONFIG_PARAM")
public class ConfigParam {
	@Id
	@GeneratedValue
	protected Long id;
	protected Long createdOn = System.currentTimeMillis();
	protected Long updatedOn = System.currentTimeMillis();
	protected String userValue;
	@ManyToOne
	protected ConfigParamMetadata configParamMetadata;

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "CONFIG_ID")
	protected BaseConfiguration baseConfig;

	public ConfigParam() {
	};

	public ConfigParam(ConfigParamMetadata configParamMetadata) {
		this.configParamMetadata = configParamMetadata;

	}

	public ConfigParam(ConfigParamMetadata configParamMetadata, String userValue) {
		this.configParamMetadata = configParamMetadata;
		this.userValue = userValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BaseConfiguration getBaseConfig() {
		return baseConfig;
	}

	public void setBaseConfig(BaseConfiguration baseConfig) {
		this.baseConfig = baseConfig;
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

	public String getUserValue() {
		return userValue;
	}

	public void setUserValue(String userValue) {
		this.userValue = userValue;
	}

	public ConfigParamMetadata getConfigParamMetadata() {
		return configParamMetadata;
	}

	public void setConfigParamMetadata(ConfigParamMetadata configParamMetadata) {
		this.configParamMetadata = configParamMetadata;
	}

}
