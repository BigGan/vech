package com.ghj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppRun  implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("AppRun.run()");
	}

}
