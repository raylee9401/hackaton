package com.volvocars.shuffl.boot;

import com.volvocars.shuffl.Controller.DataController;
import com.volvocars.shuffl.db.CassandraConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ShufflApplication.class, CassandraConfiguration.class, DataController.class })
public class ShufflApplication {

	public static void main(String[] args){
		SpringApplication.run(ShufflApplication.class, args);
	}

}
