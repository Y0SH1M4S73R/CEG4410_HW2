#Make sure that your PATH contains your JDK installation
rm HW2.jar
javac *.java
jar cfm HW2.jar manifest.txt *.class *.fxml
rm *.class