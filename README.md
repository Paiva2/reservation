# Reservation System

You are required to build backend system for a movie reservation service. The service will allow users to sign up, log
in, browse movies, reserve seats for specific showtimes, and manage their reservations. The system will feature user
authentication, movie and showtime management, seat reservation functionality, and reporting on reservations.

## Goal

The goal of this project is to help you understand how to implement complex business logic i.e. seat reservation and
scheduling, thinking about the data model and relationships, and complex queries.

## Requirements

We have intentionally left out the implementation details to encourage you to think about the design and implementation
of the system. However here are some requirements that you can consider:

## User Authentication and Authorization

- [x] Users should be able to sign up and log in.
- [x] You also need roles for users, such as admin and regular user. Admins should be able to manage movies and
  showtimes.
- [x] Regular users should be able to reserve seats for a showtime.
- [x] You can create the initial admin using seed data. Only admins should be able to promote other users to admin and
  be able to do things related to movie management, reporting, etc.

## Movie Management

- [x] Admins should be able to add, update, and delete movies.
- [x] Each movie should have a title, description, and poster image.
- [x] Movies should be categorized by genre.
- [x] Movies should have showtimes.

## Reservation Management

- [x] Users should be able to get the movies and their show times for a specific date and genre.
- [x] Users should be able to see the available seats for a specific Session and Room.
- [x] Users should be able to reserve seats for a showtime choosing the seats they want (If available).
- [x] Users should be able to see their reservations.
- Users should be able to cancel their reservations (only upcoming ones).
- Admins should be able to validate reservation tickets.
- Admins should be able to cancel reservation tickets (only upcoming ones).
- Admins should be able to see all reservations, capacity, and revenue.

## Implementation Considerations

- Think about the data model and relationships between entities.
- Think about how you will avoid overbooking, and how you will handle seat reservations.
- Think about how you will handle the scheduling of showtimes.
- Think about how you will handle the reporting of reservations.
- Think about how you will handle the authentication and authorization of users.
