cd javafx-sdk-18.0.1/lib
set LIB="%cd%"
cd ..
cd ..
java -jar --module-path %LIB% --add-modules javafx.controls,javafx.base,javafx.graphics,javafx.media,javafx.fxml,javafx.web App.jar
pause