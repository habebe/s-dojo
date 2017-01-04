package com.abebe.sdojo.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abebe.sdojo.core.services.MatchService;

public class MatchImportRunner 
{
	private ApplicationContext context = null;
	
	public static void main(String[] args) throws IOException
	{
		MatchImportRunner runner = new MatchImportRunner();
		runner.context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/config.xml"});
		MatchService service = runner.context.getBean(MatchService.class);
		List<String> filenames = new ArrayList<String>();
		filenames.add("/Users/henocka/SOCS_project/socs/data/CollinCounty.Nov.6.csv.json");
		filenames.add("/Users/henocka/SOCS_project/socs/data/CollinCounty.Nov.27.csv.json");
		filenames.add("/Users/henocka/SOCS_project/socs/data/CollinCounty.Nov.6.csv.json");
		filenames.add("/Users/henocka/SOCS_project/socs/data/CollinCounty.Nov.27.csv.json");
		filenames.add("/Users/henocka/SOCS_project/socs/data/CollinCounty.Nov.6.csv.json");
		filenames.add("/Users/henocka/SOCS_project/socs/data/CollinCounty.Nov.27.csv.json");
		filenames.add("/Users/henocka/SOCS_project/socs/data/CollinCounty.Nov.6.csv.json");
		filenames.add("/Users/henocka/SOCS_project/socs/data/CollinCounty.Nov.27.csv.json");
		for(String filename:filenames)
		{
			byte[] data = Files.readAllBytes(Paths.get(filename));
			service.importUSPSAPractiscoreJSON(data);
		}
		
		String[] names = runner.context.getBeanDefinitionNames();
		for(String name:names)
		{
			System.out.println("Beans:" + name);
		}
	}

}
