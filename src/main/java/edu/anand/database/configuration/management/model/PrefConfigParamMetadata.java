package edu.anand.database.configuration.management.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Pref")
public class PrefConfigParamMetadata extends ConfigParamMetadata {

}
