## Obs: This is not done!
#### ABOUT:
This is a school project made in course Java Enterprise for Lab 1.

#### SET-UP:
1. Clone or Fork this projekt
2. Download Payara to your local machine
3. Download Insomnia
4. Add run-configuration:
   1. Select payara server - local
   2. Choose application server (path to payara)
   3. Add deployment (student-manager-system:war)
5. Run your Configuration

Now you can open insomnia and send requests.

#### STUDENT:
A student has 5 fields. 
1. id
2. firstName
3. lastName
4. phoneNumber
5. email

#### ENDPOINTS:
The URL for all endpoints starts with:
http://localhost:8080/student-management-system/api/v1

1. Create a student:
   1. URL: .../student
   2. HTTP VERB: PUT
   3. You have to send a student in JSON format.
      1. id is optional and will be generated automatically if not specified
      2. firstName, lastName and email are mandatory
      3. phoneNumber is optional
2. Get all students:
   1. URL: .../student
   2. HTTP VERB: GET
3. ....

