/***************************************
*Program Name: Ulises's Flooring Company
*Programmer's Name: Henry Bonilla
* Program Description: This is a Graphic
* User Interface Program that takes in
* basic customer information as well
* as their flooring order. It calculates
* area and cost then follows by storing
* all information in a database. At the
* start of the program, entries that are
* already in database are loaded into 
* the program. Deletion of customers is
* an option as well.
****************************************/

package ulisesflooringcompany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DataIO {
    private final String CONNECTION = "jdbc:mysql://localhost:3306/flooringcompany";
    //private final String CONNECTION = "jdbc:mysql://192.168.1.247:3306/flooringcompany";
    private final String CUSTOMER_TABLE = "customers";
    private final String USER = "root";
    //private final String PASSWORD = "password123";
    private final String PASSWORD = "DOD_orange";
    private final String INSERT_QUERY = "INSERT INTO customers (CustomerName, CustomerAddress, FlooringType, "
                                        + "FloorArea, FloorCost) VALUES (?,?,?,?,?)";
    private final String FETCH_QUERY = "SELECT * FROM customers";
    private final String DELETE_QUERY = "DELETE FROM customers WHERE customerID = ?";
    
    Connection conn = null;
    Statement smt;
    ResultSet rs;
    
    public DataIO(){
        try
        {
            System.out.println("Attemping Connection...");
            conn = DriverManager.getConnection(CONNECTION,USER,PASSWORD);
            System.out.println("Database Connected");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Database Error");
        }
    }
    
    public boolean add(Customer c){
        boolean success = false;
        PreparedStatement statement;
        int rowCount;
        
        if(conn != null && c != null)
        {
            try{
            statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, c.getName());
            statement.setString(2, c.getAddress());
            statement.setString(3, c.getFloorType());
            statement.setDouble(4, c.getArea());
            statement.setDouble(5, c.getCost());
            
            rowCount = statement.executeUpdate();
            if (rowCount == 1)
            {
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next())
                {
                    c.setID(rs.getInt(1));
                }
                success = true;
            }
            
            }
            catch(SQLException e)
            {
                String message = e.getMessage()
                        + " cannot save customer information for"
                        + c.getName();
                JOptionPane.showMessageDialog(null, message, "SQL Server Error: Insert", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (c == null)
        {
            throw new NullPointerException("Customer object is null");
        }
        else
        {
            throw new IllegalStateException("Database is not connected");
        }
        
        return success;
    }
    
    public boolean delete(Customer c){
        boolean success = false;
        PreparedStatement statement = null;
        int rowCount;
        
        if (c != null && conn != null)
        {
        try{
            statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, c.getID());
            rowCount = statement.executeUpdate();
            if(rowCount == 1)
                success = true;
        }
        catch(SQLException e)
        {
            String message = e.getMessage()
                    + " cannot delete customer information for"
                    + c.getName();
            JOptionPane.showMessageDialog(null, message, "SQL Server Error: Delete", JOptionPane.ERROR_MESSAGE);
        }
        }
        else if (c == null)
            throw new NullPointerException("Customer object is null");
        else
            throw new IllegalStateException("Database is not connected");
        
        return success;
    }
    
    public ArrayList<Customer> getList(){
        ArrayList<Customer> list = new ArrayList<>();
        Customer c;
        
        try
        {
        smt = conn.createStatement();
        rs = smt.executeQuery(FETCH_QUERY);
        
        while(rs.next())
        {
            c = new Customer();
            c.setName(rs.getString(1));
            c.setAddress(rs.getString(2));
            String floor = rs.getString(3);
            if(floor.equalsIgnoreCase("Carpet"))
                c.setFloorType(Customer.FloorType.Carpet);
            else
                c.setFloorType(Customer.FloorType.Wood);
            c.setArea(rs.getDouble(4));
            c.setCost(rs.getDouble(5));
            c.setID(rs.getInt(6));
            list.add(c);
        }
        
        }
        catch(SQLException e)
        {
            
        }
        
        return list;
    }
    
    public void closeConnection() throws SQLException {
        try {
            if ((conn != null) && (!conn.isClosed())) {
                conn.close();
                conn = null;
            }
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
        
}
