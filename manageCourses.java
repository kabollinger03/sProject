
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author syntel
 */
public class manageCourses {
    private String courseId;
    private String courseName;
    private String moduleId;
    private String streamId;
    private int choice;
    private Scanner userInput = new Scanner(System.in).useDelimiter("\\n");
    
    public void userOptionsForCourses(Statement st){
        do{
            System.out.println("Please choose an option to manage courses: ");
            System.out.println("1. Create a course");
            System.out.println("2. Retrieve/Search a course");
            System.out.println("3. Update a course");
            System.out.println("4. Delete a course");
            System.out.println("5. Quit Managing Courses");
            choice = userInput.nextInt();
        }while(choice < 1 || choice > 5);

        if(choice == 1){
            System.out.println("To create a course please provide the information asked for below:");
            //System.out.println("Please enter the course ID: ");
            //courseId = userInput.next();
            
            streamId = viewStreams(st);
            moduleId = viewModules(st, streamId);
            
            System.out.println("Please enter the course name: ");
            courseName = userInput.next();
            
            //System.out.println("Please enter the module ID: ");
            courseId = genCourseId(streamId, moduleId, courseName);
            
            System.out.println("GENERATED MODULE ID + " + courseId);
            
            createCourse(st, courseId, courseName, moduleId);
        }
        else if(choice == 2){
            System.out.println("To retrieve or search for a course please provide the information asked for below:");
            System.out.println("Please enter the course ID: ");
            courseId = userInput.next();
            
            readCourse(st, courseId);
        }
        else if(choice == 3){
            System.out.println("To update the course name please provide the information asked for below:");
            System.out.println("Please enter the course ID: ");
            courseId = userInput.next();
            
            System.out.println("Please enter the course name: ");
            courseName = userInput.next();
             
            updateCourse(st, courseId, courseName);
        }
        else if(choice == 4){
            System.out.println("To delete a course please provide the information asked for below:");
            System.out.println("Please enter the course ID: ");
            courseId = userInput.next();
            
            deleteCourse(st, courseId);
        }
        else if(choice == 5){
            return;
        }
    }
    
    
    public manageCourses() {
        this.courseId = "";
        this.courseName = "";
        this.moduleId = "";
    }

    
    public manageCourses(String courseId, String courseName, String moduleId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.moduleId = moduleId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    
    public void createCourse(Statement st, String courseId, String courseName, String moduleId){
        
        System.out.println("IN CREATE COURSES");
        
        try{
            st.executeUpdate("INSERT INTO courses VALUES('"+courseId+"', '"+courseName+"', "+moduleId+")");
            System.out.println("course inserted."); //Take this line out if you don't want a confirmation message
        }
        catch (Exception e){
                e.printStackTrace();
        }
    }
         
        
    public void readCourse(Statement st, String courseId){
        try {
                System.out.println("Retrieving course");
                ResultSet rs = st.executeQuery("select course_id, course_name, module_id from Student_Performance.Courses WHERE course_id='" + courseId + "'");
                while(rs.next())
                        System.out.println("Result: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
        }
    }
    
    public void updateCourse(Statement st, String courseId, String courseName){
        try {
                System.out.println("Update a course");
                st.executeUpdate("UPDATE Student_Performance.Courses SET course_name ='" + courseName + "'WHERE course_id ='" + courseId + "'");
                System.out.println("Course updated");
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
        }
    }
    
    public void deleteCourse(Statement st, String courseId){
        try {
                System.out.println("Delete course");
                st.executeUpdate("DELETE FROM Student_Performance.Courses WHERE course_id = '" + courseId + "'");
                System.out.println("Course deleted");
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
        }
    }
    
    private String genCourseId(String streamId, String moduleId, String courseName){
        String generatedID = null;

        generatedID = (streamId.toUpperCase() + moduleId.toUpperCase() + courseName.toLowerCase().replaceAll("\\s+",""));
      
        return generatedID;
    }
    
    public String viewStreams(Statement st)
    {
        try {
                System.out.println("Retrieving streams");
                ResultSet rs = st.executeQuery("select stream_id, stream_name from Student_Performance.Stream");
                while(rs.next())
                        System.out.println("Result: " + rs.getString(1) + " " + rs.getString(2));
                
                System.out.println("Please choose the Stream for your course to be added into: ");
                streamId = userInput.next();                
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
                streamId = "";
        }
        
        return streamId;
    }
    
    public String viewModules(Statement st, String streamId)
    {
        try {
                System.out.println("Retrieving modules");
                ResultSet rs = st.executeQuery("select module_id, module_name, stream_id from Student_Performance.Modules WHERE stream_id='" + streamId + "'");
                while(rs.next())
                        System.out.println("Result: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                
                System.out.println("Please choose the Module for your course to be added into: ");
                moduleId = userInput.next();                               
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
                moduleId = "";
        }
        return moduleId;
    }
    
    @Override
    public String toString() {
        return "manageCourses{" + "courseId=" + courseId + ", courseName=" + courseName + ", moduleId=" + moduleId + '}';
    }   
}
