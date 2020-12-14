# NASAForFevo
To run the code: 
- Make sure you have Apache Maven Setup (Refer here for guidance- https://maven.apache.org/install.html)
- Either clone to local or download as Zip and extract all files from the Zip.
- Open a terminal and open the project folder (NASAForFevo-main\NASAForFevo-main\NASAForFevo)
- in command line type 'mvn install' and hit enter
- Upon successful build completion, open the 'target' folder within the project folder (cd target)
- Use the following command to execute the code "java -jar NASAforFEVO-0.0.1-SNAPSHOT.jar"
- Once the service is up in your browser try to hit the following 2 endpoints:
  * http://localhost:8080/api/v1/GetImagesLastTenDays?roverName=curiosity&camName=fhaz
  * http://localhost:8080/api/v1/GetImageByDate?date=2020.12.11&roverName=curiosity&camName=navcam

Thank you for taking the time to go through my code, please do leave your feedback/comments
