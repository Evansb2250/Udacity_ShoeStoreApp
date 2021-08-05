# README Template

Below is a template provided for use when building your README file for students.

# Project Title

This project simulates a simple inventory application to enter a list of shoes. The project was created to demonstrate my understanding on how to create an application using current best practices for separating UI and Business logic using a viewModel, managing android lifecycle, and manipulating the stack while using navgraph for fragment navigation. Therefore, this program is not suitable for real life application.

## Getting Started

Instructions for how to get a copy of the project running on your local machine.
To gain a copy of the project you can
* git clone https://github.com/Evansb2250/Udacity_ShoeStoreApp.git
* or you can download and unzip it on your machine.


### Dependencies

        kotlin_version = '1.3.72'
        nav_version = '2.2.0'
        androidXTestExtKotlinRunnerVersion = '1.1.1'
        androidXTestCoreVersion = '1.2.0'
        robolectricVersion = '4.3.1'
        appCompat = '1.3.1'
        core_ktx_ver = "1.3.1"
        material_ver = '1.0.0'
        contraintLayout_ver = '2.0.0-rc1'
        lifecycle_ext_ver = '2.2.0'
        timber_ver = '4.7.1'
        legacy_sup_ver = '1.0.0'
        livedata_ktx_ver = '2.3.1'
        viewModel_ktx_ver = '2.3.1'
        viewPager_ver = '1.0.0'
        junit_ver = '4.12'
        junit_ext_ver = '1.1.1'
        espressoCore_ver = '3.2.0'





### Installation

Step by step explanation of how to get a dev environment running.

* Used Android Studio 4.2.1 
* Gradle Plugin Version 4.02
* Gradle Version 6.1.1


## Testing
* Tested application using Pixel 3 XL API 29 emulator.
* performed unit test for
   * LoginViewModels
   * ShoeListViewModel
   * ShoeFormViewModels 

   (Known issues)
   * The current structure doesn't make it easy to test the LoginViewModel, therefore there are only a few use case scenarios for the class
   * The number counter for shoes in the inventory is stored as a static variable therefore additional functions had to be created to prevent unpredictable behavior.


### Break Down Tests

Explain what each test does and why


```
Examples here
```
## Project Instructions
* The project delivers 
    * an shoe inventory application that navigates across 5 different screens.
    *  Each layout uses databinding.
    *  A user can sign up and login to the application
    *  the user information is retained as long as the activity for the screen is not destroyed.
    *  users are presented with a welcome screen, instruction screen, shoe inventory screen and shoe detail screen.
    *  users can add and remove shoes from the list.
    

## Built With

* [Item1](www.item1.com) - Description of item
* [Item2](www.item2.com) - Description of item
* [Item3](www.item3.com) - Description of item

Include all items used to build project.

## License
