# ATMOSPHERIC MANAGEMENT SYSTEM

## Problem Statement
To develop a GUI enabled air quality management system for IIITA campus. The
system should be able to monitor the level of hazardous gases present in the
environment. In an alarming situation the system must be able to generate alarm (also add auto generated message
and mail system). Meanwhile, the system must also be capable of measuring
the humidity and temperature present in the atmosphere. The code must also
maintain the log record of previous days and a generate report button should be
added to generate the report with the input given as range of timestamp or the
whole record. 

### State Diagram
![State diagram](Designs%20and%20Diagrams/StateDiagram.PNG)


### Class Diagram
![Class diagram](Designs%20and%20Diagrams/ClassDiagram.PNG)


### CRC Diagram
![CRC diagram](Designs%20and%20Diagrams/CRCDiagram.PNG)


### Use Case Diagram
![Use Case diagram](Designs%20and%20Diagrams/UseCaseDiagram.PNG)

## App Walkthrough

This is the homescreen of our system. On the homescreen we display the level of
different gases present in different places of CC3. In each card we display the level of hazardous gases in the particular place
and also the temperature and humidity.. These values that are generated randomly are
updated every 4 seconds in each card and according to the level of gases.
Home Screen
According to the relative levels of the pollutants, an AQI level is generated as Very Low,
Low, Medium, High and Very High. If the level is High or Very High an email is sent as an alert.


The home screen has buttons to get the logs, or to access the settings.


If we click the Settings button, the Settings screen is displayed.
In the Settings Screen, the mailing list is displayed and emails from the list can be
added or removed. This mailing list is used to determine who will get the email alerts in
case of high pollutant level.
The active notifications checkbox is present to toggle the alert mails on or off. If the box
is unchecked the high alert mail would not be sent. This is added to prevent spamming
of inbox with alert mails.
We can always go back to our home screen by pressing the Go Back button.


If we click on the Get Logs button from the homescreen, the Select Logs Duration
Screen opens up.

Here we have to decide for what duration we want our logs to be shown. We have to
select the start and the end date and click on the display data option. Also just to note,
while testing, we need to mention the dates of 18 as start, and 20 as end to get the logs
for 19. So, Start and End Date are not included in the Log Data, only dates in between.
We can also always go back to the Home Screen by clicking the Go Back button.


When we click Display Data after selecting the bounds, the Logs screen is displayed.
The logs will give the exact details, so what was the level of each pollutant at a place
with an exact timestamp.
