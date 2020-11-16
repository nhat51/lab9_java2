package lab08.Controller;


import lab08.Models.Cart;
import lab08.Models.books;
import lab08.Models.customer;
import lab08.Models.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    List<books> listBook = new ArrayList<>();
    List<customer> listCustomer = new ArrayList<>();
    List<user> ListUser = new ArrayList<>();
    String url = "jdbc:mysql://localhost:3306/ebookstore";
    String use = "root";
    String pass = "";
    //Book
    public List<books> loadBook() {
     try (Connection conn = DriverManager.getConnection(url,use,pass);
          Statement stmt = conn.createStatement()) {
         String strSelect = "Select * from books";
         ResultSet rSet = stmt.executeQuery(strSelect);
         while (rSet.next()){
             int id = rSet.getInt("bookID");
             String name = rSet.getString("title");
             String author = rSet.getString("author");
             double price = rSet.getDouble("price");
             int qty = rSet.getInt("qty");
             books objBook = new books(id,name,author,price,qty);
             listBook.add(objBook);
         }
     } catch (SQLException throwables) {
         throwables.printStackTrace();
     }
     return listBook;
    }
    public void displayBook(){
        try(Connection conn = DriverManager.getConnection(url,use,pass);
            Statement stmt = conn.createStatement()){
            String stt = "Select * from books";
            ResultSet rset = stmt.executeQuery(stt);
            ResultSetMetaData rsetMD = rset.getMetaData();
            int numColums = rsetMD.getColumnCount();
            for (int i = 1; i <= numColums; i++) {
                System.out.printf("%-30s", rsetMD.getColumnName(i));
            }
            loadBook();
            System.out.println();
            while(rset.next()){
                for(int i=1;i<=numColums;i++){
                    System.out.printf("%-30s",rset.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public boolean insertBook(books book){
        try (Connection conn = DriverManager.getConnection(url,use,pass);
             Statement stmt = conn.createStatement()){
            String sqlInsert = "Insert into books values ("
                    + book.getId() + ", '"
                    + book.getName() + "','"
                    + book.getAuthor() +"',"
                    + book.getPrice() +", " + book.getQty() + ")";
             stmt.executeUpdate(sqlInsert);
            loadBook();
            System.out.println("Inserted");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    public int updateBook(books book){
        try (Connection conn = DriverManager.getConnection(url,use,pass);
             Statement stmt = conn.createStatement()){
            String sqlUpdate =  "update books set qty="+book.getQty()+",price="+book.getPrice()+" where bookID ="+book.getId();
            int count = stmt.executeUpdate(sqlUpdate);
            if (count == 0){
                return 0;
            }else {
                return 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }
    public void deleteBook(books book){
        try (Connection conn = DriverManager.getConnection(url,use,pass);
             Statement stmt = conn.createStatement()){
            String sqlDelete = "select * from orders where bookID ="+ book.getId();
            ResultSet rSet = stmt.executeQuery(sqlDelete);
            if (!rSet.next()){
                String deleteBook = "Delete from book where bookID =" + book.getId();
                stmt.executeQuery(deleteBook);
                System.out.println("Delete successful");
            }else {
                System.out.println("Can't delete this book");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int checkBookByID(int id) {
        try (
                Connection conn = DriverManager.getConnection(url, use, pass);
                Statement stmt = conn.createStatement();
        ) {
            ResultSet rset = stmt.executeQuery("Select * from books where bookID = "+id);
            int row = 0;
            while(rset.next()){
                ++row;
            }
            if(row == 0){
                System.out.println("Please enter the correct ID");
                return 1;
            } else
                return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    public int checkBookAmount(int id, int qty){
        try (
                Connection conn = DriverManager.getConnection(url, use, pass);
                Statement stmt = conn.createStatement();
        ) {
            ResultSet rset = stmt.executeQuery("Select * from books where bookID = "+id);
            int amount = 0;
            while(rset.next()){
                amount = rset.getInt("qty");
            }
            if(qty > amount){
                System.out.println("We dont have enough books");
                return 1;
            } else
                return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }return -1;
    }
    //Customer
    public List<customer> loadCustomer(){
        try(Connection conn = DriverManager.getConnection(url,use,pass);
        Statement stmt = conn.createStatement()) {
            String select = "Select * from customers";
            ResultSet rSet = stmt.executeQuery(select);
            while (rSet.next()){
                int id = rSet.getInt("CustomerID");
                String name = rSet.getString("CustomerName");
                String phone = rSet.getString("phone");
                String email = rSet.getString("email");
                String address = rSet.getString("address");
                int level = rSet.getInt("levels");
                customer objCustomer = new customer(id,name,email,phone,address,level);
                listCustomer.add(objCustomer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  listCustomer;
    }
    public void insertCustomer(customer customers){
        try(
                Connection conn = DriverManager.getConnection(url,use,pass);
                Statement stmt = conn.createStatement()
                ) {
            String sqlInsert = "Insert into customers values (" + customers.getCus_ID() + ", '"
                    + customers.getName() +"','"
                    + customers.getEmail() + "','"
                    + customers.getPhone() +"','"
                    + customers.getAddress() + "',"
                    + customers.getLevel() + ")";
            stmt.executeQuery(sqlInsert);
            loadCustomer();
            System.out.println("Inserted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void displayCustomer(){
        try(Connection conn = DriverManager.getConnection(url,use,pass);
            Statement stmt = conn.createStatement()){
            String stt = "Select * from customers";
            ResultSet rset = stmt.executeQuery(stt);
            ResultSetMetaData rsetMD = rset.getMetaData();
            int numColums = rsetMD.getColumnCount();
            for (int i = 1; i <= numColums; i++) {
                System.out.printf("%-30s", rsetMD.getColumnName(i));
            }
            loadBook();
            System.out.println();
            while(rset.next()){
                for(int i=1;i<=numColums;i++){
                    System.out.printf("%-30s",rset.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public int updateCustomer(customer customers){
        try(
                Connection conn = DriverManager.getConnection(url,use,pass);
                Statement stmt = conn.createStatement()
                ){
            String update = "Update customers set CustomerID = " + customers.getCus_ID()
                    + ",CustomerName ='" + customers.getName()
                    + "',phone = '" + customers.getPhone()
                    + "', address = '" + customers.getAddress()
                    +"',levles = " + customers.getLevel();stmt.executeUpdate(update);
            int count = stmt.executeUpdate(update);
            loadCustomer();
            if(count == 0){
                return 0;
            }else{
                return 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }
    public void deleteCustomer(customer customers){
        try (
                Connection conn = DriverManager.getConnection(url,use,pass);
                Statement stmt = conn.createStatement();
        ) {
            String sqlDelete = "select * from orders where bookID ="+ customers.getCus_ID();
            ResultSet rSet = stmt.executeQuery(sqlDelete);
            if (!rSet.next()){
                String deleteBook = "Delete from customers where customerID =" + customers.getCus_ID();
                stmt.executeQuery(deleteBook);
                System.out.println("Delete successful");
            }else {
                System.out.println("Can't delete because customer ordered");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //User
    public List<user> loadUSer() {
        try (
                Connection conn = DriverManager.getConnection(url,use,pass);
                Statement stmt = conn.createStatement()
                ) {
            String sqlSelect = "Select * from users";
            ResultSet rSet = stmt.executeQuery(sqlSelect);
            while (rSet.next()){
                String name = rSet.getString("userName");
                String pass = rSet.getString("password");
                String email = rSet.getString("email");
                int role = rSet.getInt("role");
                user objUser = new user(name,pass,email,role);
                ListUser.add(objUser);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  ListUser;
    }
    public  void insertUser(user users){
        try(
                Connection conn = DriverManager.getConnection(url,use,pass);
                Statement stmt = conn.createStatement();
                ) {
            String selectUser = "Select userName from users where userName = '" + users.getUserName() + "';";
            ResultSet rSet = stmt.executeQuery(selectUser);
            if (!rSet.next()){
                String insertUser = "Insert into users values ( '"
                        + users.getUserName() + "', '"
                        + users.getPassword() + "', '"
                        + users.getEmail() + "', "
                        + users.getRole() + ")";
                stmt.executeQuery(insertUser);
                loadUSer();
                System.out.println("Inserted");
            }else {
                System.out.println("User name already exist");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public user signIn() {
        user obj = new user();
        try (
                Connection conn = DriverManager.getConnection(url,use,pass);
                Statement stmt = conn.createStatement();
        ) {
            Scanner input = new Scanner(System.in);
            System.out.println("(SignIn)Enter your username: ");
            String name = input.nextLine();
            System.out.println("(SignIn)Enter your password: ");
            String pass = input.nextLine();
            String select = "Select * from users where name ='" + name + "' AND password ='" + pass + "'";
            ResultSet rset = stmt.executeQuery(select);
            int row =0;
            while (rset.next()) {
                int id = rset.getInt("id");
                String username = rset.getString("name");
                String password=rset.getString("password");
                int role=rset.getInt("role");
                String email = rset.getString("email");
                obj = new user(username,password,email,role);
                ++row;
            }
            if (row == 0){
                System.out.println("Wrong username or password");
                return null;
            }else {
                System.out.println("Sign in successfully");
                return obj;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public int checkAccount(String name){
        try (
                Connection conn = DriverManager.getConnection(url, use, pass);
                Statement stmt = conn.createStatement();
        ) {
            ResultSet rset = stmt.executeQuery("Select * from users where name ='"+name+"'");
            int row = 0;
            while(rset.next()){
                ++row;
            }
            if(row==0){
                System.out.println("You can use this account name");
                return 0;
            }
            else{
                System.out.println("Enter another account name");
                return 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }return -1;
    }

    //Cart
    public Cart loadCard(books book){
        Cart objCart = new Cart();
        try(
                Connection conn = DriverManager.getConnection(url,use,pass);
                Statement stmt = conn.createStatement();
                ) {
            String select = "Select * from books where bookID = " + objCart.getBookID();
            ResultSet rSet = stmt.executeQuery(select);
            System.out.printf("%-30s%-30s%-30s%-30s%-30s","bookID","title","price","qty","discount");
            System.out.println();
            int row = 0;
            int id = 0;
            String title = null;
            double price = 0;
            while (rSet.next()){
                id = rSet.getInt("bookID");
                title = rSet.getString("title");
                price = rSet.getDouble("price");
                row++;
            }
            if (row == 0){
                System.out.println("Can't find book ID");
                return null;
            }
            else {
                objCart = new Cart(id,book.getQty());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return objCart;
    }
    public boolean purchase(List<Cart> cartList,customer customer){
        try (
                Connection conn = DriverManager.getConnection(url,use,pass);
                Statement stmt = conn.createStatement();
                ){
            double sum = 0;
            for (int i = 0; i < cartList.size();i++){
                sum = sum + cartList.get(i).getPrice() * cartList.get(i).getQty();
            }
            conn.setAutoCommit(false);
            String insert = "insert into orders (customerID,discount,total,orderdate,status,createddate) values(" +
                    customer.getCus_ID() + ",0," + sum + ",current_Date()," + "1," + "current_Date())";
            stmt.executeUpdate(insert);
            String select = "select * from orders order by orderID DESC LIMIT 1";
            ResultSet rSet = stmt.executeQuery(select);
            int orderID = 0;
            while (rSet.next()){
                orderID = rSet.getInt("orderID");
            }
            for (int i = 0; i < cartList.size();i++){
                String update = "Update books set qty = qty -" + cartList.get(i).getQty() + "where bookID = " + cartList.get(i).getBookID();
                stmt.executeUpdate(update);
                String insert2 = "insert into orderdetail(orderID, bookID,title,amount,price,createddate) values("
                        + orderID + ","
                        + cartList.get(i).getBookID() + ",'"
                        + cartList.get(i).getTitle() + "',"
                        + cartList.get(i).getQty() + ","
                        + cartList.get(i).getPrice() + ","
                        + " CURRENT_DATE())";
                stmt.executeUpdate(insert2);
            }
            conn.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }


}
