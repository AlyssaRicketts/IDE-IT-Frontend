[![Build Status](https://travis-ci.com/AlyssaRicketts/IDE-IT-Frontend.svg?branch=master)](https://travis-ci.com/AlyssaRicketts/IDE-IT-Frontend)

# IDE-IT Frontend 
#### Introduction and User Manual

### IDE-IT Frontend Team:  
- [Alyssa Ricketts](https://www.linkedin.com/in/alyssa-ricketts/)
- [Puja Ramanathan](https://www.linkedin.com/in/pujaram/)
- [Rachel Zigman](https://www.linkedin.com/in/rachel-zigman-555751132/)

#### Quick Links: 
[1 - Introduction](#1-introduction) |
[2 - Installation](#2-installation) |
[3 - Using IDE-IT Frontend](#3-using-ide-it-frontend) |
[4 - API to Register Other Backends](#4-api-to-register-other-backends) |
[5 - Reproducing User Tests](#5-reproducing-user-tests) |
[6 - Reporting Bugs](#6-reporting-bugs)


## 1 Introduction

If you are a user or developer inspecting this branch of the IDE-IT Frontend repository, then you are likely interested in running the integration tests for the plugin as a whole. The motivation behind this is to expose faults in the interaction between integrated units, and/or ensure that the independent units work correctly when connected to each other. Specifically, these integration tests ensure that the interaction between the backend and frontend plugins are successful and work as expected. These integration tests are performed using an SWTBot: an open-source Java based UI/functional testing tool for testing SWT, Eclipse, and GEF based applications. The SWT bot simulates a user, such that it asserts expected functionality upon various user inputs. To reproduce these tests, one must not only have the prerequisites for the frontend plugin, but additionally the proposed backend plugin, as well as an SWTBot. See [prerequisites](#2-Prerequisites) for more information.

<sup>[back to top](#ide-it-frontend)</sup>

## 2 Prerequisites

1. Java JDK version 1.8.0 or higher. See [Oracle Java Downloads](https://www.oracle.com/technetwork/java/javase/downloads/index.html) for more.
2. Apache Ant version 1.10.5 or higher. See [Apache Ant Binary Distributions](https://ant.apache.org/bindownload.cgi) for more.
3. Eclipse IDE version 2018-12 or higher. See [Eclipse IDE Downloads](https://www.eclipse.org/eclipseide/) for more.
4. Eclipse SWTBot version 2.8.0 or higher. Ensure that your run configurations are properly set. See [Eclipse SWTBot](https://www.eclipse.org/swtbot/) for more.
5. The recommended back-end service for this plugin, found at [IDE-IT back-end plugin](https://github.com/DavidThien/IDE-IT).

Clone THIS BRANCH of the frontend repository, and the [frontend release](https://github.com/DavidThien/IDE-IT/tree/frontend_release) branch of the backend repository to your local machine. Follow the instructions found on the respective repository's README.md to build and open the plugins in eclipse. 

<sup>[back to top](#ide-it-frontend)</sup>

## 3 Running Integration Tests

After properly installing the SWTBot into your Eclipse IDE, open the **src -> test -> java** folder from within the frontend repository that you cloned. Here, you will see a **UserInterfaceTester** file. Right click on it, hover over **Run as**, and select **SWTBot Test**. You should now not touch your machine until the tests have completed running, as any user input can interrupt the simulation. You will see a new Eclipse workspace launch, the IDE-IT window become opened, all checkboxes become selected, and the window then close. When it returns to your original workspace, you should now see a green line in your **JUnit** window, with 0 errors and 0 failures. If this is not the case, please submit a bug report.

<sup>[back to top](#ide-it-frontend)</sup>

## 5 Reproducing User Tests

Our method of user testing is described in detail in our project report which can be found in the most recently updated folder in our reports folder. If you are interested in reproducing our user tests the process is rather simple. Follow the above instructions on cloning and starting up the plugin and find participants within your target group to test the plugin. Then provide them with the following link which will take them to the survey we provided to our users: https://goo.gl/forms/TeKRrIywe8EfxUWr1. However, this link would send our team the results from the survey so if you are interested in analyzing the results yourself you would need to make a new google form following the same format as our survey and then provide your participants with that link instead. By doing this you will be able to sign into your google account and view the results of the survey. In the results section you are able to view each individual survey that has been submitted as well as graphs representing all of the data from the submitted surveys. 

<sup>[back to top](#ide-it-frontend)</sup>

## 6 Reporting Bugs

If you find any bugs in our plugin, please submit an issue to our GitHub page: https://github.com/AlyssaRicketts/IDE-IT-Frontend/issues with the following information:

- Eclipse Version located by going to **Eclipse | About Eclipse**.
- A brief description of the bug.
- Specific steps to recreate the bug.
- (Optional) A screen shot of the bug.

<sup>[back to top](#ide-it-frontend)</sup>
