
# CareerVista

A Native Android app that aims to provide users with an intuitive and accessible platform to access academic and professional resources. Compatible with Android devices, support English language, integrate with external services, and meets high standards of performance, usability, and security.

## Team Details

 invincible
   
   1.Priyanshu Prasad

   2.Shreyansh Singh

   3.Aditya Mishra
   
   4.Vishwajeet Prasad
## Product Documentation


We're working to find a solution to the issue of students around the world not having access to high-quality education because of various restrictions including distance, cost, or other personal or financial considerations. Our approach is to develop an online learning environment that offers students access to a variety of programmes and learning materials from the convenience of their homes.

The platform will provide user-friendly interface, allowing students to easily navigate and access content.

Students of all ages and backgrounds are our target demographic, especially those who have trouble obtaining traditional education. The platform will be accessible to users everywhere and will meet the needs of both independent learners and organisations looking to increase their educational options.
## Demo

Link to be updated

## Installation

Install CareerVista

```bash
  link
```
    
## User Guide

Welcome to the user guide for the Java Android app for online course search, scholarship search, mentorship search, and job/internship search. Here are the steps you can follow to make the most out of the app:


### 1).Download and Install the App:
##### ●	You can download the app from the Google Play Store on your Android device.
##### ●	Once the app is downloaded, install it by following the installation instructions.
### 2).Create an Account:
###### ●	Launch the app and click the register here to redirect on registration page.
###### ●	On registration page enter your first name, last name , password and mobile number and click on register button.
###### ●	An OTP Verification page will open, enter the correct OTP received on your phone message.
##### ●	After successful authentication the user will be registered and user account is created.



### 3). How to login:
###### ●	Enter the registred email id and valid password to login.
###### ●	If the credentials are correct then it will display successful Login.
### 4). Home Page:
###### ●	Home Page Consist of 4 section Mentorship, Courses ,Jobs and Scholarship.
###### ●	Clicking on respective section will redirect to respective sections.
### 5). Courses List Page:
###### ●	Course Page consist of the list of courses with course name,duration of course, instructor name and rating of each course.
###### ●	To buy Course click on Buy Course ,then the payment option page will be appeared .
###### ●	Select the suitable payment Method and buy the course.
### 6). Scholarship List Page:
###### ●	Scholarship Page consist of the list of Scholarships with Scholarship name, Scholarship Provider’s name, Application start date, Application end date, Eligibility and Scholarship amount.
###### ●	Select the Scholarship relevant to user and can apply for scholarship by pressing button APPLY FOR SCHOLARSHIP.



7). Job / Internship Page:


●	Job / Internship Page consist of list of Job Search with Job title, Job Description, Job application start date, Job application end date  and expected Salary of each Job title.


8). Mentorship Page:


●	Mentorship Page consist of list of mentor list with Mentor name, Experience of Mentor, Course name and mentor specialization.

## Developer Guide



Development Environment Setup:
To set up the development environment for an Android app, follow these steps:

### Install the Java Development Kit (JDK) on your computer.
##### ● Download and install Android Studio, the official integrated development environment (IDE) for Android app development.

##### ● Install the Android Software Development Kit (SDK) through Android Studio or separately.
##### ● Create a new Android project in Android Studio.
##### ● Configure the project settings, including the minimum SDK version and target SDK version.
##### ● Connect an Android device or set up an emulator for testing our app.
##### ● Once the development environment is set up, you can start developing your Android app using the Android Studio IDE and the Android SDK.





### Firebase Integration:

Set up a Firebase project and configure it for Android.
Add Firebase SDK dependencies to the app's build.gradle file.
Initialize Firebase in the app's Application class or main activity.
Use Firebase APIs to integrate services such as Authentication, Realtime Database, Storage, and Messaging.


### Braintree Integration:

Sign up for a Braintree account and obtain API credentials.
Add Braintree SDK dependencies to the app's build.gradle file.
Initialize the Braintree client with the API credentials.
Use Braintree APIs to process payments and manage payment methods.


### Volley Integration:

Add Volley dependency to the app's build.gradle file.
Create a RequestQueue object and add requests to it using Volley APIs.
Handle responses using Volley's ResponseListener and RequestErrorListener interfaces.

### BECKN Integration:

Use BECKN's API specifications to create services that adhere to the protocol.
Implement BECKN's communication mechanisms such as Discovery, Lookup, and Response.
Integrate BECKN services in the app using the provided SDKs or REST APIs
## Architecture

CareerVista is based on MVVM (Model-View-ViewModel) Architecture.

###### It is a software architectural pattern that separates the user interface (View) from the application logic (ViewModel) and the data model (Model). 
##### In MVVM, the ViewModel acts as an intermediary between the View and the Model, providing a clean separation of concerns and facilitating easy testing and maintenance. The ViewModel exposes data and behaviors to the View through observable properties and commands, which are bound to UI elements using data binding.

  
## Functionality

### Registration
This functionality allows users to create an account in the app by providing their personal details such as name, email,password and phone number. This information is stored securely in the Firebase and can be used to personalize the user's experience.

### Verification
This functionality ensures that the user's account is valid and authorized. Verification can be done through various methods such as email verification and phone number verification.

### Authentication
This functionality ensures that the user is who they claim to be. Once the user is registered and verified, they can log in to the app using their email and password or other authentication methods such as biometrics or two-factor authentication.

### Beckn API
This is a standardized protocol for connecting different services and systems.Beckn protocol specification defines a generic, abstracted API that when implemented allows domain-agnostic interoperable e-commerce transactions between a buyer and seller irrespective of which platform they are on. 
##### An analogy similar to this would be the case of SMTP specification that allows email communication between any two platforms that have implemented the same specification

### Purchasing a course
This functionality enables users to browse and purchase courses offered by the app. Users can view course descriptions, prices, and reviews before making a purchase. Payment can be made securely through integrated payment gateways such as BrainTree.

### Applying for scholarships/internships/jobs
This functionality allows users to apply for scholarships or internships/job offered by the app.Users can view their purchased courses and have personalized interactions with the mentors. 
## Source Code

