package com.hsqldbmanager.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.hsqldbmanager.DBManager;
import com.hsqldbmanager.database.QueryHandler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panelHead;
	private JTextArea textArea;
	private JPanel panelCenterTable;
	private JPanel panelFooter;
	private JLabel label;
	private JScrollPane textAreaScrollPane;
	private JButton btnExecute;

	

	/**
	 * Create the frame.
	 */
	public MainScreen() {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		panelHead = new JPanel();
		contentPane.add(panelHead, BorderLayout.NORTH);
		GridBagLayout gbl_panelHead = new GridBagLayout();
		gbl_panelHead.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelHead.rowWeights = new double[]{1.0};
		panelHead.setLayout(gbl_panelHead);
		
		textArea = new JTextArea(4, 30);
		textArea.setTabSize(4);
		textAreaScrollPane = new JScrollPane(textArea);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		panelHead.add(textAreaScrollPane, gbc_textArea);
		
		btnExecute = new JButton("Execute");
		GridBagConstraints gbc_btnExecute = new GridBagConstraints();
		gbc_btnExecute.fill = GridBagConstraints.VERTICAL;
		gbc_btnExecute.gridx = 1;
		gbc_btnExecute.gridy = 0;
		panelHead.add(btnExecute, gbc_btnExecute);
		
		panelCenterTable = new JPanel();
		contentPane.add(panelCenterTable, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenterTable = new GridBagLayout();
		gbl_panelCenterTable.columnWidths = new int[] {0};
		gbl_panelCenterTable.rowHeights = new int[] {0};
		gbl_panelCenterTable.columnWeights = new double[]{1.0};
		gbl_panelCenterTable.rowWeights = new double[]{1.0};
		panelCenterTable.setLayout(gbl_panelCenterTable);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		panelCenterTable.add(table, gbc_table);
		
		panelFooter = new JPanel();
		contentPane.add(panelFooter, BorderLayout.SOUTH);
		GridBagLayout gbl_panelFooter = new GridBagLayout();
		gbl_panelFooter.columnWidths = new int[]{0, 0};
		gbl_panelFooter.rowHeights = new int[]{0, 0};
		gbl_panelFooter.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelFooter.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelFooter.setLayout(gbl_panelFooter);
		
		label = new JLabel("...");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panelFooter.add(label, gbc_label);
		setLocationRelativeTo(null);
		
		addEventListenersToComponents();
	}

	private void addEventListenersToComponents() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				DBManager.CloseConnection();
			}
		});
		
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				actionPerformedOnBtnExecute(arg);
			}
		});
	}

	private void actionPerformedOnBtnExecute(ActionEvent e){
		QueryHandler.DoQuery(textArea.getText());
	}
}
