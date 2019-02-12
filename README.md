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
[5 - Reporting Bugs](#5-reporting-bugs)


## 1 Introduction

The Eclipse IDE provides helpful user interfaces and features for authoring, modifying, compiling, deploying, and debugging software. However, due to low accessibility and awareness of these tools and features, only a small number of these powerful IDE functionalities get used. Additionally, many developers find that many tools in their IDEs are not trivial to configure, and this prevents them from using the tool at all. The aim of IDE-IT Frontend is to improve the discoverability of existing IDE tools and shortcuts.

IDE-IT Frontend teaches users about the existance of features through non-invasive notifications when users neglect to use relevant features. It displays suggestions to the user in two forms: tool enable/disable and hotkey shortcut tips. Tool enable/disable suggestions appear with a checkbox next to them, which enables or disables the suggested tool accordingly if clicked.

<sup>[back to top](#ide-it-frontend)</sup>

## 2 Installation

This section provides the instruction for installing IDE-IT Frontend.

1. Clone this repository to your local machine
2. Build the plugin locally using gradle:

    `./gradlew clean build`
    
3. Open Eclipse
4. From the menu choose: **Help|Install New Software**
5. Click the **Add** button
6. In the **Add Repository** dialog that appears, click the **Archive** button next to the **Location** field
7. Select your plugin file, click **OK**
8. Restart Eclipse

If the IDE-IT window does not appear right away, it can be made visible by going to the **Window** drop down menu. From here, select **Show View**, and then choose the **Other** option. From the list of folders that appears, under the **IDE-IT Plug-in** folder, select the **IDE-IT** option. Then, click **Open**.

<sup>[back to top](#ide-it-frontend)</sup>

## 3 Using IDE-IT Frontend

To use this plugin first make sure the IDE-IT window is visible in your IDE, if it is not visible finish the steps in Installation above. 

Once the window is visible, just begin working on your project. The window will update based on patterns in you keystrokes with hotkey tips and features you might want to enable or disable. The goal is to help make coding easier and more fluent for you by introducing you to things that exist in the IDE that you may not be aware of. However, if you notice a tip or enable/disable feature is showing up and you do not want to see it again, simply press the "X" to the right of it and you will not see it again. 

<sup>[back to top](#ide-it-frontend)</sup>

## 4 API to Register Other Backends

The backend has a feature suggestion observer which determines the feature(s) the user could be using and returns a list of strings of the feature ids to the frontend. This is called with the getAllFeatureIDs() method within the feature suggestion interface. 

If new features are being observed in the API, include the feature id string and the configuration/shortcut in the documentation. If the new feature is a hotkey shortcut, this can be added into the front end suggestions manually. If the new feature is a configuration, create a new issue in this repository. Otherwise, for existing features, use the same feature ids present in the current frontend tool.

<sup>[back to top](#ide-it-frontend)</sup>

## 5 Reporting Bugs

If you find any bugs in our plugin, please submit an issue to our GitHub page:https://github.com/AlyssaRicketts/IDE-IT-Frontend/issues with the following information:

- Eclipse Version located by going to **Eclipse|About Eclipse**.
- A brief description of the bug.
- Specific steps to recreate the bug.
- (Optional) A screen shot of the bug.

<sup>[back to top](#ide-it-frontend)</sup>
