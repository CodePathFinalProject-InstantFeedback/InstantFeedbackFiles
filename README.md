CS:Wait Codepath Project - README Template
===

# Instant Feedback

## Table of Contents
1. [Overview](#Overview)
1. [Product Specifications](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description

An application for professors and mentors to see instant feedback given by students regarding their assignments.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Academics 
- **Mobile:** Mobile is essential for istant feedback as it is protable, and user friendly. App that allows students to log in to accounts and give feedback to particular classes. Professors get the aggregate results and use the data to alter the course of the class.
- **Story:** In many colleges, students are required to give feedback about a course at the end of the semester. This feedback does not help to know about students view on the course during the semester which could allow to make changes and adjustments accordingly. Thus, we thought about making a rating based review app for all assignments in the course so that the instructor can keep track of student responses, and make improvements accordingly throughout the semster.  

- **Market:** Our primary customers will be schools from K-12, and universities that want to know about students' view on the course during the semester. This could also be a valuable asset for the education ministry to make certain changes after analyzing the data over the course of a particular time period. 

- **Habit:** Teachers/Professors will be using this after every assignment to get feedback, and make adjustment if needed to the next assignment. Students will be required to provide feedback at the end of every assignment.

- **Scope:** Version 1 will allow professors, and teachers to add a course and assignments, and students to keep track fo that specific course. Version 2 will allow students to rate the assignments, and professors to view the feedback. Version 3 will graph, and make visualization based on the data. Version 4 will have machine learning/sentiment analysis, to allow textual feedback, and its analysis. 

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Students and Professors can create an account.
* Students and Professors login to the app. 
* Professors can add a course, and assignments within it. 
* Students can register for the available courses. 
* Professor and students can view their courses.
* Students can provide a rating in five stars for all the added assignment.  
* Professors can see the feedback as the average number of stars for each assignment.

**Optional Nice-to-have Stories**

* Make real time graph, and charts based on student feedback.
* Implement sentiment analysis/machine learning/NLP so that student  can provide feedback in       paragraph. 

### 2. Screen Archetypes

* Login  - User logs into their account
   * Students and Professors login to the app. 
   * 
* Register - User signs up for a new account 
   * Students and Professors can create an account
   * 
* Creation - User can create a new resource
   * Professors can add a course, and assignments within it. 
   * Students can register for the available courses. 
   
* Stream - User can scroll through important resources in a list
   * Professor and students can view their courses.
   

* Profile - User can view their identity and stats
   
* Detail- User can view the detail of particular resource. 
    *  Professors can see the feedback as the average number of stars for each assignment of a course.
     * Students can provide a rating in five stars for all the added assignment.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Creation 
* Stream

**Flow Navigation** (Screen to Screen)

*  Login
    * Stream 
    * Register
* Register
    * Stream 
* Stream
    * Creation 
    * Detail
* Creation
    * Stream
* Detail
    * Stream 


## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="wireframe.jpg" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 

### Models
#### User 

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | author        | Pointer to User| image author |
   | image         | File     | image that user posts |
   | caption       | String   | image caption by author |
   | commentsCount | Number   | number of comments that has been posted to an image |
   | likesCount    | Number   | number of likes for the post |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
   
   
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
