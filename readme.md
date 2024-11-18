# Aurora Skin Care Management System

This project is a management system designed for **Aurora Skin Care**, a private skin clinic in Colombo offering affordable dermatological services. The system facilitates appointment scheduling, fee calculations, treatment invoicing, and patient information management, helping the clinic's front desk manage appointments and billing efficiently.

---

## Project Overview

Aurora Skin Care provides outpatient dermatology services, with two dermatologists available for consultations. Patients can book 15-minute sessions based on their preference and availability, paying a registration fee upon booking. Once treatment is complete, the total fee is calculated, including treatment costs and taxes, and an invoice is generated for payment.

---

## Features

The system includes the following key functionalities:

- **Appointment Scheduling**: Allows booking and managing patient appointments based on the available consultation schedules.
- **Patient Information Management**: Collects and stores patient details such as NIC, name, email, and phone number.
- **Fee Calculation and Invoicing**:
  - Calculates the treatment fee and a 2.5% tax on the final amount.
  - Rounds up the tax-inclusive amount to the nearest decimal.
  - Generates invoices that clearly show the breakdown of charges, registration fees, and taxes.
- **Appointment Management**:
  - Update and view appointment details.
  - Filter appointments by date.
  - Search for appointments by patient name or appointment ID.

---

## System Requirements

- **Programming Language**: Java (Object-oriented approach).
- **Interface**: Command Line Interface (CLI) or Graphical User Interface (GUI).
- **UML Class Diagram**: A class diagram is created using UML, with each class name prefixed by the Kingston University student ID.
- **Testing**: General test cases created using a standard template.

---

## Project Structure

```plaintext
aurora-skin-care-management/
├── src/
│   ├── models/              # Class files for Patient, Appointment, Dermatologist, etc.
│   ├── services/            # Service classes for handling appointment logic and fee calculations
│   └── ui/                  # CLI or GUI components for user interaction
├── resources/
│   └── images/              # UML diagrams, screenshots, etc.
├── tests/
│   └── AppointmentTests.java # Unit tests for appointment functionality
└── README.md                # Project documentation
