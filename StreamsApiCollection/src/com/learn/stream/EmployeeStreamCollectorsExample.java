package com.learn.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeStreamCollectorsExample {

	public static void main(String[] args) {
		List<Employee> empList=new ArrayList<>();
		Employee e1=new Employee(1,"Saikumar","Menda","DEV",60000.00,27);
		Employee e2=new Employee(2,"Kirankumar","Muvalla","Tester",20000.00,23);
		Employee e3=new Employee(3,"Praveenkumar","Tumapala","HR",75000.00,30);
		Employee e4=new Employee(4,"DurgaPrasad","Nutulla","Product",30000.00,26);
		Employee e5=new Employee(5,"Teja","M","DEV",35000.00,25);
		Employee e6=new Employee(6,"Nagaraju","Pujjari","DEV",70000.00,29);
		
		empList.add(e1);
		empList.add(e2);
		empList.add(e3);
		empList.add(e4);
		empList.add(e5);
		empList.add(e6);
		
		//1. join all the first name of employees using collectors in stream.
		
		String employeeFirstNames=empList.stream().map(emp->emp.getFirstName()).collect(Collectors.joining(", "));
		System.out.println(employeeFirstNames);
		
		// 2.partition by employee having salary greater than 35k.
		// partitionBy Collector(Predicate)
	    Map<Boolean, List<Employee>> employeeGreaterThan35k=empList.stream()
	    		.collect(Collectors.partitioningBy(e->e.getSalary()>35000.00));
	    System.out.println("----employeeGreaterThan35k: "+ employeeGreaterThan35k);
	    
	    // 3.// partitionBy Collector(Predicate,collectors)
	    Map<Boolean, Long> employeeGreaterThan35kCount=empList.stream()
	    		.collect(Collectors.partitioningBy(e->e.getSalary()>35000.00,Collectors.counting()));
	    System.out.println("----employeeGreaterThan35kCount: "+ employeeGreaterThan35kCount);
	    
	    // 4.groupBy(key) 
	    
        Map<String,List<Employee>> employeeGroupByRole=empList.stream().collect(Collectors.groupingBy(e->e.getRole()));
        System.out.println("----employeeGroupByRole: "+ employeeGroupByRole);
        
        // 5.groupBy(key,collectors)
        Map<String,Long> employeeGroupByRoleCount=empList.stream()
        		                     .collect(Collectors.groupingBy(e->e.getRole(),Collectors.counting()));
        System.out.println("----employeeGroupByRoleCount: "+ employeeGroupByRoleCount); 
        
        //6.groupBy role and list of employee first names
        Map<String,List<String>> employeeRoleWithFirstNames=empList.stream()
        .collect(Collectors.groupingBy(e->e.getRole(),Collectors.mapping(e->e.getFirstName(),Collectors.toList())));
        
        System.out.println("----employeeRoleWithFirstNames :"+employeeRoleWithFirstNames);
        
        // 7. groupBy role and give sum by role
        Map<String,Optional<Double>> employeeSalarySumByRole=empList.stream()
        						.collect(Collectors.groupingBy(e->e.getRole(),
        	                        	Collectors.mapping(e->e.getSalary(), Collectors.reducing((a,b)->a+b))));
        
        System.out.println("----employeeSalarySumByRole : "+employeeSalarySumByRole);
        Map<String, Double> employeeSalarySumByRole1=empList.stream()
				.collect(Collectors.groupingBy(e->e.getRole(),Collectors.summingDouble(e->e.getSalary())));
        System.out.println("----employeeSalarySumByRole1 : "+employeeSalarySumByRole1);
        
        Map<String, DoubleSummaryStatistics> employeeSalarySummarizingByRole=empList.stream()
				.collect(Collectors.groupingBy(e->e.getRole(),Collectors.summarizingDouble(e->e.getSalary())));
        System.out.println("----employeeSalarySumByRole1 : "+employeeSalarySummarizingByRole );
        
        DoubleSummaryStatistics employeeSalaryDevSummary=empList.stream().collect(Collectors.filtering(e->e.getRole().equals("DEV"),
        		Collectors.summarizingDouble(e->e.getSalary())));
        
        System.out.println("----employeeSalaryDev: "+employeeSalaryDevSummary);
        
        Optional<Employee> employeeWithHighestSalaries=empList.stream().collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
        
        System.out.println("----employeeWithHighestSalaries:"+employeeWithHighestSalaries);
		
        List<Employee> sortedEmployeesSalary=empList.stream()
        		.sorted((o1,o2)->Double.compare(o1.getSalary(),o2.getSalary()) ).collect(Collectors.toList());
        System.out.println("----sortedEmployeesSalary:"+sortedEmployeesSalary);
        
        List<Double> sortedEmployeesSalary1=empList.stream().map(e->e.getSalary())
        		.sorted(Double::compare).collect(Collectors.toList());
        System.out.println("----sortedEmployeesSalary1:"+sortedEmployeesSalary1);
        
        
        
        
	}

}
