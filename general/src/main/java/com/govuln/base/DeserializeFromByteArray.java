package com.govuln.base;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
/**
 * JAVA进行序列化和反序列化操作，触发目标类静态方法，导致命令执行
 *
 * */
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    static{
        System.out.println("static");
        try {
            Runtime.getRuntime().exec("calc.exe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
public class DeserializeFromByteArray {
    public static void main(String[] args) {
        try {
            // 假设这里有一个已经序列化好的 byte[] 数据
            byte[] serializedData = serializePerson();

            // 反序列化 byte[] 为对象
            Person person = deserializePerson(serializedData);

            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 模拟序列化过程，将 Person 对象序列化为 byte[]
    private static byte[] serializePerson() throws IOException {
        Person person = new Person("Alice", 25);
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(bos);
        oos.writeObject(person);
        oos.flush();
        return bos.toByteArray();
    }

    // 反序列化 byte[] 为 Person 对象
    private static Person deserializePerson(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Person) ois.readObject();
    }
}