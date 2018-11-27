package com.DDS.DDS;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.DDS.Processors.ReleaseNoteProcessor;

@SpringBootApplication
public class DdsApplication implements CommandLineRunner{

	final static String releasenoteprocessor = "RELEASENOTEPROCESSOR";
	
	public static void main(String[] args) {
		SpringApplication.run(DdsApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		
		String cmd = System.getProperty("processorselect");
		
		if(cmd.contains(releasenoteprocessor)) {
			ReleaseNoteProcessor RNP = new ReleaseNoteProcessor();
			RNP.initialize();
			RNP.process();
		}
		else {
			System.out.println("None Selected");
		}

	}
}
