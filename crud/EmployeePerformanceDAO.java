package sProject.crud;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Employee Performance Database Access Object
 * Provides methods to perform CRUD operations on Users, Employees, Classes, etc.
 */
public class EmployeePerformanceDAO {
	
	private final String dbName = "Student_Performance"; // we'll always be working with this DB
	private Connection connection;
        private Statement stment; // generic statement
	private PreparedStatement prepStment; // prepared statement to be reused
	private CallableStatement callStment; // callable statement for procedure, function calls
	
	
	public EmployeePerformanceDAO() {
            String oracleLocalhost = "jdbc:oracle:thin:@localhost:1521:XE";

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // store the connection to the DB
                connection = DriverManager.getConnection(oracleLocalhost, dbName, dbName);

                stment = connection.createStatement();
                prepStment = null; // only set right before it's executed
                callStment = null;
                
            }catch (Exception e) {
                e.printStackTrace();
            }
	}
	
        // User CRUD wrapper functions
        public void insertUser(String userID, String pswd, String isAdmin) {
            userCRUD.insertUser(stment, userID, pswd, isAdmin); 
        }
        
        public void deleteUser(String userId) {
            userCRUD.deleteUser(stment, userId);
        }
        
        public void updateUser(String userId, String pswd, String isAdmin) {
            userCRUD.updateUser(stment, userId, pswd, isAdmin);
        }
        
        // TODO: should return a User object
        public void retrieveUser(String userId) {
            userCRUD.retrieveUser(stment, userId);
        }

       
        
        /*
         * Employee CRUD wrapper functions 
         */
        public void insertEmployee(String employee_id, String name, String email, String class_id) {
            EmployeeCRUD.insertEmployee(stment, employee_id, name, email, class_id);
        }
        
        // TODO: should return a Employee object
        public void retrieveEmployee(String employee_id) {
            EmployeeCRUD.readEmployee(stment, employee_id);
        }
        
        public void updateEmployee(String employee_id, String name, String email) {
            EmployeeCRUD.updateEmployee(stment, employee_id, name, email);
        }
        
        public void deleteEmployee(String employee_id) {
            EmployeeCRUD.deleteEmployee(stment, employee_id);
        }
        
        
        /*
         * Class CRUD wrapper functions 
         */
        public void insertClass(String class_id, String stream_id, String user_id) {
            ClassCRUD.insertClass(stment, class_id, stream_id, user_id);
        }
        // TODO: should return a Class object
        public void retrieveClass(String class_id) {
            ClassCRUD.readClass(stment, class_id);
        }
        
        public void updateClass(String class_id, String stream_id, String user_id) {
            ClassCRUD.updateClass(stment, class_id, stream_id, user_id);
        }
        
        public void deleteClass(String class_id) {
            ClassCRUD.deleteClass(stment, class_id);
        }        
        
        // 
        public ArrayList<String> getModuleNamesForStream(String streamID) {
            return chrisModuleCRUD.getModuleNamesForStream(stment, streamID);
        }
        
        
        public boolean streamExists(String ID) {
            return chrisStreamCRUD.streamExists(stment, ID);
        }
        
        public Connection getConnection() {
            return connection;
        }
        
        public Statement getStment() {
            return stment;
        }
	
	// CRUD operations can be wrapper functions around CRUD classes with static functions
	// OR
	// CRUD classes can be members of this class

	
	// STATIC FUNCTIONS EXAMPLE
	// UserCRUD class
	//		static void addUser(User newUser, PreparedStatemt st, Connection conn)
	//
	// EmployeePerformanceDAO
	//		void addUser(User user) {
	//			UserCRUD.addUser(user, prepStment, connection);
	//		}
	
	
	// CRUD CLASSES AS MEMBERS EXAMPLE
	// UserCRUD class
	//		void addUser(User newUser, PreparedStatement st, Connection conn)
	//
	// EmployeePerformanceDAO
	// 		private UserCRUD userCRUD
	//
	//		void addUser(User newUser)
	//			userCRUD.addUser(newUser, prepStment, connection)



}
