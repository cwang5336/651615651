# Redacted
Redacted - README Template
===

# Redacted

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
This app will act like a text messaging app where text messages are hidden. To view text messages one must request an unredaction to the other person they are communicating with. If a request is accepted then the text message will be shown. If the request is denied then that message will not be shown. This allows privacy for users. 

The look of it will look like WhatsApp where the message will stay. 

### App Evaluation
[Evaluation of your app across the following attributes] 
- **Category:** Messaging
- **Mobile:** easy to use by messaging either photos/documents/pictures and easy notification check. Everyone has a phone and are constantly checking their phones so it will be easier to grant permission. 
- **Story:** There is no other app like this where it redact messages. 
- **Market:** For businesses to send and receive confidential messages. 
- **Habit:** Ease of use for individuals or businesses who wants to share messaging/photos and or documents but want to be sure that only the receiver can view them. 
- **Scope:**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Sign in/sign up
* Add contact
* Message page

**Optional Nice-to-have Stories**

* Group Messaging 

### 2. Screen Archetypes

* Login/Signup
   * upon entering it will ask for a username and password
   * a button for login and a button for sign up
* Signup page
   * username, password, email.. Ect
   * then sign up button
* Menu with contacts
   * click on the person to message them

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* menu for contacts
* profile with profile editing options
* camera to send photos

**Flow Navigation** (Screen to Screen)

Flow navigation in the wireframe below with link to figma mockup

## Wireframes
[Add picture of your hand sketched wireframes in this section]
https://www.figma.com/file/M0EpPJyfmfoPNTb9Kyem7X/WireFrame-for-Redacted?node-id=0%3A1

### [BONUS] Digital Wireframes & Mockups
https://www.figma.com/file/M0EpPJyfmfoPNTb9Kyem7X/WireFrame-for-Redacted?node-id=0%3A1


### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
| Property | Type | Description |
| ------------ | ------- | ------------------------------------------- |
| username | String | Username for the user to chat |
| image | File | Image that user can send to chat |
| date | DateTime | The date when the message was sent |
| message | String | The text message user can send to chat |
| countdown | Time | The time left before the message redacts |


[Add table of models]
### Networking
### Profile
* (Update/PUT) Update user profile

### Camera
* (Create/POST) - take picture to send to someone

### Chats
* (Create/POST) - make new contact
* (Create/POST) - create post 
* (Read/GET) - post
* (Read/GET) - contact
* (DELETE) - delete chat
* (Update/PUT) Update contact profile
