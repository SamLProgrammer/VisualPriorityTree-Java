package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import control.Actions;

public class MyDialog extends JDialog{

	JTextField dataField;
	JButton okButton;
	
	public MyDialog(ActionListener listener) {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenDimension.getWidth()/3, (int)screenDimension.getHeight()/2);
		setLayout(new GridLayout(2, 1));
		initComponents(listener);
		locate();
	}
	
	private void initComponents(ActionListener listener) {
		dataField = new JTextField();
		okButton = new JButton("ADD");
		okButton.addActionListener(listener);
		okButton.setActionCommand(Actions.OK_ADD_BUTTON.name());
		add(dataField);
		add(okButton);
	}
	
	private void locate() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dimension.getWidth()/2 - this.getWidth()/2), (int)(dimension.getHeight()/2 - this.getHeight()/2));
	}

	public JTextField getDataField() {
		return dataField;
	}	
	
}