package edu.anand.database.configuration.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.anand.database.configuration.management.model.PrefConfigParamMetadata;


@Repository
public interface PrefConfigParamMetadataRepo extends JpaRepository<PrefConfigParamMetadata, String> {

}
