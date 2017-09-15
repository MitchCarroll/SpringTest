# SpringTest
This is just a simple test project that I am using to learn the Spring framework, 
and play with asynchronous operations in a web / database framework.

**Setup instructions:**
- Install MySQL
- Create a MySQL user called sqluser, with password: dbpassword
- Create a schema called test, and make sure sqluser has adequate permissions
- In Test, create a table called people with the columns id, last_name, first_name, middle_name, dob, sex
- Insert some test data into the table
- Install the latest Eclipse (Java EE)
- Using the Eclipse Marketplace, install the Spring Tool Set
- Clone this repository to a folder of your choice
- In Eclipse, import the project as an existing Maven project, using the folder containing pom.xml as the project folder
- Run as Spring-boot application
- Open a web browser, and navigate to localhost:8080
