@echo off
REM Make sure that your PATH contains your JDK installation
if exist HW2.jar del HW2.jar
javac *.java
jar cfm HW2.jar manifest.txt *.class *.fxml
del *.class