#### A simple console drawing program written in java.
#### The currently implemented features allow you to perform the following action
##### [Source on Github](https://github.com/yeongsheng-tan/drawing_app_java)
---
###### 1. Creates a new canvas for a specified positive integer width and height (max of 30 x 30 allowed)
###### 2. Quit
###### 3. Draw a line in the Canvas created in (1)
###### 4. Draw a rectangle in the Canvas created in (1)
---
## Installation for running the compiled java jar binary 'drawing_app_java-all.jar'
1. Install JDK8 for your target Operating System, see ([details](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))

   1.1 On macOS High Sierra, run:
   ```
   brew update && brew tap caskroom/versions && brew cask install java8
   ```
   
   1.2 On a Debian Stretch 64-bit OS, run:
   ```
   sudo apt-get update && sudo apt-get install openjdk-8-jdk
   ```
  
   1.3 On a CentOS 7.x 64-bit OS, run:
   ```
   sudo yum -y install java-1.8.0-openjdk java-1.8.0-openjdk-devel
   ```
   
2. Install Gradle 4.7 for your target Operating System, see ([details](https://gradle.org/install/))

   2.1 On macOS High Sierra, run:
   ```
   brew update && brew install gradle
   ```
   
   2.2 On a Debian Stretch and CentOS 7 64-bit OS, run:
   ```
   wget https://services.gradle.org/distributions/gradle-4.7-bin.zip
   mkdir -p /opt/gradle
   unzip -d /opt/gradle gradle-4.7-bin.zip
   echo "export PATH=$PATH:/opt/gradle/gradle-4.7/bin" >> ~/.bashrc
   source ~/.bashrc
   ```

3. Compile, run tests & run the app from root of app source folder:
```
gradle
cd build/libs
java -jar drawing_app_java-all.jar
```

2.1 You should see the following console output once the above runs successfully:
```
➜ ~/g/d/b/libs master ✓ java -jar drawing_app_java-all.jar
+++++++++++++++++++++++++++++++++++


WELCOME to the Drawing App.


How to use:
============
        C - format: "C w h". Creates a new canvas of width w and height h. w and h must be greater than 0 and less than or equals to 30
        L - format: "L x1 y1 x2 y2". Creates a new line of 'x' from (x1,y1) to (x2,y2). Only supports horizontal or vertical lines.
        R - format: "R x1 y1 x2 y2". Creates a new rectangle, (x1,y1) is upper left corner & (x2,y2) is lower right corner.
        B - format: "B x y c". Fills the entire area around (x,y) with "colour" c. Same as that of the "bucket fill" tool in paint programs.
        Q - Quit
enter command:
```
