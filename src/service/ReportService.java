package service;

import model.Employee;
import java.math.BigDecimal;
import java.io.*;
import java.util.List;

public class ReportService {
    public void writeReportToFile(String filename, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        }
    }

    public String readReportFromFile(String filename) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        }
        return result.toString();
    }

    public double calculateTotalEmployeeSalaries(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getBaseSalary)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }
}