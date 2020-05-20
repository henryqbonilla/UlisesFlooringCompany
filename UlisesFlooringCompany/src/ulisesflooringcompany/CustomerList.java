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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultListModel;

public class CustomerList {
    private ArrayList<Customer> customerList;
    private final DataIO dataIO;
    
    public CustomerList(){
        customerList = new ArrayList<>();
        dataIO = new DataIO();
    }
    
    private void getList(){
        customerList.clear();
        customerList = dataIO.getList();
    }
    
    public boolean add(Customer c){
        boolean success = false;
        if(c != null)
        {
            customerList.add(c);
            success = dataIO.add(c);
        }
        return success;
    }
    
    public boolean remove(Customer c){
        boolean success = false;
        if(c != null)
        {
            customerList.remove(c);
            success = dataIO.delete(c);
        }
        return success;
    }
    
    public int setList(DefaultListModel<Customer> listModel){
        int count = 0;
        getList();
        
        if(!customerList.isEmpty())
        {
            listModel.clear();
            count = customerList.size();
            for(Customer c : customerList)
                listModel.addElement(c);
        }
        return count;
    }
    
  
}
