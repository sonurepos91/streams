package com.streams8.straeamsapp;

import com.streams8.dtos.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiApplicationPart2 {

    public static void main (String[] args) {

        Employee[] employeeData = {new Employee(1, "Jeff Bezos", 100000, "Accounts"), new Employee(2, "Bill Gates", 200000, "Solutioning"), new Employee(3, "Mark Zuckerberg", 300000, "HR"), new Employee(4, "Tesla", 400000, "Accounts"), new Employee(4, "Tesla", 400000, "Accounts")};

        Employee[] employeeData1 = {new Employee(1, "Jeff Bezos", 100000, "Accounts"), new Employee(2, "Bill Gates", 200000, "Solutioning"), new Employee(3, "Mark Zuckerberg", 300000, "HR"), new Employee(4, "Tesla", 400000, "Accounts")};

        // Group Employee By Department
        Map<String, List<Employee>> groupByDepartment = Arrays.asList(employeeData).stream().collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("Group By Department :" + groupByDepartment);

        // Employee Having max Salary
        Optional<Employee> employeeMax = Arrays.asList(employeeData).stream().max(Comparator.comparingInt(Employee::getSalary));

        System.out.println("Employee Having max Salary " + employeeMax.get());

        // Employee Having Min Salary
        Optional<Employee> employeeMin = Arrays.asList(employeeData).stream().min((e1, e2) -> e1.getSalary() - e2.getSalary());
        System.out.println("Employee Having min Salary " + employeeMin.get());

        Optional<Employee> minSalary = Arrays.asList(employeeData).stream().min(Comparator.comparingInt(Employee::getSalary));
        System.out.println("Employee Having min Salary " + minSalary);


        // Remove Duplicate Employee
        List<Employee> empList = Arrays.asList(employeeData).stream().distinct().collect(Collectors.toList());
        System.out.println("Employee after Removal of Duplicates " + empList);

        List<Integer> list = Arrays.asList(2, 4, 6, 8);

        // allMatch() , anyMatch(), noneMatch()
        System.out.println(list.stream().allMatch(i -> i % 2 == 0 && i % 1 == 0));
        System.out.println(list.stream().anyMatch(i -> i % 3 == 0));
        System.out.println(list.stream().noneMatch(i -> i % 5 == 0));

        // Get All Employee ids from List of employee
        List<Integer> employeeIds = Arrays.asList(employeeData).stream().map(Employee::getEmpId).distinct().collect(Collectors.toList());
        System.out.println("List of Employee Ids : " + employeeIds);

        // Employee having Max Salary
        System.out.println(Stream.of(employeeData1).max((e1, e2) -> e1.getSalary() - e2.getSalary()).get().getSalary());
        // Average of all Employee Salary
        System.out.println(Stream.of(employeeData1).mapToDouble(Employee::getSalary).average().getAsDouble());

        // Sum of Salary Of Employees
        Double sum = Arrays.asList(employeeData1).stream().mapToDouble(Employee::getSalary).sum();
        System.out.println("Employee Salary Sum :" + sum);

        // Using Reduce Function To Calculate Avg, Sum, Min, Max
        Double sumSal = Arrays.asList(employeeData1).stream().mapToDouble(Employee::getSalary).reduce(0, Double::sum);

        System.out.println("Employee Salary Sum :" + sumSal);

        System.out.println(Arrays.asList(employeeData1).stream().mapToDouble(Employee::getSalary).reduce(Double::min).getAsDouble());

        // Summary Statistics
        DoubleSummaryStatistics stats = Arrays.asList(employeeData1).stream().mapToDouble(Employee::getSalary).summaryStatistics();

        System.out.println("Get Employee Count : " + stats.getCount());
        System.out.println("Get Employee Max Salary : " + stats.getMax());
        System.out.println("Get Employee Min Salary : " + stats.getMin());
        System.out.println("Get Employees Average Salary : " + stats.getAverage());
        System.out.println("Get Employees Total Salary : " + stats.getSum());

        Long startTimeInStream = System.currentTimeMillis();
        Arrays.asList(employeeData1).stream().forEach(employee -> employee.setSalary(employee.getSalary() + 10));
        Long endTimeInStream = System.currentTimeMillis();
        System.out.println("Time Taken in Stream " + (endTimeInStream - startTimeInStream));


        Long startTimeInParallelStream = System.currentTimeMillis();
        Arrays.asList(employeeData1).stream().parallel().forEach(employee -> employee.setSalary(employee.getSalary() + 10));
        Long endTimeInParallelStream = System.currentTimeMillis();
        System.out.println("Time Taken in Parallel Stream " + (endTimeInParallelStream - startTimeInParallelStream));

        // Infinite Streams
        Stream.iterate(2, i -> i * 2).limit(5).forEach(System.out::println);

        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Sonu");
        map.put(2, "Megha");
        map.put(3, "Rajesh");
        map.put(4, "Mahi");

        // Primitive Data Types
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::;");
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).forEach(System.out::println);

        // Custom Data Types : Objects
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(1, new Employee(1, "Jeff Bezos", 100000, "Accounts"));
        employeeMap.put(2, new Employee(2, "Bill Gates", 200000, "Solutioning"));
        employeeMap.put(3, new Employee(3, "Mark Zuckerberg", 300000, "HR"));
        employeeMap.put(4, new Employee(4, "Tesla", 400000, "Accounts"));

        employeeMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparingLong(Employee::getSalary))).forEach(System.out::println);
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::;");
        //Reverse order by Employee Name
        employeeMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getName).reversed())).forEach(System.out::println);

        List<String> employees = Arrays.asList(employeeData).stream().map(Employee::getName).collect(Collectors.toList());

    }
}
