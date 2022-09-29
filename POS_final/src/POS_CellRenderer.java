import java.awt.Color;
import java.awt.Component;
import java.security.PublicKey;
import java.security.cert.CertPathParameters;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class POS_CellRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			 if (!isSelected) 
				 {
					 if (row % 2 == 0) 
					 {
						 cell.setBackground(Color.GREEN); 
					 }
					 else
					 {
						 cell.setBackground(Color.WHITE);
					 }
				 }
			 return cell;
			 
			 }
	
		
}
