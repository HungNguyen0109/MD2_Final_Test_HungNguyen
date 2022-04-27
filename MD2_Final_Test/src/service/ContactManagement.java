package service;

import data.ContactIO;
import model.Contact;

import java.util.ArrayList;
import java.util.List;

import static data.Path.PATH_FILE;

public class ContactManagement {
    private List<Contact> contacts;

    public ContactManagement() {
        this.contacts= ContactIO.readFromFile(PATH_FILE);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
    public void create(Contact contact){
        this.contacts.add(contact);
//        ContactIO.writeToFile(PATH_FILE,this.contacts);
    }
    public void display(){
        for (Contact contact:this.contacts) {
            System.out.println(contact);
        }
    }
    public int findByPhoneNumber(String phoneNumber){
        int index=-1;
        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getPhoneNumber().equals(phoneNumber)) index=i;
        }
        return index;
    }
    public List<Contact> findByName(String name){
        List<Contact> listFind=new ArrayList<>();
        for (Contact contact:contacts) {
            if(contact.getName().equals(name)) listFind.add(contact);
        }
        return listFind;
    }
    public void update(String phoneNumber, Contact contact){
        int index;
        do{
            index=findByPhoneNumber(phoneNumber);
            if(index==-1) {
                System.out.println("Số điện thoại không tồn tại, vui lòng nhập lại: ");
                break;
            }
            else {
                contacts.set(index, contact);
                ContactIO.writeToFile(PATH_FILE, contacts);
            }
        }while (index==-1);
    }
    public void delete(String phoneNumber) {
        int index=-1;
        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                contacts.remove(i);
                index=i;
            }
        }
        if(index==-1) System.out.println("Số điện thoại không tồn tại !");
        else System.out.println("Xóa thành công !");
    }
    public void writeToFile(){
        ContactIO.writeToFile(PATH_FILE,contacts);
        System.out.println("Viết thành công !");
    }
    public void readFromFile(){
        this.contacts= ContactIO.readFromFile(PATH_FILE);
    }
}
