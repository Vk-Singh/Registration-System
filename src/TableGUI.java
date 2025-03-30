//import all the GUI classes
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.table.*;


/** The TableGUI class uses the JTableModelclass to create a table GUI
 * Reference: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 * Reference: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#combobox
 * Reference: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#sorting
 * Reference: http://www.fredosaurus.com/notes-java/GUI/components/50radio_buttons/25radiobuttons.html
 * Extends JFrame
 * Implements ActionListener interface for JButtons, ItemListener interface for radio buttons and 
 * DocumentListener interface for reading text changes while filtering rows
 * @author Vikram Singh - H00391053
 * @version 1.0
 */

/*Although TableGUI is a serializable class but
 * no need to define static serialization version ID as single version of the code will be used.
 */
@SuppressWarnings("serial")
public class TableGUI extends JFrame implements ActionListener, ItemListener, DocumentListener{
	
	private CompetitorList compList;

	//GUI components
    private JTextField searchField, filterField;
    private JScrollPane scrollList;
    private JButton search, close, help ;
    private JTable detailsJTable;
    private JRadioButton dotaBtn, footballBtn, boxingBtn, allBtn, professionalBtn, masterBtn, juniorBtn;
    private ButtonGroup group;
    private TableRowSorter<TableModel> sorter;
     
    /**
     * Constructor
     * @param list: competitor list
     */
    public TableGUI(CompetitorList list) {
    	this.compList = list;
    	//set up window title
        setTitle("Competitors List");
        
        /*
         * Disable standard close button and setup window listener on JFrame
         */
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		addWindowListener(new JFrameClose(compList));
		
		/**
		 * Calling method to create JTable 
		 */
		createTable();
		
		// Creating different panels of JFrame
		setupSouthPanel();
		setupNorthPanel();
		setupCenterPanel(detailsJTable);
				 
        //pack and set visible
		this.setPreferredSize(new Dimension(1200, 600));
        pack();
        setVisible(true);  
        
        // Method to show prompt at the beginning containing JTable editing steps
        helpPrompt();
    }
    
    
    /**
     * Method to create JTable using JTableModel class as data model
     * @param void
     * @return void
     */
    private void createTable() {
    	
    	JTableModel compModel = new JTableModel(compList.getCompList());
    			
    	detailsJTable = new JTable(compModel);
    	detailsJTable.setFillsViewportHeight(true);
    	// Stopping reordering of columns  
    	detailsJTable.getTableHeader().setReorderingAllowed(false);
    	
    	// Sorting Based on Every column
    	detailsJTable.setAutoCreateRowSorter(true);
    	sorter = new TableRowSorter<TableModel>(compModel);
    				
    			
    	TableColumnModel columnModel = detailsJTable.getColumnModel();
    	columnModel.getColumn(0).setMinWidth(60);
    	columnModel.getColumn(1).setMinWidth(200);
    	
    	/*
    	 * Defining different Combo boxes and adding to JTable columns 
    	 */
    	
    	// comboBox for competitor levels
    	TableColumn levelColumn = columnModel.getColumn(3);
    	JComboBox<String> levelComboBox  = new JComboBox<String>();
    	levelComboBox.addItem("Junior");
    	levelComboBox.addItem("Master");
    	levelComboBox.addItem("Professional");	
    	levelColumn.setCellEditor(new DefaultCellEditor(levelComboBox));
    	
    	//ComboBox for scores (from 0 to 5 both inclusive)
    	JComboBox<Integer> scoreComboBox  = new JComboBox<Integer>();
    	scoreComboBox.addItem(0);
    	scoreComboBox.addItem(1);
    	scoreComboBox.addItem(2);
    	scoreComboBox.addItem(3);
    	scoreComboBox.addItem(4);
    	scoreComboBox.addItem(5);
    	
    	TableColumn scoreColumn1 = columnModel.getColumn(6);
    	scoreColumn1.setCellEditor(new DefaultCellEditor(scoreComboBox));
    			
    	TableColumn scoreColumn2 = columnModel.getColumn(7);
    	scoreColumn2.setCellEditor(new DefaultCellEditor(scoreComboBox));
    			
    	TableColumn scoreColumn3 = columnModel.getColumn(8);
    	scoreColumn3.setCellEditor(new DefaultCellEditor(scoreComboBox));
    			
    	TableColumn scoreColumn4 = columnModel.getColumn(9);
    	scoreColumn4.setCellEditor(new DefaultCellEditor(scoreComboBox));
    			
    	TableColumn scoreColumn5 = columnModel.getColumn(10);
    	scoreColumn5.setCellEditor(new DefaultCellEditor(scoreComboBox));			
    }
    
    
    /**
     * Method to design the center panel of JFrame
     * @param table: Jtable to be added to center panel of JFrame
     */
    private void setupCenterPanel(JTable table) {
    	
    	JPanel panel = new JPanel();
    	Border etchedBdr = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etchedBdr, "Competitor Details");
        panel.setBorder(titled);
       
        // creating a scroll pane and adding JTable to it
        scrollList = new JScrollPane(table);        
        panel.add(scrollList);
        this.add(scrollList,BorderLayout.CENTER);
       
    }

    /**
     * Method to design north panel of JFrame
     * Different sub panels are created (viewPanel, radioPanel, filterPanel, btnPanel) and joined together
     * to make northPanel
     * @param void
     */
	private void setupNorthPanel() {
		 //add north panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,1));
        
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new GridLayout(1,3));
 
        /**
         * 2 Radio panels which contain radio buttons for name of games and competitor's levels.
         * Each radio button will have hover tool tip text attached.
         * Item listener attached to radio buttons.
         */
        JPanel radioPanel = new JPanel();
        JPanel radioPanel2 = new JPanel();
        
        allBtn = new JRadioButton("ALL");
        allBtn.setSelected(true);
        allBtn.setToolTipText("Select this to view All Competitors");
        allBtn.addItemListener(this);
        
        dotaBtn = new JRadioButton("Dota 2");
        dotaBtn.setToolTipText("Select this to view only Dota 2 Competitors");
        dotaBtn.addItemListener(this);
        
        footballBtn = new JRadioButton("Football");
        footballBtn.setToolTipText("Select this to view only Football Competitors");
        footballBtn.addItemListener(this);
        
        boxingBtn = new JRadioButton("Boxing");
        boxingBtn.setToolTipText("Select this to view only Boxing Competitors");
        boxingBtn.addItemListener(this);
        
        professionalBtn = new JRadioButton("Level: Professional");
        professionalBtn.setToolTipText("Select this to view competitors having level 'Professional'");
        professionalBtn.addItemListener(this);
        
        masterBtn = new JRadioButton("Level: Master");
        masterBtn.setToolTipText("Select this to view competitors having level 'Master'");
        masterBtn.addItemListener(this);
        
        juniorBtn = new JRadioButton("Level: Junior");
        juniorBtn.setToolTipText("Select this to view competitors having level 'Junior'");
        juniorBtn.addItemListener(this);
        
        /**
         * Create Radio button group 
         * Add all radio buttons to button group so that only one radio button is pressed at a time
         */
        group = new ButtonGroup();
        group.add(dotaBtn);
        group.add(footballBtn);
        group.add(boxingBtn);
        group.add(allBtn);
        group.add(professionalBtn);
        group.add(masterBtn);
        group.add(juniorBtn);
        
        
        radioPanel.add(allBtn);
        radioPanel.add (dotaBtn);
        radioPanel.add(footballBtn);
        radioPanel.add(boxingBtn);
        radioPanel2.add(professionalBtn);
        radioPanel2.add(masterBtn);
        radioPanel2.add(juniorBtn);
                      
        
        /**
         * Add Jlabel and Both radio panels to view panel
         */
        viewPanel.add(new JLabel("                   View Sub-Categories"));
        viewPanel.add(radioPanel);
        viewPanel.add(radioPanel2);
        
        /**
         * Create Filter Panel which will have buttons and text box which will filter table data
         * using document listener
         */
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(1,3));
        filterField = new JTextField(25);
        filterField.setEditable(true);
        filterField.getDocument().addDocumentListener(this);
        
        help = new JButton("Help");
        help.addActionListener(this);    
        
        close = new JButton("Close");
        close.addActionListener(this);
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(help);
        btnPanel.add(close);
        
        filterPanel.add(new JLabel ("                   Enter Filter Criteria"));
        filterPanel.add(filterField); 
        filterPanel.add(btnPanel);
               
        northPanel.add(viewPanel);
        northPanel.add(filterPanel);
        this.add(northPanel, BorderLayout.NORTH);
		
	}

	
	/**
	 * Method to design south panel of JFrame
	 * @param void
	 */
	private void setupSouthPanel() {
		 //search panel contains label, text field and button
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1,3));
        searchPanel.add(new JLabel("Enter ID"));   
        searchField = new JTextField(5);
        searchPanel.add(searchField);   
        search = new JButton("Search");  
        searchPanel.add(search);    
        //specify action when button is pressed
        search.addActionListener(this) ;
       
        /*
        //Set up the area where the results will be displayed.
        result= new JTextField(25);     
        result.setEditable(false);
        */
        //set up south panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,1));
        southPanel.add(searchPanel);
       // southPanel.add(result);
        
        //add south panel to the content pane
        this.add(southPanel, BorderLayout.SOUTH);
		
	}

	
	
	/**
	 * Method to specify actions on button pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == search) {
    		search();
    	}
    	else if (e.getSource() == close) {
    		// generate report and close
    		compList.generateReport();
    		System.exit(0);
    	}
    	else if(e.getSource() == help) {
    		// show help prompt 
    		helpPrompt();}
		
	}

	/**
	 * Method to search entered competitor number and show full details
	 */
	private void search() {
    	//get search text and search staff list
    	//setting result text 
        String searchString = searchField.getText().trim();
        if(searchString.length() > 0) {
        	// searching in competitor List on competitor number
            Competitors person = compList.findByID(searchString);
            if (person != null ) {
            	JOptionPane.showMessageDialog(this,person.getFullDetails());
            }
            else
            	JOptionPane.showMessageDialog(this,"Invalid competitor Number.\nPlease try again.");
        }   
        else
        	JOptionPane.showMessageDialog(this,"No text entered.\nPlease try again.");
    }
	
	/**
	 * Method to show help prompt to users
	 * @param void
	 */
	private void helpPrompt() {
		JOptionPane.showMessageDialog(this, "1. Double click on Table Values to edit.\n"
				+ "2. Competitor Number, Game and Overall Score can't be changed manually.\n"
				+ "3. All changes will be updated in report as well.\n"
				+ "4. Overall Score will be updated real time.\n"
				+ "5. Click on Column headers to sort table on column values\n"
				+ "6. Click on radio buttons to filer table.\n"
				+ "7. Enter text in filter text field to filter based based on keywords\n"
				+ "8. Search competitors by entering competitor number in search field");
		
	}

	/**
	 * Method to specify radio buttons item state change actions
	 * @param e: radio button Item event e 
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		int stateChange = e.getStateChange();
		/*
		 * Making radio buttons state to be changed to "ALL" radio button selected  when filter text field is used.
		 * CLear the filter field text when radio buttons are used and there is something written in filter field		
		 */
		if((filterField.getText().trim().length() !=0) && e.getSource() != allBtn) {
			if (stateChange == ItemEvent.SELECTED) {
			filterField.setText(null);
			}
		}
		
		// Filtering the table when radio buttons are pushed using TableRowSorter "sorter" variable
		if (e.getSource() == dotaBtn) {
			if (stateChange == ItemEvent.SELECTED) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "Dota2"));
				detailsJTable.setRowSorter(sorter);
			}
		}else if(e.getSource() == footballBtn) {
			if (stateChange == ItemEvent.SELECTED) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "Football"));	
				detailsJTable.setRowSorter(sorter);	
			}
		}else if(e.getSource() == boxingBtn){
			if (stateChange == ItemEvent.SELECTED) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "Boxing"));
				detailsJTable.setRowSorter(sorter);
			}
		}else if(e.getSource() == allBtn) {
			if (stateChange == ItemEvent.SELECTED) {
				sorter.setRowFilter(null);
				detailsJTable.setRowSorter(sorter);
			}
		
		}else if(e.getSource() == professionalBtn) {
			if (stateChange == ItemEvent.SELECTED) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "Professional"));
				detailsJTable.setRowSorter(sorter);
			}
		}
		else if(e.getSource() == masterBtn) {
			if (stateChange == ItemEvent.SELECTED) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "Master"));
				detailsJTable.setRowSorter(sorter);
			}
		}else if(e.getSource() == juniorBtn) {
			if (stateChange == ItemEvent.SELECTED) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "Junior"));
				detailsJTable.setRowSorter(sorter);
			}
		}
	}
	
	
	/**
	 * Method to filter the table using TableRowSorter "sorter" variable 
	 * Make "All" radio button as selected
	 */
	private void filter() {
		
		// Read text from filter field
		String str = filterField.getText();
		if(str.trim().length() == 0) {
			sorter.setRowFilter(null);
		}else {
			allBtn.setSelected(true);
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
			detailsJTable.setRowSorter(sorter);
		}				
	}
	
	/**
	 * Interface implementation method for Document event listener
	 * Method specifies what happens when text is added to filter field
	 * @param e: Document Event
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		filter();
	}
	
	/**
	 * Interface implementation method for Document event listener
	 * Method specifies what happens when text is removed from filter field
	 * @param e: Document Event
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		filter();
	}
	
	/**
	 * Interface implementation method for Document event listener
	 * Method specifies what happens when text is changed in filter field
	 * @param e: Document Event
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		filter();	
	}
}
