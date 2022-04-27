# Kyle_Pfunder

Project Description

Springdata-demo "Item-shop" is an application programming interface (API) that allows users to create an account and add items to a shopping list for easy management and access. The API is deployed in a Docker container and makes use of a containerized instance of a PostgreSQL database for storing user information, as well as the items added by the users. Loki, Promtail, Prometheus were also utilized to aggregate logs for collecting metrics to display on Grafana.

Technologies Used

   * Java
   * Maven
   * Spring
   * JUnit
   * Mockito
   * Docker
   * Promtail
   * Loki
   * Grafana
   * Prometheus
 

Features

  Users can:

   * create account
   * login to their account
   * add/update/delete/view their item(s) 

  Admin users can:

   * add/update/delete/view item(s)
   * add/update/delete/view users

  To-do list:

   * incorporate alert system
   * add stronger user authentication
   * implement more custom metrics


Getting Started

   * clone repository :: git clone https://github.com/030722-VA-SRE/Kyle_Pfunder/tree/main/projects/sts-workspace/springdata-demo
   * install Docker
   * create deployment :: kubectl apply -f ./deployment


Usage

   * send requests to API (port 8080) using webbrowser, Postman, or similar application
   * login :: <host-address>:8080/auth
   * get all items :: <host-address>:8080/items
   * add/update/delete/view item by id :: <POST/PUT/DELETE/GET> <host-address>:8080/items/<id>
   * view user list <  /users
   * add/update/delete/view users by id :: <POST/PUT/DELETE/GET> <host-address>:8080/users/<id>

   visualize metrics using grafana
      * <host-address>:3000

Contributors :: Kyle Pfunder
