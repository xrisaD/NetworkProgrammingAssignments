# Guessing game
A guessing game with sockets extended to use encryption with a certificate.
<br> <br>
Keytool commands used to make self signed certificates:
```
keytool -genkey -alias id1212 -keyalg RSA -keypass guess -storepass changeit -keystore keystore.jks -ext "SAN=dns:guess.localhost"
  
keytool -export -alias id1212 -storepass guess -file server.cer -keystore keystore.jks
```

The server can be accessed using the follows url: `https://game.localhost/index.html`
