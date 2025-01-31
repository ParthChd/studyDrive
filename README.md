## Readme To Run the test and Basic Setup.

This repository features a Appium-powered mobile test automation framework, crafted with Java, Maven, and TestNG, following the Page Object Model (POM) design pattern for enhanced scalability and maintainability. 🚀

#### 🚀 Setup & Installation Guide

1️⃣ Install Java (JDK 11)
> brew install openjdk@11

2️⃣ Install Android SDK
> brew install --cask android-sdk

3️⃣ Set Environment Variables
>export ANDROID_HOME=/usr/local/share/android-sdk
> 
>export PATH=$ANDROID_HOME/platform-tools:$PATH
> 
> export PATH=$ANDROID_HOME/tools:$PATH

4️⃣ Install Appium & Required Drivers
>npm install -g appium
> 
>appium driver install uiautomator2

5️⃣ Setup a Real Device
> Setting -- > About phone --> software infomation --> tap on build number --> developers options enabled
> 
> Go to developer options --> toggle on USB debugging
> 
> Connect your Android device via USB
> 
> To verify hit --- adb devices 

6️⃣ Start the Appium Server
> appium --allow-insecure adb_shell

7️⃣ Run TestNG XML File
>Open IntelliJ IDEA.
> 
>Right-click on testng.xml and select Run '.../testng.xml'.

#### 📌 StudyDrive Test Cases

| Test Case                                        | Verification for the Test  |
|--------------------------------------------------|----------------------------|
| verify e2e flow                                  | ✅                         |

#### 🛠️ Technologies & Tools Used

| Tool                    | Description                         |
|-------------------------|-------------------------------------|
| IntelliJ IDEA	          | IDE for writing and debugging tests |
| Appium 2.15.0	          | Mobile Automation Library           |
| Node.js v20.11.1	       | JavaScript Runtime for Appium       |
| Maven 3.9.9	            | Build Automation Tool |
| Java (JDK 11)	          | Programming Language |
| TestNG	          | Test Management Framework |
| Log4J	          | Logging Framework |


#### 💻 Basic Automation Setup on a New Machine

✅ Install Homebrew on macOS.

✅ Install Java (JDK 11).

✅ Install Android Studio.

✅ Configure JAVA_HOME and ANDROID_HOME.

✅ Install Node.js.

✅ Install Appium Server & Appium Client.

✅ Install IntelliJ IDEA (IDE).
