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
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Floor_GUI extends javax.swing.JFrame {
    
    private static final NumberFormat CF = NumberFormat.getCurrencyInstance(Locale.US);
    private Customer aCustomer;
    private final DefaultListModel<Customer> listModel;
    private final CustomerList customers;

    public Floor_GUI() {
        initComponents();
        newCustomer();
        
        listModel = new DefaultListModel<>();
        customerJList.setModel(listModel);
        
        customers = new CustomerList();
        retrieveCustomerList();
    }
    
    private void retrieveCustomerList(){
        int count = 0;
        String message;
        count = customers.setList(listModel);
        message = count + " customer records retrieved";
        JOptionPane.showMessageDialog(null, message, "Customers Retrieved", JOptionPane.INFORMATION_MESSAGE);
    }
    private void terminateApplication(){
        int decision = JOptionPane.NO_OPTION;
            String question = "Are you sure you want to quit?";
            String title = "Quit Ulises's Flooring";
            decision = JOptionPane.showConfirmDialog(null, question, title,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (decision == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
    }
    private void clearItems(){
        if (customerInfoPanel.isShowing())
        {
            txtFirstName.setText("");
            txtLastName.setText("");
            txtStreet.setText("");
            txtCity.setText("");
            txtZip.setText("");
            cmBoxState.setSelectedIndex(20);
        }
        else if (orderInfoPanel.isShowing())
        {
            txtLengthFt.setText("");
            txtLengthIn.setText("");
            txtWidthFt.setText("");
            txtWidthIn.setText("");
            txtSubTotal.setText("");
            radWood.setSelected(true);
        }
    } 
    private void clearAll(){
        
        txtFirstName.setText("");
        txtLastName.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtZip.setText("");
        cmBoxState.setSelectedIndex(20);
        txtLengthFt.setText("");
        txtLengthIn.setText("");
        txtWidthFt.setText("");
        txtWidthIn.setText("");
        txtSubTotal.setText("");
        radWood.setSelected(true);
    }
    private void newCustomer(){
        aCustomer = new Customer();
        clearItems();
    }
    private void showOrderDetails(){
        if(customerJList.getSelectedIndex() != -1)
        {
            aCustomer = customerJList.getSelectedValue();
            txtAreaSummary.setText(aCustomer.getOrderInformation());
        }
    }
    private void submitOrder(){
        aCustomer = new Customer();
        boolean success = false;
        String message;
        
        if(checkEmpty())
        {
            try
            {
                Integer.parseInt(txtZip.getText());
                Double.parseDouble(txtLengthFt.getText());
                Double.parseDouble(txtLengthIn.getText());
                Double.parseDouble(txtWidthFt.getText());
                Double.parseDouble(txtWidthIn.getText());
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Zip, Length, and Width must all be numeric");
                return;
            }
            
        aCustomer.setName(txtFirstName.getText(), txtLastName.getText());
        aCustomer.setAddress(txtStreet.getText(), txtCity.getText(), 
                String.valueOf(cmBoxState.getSelectedItem()), Integer.parseInt(txtZip.getText()));
        if(radWood.isSelected())
            aCustomer.setFloorType(Customer.FloorType.Wood);
        else if(radCarpet.isSelected())
            aCustomer.setFloorType(Customer.FloorType.Carpet);
        else
            aCustomer.setFloorType(Customer.FloorType.Wood);
        
        aCustomer.setArea(Integer.parseInt(txtLengthFt.getText()), Integer.parseInt(txtLengthIn.getText()),
        Integer.parseInt(txtWidthFt.getText()), Integer.parseInt(txtWidthIn.getText()));
        aCustomer.setCost();
        System.out.println(aCustomer.getCost());
        listModel.addElement(aCustomer);
        success = customers.add(aCustomer);
        
        if (success)
        {
            message = "Successfully added " + txtFirstName.getText() + " to the database";
            JOptionPane.showMessageDialog(null, message, "Customer Successfully added",
                    JOptionPane.INFORMATION_MESSAGE);
            clearAll();
            myTab.setSelectedIndex(2);
        }
        else
        {
            message = "Failed to add " + txtFirstName.getText() + " to the the database";
            JOptionPane.showMessageDialog(null, message, "Failed", JOptionPane.ERROR_MESSAGE);
        }
        
        }
        else
        {
            return;
        }
    }
    
    private void deleteOrder(){
        int item = customerJList.getSelectedIndex();
        if (item != -1)
        {
            int response;
            Customer c = customerJList.getSelectedValue();
            String message = "Are you sure you want to delete customer: " + c;
            response = JOptionPane.showConfirmDialog(null, message, "Delete Customer",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(response == JOptionPane.YES_OPTION)
            {
                listModel.removeElement(c);
                customers.remove(c);
                txtAreaSummary.setText("");
            }
        }
    }
    
    private boolean checkEmpty(){
        boolean success = false;
        
        if (!txtFirstName.getText().equalsIgnoreCase("") &&
            !txtLastName.getText().equalsIgnoreCase("") &&
            !txtStreet.getText().equalsIgnoreCase("") &&
            !txtCity.getText().equalsIgnoreCase("") &&
            !txtZip.getText().equalsIgnoreCase("") &&
            !txtLengthFt.getText().equalsIgnoreCase("") &&
            !txtLengthIn.getText().equalsIgnoreCase("") &&
            !txtWidthFt.getText().equalsIgnoreCase("") &&
            !txtWidthIn.getText().equalsIgnoreCase(""))
        {
            success = true;
        }
        else
            JOptionPane.showMessageDialog(null, "Please enter all fields", "Empty Fields", JOptionPane.ERROR_MESSAGE);
        
        return success;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupFlooring = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        myTab = new javax.swing.JTabbedPane();
        customerInfoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtStreet = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmBoxState = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtZip = new javax.swing.JTextField();
        btnNext1 = new javax.swing.JButton();
        btnBack1 = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        btnExit1 = new javax.swing.JButton();
        orderInfoPanel = new javax.swing.JPanel();
        radWood = new javax.swing.JRadioButton();
        radCarpet = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtLengthFt = new javax.swing.JTextField();
        txtLengthIn = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtWidthFt = new javax.swing.JTextField();
        txtWidthIn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        btnCalculate = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnClear2 = new javax.swing.JButton();
        btnBack2 = new javax.swing.JButton();
        btnNext2 = new javax.swing.JButton();
        btnExit2 = new javax.swing.JButton();
        summaryPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerJList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaSummary = new javax.swing.JTextArea();
        btnDelete = new javax.swing.JButton();
        btnPrintSummary = new javax.swing.JButton();
        btnExit3 = new javax.swing.JButton();
        btnBack3 = new javax.swing.JButton();
        btnNext3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenuItem();
        mnuOrder = new javax.swing.JMenu();
        mnuClear = new javax.swing.JMenuItem();
        mnuSubmitOrder = new javax.swing.JMenuItem();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("First Name");

        jLabel2.setText("Last Name");

        jLabel3.setText("Street Address");

        jLabel4.setText("City");

        jLabel5.setText("State");

        cmBoxState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY" }));
        cmBoxState.setSelectedIndex(20);

        jLabel6.setText("Zip Code");

        btnNext1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnNext1.setText("Next");
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });

        btnBack1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBack1.setText("Back");
        btnBack1.setEnabled(false);

        btnClear1.setText("Clear Items");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        btnExit1.setText("Exit");
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customerInfoPanelLayout = new javax.swing.GroupLayout(customerInfoPanel);
        customerInfoPanel.setLayout(customerInfoPanelLayout);
        customerInfoPanelLayout.setHorizontalGroup(
            customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerInfoPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerInfoPanelLayout.createSequentialGroup()
                        .addComponent(btnClear1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerInfoPanelLayout.createSequentialGroup()
                        .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerInfoPanelLayout.createSequentialGroup()
                                .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(customerInfoPanelLayout.createSequentialGroup()
                                        .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(34, 34, 34)
                                        .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(customerInfoPanelLayout.createSequentialGroup()
                                        .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(cmBoxState, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 276, Short.MAX_VALUE))
                            .addGroup(customerInfoPanelLayout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExit1)))
                        .addContainerGap())))
        );
        customerInfoPanelLayout.setVerticalGroup(
            customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerInfoPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmBoxState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(customerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit1))
        );

        myTab.addTab("Customer's Information", customerInfoPanel);

        btnGroupFlooring.add(radWood);
        radWood.setSelected(true);
        radWood.setText("Wood Flooring");

        btnGroupFlooring.add(radCarpet);
        radCarpet.setText("Carpet Flooring");

        jLabel8.setText("Length");

        jLabel9.setText("ft");

        jLabel10.setText("in");

        jLabel11.setText("Width");

        jLabel12.setText("ft");

        jLabel13.setText("in");

        txtSubTotal.setEditable(false);

        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

        jLabel14.setText("Subtotal");

        btnSubmit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSubmit.setText("Submit Order");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnClear2.setText("Clear Items");
        btnClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear2ActionPerformed(evt);
            }
        });

        btnBack2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBack2.setText("Back");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        btnNext2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnNext2.setText("Next");
        btnNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext2ActionPerformed(evt);
            }
        });

        btnExit2.setText("Exit");
        btnExit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout orderInfoPanelLayout = new javax.swing.GroupLayout(orderInfoPanel);
        orderInfoPanel.setLayout(orderInfoPanelLayout);
        orderInfoPanelLayout.setHorizontalGroup(
            orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderInfoPanelLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit2)
                .addContainerGap())
            .addGroup(orderInfoPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderInfoPanelLayout.createSequentialGroup()
                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderInfoPanelLayout.createSequentialGroup()
                                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radCarpet)
                                    .addComponent(radWood))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(orderInfoPanelLayout.createSequentialGroup()
                                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtLengthFt)
                                            .addComponent(txtLengthIn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))))
                                .addGap(95, 95, 95)
                                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11)
                                    .addComponent(txtWidthFt)
                                    .addComponent(txtWidthIn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(orderInfoPanelLayout.createSequentialGroup()
                                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(orderInfoPanelLayout.createSequentialGroup()
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCalculate))
                                    .addComponent(jLabel14))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(220, 220, 220))
                    .addGroup(orderInfoPanelLayout.createSequentialGroup()
                        .addComponent(btnClear2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
        );
        orderInfoPanelLayout.setVerticalGroup(
            orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderInfoPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderInfoPanelLayout.createSequentialGroup()
                        .addComponent(radWood)
                        .addGap(18, 18, 18)
                        .addComponent(radCarpet))
                    .addGroup(orderInfoPanelLayout.createSequentialGroup()
                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLengthFt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtWidthFt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLengthIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtWidthIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addGap(47, 47, 47)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(orderInfoPanelLayout.createSequentialGroup()
                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCalculate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear2))
                    .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNext2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addComponent(btnExit2, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        myTab.addTab("Order Information", orderInfoPanel);

        customerJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                customerJListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(customerJList);

        txtAreaSummary.setEditable(false);
        txtAreaSummary.setColumns(20);
        txtAreaSummary.setRows(5);
        jScrollPane2.setViewportView(txtAreaSummary);

        btnDelete.setText("Delete Order");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnPrintSummary.setText("Print Summary");
        btnPrintSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintSummaryActionPerformed(evt);
            }
        });

        btnExit3.setText("Exit");
        btnExit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit3ActionPerformed(evt);
            }
        });

        btnBack3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBack3.setText("Back");
        btnBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack3ActionPerformed(evt);
            }
        });

        btnNext3.setText("Next");
        btnNext3.setEnabled(false);

        javax.swing.GroupLayout summaryPanelLayout = new javax.swing.GroupLayout(summaryPanel);
        summaryPanel.setLayout(summaryPanelLayout);
        summaryPanelLayout.setHorizontalGroup(
            summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, summaryPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addComponent(btnExit3))
            .addGroup(summaryPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(summaryPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(summaryPanelLayout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, summaryPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrintSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        summaryPanelLayout.setVerticalGroup(
            summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summaryPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(summaryPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrintSummary))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addGroup(summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(summaryPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit3))
                    .addGroup(summaryPanelLayout.createSequentialGroup()
                        .addGroup(summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNext3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 25, Short.MAX_VALUE))))
        );

        myTab.addTab("Summary List", summaryPanel);

        mnuFile.setText("File");

        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuExit);

        jMenuBar1.add(mnuFile);

        mnuOrder.setText("Order");

        mnuClear.setText("Clear All");
        mnuClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClearActionPerformed(evt);
            }
        });
        mnuOrder.add(mnuClear);

        mnuSubmitOrder.setText("Submit Order");
        mnuSubmitOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSubmitOrderActionPerformed(evt);
            }
        });
        mnuOrder.add(mnuSubmitOrder);

        jMenuBar1.add(mnuOrder);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myTab)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myTab)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        myTab.setSelectedIndex(1);
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        myTab.setSelectedIndex(0);
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void btnNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext2ActionPerformed
        myTab.setSelectedIndex(2);
    }//GEN-LAST:event_btnNext2ActionPerformed

    private void btnBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack3ActionPerformed
        myTab.setSelectedIndex(1);
    }//GEN-LAST:event_btnBack3ActionPerformed

    private void btnExit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit3ActionPerformed
        terminateApplication();
    }//GEN-LAST:event_btnExit3ActionPerformed

    private void btnExit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit2ActionPerformed
        terminateApplication();
    }//GEN-LAST:event_btnExit2ActionPerformed

    private void btnExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit1ActionPerformed
        terminateApplication();
    }//GEN-LAST:event_btnExit1ActionPerformed

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        terminateApplication();
    }//GEN-LAST:event_mnuExitActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        clearItems();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear2ActionPerformed
        clearItems();
    }//GEN-LAST:event_btnClear2ActionPerformed

    private void mnuClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClearActionPerformed
        clearAll();
    }//GEN-LAST:event_mnuClearActionPerformed

    private void customerJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_customerJListValueChanged
        showOrderDetails();
    }//GEN-LAST:event_customerJListValueChanged

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        submitOrder();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteOrder();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void mnuSubmitOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSubmitOrderActionPerformed
        submitOrder();
    }//GEN-LAST:event_mnuSubmitOrderActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        int Lfeet, Linch, Wfeet, Winch;        
        Lfeet = Integer.parseInt(txtLengthFt.getText());
        Linch = Integer.parseInt(txtLengthIn.getText());
        Wfeet = Integer.parseInt(txtWidthFt.getText());
        Winch = Integer.parseInt(txtWidthIn.getText());
        
        double result = Customer.calculateArea(Lfeet, Linch, Wfeet, Winch);
        double cost = 0.0;
        if (radWood.isSelected())
            cost = 20.0 * result;
        else if (radCarpet.isSelected())
            cost = 10.0 * result;
        txtSubTotal.setText(CF.format(cost));
    }//GEN-LAST:event_btnCalculateActionPerformed

    private void btnPrintSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintSummaryActionPerformed
        JOptionPane.showMessageDialog(null, txtAreaSummary.getText(),"Print Customer Summary", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_btnPrintSummaryActionPerformed

    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Floor_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Floor_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Floor_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Floor_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Floor_GUI mainGUI = new Floor_GUI();
                mainGUI.setVisible(true);
                mainGUI.setLocationRelativeTo(null);
                mainGUI.setTitle("Ulises's' Flooring Company");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnBack3;
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnClear2;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnExit2;
    private javax.swing.JButton btnExit3;
    private javax.swing.ButtonGroup btnGroupFlooring;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnNext2;
    private javax.swing.JButton btnNext3;
    private javax.swing.JButton btnPrintSummary;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> cmBoxState;
    private javax.swing.JPanel customerInfoPanel;
    private javax.swing.JList<Customer> customerJList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mnuClear;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuOrder;
    private javax.swing.JMenuItem mnuSubmitOrder;
    private javax.swing.JTabbedPane myTab;
    private javax.swing.JPanel orderInfoPanel;
    private javax.swing.JRadioButton radCarpet;
    private javax.swing.JRadioButton radWood;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JTextArea txtAreaSummary;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLengthFt;
    private javax.swing.JTextField txtLengthIn;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtWidthFt;
    private javax.swing.JTextField txtWidthIn;
    private javax.swing.JTextField txtZip;
    // End of variables declaration//GEN-END:variables
}
