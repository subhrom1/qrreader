# Secure QR Code Reader
This is a Proof of Concept implementation of research on Scrambled QR Codes.
Scrambled QR codes have an additional random masking applied using a 256 bit key.
This makes their scan possible only with readers that posses the key that was used
for masking.

### Reference Documentation
To run this application, type the following command from its base directory:
./mvnw spring-boot:run
The QR Reader application will start running on localhost:8080.
The application is tested to be compatible with JDK 8 using Maven 3.8.2.

