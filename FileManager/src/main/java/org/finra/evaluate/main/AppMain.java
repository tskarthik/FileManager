package org.finra.evaluate.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="org.finra")
public class AppMain {

	public static void main(String[] args) {
		 SpringApplication.run(AppMain.class, args);
	}

}
