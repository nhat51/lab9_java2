package lab08.view;

import lab08.Controller.Controller;
import lab08.Models.Cart;
import lab08.Models.books;
import lab08.Models.customer;
import lab08.Models.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class newView {
    Scanner input = new Scanner(System.in);
    Controller objControl = new Controller();
    //Menu
   public static void Home(){
        System.out.println("1.Manager book");
        System.out.println("2.Manager customer");
       System.out.println("3.Sign In");
       System.out.println("4.Sign Up");
    }
   public void customerMenu(){
        System.out.println("=======================");
        System.out.println("1.Add customer");
        System.out.println("2.Delete customer");
        System.out.println("3.Update customer");
        System.out.println("4View customer");
        System.out.println("5.Return");
        System.out.println("=======================");
    }
   public void bookMenu(){
        System.out.println("=======================");
        System.out.println("1.Add book");
        System.out.println("2.Update book");
        System.out.println("3.Delete book");
        System.out.println("4.View book");
       System.out.println("5.Add book to cart");
        System.out.println("6.Return");
        System.out.println("=======================");
    }
    //Add book, customer and user
    public void addBook(){
        System.out.println("Enter book information");
        books objBooks = new books();
        System.out.println("Enter book id: ");
        int id = Integer.parseInt(input.nextLine());
        objBooks.setId(id);
        System.out.println("Enter book name: ");
        String name = input.nextLine();
        objBooks.setName(name);
        System.out.println("Enter author: ");
        String author = input.nextLine();
        objBooks.setAuthor(author);
        System.out.println("Price of book: ");
        double price = input.nextFloat();
        objBooks.setPrice(price);
        System.out.println("The quantity of book is: ");
        int qty = input.nextInt();
        objBooks.setQty(qty);

        Controller objControl = new Controller();
        objControl.insertBook(objBooks);
   }
    public void addCustomer(){
        System.out.println("Enter customer information");
        customer objCus = new customer();

        System.out.println("Enter customer id:");
        int id = Integer.parseInt(input.nextLine());
        objCus.setCus_ID(id);

        System.out.println("Enter customer name: ");
        String name = input.nextLine();
        objCus.setName(name);

        System.out.println("Enter phone number:");
        String phone = input.nextLine();
        objCus.setPhone(phone);

        System.out.println("Enter email:");
        String email = input.nextLine();
        objCus.setEmail(email);

        System.out.println("Enter customer address: ");
        String address = input.nextLine();
        objCus.setAddress(address);

        Controller objControl = new Controller();
        objControl.insertCustomer(objCus);
    }
    public void SignUp(){
        user objUser = new user();

        System.out.println("Enter user name: ");
        String userName = input.nextLine();
        objUser.setUserName(userName);

        System.out.println("Enter pass word: ");
        String password = input.nextLine();
        objUser.setPassword(password);
        System.out.println("Retype password");
        String password1 = input.nextLine();
        if (password1 != password){
            System.out.println("password don't match try agian");
            do {
                System.out.println("Enter pass word: ");
                password = input.nextLine();
                objUser.setPassword(password);
                System.out.println("Retype password");
                password1 = input.nextLine();
            }while (password == password1);
        }

        System.out.println("Enter email: ");
        String email = input.nextLine();
        objUser.setEmail(email);

        System.out.println("Enter role: ");
        int role = input.nextInt();
        objUser.setRole(role);

        Controller objControl = new Controller();
        objControl.insertUser(objUser);
    }
    //Delete book and customer
    public void deleteBook(){
       System.out.println("Enter book ID you want delete: ");
        int id = input.nextInt();
        books objBook = new books();
        objBook.setId(id);
        objControl.deleteBook(objBook);
    }
    public void deleteCustomer(){
        System.out.println("Enter customer ID you want delete: ");
        int id = input.nextInt();
        customer objCus = new customer();
        objCus.setCus_ID(id);
        objControl.deleteCustomer(objCus);
    }
    //Update book and customer
    public void updateBook(){
        books obj = new books();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the book id you want to update:");
        int id = Integer.parseInt(input.nextLine());
        System.out.println("Enter new qty: ");
        int qty = Integer.parseInt(input.nextLine());
        System.out.println("Enter new price: ");
        double price = input.nextDouble();
        System.out.println();
        obj.setId(id);
        obj.setPrice(price);
        obj.setQty(qty);
        Controller ctr = new Controller();
        if(ctr.updateBook(obj)==0){
            System.out.println("Wrong book's information");
        }else if(ctr.updateBook(obj)==1){
            System.out.println("Book has been updated");
        };
    }
    public void updateCustomer(){
        customer obj = new customer();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the customer id you want to update:");
        int id = Integer.parseInt(input.nextLine());
        System.out.println("Enter new address: ");
        String address = input.nextLine();
        System.out.println("Enter new email: ");
        String email = input.nextLine();
        System.out.println("Enter new phone: ");
        String phone = input.nextLine();
        obj.setAddress(address);
        obj.setCus_ID(id);
        obj.setEmail(email);
        obj.setPhone(phone);
        Controller objControl = new Controller();
        if(objControl.updateCustomer(obj) == 0){
            System.out.println("No customer found");
        }else if(objControl.updateCustomer(obj) == 1){
            System.out.println("Customer has been updated");
        }
    }

    //book management and customer management
    public void customerManagement(){
    int choice;
    Scanner input = new Scanner(System.in);
    do {
        customerMenu();
        System.out.println("Your choice: ");
        choice = input.nextInt();
        switch (choice){
            case 1:
                addCustomer();
                break;
            case 2:
                deleteCustomer();
                break;
            case 3:
              updateCustomer();
              break;
            case 4:
                objControl.displayCustomer();
        }
    }while (choice != 5);
    }
    public void BookManagement(){
        int choice;
        Scanner input = new Scanner(System.in);
        do {
            bookMenu();
            System.out.println("Your choice: ");
            choice = input.nextInt();
            switch (choice){
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    objControl.displayBook();
                    break;
                case 5:
                    addToCart();
                    break;
            }
        }while (choice != 6);
    }
    public List<Cart> addToCart(){
       List<Cart> item = new ArrayList<>();
      Scanner input = new Scanner(System.in);
      char choice = 0;
      int i = 0;
      int id = 0;
      int qty;
      int sum = 0;
      int y = 0;
      do {
          System.out.println("Do you want purchasing for a book? (Y/N)");
          choice = input.nextLine().charAt(0);
          switch (choice){
              case 'y':
                  books books = new books();
                  do {
                      System.out.println("Enter book id you want purchase: ");
                      id = Integer.parseInt(input.nextLine());
                      i = objControl.checkBookByID(id);
                      books.setId(id);
                  }while (i != 0);
                  do {
                      System.out.println("Enter amount of book: ");
                      qty = Integer.parseInt(input.nextLine());
                      if (qty < 0 ){
                          System.out.println("Enter another number: ");
                          i = 1;
                      }
                      else {
                          sum += qty;
                          i = objControl.checkBookAmount(id,sum);
                          if (i != 0){
                              sum -= qty;
                          }
                          books.setQty(qty);
                      }
                  }while (i != 0);
                  Cart cartItem = objControl.loadCard(books);
                  item.add(cartItem);
                  for (int n = 0; n < item.size();n++){
                      System.out.println(item.get(n));
                  }
                  break;
              case 'n':
                  if (item.size() == 0) {
                  System.out.println("Thank you");
              }
          }
      }while (choice != 'n' );
      return item;
    }

    public static void main(String[] args) {
        System.out.println("!!!---Welcome to ebookStore---!!!");
        System.out.println("==============================");
        newView objNew = new newView();
        Controller objControl = new Controller();
        int choice;
        do {
            Home();
            System.out.println("Your choice: ");
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            switch (choice){
                case 1:
                    objNew.BookManagement();
                    break;
                case 2:
                    objNew.customerManagement();
                    break;
                case 3:
                    objControl.signIn();
                case 4:
                    objNew.SignUp();
            }
        }while (choice != 5);
    }
}
