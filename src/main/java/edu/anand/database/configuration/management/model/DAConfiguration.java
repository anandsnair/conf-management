package edu.anand.database.configuration.management.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DA_CONGIFURATION")
public class DAConfiguration extends BaseConfiguration {

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	Project project;
	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
}
