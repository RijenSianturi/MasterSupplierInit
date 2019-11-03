package id.co.sm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;

@Controller
public class GeneratorFileSupplierController {
	File file;
	String res;
	Logger logger = Logger.getLogger("MyLog");  
    FileHandler fh;
	
	private final String configFile ="/users/rijensianturi/Desktop/initsupplier/Main";
	private  String log ="/users/rijensianturi/Desktop/initsupplier/Log/";

	@Override
	public String toString() {
		
		String checkExcel = "Log Excel Validate "+ new Date()+".txt";
		final File logText = new File(log, checkExcel);
		log = log+checkExcel;
		FileWriter writer2;
		try {
			logText.createNewFile();
			writer2 = new FileWriter(logText);
			writer2.write("==== Check Folder File ==== ");
			writer2.write(System.getProperty("line.separator"));
			//writer2.write(conf+"/"+item.getName()+" is Exist");
			writer2.write(System.getProperty("line.separator"));
			writer2.close();
			crawlFolder(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crawlFolder(log+"/"+"Checking file finished ");
		return "Checking file finished " ;
	}
	
	public UUID defineUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid;
	}
	
	public void fileRename() {	
		Path source = Paths.get(configFile);	
		System.out.println(source.getRoot().toString());
		try {
			Files.move(source, source.resolveSibling(""+ defineUuid()));
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
	}
	public void crawlFolder(String conf) {
		//conf txt log
		BufferedWriter output = null;
		File dir = new File(conf);
		File[] contentOfDir = dir.listFiles();
		if (contentOfDir.length >0) {
		for(File item : contentOfDir) {
			
			//dsstore in mac will be delete
			if (!".DS_Store".equals(item.getName())) {
			
			if (item.isFile()) {
				if (existValidate(conf+"/"+item.getName())) {
					System.out.println(conf+"/"+item.getName() +" is Exist");
					//FileWriter writer = new FileWriter(logText);
					
					
					appendStrToFile(log, conf+"/"+item.getName()+" is Exist");
				}
			
				else {
					System.out.println("salah");
				}
			}
			else {
				File dir2 = new File(conf+"/"+item.getName());				
				System.out.format("Dir Name: %s%n ", item.getName());
				//if directory is photo then check is exist or not
				//if ("PHOTO".equals(item.getName())) {
					
				//}
				crawlFolder(conf+"/"+item.getName());
			}
			
		}
		}
		}else {
			 System.out.println(conf +" not exist / wrong format please enter a valid jpeg file");
			 appendStrToFile(log, conf +" not exist / wrong format please enter a valid jpeg file");
			 System.exit(0); 
		}
       
		
		
	}
	
	//1. check validation exist or not
	public boolean existValidate(String location) {
		 File file = new File(location);
		 boolean exists = file.exists();

		 try {
			if(exists && isJpeg(location)) {
				 //System.out.println(location +" exist");
				 return true;	 
			 }
			 else {
				 System.out.println(location +" not exist / wrong format please enter a valid jpeg file");
				 return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;  
        
	}
	
	//check if it valid jpeg?
	private static Boolean isJpeg(String filename) throws Exception {
	    DataInputStream ins = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
	    try {
	        if (ins.readInt() == 0xffd8ffe0) {
	            return true;
	        } else {
	            return false;

	        }
	    } finally {
	        ins.close();
	    }
	}
	
	public static void appendStrToFile(String fileName, String str) 
	{ 
		try { 
		
		// Open given file in append mode. 
			BufferedWriter out = new BufferedWriter( 
			new FileWriter(fileName, true)); 
			out.write(str);
			out.write(System.getProperty("line.separator"));
			out.close(); 
		} 
		catch (IOException e) { 
			System.out.println("exception occoured" + e); 
		} 
	} 
	
}
