# ORDER MANAGEMENT SYSTEM

Orders' system API to manage products, orders, customers and users data.

Framework Spring Boot.

Used IDE: IntelliJ IDEA 2023.2 EAP.

### Business Logic
The customer inserts contact info, creates the order connected to the customer, creates order lines 
connected to the order with products and a quantity of products. The customer can register as permanent user
inserting a username and a password and have a role of User.  
Spring Security has been used to authenticate and authorize users. Currently, all requests are permitted
for all users.  

### Functionalities
Add, get, edit, delete Customers, Products, OrderLines, Orders, Roles, Users.  
Slf4j has been used to generate logging statements.

### Testing
For testing has been used the framework support of Spring Boot (@SpringBootTest and for JPA components 
@DataJpaTest) with Mockito framework.   

### Database
PostgreSQL credentials are set in application.properties file.  







