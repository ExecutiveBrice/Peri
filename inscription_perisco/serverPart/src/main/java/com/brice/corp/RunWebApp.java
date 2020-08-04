package com.brice.corp;

import com.brice.corp.configuration.JpaConfiguration;
import com.brice.corp.service.OltSlotPortPonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.bouyguestelecom.oss"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class RunWebApp {

	@Autowired
	private OltSlotPortPonService oltSlotPortPonService;

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(RunWebApp.class);
		springApplication.addListeners(new ApplicationPidFileWriter("/var/opt/oss/data/config/dashboard_ftth_sb2.pid"));
		springApplication.run(args);
	}

	/**
	 * Permet de lancer une fonction au démarrage de l'application
	 * ici, on charge le cache des olts
	 */
	@PostConstruct
	private void init() {
		oltSlotPortPonService.fillCache();
	}
}