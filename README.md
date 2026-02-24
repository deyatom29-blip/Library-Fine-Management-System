# Library-Fine-Management-System

##  Team Members
- Deya Tom
- Ashir Nasar

##  Problem Statement

Managing student records of the books in the library manually using paper or spreadsheets can lead to data loss, duplication, and inefficiency. Educational institutions need a reliable system to store, update, and retrieve student information easily.

##  Objective

The objective of this project is to develop a Java-based GUI application that:
- Manages student book  records digitally
- Performs CRUD operations (Create, Read, Update, Delete)
- Connects Java application with a MySQL database
- Provides a simple and user-friendly interface

---

##  Features

-  Login Authentication System
-  Add New Student Records
-  Update Existing Records
-  Delete Records

-  #  Project Structure

```
Library-Management-System/
│
├── src/
│   ├── LoginScreen.java
│   ├── MainPage.java
│   ├── IssueBook.java
│   ├── ReturnBook.java
│   ├── ViewRecords.java
│   └── DBConnection.java
│
├── screenshots/
│   ├── login.png
│   ├── mainpage.png
│   ├── issuebook.png
│   ├── returnbook.png
│   └── viewrecords.png
│
└── README.md

💻 Steps to Run the Program

## 1️⃣ Install Required Software
- Install Java JDK (8 or above)
- Install MySQL Server
- Install IntelliJ IDEA or Eclipse

---

## 2️⃣ Create Database

Open MySQL and execute:

```sql
CREATE DATABASE project_db;

USE project_db;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(50),
    course VARCHAR(50)
);
```

---

## 3️⃣ Configure Database in Java

Open `DatabaseConnection.java` and update:

```java
String url = "jdbc:mysql://localhost:3306/project_db";
String username = "root";
String password = "your_password";
```

---

## 4️⃣ Run the Project

### Using IDE:
- Open project
- Run `Main.java`

### Using Command Prompt:

```bash
javac Main.java
java Main

