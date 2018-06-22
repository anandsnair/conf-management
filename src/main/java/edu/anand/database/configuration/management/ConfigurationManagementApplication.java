package edu.anand.database.configuration.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import edu.anand.database.configuration.management.model.ConfigParam;
import edu.anand.database.configuration.management.model.ConfigParamMetadata;
import edu.anand.database.configuration.management.model.DAConfigParamMetadata;
import edu.anand.database.configuration.management.model.DAConfiguration;
import edu.anand.database.configuration.management.model.PrefConfigParamMetadata;
import edu.anand.database.configuration.management.model.Project;
import edu.anand.database.configuration.management.repository.DAConfigParamMetadataRepo;
import edu.anand.database.configuration.management.repository.DAConfigurationRepo;
import edu.anand.database.configuration.management.repository.PrefConfigParamMetadataRepo;

@SpringBootApplication
@ComponentScan
@EnableJpaAuditing
public class ConfigurationManagementApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ConfigurationManagementApplication.class, args);
		createConfigMetadata(ctx);

		DAConfiguration daConfig = createDAConfiguration(ctx);
		DAConfigurationRepo bConRepo = ctx.getBean(DAConfigurationRepo.class);
		Optional<DAConfiguration> retrivedDaConfig = bConRepo.findById(daConfig.getId());
		printConfiguration(retrivedDaConfig.get());
		System.out.println("UpdatingValues");
		Map<Long, String> mapOfMetadataIdToValue = new HashMap<>();
		for (ConfigParam param : daConfig.getConfigParams()) {
			mapOfMetadataIdToValue.put(param.getId(), "Updated " + param.getConfigParamMetadata().getDefaultValue());
		}
		System.out.println("UpdatingValues on Config");

		updateDAConfiguration(ctx, daConfig.getId(), mapOfMetadataIdToValue);
		retrivedDaConfig = bConRepo.findById(daConfig.getId());

		printConfiguration(retrivedDaConfig.get());

		daConfig = createDAConfiguration(ctx);
		retrivedDaConfig = bConRepo.findById(daConfig.getId());
		printConfiguration(retrivedDaConfig.get());
		System.out.println("Printing all DA Configurations");
		printAllConfiguration(bConRepo.findAll());
		
	}

	private static void printConfiguration(DAConfiguration bConf1) {
		System.out.println(bConf1.getId());
		System.out.println("-----Paramaters -----");
		for (ConfigParam param : bConf1.getConfigParams()) {
			System.out.println(param.getConfigParamMetadata().getName() + " -> " + param.getConfigParamMetadata().getDefaultValue() + " -> "
					+ param.getUserValue());
		}

	}
	
	private static void printAllConfiguration(List<DAConfiguration> daList) {
		for(DAConfiguration daConf : daList) {
			System.out.println("ID=>"+daConf.getId());
			System.out.println("PROJECT_ID=>"+daConf.getProject().getId());
			System.out.println("-----Paramaters -----");
			for (ConfigParam param : daConf.getConfigParams()) {
				System.out.println(param.getConfigParamMetadata().getName() + " -> " + param.getConfigParamMetadata().getDefaultValue() + " -> "
						+ param.getUserValue());
			}

		}

	}


	private static void updateDAConfiguration(ConfigurableApplicationContext ctx, Long id, Map<Long, String> mapOfMetadataIdToValue) {
		DAConfigurationRepo bConRepo = ctx.getBean(DAConfigurationRepo.class);
		Optional<DAConfiguration> retrivedDaConfig = bConRepo.findById(id);

		for (ConfigParam param : retrivedDaConfig.get().getConfigParams()) {
			if (mapOfMetadataIdToValue.containsKey(param.getId())) {
				param.setUserValue(mapOfMetadataIdToValue.get(param.getId()));
			}
		}
		bConRepo.save(retrivedDaConfig.get());

	}

	public static void createConfigMetadata(ConfigurableApplicationContext ctx) {
		DAConfigParamMetadata daMetadata = new DAConfigParamMetadata();
		daMetadata.setName("Is Saleforce Supported");
		daMetadata.setDefaultValue("true");
		daMetadata.setDatatype(ConfigParamMetadata.DATATYPE.STRING);
		DAConfigParamMetadataRepo daRepo = ctx.getBean(DAConfigParamMetadataRepo.class);
		daRepo.save(daMetadata);

		daMetadata = new DAConfigParamMetadata();
		daMetadata.setName("Is Netsuite Supported");
		daMetadata.setDefaultValue("false");
		daMetadata.setDatatype(ConfigParamMetadata.DATATYPE.STRING);
		daRepo.save(daMetadata);

		PrefConfigParamMetadata prefMetadata = new PrefConfigParamMetadata();
		prefMetadata.setName("User Pref");
		prefMetadata.setDefaultValue("false");
		prefMetadata.setDatatype(ConfigParamMetadata.DATATYPE.STRING);
		PrefConfigParamMetadataRepo predRepo = ctx.getBean(PrefConfigParamMetadataRepo.class);
		predRepo.save(prefMetadata);
		System.out.println("Created Successfully");

	}

	public static DAConfiguration createDAConfiguration(ConfigurableApplicationContext ctx) {
		DAConfiguration daConfig = new DAConfiguration();
		DAConfigParamMetadataRepo daRepo = ctx.getBean(DAConfigParamMetadataRepo.class);

		List<DAConfigParamMetadata> configMetadata = daRepo.findAll();
		List<ConfigParam> configParams = new ArrayList<>();

		for (DAConfigParamMetadata paramMetadata : configMetadata) {
			System.out.println(paramMetadata.getName() + " -> " + paramMetadata.getDefaultValue());
			ConfigParam param = new ConfigParam(paramMetadata);
			configParams.add(param);
			param.setBaseConfig(daConfig);
		}

		DAConfigurationRepo bConRepo = ctx.getBean(DAConfigurationRepo.class);
		daConfig.setConfigParams(configParams);
		Project p = new Project("test");
		daConfig.setProject(p);

		bConRepo.save(daConfig);
		return daConfig;
	}
}
