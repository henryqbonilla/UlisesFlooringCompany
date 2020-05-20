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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Customer {
    
    //declerations
    private static final NumberFormat CF = NumberFormat.getCurrencyInstance(Locale.US);
    //enumeration for floor type
    public enum FloorType{Wood, Carpet}
    
    private String name;
    private String address;
    
    private FloorType floorType;
    private double area;
    private double cost;
    
    private static final double WOOD_COST = 20.0;
    private static final double CARPET_COST = 10.0;
    
    private int id = -1;
    
    //constructor
    public Customer(){
        name = "";
        address = "";
        floorType = FloorType.Wood;
        area = 0.0;
        cost = 0.0;
    }
    
    //getters and setters
    public void setName(String first, String last){
        name = first + " " + last;
    }
    
    public void setName(String fullName){
        name = fullName;
    }
    
    public String getName(){
        return name;
    }
    
    public void setAddress(String street, String city, String state, int zip){
        address = street + " " + city + " " + state + ", " + Integer.toString(zip);
    }
    
    public void setAddress(String fullAddress){
        address = fullAddress;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setFloorType(FloorType floorType){
        this.floorType = floorType;
    }
    
    public String getFloorType(){
        String floor;
        if (floorType == FloorType.Carpet)
        floor = "Carpet";
        else
            floor = "Wood";
        return floor;
    }
    
    public void setID( int id){
        this.id = id;
    }
    
    public int getID(){
        return id;
    }
    
    //calculate and sets the area
    public void setArea(int lengthFt, int lengthIn, int widthFt, int widthIn){
        area = ( lengthFt +  (lengthIn / 12.0) ) * ( widthFt + (widthIn / 12.0) );
        area = formatDouble(area);
    }
    
    public void setArea(double area){
        
        this.area = formatDouble(area);
    }
    
    public double getArea(){
        return area;
    }
    
    //calculates and sets the total cost
    public void setCost(){
        if(floorType == FloorType.Carpet)
            cost = area * CARPET_COST;
        else if (floorType == FloorType.Wood)
            cost = area * WOOD_COST;
        else
            cost = 0;
    }
    
    public void setCost(double cost){
        this.cost = formatDouble(cost);
    }
    
    public double getCost(){
        return cost;
    }
    
    public static Double calculateArea(int Lft, int Lin, int Wft, int Win){
        return (Lft + Lin/12.0) * (Wft + Win/12.0);
    }
    
    private double formatDouble(double d){
        DecimalFormat df = new DecimalFormat("#.00");
        String s = df.format(d);
        Double newD = Double.parseDouble(s);
        return newD;
    }
    
    public String getOrderInformation(){
        StringBuilder str = new StringBuilder();
        str.append("Customer Order for: \n");
        str.append(name);
        str.append("\n");
        str.append(address);
        str.append("\n");
        str.append("Floor Type: ");
        str.append(getFloorType());
        str.append("\n");
        str.append("Area: ");
        str.append(area);
        str.append(" sqft");
        str.append("\n");
        str.append("Total: ");
        str.append(CF.format(cost));
        
        return str.toString();
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
