Overview

The Bid & Evaluation Service is a Spring Boot–based backend service responsible for managing bid submissions, bid evaluations, 
and winner selection within a procurement or project management system.
It supports transparent, auditable, and rule-based evaluation workflows.

Key Features

  Bid creation and submission
  Bid evaluation based on predefined criteria
  Scoring and ranking of bids
  Bid status management (Submitted, Under Review, Evaluated, Approved/Rejected)
  Winner selection and evaluation summary
  Secure REST APIs for integration with other services

Tech Stack

  Java 21
  Spring Boot
  Spring Data JPA
  MySQL
  Spring Security (JWT-based authentication) witj keycloak integration
  Maven

Architecture

  Controller Layer – REST APIs
  Service Layer – Business logic for bids and evaluations
  Repository Layer – Database access
  DTOs & Mappers – Clean data transfer
  Exception Handling – Centralized error management
Running the Service
  mvn clean install
  mvn spring-boot:run
