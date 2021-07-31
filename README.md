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
* performed basic unit test to check if users can add and remove shoes from there repository.

        @Test
    * fun testGetShoe_notNull_newShoe()
    * fun testGetShoe_Null_returnsNull()
    * fun testAddShoe_ShoeInst_sizeOne() 
    * fun testRemoveShoe_RemoveOne_ZeroShoesLeft()
    * fun testRemovingShoes_TWO_OneShoeLeft()
    * fun testRemovingShoesAndAdding_TWO_OneShoeLeft() 



### Break Down Tests

Explain what each test does and why

```
Examples here
```
## Project Instructions

This section should contain all the student deliverables for this project.

## Built With

* [Item1](www.item1.com) - Description of item
* [Item2](www.item2.com) - Description of item
* [Item3](www.item3.com) - Description of item

Include all items used to build project.

## License
