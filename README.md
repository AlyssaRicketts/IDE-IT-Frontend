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
[5 - Add Your Own Suggestions](#5-add-your-own-suggestions) |
[6 - Reproducing User Tests](#5-reproducing-user-tests) |
[7 - Reporting Bugs](#6-reporting-bugs)


## 1 Introduction

### 1.1 Motivation

The Eclipse IDE provides helpful user interfaces and features for authoring, modifying, compiling, deploying, and debugging software. However, due to low accessibility and awareness of these tools and features, only a small number of these powerful IDE functionalities get used. Additionally, many developers find that many tools in their IDEs are not trivial to configure, and this prevents them from using the tool at all. The aim of the Integrated Development Environment - Intelligent Tutorials (IDE-IT) is to improve the discoverability of existing IDE tools and shortcuts, by providing developers non-invasive suggestions on Eclipse features that they may not be aware of. The suggestions take the form of hotkey tips and easy enabling/disabling of configuration settings. The hotkey suggestions are shown when the user is continually performing tasks manually that could be done with a hotkey and the enable/disable features appear periodically with configuration settings that other developers have found to be extremely useful but many are unaware of their locations within the menu or of their existence.

### 1.2 IDE-IT Frontend

This repository / plugin is specifically for the frontend service of IDE-IT. This is not designed to be a standalone plugin. It requires a the use of a backend service that tracks user action such as document changes, key presses, and mouse clicks, and then performs the evaluation of which hotkeys and configurations to suggest. We recommend the IDE-IT backend plugin, located at https://github.com/DavidThien/IDE-IT, as this framework is built specifically for IDE-IT. If you would like to use your own custom backend framework, see [below](#4-api-to-register-other-backends) on how to incorporate our service to your own plugin.

<sup>[back to top](#ide-it-frontend)</sup>

## 2 Installation

This section provides the instruction for installing IDE-IT Frontend.

### 2.1 Prerequisites

1. Java JDK version 1.8.0 or higher. See [Oracle Java Downloads](https://www.oracle.com/technetwork/java/javase/downloads/index.html) for more.
2. Apache Ant version 1.10.5 or higher. See [Apache Ant Binary Distributions](https://ant.apache.org/bindownload.cgi) for more.
3. Eclipse IDE version 2018-12 or higher. See [Eclipse IDE Downloads](https://www.eclipse.org/eclipseide/) for more.

### 2.2 Build and Open in Eclipse

1. Clone this repository to your local machine
2. Navigate into the **IDE-IT-Frontend** folder within the cloned IDE-IT-Frontend folder
3. Build the plugin locally using ant:

    `ant build`
    
4. Open Eclipse
5. From the menu choose: **Help | Install New Software**
6. Click the **Add** button
7. In the **Add Repository** dialog that appears, click the **Archive** button next to the **Location** field
8. Select your plugin file, click **OK**
9. Restart Eclipse

If the IDE-IT window does not appear right away, it can be made visible by going to the **Window** drop down menu. From here, select **Show View**, and then choose the **Other** option. From the list of folders that appears, under the **IDE-IT Plug-in** folder, select the **IDE-IT** option. Then, click **Open**.

<sup>[back to top](#ide-it-frontend)</sup>

## 3 Using IDE-IT Frontend

To use this plugin first make sure the IDE-IT window is visible in your IDE, if it is not visible finish the steps in Installation above. Once the window is visible, just begin working on your project. The window will update based on patterns in your keystrokes with hotkey tips and features you might want to enable or disable.

![](https://github.com/AlyssaRicketts/IDE-IT-Frontend/blob/master/IDE-IT-Frontend/icons/GUI.png)
Figure 1: Mockup of IDE-IT user interface window within an Eclipse workspace.
    
### 3.1 Window Appearance    

Tool enable/disable suggestions appear with a checkbox next to a text description of the tool. Hotkey tips appear with a lightbulb icon next to a text description of the hotkey/shortcut.

### 3.2 Enabling/Disabling Suggested Configurations

Simply check or uncheck the box next to the tool you would like to enable or disable.
    
### 3.3 Using Suggested Hotkeys

Next to the lightbulb icons will be suggested hotkeys, these are keyboard shortcuts that might make your coding experience easier and quicker.
    
### 3.4 Removing Unwanted Suggestions

If you do not wish to see a hotkey tip or are not interested in an enable/disable feature, simply click the "X" on the right side of that tip or tool and it will not show up again.

<sup>[back to top](#ide-it-frontend)</sup>

## 4 API to Register Other Backends

This plugin requires a back-end service that tracks document changes within the Eclipse IDE to provide a list of feature suggestions to this plugin. The recommended back-end service for this plugin is the [IDE-IT back-end plugin](https://github.com/DavidThien/IDE-IT). If you would like to use a different back-end service, please continue reading.

The front-end depends on a [FeatureSuggestionObserver](https://github.com/DavidThien/IDE-IT/blob/master/backend_plugin/src/interfaces/FeatureSuggestionObserver.java) abstract class and a [FeatureSuggestionInterface](https://github.com/DavidThien/IDE-IT/blob/master/backend_plugin/src/interfaces/FeatureSuggestionInterface.java) abstract class. You should include these abstract classes within your project when you implement your own observers. You must implement a FeatureSuggestion class which extends [FeatureSuggestionInterface](https://github.com/DavidThien/IDE-IT/blob/master/backend_plugin/src/interfaces/FeatureSuggestionInterface.java). This class should call the notify function in the [FeatureSuggestionObserver](https://github.com/DavidThien/IDE-IT/blob/master/backend_plugin/src/interfaces/FeatureSuggestionObserver.java) class to send the feature ID that uniquely identifies each Eclipse feature to the front-end.

If the feature suggestions you implement are included within the [current features](https://github.com/DavidThien/IDE-IT/blob/master/featureIDStrings.txt), you should use the same feature IDs as listed in [this file](https://github.com/DavidThien/IDE-IT/blob/master/featureIDStrings.txt). To add additional features, add the unique feature ID along with the [Suggestion](https://github.com/AlyssaRicketts/IDE-IT-Frontend/blob/master/IDE-IT-Frontend/src/main/java/Suggestion.java) into the suggestions map in the [Controller](https://github.com/AlyssaRicketts/IDE-IT-Frontend/blob/master/IDE-IT-Frontend/src/main/java/Controller.java). For adding configurations specifically, there will need to be additional implementation to enable or disable the specified configuration.

To connect your plugin, export your project as a jar file named as 'backend_plugin.jar' and place the jar file in the IDE-IT-Frontend/IDE-IT-Frontend/lib folder. This will include your plugin as a dependency, so the project can be built and run.

<sup>[back to top](#ide-it-frontend)</sup>

## 5 Add Your Own Suggestions

### 5.1 Adding Hotkey Suggestions

1. In [Controller.java](https://github.com/AlyssaRicketts/IDE-IT-Frontend/blob/master/IDE-IT-Frontend/src/main/java/Controller.java), add the desired string identifier and corresponding `Suggestion` object to the `suggestionsMap`

    a. `Suggestion` object should include string identifier, the text to be displayed, `HOTKEY` as the `type` field, and `true` for the `display` field. See [Suggestion.java](https://github.com/AlyssaRicketts/IDE-IT-Frontend/blob/master/IDE-IT-Frontend/src/main/java/Suggestion.java) for more information.
    
    b. If the hotkey varies for different operating systems, add the corresponding text for each operating system, and put each `Suggestion` in the map under the corresponding branches for the operating systems.
    
<sup>[back to top](#ide-it-frontend)</sup>

### 5.2 Adding Preference Suggestions

1. In [Controller.java](https://github.com/AlyssaRicketts/IDE-IT-Frontend/blob/master/IDE-IT-Frontend/src/main/java/Controller.java), add the desired string identifier and corresponding `Suggestion` object to the `suggestionsMap`

    a. `Suggestion` object should include string identifier, the text to be displayed, `CONFIG` as the `type` field, and `true` for the `display` field. See [Suggestion.java](https://github.com/AlyssaRicketts/IDE-IT-Frontend/blob/master/IDE-IT-Frontend/src/main/java/Suggestion.java) for more information.

2. In [ConfigDisplayComposite.java](https://github.com/AlyssaRicketts/IDE-IT-Frontend/blob/master/IDE-IT-Frontend/src/plugin/views/ConfigDisplayComposite.java):

    a. At the bottom of the document, create a new method with an input parameter `Button checkBox` and no return values
    
    b. In this method, follow the pattern of the other methods here, by calling 
        ```java
        checkBox.addSelectionListener(new SelectionAdapter() { 
            @Override
            public void widgetSelected(SelectionEvent event) {
                Button btn = (Button) event.getSource();
                if(btn.getSelection()) {
                    **Insert selection code here**
                    try {
                        prefs.flush();
                    } catch (org.osgi.service.prefs.BackingStoreException f) {
                        f.printStackTrace();
                    }
                } else {
                    **Insert deselection code here**
                    try {
                        prefs.flush();
                    } catch (org.osgi.service.prefs.BackingStoreException f) {
                        f.printStackTrace();
                    }
                }
            }
        }
        ```
        
    c. Where the **Insert selection/deselection code here** is, insert the following:
        ```java
        IEclipsePreferences prefs = <YourScope>Scope.<YourScope>.getNode(<YourNode>);
        prefs.put(<NodeName>, <value>);
        ```
    
      YourScope should be replaced with the specific scope you are interested. I.e. Instance, Configuration, Default, or BundleDefaults. See [here](https://www.vogella.com/tutorials/EclipsePreferences/article.html) for more information.
      YourNode should be replaced with the preference node of interest. I.e. "org.eclipse.jdt.ui". 
      NodeName should be replaced with the name of the preference of interest.
      Value should indicate the value you want this preference to be set to upon check/uncheck.
    
    d. Add a new `else if` branch where the `Suggestion` object ID is checked against the desired string identifier
        i. Use `s.getID().equals(<insert string identifier>)`
    
    e. In this branch, call the newly created method, passing the parameter `checkBox` to it
    
<sup>[back to top](#ide-it-frontend)</sup>

## 6 Reproducing User Tests

Our method of user testing is described in detail in our project report which can be found in the most recently updated folder in our reports folder. If you are interested in reproducing our user tests the process is rather simple. Follow the above instructions on cloning and starting up the plugin and find participants within your target group to test the plugin. Then provide them with the following link which will take them to the survey we provided to our users: https://goo.gl/forms/TeKRrIywe8EfxUWr1. However, this link would send our team the results from the survey so if you are interested in analyzing the results yourself you would need to make a new google form following the same format as our survey and then provide your participants with that link instead. By doing this you will be able to sign into your google account and view the results of the survey. In the results section you are able to view each individual survey that has been submitted as well as graphs representing all of the data from the submitted surveys. 

<sup>[back to top](#ide-it-frontend)</sup>

## 7 Reporting Bugs

If you find any bugs in our plugin, please submit an issue to our GitHub page: https://github.com/AlyssaRicketts/IDE-IT-Frontend/issues with the following information:

- Eclipse Version located by going to **Eclipse | About Eclipse**.
- A brief description of the bug.
- Specific steps to recreate the bug.
- (Optional) A screen shot of the bug.

<sup>[back to top](#ide-it-frontend)</sup>
