Project Title - AccountAdministrativeService

Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on system.

Prerequisites

IDE(Eclipse, RAD)

Server ( Websphere or Tomcat Server with Jax-WS support)

Installing

1) Import existing TransferServiceImplementation, TransferServiceWeb, TransferServiceEAR projects in IDE.
2) Create H2 database connection on IDE. Properties - Database: h2 , URL : jdbc:h2:~/test,User NAme - sa, password- spaces
2) Start Server
3) ADD TransferServiceEAR to server
4) Import WSDL into soapUi and create soapui project for testing