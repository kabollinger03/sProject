<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.sql.*" %>

<%
  
  //initialize driver class
  try {    
    Class.forName("oracle.jdbc.driver.OracleDriver");
  } catch (Exception e) {
    out.println("Fail to initialize Oracle JDBC driver: " + e.toString() + "<P>");
  }
  
  String dbUser = "Student_Performance";
  String dbPasswd = "Student_Performance";
  String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";

  //connect
  Connection conn = null;
  try {
    conn = DriverManager.getConnection(dbURL,dbUser,dbPasswd);
    //out.println(" Connection status: " + conn + "<P>");
  } catch(Exception e) {
    out.println("Connection failed: " + e.toString() + "<P>");      
  }

  String sql;
  int numRowsAffected;
  Statement stmt = conn.createStatement();
  ResultSet rs;
  
  // insert
  /*try {
    
    sql = "insert into users values ('chris@syntelinc.com', 'password', 'N')";
    numRowsAffected = stmt.executeUpdate(sql);
    out.println(numRowsAffected + " user(s) inserted. <BR>");
  
  } catch (SQLException e) {
    
    out.println("Error encountered during row insertion for employee: " + e.toString() + "<BR>");
  
  }*/
  
  
  // select
  sql = "select user_id from users";
  rs = stmt.executeQuery(sql);
  
  ArrayList usersList = new ArrayList();
  request.setAttribute("usersList", usersList);
  
  while (rs.next()) {
        usersList.add(rs.getString("user_id"));
        //out.println("User Id = " + rs.getString("user_id") + "<BR>"); 
        } // End while 
  
   out.println("<P>");
 
  // delete
  /* try {
    sql = "delete from users";
    numRowsAffected = stmt.executeUpdate(sql);
    out.println(numRowsAffected + " user(s) deleted. <BR>");
  } catch (SQLException e) {

    out.println("Error encountered during deletion of employee: " + e.toString() + "<BR>");
  
  }  

  out.println("<P>"); */
  
  rs.close();
  stmt.close();

  //commit
  conn.commit();
  
  //disconnect
  conn.close();
  
%>  

<HTML>
<BODY>
 <form name="registerform" action="javascript:pre()"> 
  <div class="container"> 
      
<div class="input-group mb-3">
  <div class="input-group-prepend">
  <label class="input-group-text" for="inputGroupSelect01">Users</label>
  </div>
   <select class="custom-select" name= "users" id="users">
       <c:forEach items="${usersList}" var="user">
           <option value="${user}">
               ${user}
           </option>
       </c:forEach>
   </select>
</div> 

<label for="a1"><b>Choose who you want to email:</b></label>
    <div class="btn-group-toggle" data-toggle="buttons">
  	<label class="btn btn-secondary active">
        <c:forEach items="${usersList}" var="user">
           <input type="checkbox" value="${user}">
           ${user} <br>
        </label>
        </c:forEach>
    </div>

    <button type="submit" value="Submit" class="btn btn-danger">Register</button>
    <button type="reset" value="Reset" class="btn btn-danger">Reset</button>
    
</div>
</form>

</BODY>
</HTML>