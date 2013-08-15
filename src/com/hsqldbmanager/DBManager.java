package com.hsqldbmanager;

import java.awt.EventQueue;
import java.sql.SQLException;

import com.hsqldbmanager.database.DatabaseConnector;
import com.hsqldbmanager.view.MainScreen;

public class DBManager {

	private static DatabaseConnector db = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		connect();
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void connect() {
	
		try {
		    db  = new DatabaseConnector("Database/db_file");
		} catch (Exception ex1) {
		    ex1.printStackTrace();    // could not start db
		
		    return;                   // bye bye
		}
		
//		try {
//		
//		    //make an empty table
//		    //
//		    // by declaring the id column IDENTITY, the db will automatically
//		    // generate unique values for new rows- useful for row keys
//		    db.update(
//		        "CREATE TABLE sample_table ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER)");
//		} catch (SQLException ex2) {
//		
//		    //ignore
//		    //ex2.printStackTrace();  // second time we run program
//		    //  should throw execption since table
//		    // already there
//		    //
//		    // this will have no effect on the db
//		}
		
//		try {
//		
//		    // add some rows - will create duplicates if run more then once
//		    // the id column is automatically generated
//		    db.update("INSERT INTO sample_table(str_col,num_col) VALUES('Ford', 100)");
//		    db.update("INSERT INTO sample_table(str_col,num_col) VALUES('Toyota', 200)");
//		    db.update("INSERT INTO sample_table(str_col,num_col) VALUES('Honda', 300)");
//		    db.update("INSERT INTO sample_table(str_col,num_col) VALUES('GM', 400)");
//		
//		    // do a query
//		    db.query("SELECT * FROM sample_table WHERE num_col < 250");
//		
//		    // at end of program
//		    db.shutdown();
//		} catch (SQLException ex3) {
//		    ex3.printStackTrace();
//		}
		
	}

	public static void CloseConnection() {
	    try {
	    	
			db.shutdown();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
