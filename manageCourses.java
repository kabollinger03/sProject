package AdminPages;

import java.sql.ResultSet;
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
    private int choice;
    private Scanner userInput;
    
    public void userOptionsForCourses(Statement st){
        System.out.println("Please choose an option to manage courses: ");
        System.out.println("1. Create a course");
        System.out.println("2. Retrieve/Search a course");
        System.out.println("3. Update a course");
        System.out.println("4. Delete a course");
        System.out.println("5. Quit Managing Courses");
        choice = userInput.nextInt();
        
        while(choice < 1 || choice > 5)
        {
            System.out.println("Please make a choice between 1 and 5");
            System.out.println("Please choose an option to manage courses: ");
            System.out.println("1. Create a course");
            System.out.println("2. Retrieve/Search a course");
            System.out.println("3. Update a course");
            System.out.println("4. Delete a course");
            System.out.println("5. Quit Managing Courses");
            choice = userInput.nextInt();
        }

        if(choice == 1){
            System.out.println("To create a course please provide the information asked for below:");
            System.out.println("Please enter the course ID: ");
            courseId = userInput.next();
            
            System.out.println("Please enter the course name: ");
            courseName = userInput.next();
            
            System.out.println("Please enter the module ID: ");
            moduleId = userInput.next();
            
            createCourse(st, courseId, courseName, moduleId);
        }
        else if(choice == 2){
            System.out.println("To retrieve or search for a course please provide the information asked for below:");
            System.out.println("Please enter the course ID: ");
            courseId = userInput.next();
            
            readCourse(st, courseId);
        }
        else if(choice == 3){
            System.out.println("To update a course please provide the information asked for below:");
            System.out.println("Please enter the course ID: ");
            courseId = userInput.next();
            
            System.out.println("Please choose what you would like to update for the course: ");
            System.out.println("1. Update Course name");
            System.out.println("2. Update Module ID");
            System.out.println("3. Update both Course name and Module ID");
            choice = userInput.nextInt();
            
            if(choice == 1)
            {
                System.out.println("Please enter the course name: ");
                courseName = userInput.next();
            }
            else if(choice == 2)
            {
                System.out.println("Please enter the module ID: ");
                moduleId = userInput.next();
            }
            else if(choice == 3){
                System.out.println("Please enter the course name: ");
                courseName = userInput.next();
                System.out.println("Please enter the module ID: ");
                moduleId = userInput.next();
            }             

            updateCourse(st, courseId, courseName, moduleId);
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
    
    
    public manageCourses(Scanner sc) {
        userInput = sc;
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
    
    public void updateCourse(Statement st, String courseId, String courseName, String moduleId){
        try {
                System.out.println("Update a course");
                st.executeUpdate("UPDATE Student_Performance.Courses SET course_name ='" + courseName + "', module_id='" + moduleId + "'WHERE course_id ='" + courseId + "'");
                System.out.println("Course updated");
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
        }
    }
    
    public void deleteCourse(Statement st, String courseId){
        try {
                System.out.println("Delete course");
                st.executeUpdate("DELETE FROM Student_Performance.Class WHERE class_id = '" + courseId + "'");
                System.out.println("Course deleted");
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
        }
    }
    
    
    @Override
    public String toString() {
        return "manageCourses{" + "courseId=" + courseId + ", courseName=" + courseName + ", moduleId=" + moduleId + '}';
    }
    
    
}
