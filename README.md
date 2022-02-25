## LAB 1 - Java Enterprise Course
### ABOUT:
This is a school project (lab 1) made in the course Java Enterprise.
It is a simple JAX-RS application that has one entity (student). Students can be added, deleted, updated and read.
#####  STUDENT :
A student has 5 attributes.
1. id 
2. firstName
3. lastName
4. phoneNumber
5. email

### SET-UP:
1. Clone or Fork this projekt
2. Download Payara to your local machine
3. Add run-configuration:
   1. Select payara server - local
   2. Choose application server (path to payara)
   3. Add deployment (student-manager-system:war)
4. Run your Configuration

Now you can use insomnia to send requests to the application.


### ENDPOINTS:
The URL for all endpoints starts with:
http://localhost:8080/student-management-system/api/v1

1. Create a student:
   - URL: http://localhost:8080/student-management-system/api/v1/students 
   - HTTP VERB: `PUT `
   - You have to send a student in JSON format.
      1. id is optional and will be generated automatically if not specified
      2. firstName, lastName and email are mandatory
      3. phoneNumber is optional
```json
{
  "firstName": "Helena",
  "lastName": "Halldin",
  "email": "example@example.com",
  "phoneNumber": "123456789"
}
```
2. Find all students:
   - URL: http://localhost:8080/student-management-system/api/v1/students 
   - HTTP VERB: `GET`
3. Find a student by id:
   - URL: http://localhost:8080/student-management-system/api/v1/students/{id} 
     - replace {id} with the id you are looking for
   - HTTP VERB: `GET`
4. Find all students with a specific last name:
   - URL: http://localhost:8080/student-management-system/api/v1/students?lastname={lastname} 
     - replace {lastname} with the name you are looking for
   - HTTP VERB: `GET`
5. Update a student:
   - URL: http://localhost:8080/student-management-system/api/v1/students/{id}
     - (replace {id} with the id you are looking for)
   - HTTP VERB: `PUT`
   - Note: this is a full update where you replace all attributes.
   - You have to send a student in JSON format. 
```json
{
  "firstName": "Helena",
  "lastName": "Eklund",
  "email": "example@example.com"
}
```
6. Delete a student:
   - URL: http://localhost:8080/student-management-system/api/v1/students/{id} 
     - replace {id} with the id of the student you want to delete
   - HTTP VERB: `DELETE`

