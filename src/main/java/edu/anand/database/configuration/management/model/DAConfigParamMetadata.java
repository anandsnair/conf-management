package edu.anand.database.configuration.management.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;

@Entity
@DiscriminatorValue("DA")
public class DAConfigParamMetadata extends ConfigParamMetadata {

}
