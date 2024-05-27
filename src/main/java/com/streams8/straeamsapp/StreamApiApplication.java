package com.streams8.straeamsapp;

import com.streams8.dtos.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiApplication {


    public static void main (String[] args){

        Employee[] employeeData={
                new Employee(1, "Jeff Bezos", 100000,"Accounts"),
                new Employee(2, "Bill Gates", 200000,"HR"),
                new Employee(3, "Mark Zuckerberg", 300000,"IT")
        };

        Employee[] employeeData1={
                new Employee(1, "Jeff Bezos", 100000,"Accounts"),
                new Employee(2, "Bill Gates", 200000,"HR"),
                new Employee(3, "Mark Zuckerberg", 300000,"IT")
        };

        // Convert to Stream from Array
        System.out.println(Stream.of(employeeData));
        Stream.of(employeeData,employeeData1);

        // Arrays To List and then to Stream
        List<Employee> employeesList = Arrays.asList(employeeData);

        // Sort Employees By Name Using Collections.sort()
        Collections.sort(employeesList,(e1,e2)-> e1.getName().compareTo(e2.getName()));
        System.out.println("After Applying Comparator " + employeesList);
        employeesList.stream();

        // GroupBy
        Map<String,List<Employee>> collect = employeesList.stream().filter(e -> e.getSalary() >= 200000)
                .collect(Collectors.groupingBy(Employee::getName));
        System.out.println("MAP :: " + collect);
        Optional<Employee> billGates = employeesList.stream().filter(e -> e.getName().equals("Bill Gates")).findAny();
        System.out.println(billGates.get());


        employeesList.stream().forEach(e -> e.setSalary(e.getSalary()+10));
        System.out.println(employeesList);

        // Short Circuiting Operantions

        Stream intStream = Stream.iterate(2,i-> i*2);
        System.out.println(intStream.skip(3).limit(5).collect(Collectors.toList()));


    }
}
