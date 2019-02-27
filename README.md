# IDE-IT Frontend: Running Integration Tests

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
