import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

/** The JTableModel class implements the data model to be used by JTable present in TableGUI class
 * Reference: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 * Reference: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data
 * Reference: https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#modelchange
 * @author Vikram Singh - H00391053
 * @version 1.0
 */


/*Although JTableModel is a serializable class but
 * no need to define static serialization version ID as single version of the code will be used.
 */
@SuppressWarnings("serial")
public class JTableModel extends AbstractTableModel{
	
	private ArrayList <Competitors> modelList;
	private String[] columns = {"Competitor ID", "Full Name", "Age", "Level", "Game", "Country", "Score 1", "Score 2", "Score 3", "Score 4", "Score 5", "Overall Score"};
	
	@SuppressWarnings("rawtypes") // Use of raw types in method
	private final Class[] columnClass = new Class[] {
	        String.class, String.class, Integer.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Double.class
	    };
	
	/**
	 * Constructor to manage JTable data model
	 * @param list: ArrayList of competitors
	 */
	public JTableModel(ArrayList<Competitors> list) {
		this.modelList = list;
			
	}
	
	/**
	 * Get column name of mentioned column number
	 * @param col
	 * @return columns[col]
	 */
	@Override
    public String getColumnName(int col)
    {
        return columns[col];
    }
	
	/**
	 * Get column class of mentioned column number
	 * @param columnIndex
	 * @return columnClass[columnIndex]
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return columnClass[columnIndex];
	}
	
	/**
	 * Get number of columns of JTable
	 * @param void
	 * @return columns.length
	 */
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	/**
	 * Get number of rows of JTable
	 * @param void
	 * @return modelList.size()
	 */
	@Override
	public int getRowCount() {
		return modelList.size();
	}

	/**
	 * Method to take data from CompetitorList and generate table structure
	 * to be displayed by JTable
	 * @param row: row where value needs to be added in JTable
	 * @param column: column where value needs to be added in JTable
	 * @return Object: value to be added to JTable for specified column number and row number combination
	 */
	
	@Override
	public Object getValueAt(int row, int column) {
		
		Competitors t1 = modelList.get(row);
		
		switch(column) {
		case(0): {return t1.getID();}
		case(1): {return t1.getCompName().getFullName();}
		case(2): {return t1.getage();}
		case(3): {return t1.getLevel();}
		case(4): {return t1.getGame();}
		case(5): {return t1.getCountry();}
		case(6): {return t1.getScoreArray()[0];}
		case(7): {return t1.getScoreArray()[1];}
		case(8): {return t1.getScoreArray()[2];}
		case(9): {return t1.getScoreArray()[3];}
		case(10):{return t1.getScoreArray()[4];}
		case(11):{return t1.getOverallScore();} 
		}
		return null;
	}
	
	
	/**
	 * Method to specify what will happen when Data Values are changed in JTable GUI
	 * @param objValue: object for changed value in GUI
	 * @param row: row number of changed value
	 * @param column: column number of changed value
	 * @return void
	 */
	@Override
	public void setValueAt(Object objValue, int row, int column) {
		
		// Get competitors object by specifying row of JTable 
		Competitors t1 = modelList.get(row);
		
		// Checking which column's value has changed and corresponding steps to perform
		switch(column) {
		case(1): {
			t1.setCompName(new CompetitorName ((String) objValue));
			break;
			}
		case(2): {
			// Age should be more than 0
			if ((Integer) objValue <= 0) {break;}
			t1.setAge((Integer) objValue);
			break;
			}
		case(3): {
			t1.setLevel((String) objValue);
			break;
			}
		case(5): {
			t1.setCountry((String) objValue);
			break;}
		case(6): {
			t1.SetScoreArray((Integer) objValue, (column - 6));
			this.fireTableCellUpdated(row, 11);
			break;}
		case(7): {
			t1.SetScoreArray((Integer) objValue, (column - 6));
			this.fireTableCellUpdated(row, 11);
			break;}
		case(8): {
			t1.SetScoreArray((Integer) objValue, (column - 6));
			this.fireTableCellUpdated(row, 11);
			break;}
		case(9): {
			t1.SetScoreArray((Integer) objValue, (column - 6));
			this.fireTableCellUpdated(row, 11);
			break;}
		case(10): {
			t1.SetScoreArray((Integer) objValue, (column - 6));
			this.fireTableCellUpdated(row, 11);
			break;}		
		}			
		
	}
	
	
	/**
	 * Method to make cells editable
	 * Making competitor Number, game and overall score to be non editable columns
	 * @param rowIndex: row numnber of cell
	 * @param columnIndex: column number of cell
	 * @return boolean
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{		
		ArrayList <Integer> colCheck = new ArrayList<Integer>();
		colCheck.add(0);
		colCheck.add(4);
		colCheck.add(11);
		
		if (colCheck.contains(columnIndex)) {return false;}
		else {return true;}
	}
	
	
}