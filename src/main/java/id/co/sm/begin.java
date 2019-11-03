package id.co.sm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import id.co.sm.controller.GeneratorFileSupplierController;

public class begin {
	public static void main(String[] args) {
		ApplicationContext con = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
		
		GeneratorFileSupplierController cust = (GeneratorFileSupplierController)con.getBean("generatorFileSupplierController");
    	System.out.println(cust.toString());
    	
	}
}
