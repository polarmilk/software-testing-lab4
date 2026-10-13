# Lab Four: The Library Alert System

## Overview

The `LibraryService` is responsible for checking out books. If a book is checked out successfully, it must notify the member via email.

**The Problem:** We don't want to send real emails every time we run a unit test, and we don't want our tests to fail just because the email server is down. We will use **Mockito** to mock the Email Provider.

## Specification

- Books (and other library resources) are identified with UUIDs.
- The `checkoutResource` function takes:
    - A `UUID` identifying the resource.
    - A `String memberEmail` to send an email to the person checking out the book.
- The function returns a boolean indicating whether the operation was successful.
- If the `resourceId` is `null`, the function should return `false`.
- If the resource is not available in the repository/database (e.g., it is already checked out), the function should return `false`.
- If the book is available, its status should be updated, and an email should be sent to `memberEmail`. Then, the function should return `true`.
- If either the status update or the `sendEmail` function fails, an exception of the appropriate type should be thrown.

Provisional code is available in the class repository.

## Part One: Test Design

Design tests for the system using the methods established in Module 1.

## Part Two: Test Implementation

1. Implement your tests.
2. Because the function depends on the `ResourceRepository` and the `EmailProvider`, use Mockito to mock examples of those two dependencies and inject them into your test. This allows you to test the function's logic without actually checking out books or sending emails to patrons.
