package storage;

import model.Employee;

import java.io.*;

public class SerializationUtil {
    public static void serializeEmployee(Employee emp, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(emp);
        }
    }

    public static Employee deserializeEmployee(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Employee) in.readObject();
        }
    }
}