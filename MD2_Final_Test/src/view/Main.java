package view;

import model.Contact;
import model.Input;
import service.ContactManagement;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ContactManagement contactManagement=new ContactManagement();
        Scanner scanner=new Scanner(System.in);
        int choice;
        do {
            menu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Hiển thị danh bạ : ");
                    contactManagement.display();
                    break;
                case 2:
                    Contact contact= Input.inputContact();
                    contactManagement.create(contact);
                    System.out.println("Thêm liên hệ mới thành công !");
                    break;
                case 3:
                    String phoneNumberUpdate=Input.inputPhoneNumber();
                    Contact contactUpdate=Input.inputContact();
                    contactManagement.update(phoneNumberUpdate,contactUpdate);
                    break;
                case 4:
                    String phoneNumberDelete=Input.inputPhoneNumber();
                    System.out.print("Xóa liên hệ này" +
                            " ? Y/N: ");
                    String choiceDelete=scanner.nextLine();
                    if(choiceDelete.equals("Y")){
                        contactManagement.delete(phoneNumberDelete);
                    }
                    break;
                case 5:
                    int choiceFind;
                    boolean check=false;
                    do{
                        menu_find();
                        System.out.print("Nhập lựa chọn : ");
                        choiceFind= scanner.nextInt();
                        scanner.nextLine();
                        switch (choiceFind){
                            case 1:
                                String phoneNumberFind=Input.inputPhoneNumber();
                                int index= contactManagement.findByPhoneNumber(phoneNumberFind);
                                if(index==-1) System.out.println("Số điện thoại không tồn tại "+phoneNumberFind);
                                else System.out.println(contactManagement.getContacts().get(index));
                                check=true;
                                break;
                            case 2:
                                System.out.print("Nhập tên : ");
                                String name=scanner.nextLine();
                                List<Contact> contactsFine=contactManagement.findByName(name);
                                if(contactsFine==null) System.out.println("Không tìm thấy liên hệ với tên : "+name);
                                else {
                                    for (Contact c :contactsFine) {
                                        System.out.println(c);
                                    }
                                }
                                check=true;
                                break;
                        }
                    } while (!check);
                    break;
                case 6:
                    System.out.print("Bạn có chắc chắn để đọc tệp không? Y/N");
                    String choiceRead=scanner.nextLine();
                    if(choiceRead.equals("Y")) contactManagement.readFromFile();
                    break;
                case 7:
                    System.out.print("Bạn có chắc chắn để viết tệp không ? Y/N");
                    String choiceWrite=scanner.nextLine();
                    if(choiceWrite.equals("Y")) contactManagement.writeToFile();
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        } while (choice >= 1 && choice <= 8);

    }
    public static void menu_find(){
        System.out.println("\n----MENU----");
        System.out.println("1. Tìm theo số điện thoại");
        System.out.println("2. Tìm theo tên");
    }
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
        System.out.println("Chọn chức năng theo số (để tiếp tục): ");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc vào file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng: ");

    }
}
