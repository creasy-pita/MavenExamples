pom.xml bind including or exclude files or directroies

step
+ 1 pom.xml set exclude resource element which is in build element 
+ 2 mvn clean
+ 3 mvn package
+ 4 //inspect the files inside the jar use jar command line which is provide in jdk
    %java_home%\bin\jar -tf  %ProjectDirectory%\target\PomResourceExclude-1.0.jar   
