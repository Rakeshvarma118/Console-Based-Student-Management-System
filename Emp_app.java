package com.cbsmsTest;
import java.sql.*;
import java.util.Scanner;
public class Emp_app {

 
      
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdb","root","Rakesh@118");


            while (true) {
                System.out.println("===== Employee Menu =====");
                System.out.println("1. Insert Record");
                System.out.println("2. Update Record");
                System.out.println("3. Fetch Single Record");
                System.out.println("4. Fetch All Records");
                System.out.println("5. Delete Record");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: insertRecord(con, sc); break;
                    case 2: updateRecord(con, sc); break;
                    case 3: fetchRecord(con, sc); break;
                    case 4: fetchAllRecords(con); break;
                    case 5: deleteRecord(con, sc); break;
                    case 6:
                        System.out.println("Exiting...");
                        sc.close();
                        con.close();
                        System.exit(0);
                    default: System.out.println("Invalid choice, try again!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insert Record
    private static void insertRecord(Connection con, Scanner sc) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Salary: ");
        double sal = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();

        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setDouble(3, sal);
        ps.setString(4, dept);
        ps.setString(5, contact);

        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Record Inserted Successfully!" : "Insert Failed!");
    }

    // Update Record
    private static void updateRecord(Connection con, Scanner sc) throws SQLException {
        String sql = "UPDATE employee SET name=?, salary=?, dept=?, contact=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        

        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Salary: ");
        double sal = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter New Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter New Contact: ");
        String contact = sc.nextLine();

        ps.setString(1, name);
        ps.setDouble(2, sal);
        ps.setString(3, dept);
        ps.setString(4, contact);
        ps.setInt(5, id);

        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Record Updated Successfully!" : "Update Failed!");
    }
    
    

    // Fetch Single Record
    private static void fetchRecord(Connection con, Scanner sc) throws SQLException {
        String sql = "SELECT * FROM employee WHERE eid=?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) +
                               ", Name: " + rs.getString(2) +
                               ", Salary: " + rs.getDouble(3) +
                               ", Dept: " + rs.getString(4) +
                               ", Contact: " + rs.getString(5));
        } 
//        else {
//            System.out.println("No record found!");
//        }
    }

    // Fetch All Records
    private static void fetchAllRecords(Connection con) throws SQLException {
        String sql = "SELECT * FROM employee";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("---- All Employee Records ----");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) +
                               ", Name: " + rs.getString(2) +
                               ", Salary: " + rs.getDouble(3) +
                               ", Dept: " + rs.getString(4) +
                               ", Contact: " + rs.getString(5));
        }
    }

    // Delete Record
    private static void deleteRecord(Connection con, Scanner sc) throws SQLException {
        String sql = "DELETE FROM employee WHERE eid=?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Record Deleted Successfully!" : "Delete Failed!");
    }

	

}