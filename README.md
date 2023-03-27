# FoodAllergyApp

Tools Required:
Android Studio.

Development Languages Used:
Java, Android.

Would like to detail about the app in this README.
 
Firstly, Let me explain you the structure and functionality of the code.

There are few important folders to be considered here which are as follows:
 App
 Gradle Scripts
 
 App
   1. Manifests
   2. Java
   3. res
 
Manifests: AndriodManifest.xml is the file present under Manifests folder which consists of the integratory segments or modules of the food allergy project like user profile, scanner, Dashboard in a form of activities.

Java consists of the below packages in it,
   com.db.foodallergyapp
   com.db.foodallergyapp(androidTest)
   com.db.foodallergyapp(Test)
 

com.db.foodallergyapp consists of the below structured folder:
   Activities
   AndroidUtils
   Fragments
   Model
   RecyclerViewAdapters
   View
 
Activities consists of the functional class files which are as follows:
   AllFoodAllergyActivity:
      Functionality and back end logic of All Food Allergy is written in this class file. 
   FoodAndNutritionActivity:
      Functionality and back end logic of FoodAndNutritionActivity is written in this class file. 
   ProfileActivity:
      Functionality and back end logic of ProfileActivity is written in this class file. 
   ScannerActivity:
      Functionality and back end logic of ScannerActivity is written in this class file. 
   ScannerResultActivity:
      Functionality and back end logic of ScannerResultActivity is written in this class file. 
   SearchInFoodActivity:
      Functionality and back end logic of SearchInFoodActivity is written in this class file. 
 
View:
   CaptureAct:
      If we need to capture any activities within the project then we can use it.
   MainActivity:
      Inorder to start, to stop, for the permissions etc all the activities will be present in MainActivity.
   SplashActivity:
      If at any case any information needs to be displayed only for certain amount of time and then hide it etc, such functionality can be pass under the activity here
 
Res:
  drawable
      All the project design are available here
  layout
      layout design is available here 
  menu
      Menu design is availabe here
  mipmap
  values
  xml
  

Gradle:
  Build gradle:
      Integration of the android in the json format is present here.
      
      
      
How to run the application:


Install Android studio.

Download FoodAllergyApp from Github.

Import or Open the FoodAllergyApp into Android Studio.

Right side of the panel you will be able to see Device Manager

Click on Device Manager.

Click on Virtual.

Click on Create device.

You will be seeing a Select Hardware in Virtual Device Configuration and by default Phone will be selected if not select phone and click on Next.

You will be seeing a System Image in Virtual Device Configuration and by default Sv2 will be selected if not select Sv2 and click on Next.

You will be seeing a Android Virtual Device(AVD) in Virtual Device Configuration, verify configuration and click on Finish.

Then the device will be added under Device Manager Virtual tab then click on Launch the emulator, you will be seen launching the mobile emulator.

Click on Run app then you can see the functionality 
  


Attaching the Functionality as follows:
  
![image](https://user-images.githubusercontent.com/55182135/228025797-7e8ab2c7-137e-41b4-96cc-5d4179108e18.png)
  
Click on APP:

![image](https://user-images.githubusercontent.com/55182135/228027973-d2800266-d736-4e71-85f0-3d85c4cb51d3.png)
    
![image](https://user-images.githubusercontent.com/55182135/228025992-f2cb3698-ac78-4110-a78e-b80c5e729d64.png)

Click on Menu:

![image](https://user-images.githubusercontent.com/55182135/228033259-0f7a9112-8ad6-42b0-841c-d3e746f3b0d5.png)

  
Click on User:
    
![image](https://user-images.githubusercontent.com/55182135/228028668-e451be9a-02f8-4329-8c20-396a20b754f0.png)
    
Every field is editable and back functionality is implemented in all screens.
View of the main interface
    
![image](https://user-images.githubusercontent.com/55182135/228030198-d2bf13a9-3b81-44bb-99e9-27d7ab599b6c.png)
    
After clicking on the functionalities present on the screens you will be able to run the functionality and see the things.
  
  



  

  
  
  
