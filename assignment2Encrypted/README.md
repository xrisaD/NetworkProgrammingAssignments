# Guessing game
A guessing game with sockets extended to use encryption with a certificate.
<br> <br>
Keytool commands used to make self signed certificates:
```
keytool -genkey -alias id1212 -keyalg RSA -keypass guess -storepass changeit -keystore keystore.jks -ext "SAN=dns:guess.localhost"
  
# exports cert for https://game.localhost to "server.cer" for import in browser
keytool -export -alias id1212 -storepass guess -file server.cer -keystore keystore.jks
```
