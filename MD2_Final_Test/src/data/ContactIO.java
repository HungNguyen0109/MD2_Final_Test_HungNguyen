package data;

import model.Contact;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContactIO {
    public static void writeToFile(String pathFile, List<Contact> contacts) {
        File file=new File(pathFile);
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write("Số điện thoại , Nhóm, Tên, Địa chỉ, Giới tính , Ngày sinh, Email\n");
            for (Contact contact:contacts) {
                bufferedWriter.write(contact.getPhoneNumber()+","+
                        contact.getGroup()+","+
                        contact.getName()+","+
                        contact.getAddress()+","+
                        contact.getGender()+","+
                        contact.displayDate()+","+
                        contact.getEmail()+",\n"
                );
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List< Contact> readFromFile(String pathFile) {
        List<Contact> contacts=new ArrayList<>();
        File file=new File(pathFile);
        FileReader fileReader= null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String lineCSV= bufferedReader.readLine();
            while ((lineCSV=bufferedReader.readLine())!=null){
                String[] content=lineCSV.split(",");
                String phoneNumber=content[0];
                String group=content[1];
                String name=content[2];
                String gender=content[3];
                String address=content[4];
                String email=content[6];
                String dateString=content[5];
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateOfBirth=LocalDate.parse(dateString,formatter);
                Contact contact=new Contact(phoneNumber,group,name,gender,address,email,dateOfBirth);
                contacts.add(contact);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
