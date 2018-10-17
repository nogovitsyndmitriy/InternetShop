# InternetShop
Motor oils InternetShop
 Environment:
 Java 8
 Spring 5.0.8.RELEASE
 Hibernate 5.3.5.Final
 Tomcat 9.0.11
 GitHub
 Apache Maven 3.5.4
 MySQL 8.0.12
 
 
 Description:
 Online shopping is a form of electronic commerce which allows consumers to directly buy goods or services from a seller over the Internet using a web browser. Consumers find a product of interest by visiting the website of the retailer directly or by searching among alternative vendors using a shopping search engine, which displays the same product's availability and pricing at different e-retailers.
 Roles and functionality:

 1. For all users
    Login
    Registration
    Logout
 
 2. Security user
    Users: change role/password, disable, delete
    Audit: show events(USER, ITEM, ORDER, NEWS, COMMENTS)
 
 3. Sale user
    News: create, update, delete, delete comments.
    Items: create, copy, remove (soft delete), upload with help of xml
    Orders: show orders, change status(NEW, REVIEWING, IN_PROGRESS, DELIVERED)
 
 4. Customer user
    News:  show, create comment for news.
    Orders: add item to bucket, create order, show orders
    Profile: change information except email, change password
 
 5. API user
    Items: create, update, remove (soft delete), delete if not exists in any order
