CREATE USER Student_Performance IDENTIFIED BY Student_Performance;
GRANT CONNECT, RESOURCE, DBA To Student_Performance;
connect Student_Performance/Student_Performance;

-- -----------------------------------------------------
-- Table Student_Performance.Users
-- -----------------------------------------------------
CREATE TABLE Student_Performance.Users (
  user_id VARCHAR2(100) NOT NULL,
  password VARCHAR2(30) NULL,
  isAdmin VARCHAR2(1) NULL,
  PRIMARY KEY (user_id));


-- -----------------------------------------------------
-- Table Student_Performance.Stream
-- -----------------------------------------------------
CREATE TABLE Student_Performance.Stream (
  stream_id VARCHAR2(40) NOT NULL,
  stream_name VARCHAR2(50) NULL,
  PRIMARY KEY (stream_id));

-- -----------------------------------------------------
-- Table Student_Performance.Class
-- -----------------------------------------------------
CREATE TABLE Student_Performance.Class (
  class_id VARCHAR2(30) NOT NULL,
  stream_id VARCHAR2(40) NOT NULL,
  user_id VARCHAR2(100) NOT NULL,
  PRIMARY KEY (class_id)
 ,
  CONSTRAINT fk_Class_Stream1
    FOREIGN KEY (stream_id)
    REFERENCES Student_Performance.Stream (stream_id)
   ,
  CONSTRAINT fk_Class_Users1
    FOREIGN KEY (user_id)
    REFERENCES Student_Performance.Users (user_id)
   );

-- -----------------------------------------------------
-- Table Student_Performance.Employees
-- -----------------------------------------------------
CREATE TABLE Student_Performance.Employees (
  employee_id VARCHAR2(10) NOT NULL,
  name VARCHAR2(100) NULL,
  email VARCHAR2(100) NULL,
  class_id VARCHAR2(30) NOT NULL,
  PRIMARY KEY (employee_id)
 ,
  CONSTRAINT fk_Students_Class1
    FOREIGN KEY (class_id)
    REFERENCES Student_Performance.Class (class_id)
   );

-- -----------------------------------------------------
-- Table Student_Performance.Type
-- -----------------------------------------------------
CREATE TABLE Student_Performance.Type (
  type_id VARCHAR2(30) NOT NULL,
  stream_id VARCHAR2(40) NOT NULL,
  PRIMARY KEY (type_id)
 ,
  CONSTRAINT fk_Type_Stream1
    FOREIGN KEY (stream_id)
    REFERENCES Student_Performance.Stream (stream_id)
   );

-- -----------------------------------------------------
-- Table Student_Performance.Modules
-- -----------------------------------------------------
CREATE TABLE Student_Performance.Modules (
  module_id NUMBER(10) NOT NULL,
  module_name VARCHAR2(45) NULL,
  category VARCHAR2(30) NULL,
  stream_id VARCHAR2(40) NOT NULL,
  PRIMARY KEY (module_id)
 ,
  CONSTRAINT fk_Modules_Stream1
    FOREIGN KEY (stream_id)
    REFERENCES Student_Performance.Stream (stream_id)
   );

-- -----------------------------------------------------
-- Table Student_Performance.Courses
-- -----------------------------------------------------
CREATE TABLE Student_Performance.Courses (
  course_id VARCHAR2(20) NOT NULL,
  course_name VARCHAR2(45) NULL,
  module_id NUMBER(10) NOT NULL,
  PRIMARY KEY (course_id)
 ,
  CONSTRAINT fk_Courses_Modules1
    FOREIGN KEY (module_id)
    REFERENCES Student_Performance.Modules (module_id)
   );

-- -----------------------------------------------------
-- Table Student_Performance.Employees_take_Modules
-- -----------------------------------------------------
CREATE TABLE Student_Performance.Employees_take_Modules (
  module_id NUMBER(10) NOT NULL,
  employee_id VARCHAR2(10) NOT NULL,
  score NUMBER(6,2)
 ,
  CONSTRAINT fk_Modules_has_Modules1
    FOREIGN KEY (module_id)
    REFERENCES Student_Performance.Modules (module_id)
   ,
  CONSTRAINT fk_Modules_has_Students
    FOREIGN KEY (employee_id)
    REFERENCES Student_Performance.Employees (employee_id)
   );