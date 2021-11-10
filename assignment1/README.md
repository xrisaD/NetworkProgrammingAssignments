# Chat application using sockets

A simple chat program consisting of a server and a client where the server forwards incoming messages from a client to all other clients. Specifically regarding threads, they should be as follows:

* ChatClient.java: Has two threads, one to listen for incoming messages from the server and one to send messages to the server.
* ChatServer.java: Has one thread to each of the clients currently connected but also one thread to listen for new incoming connections from new clients.
