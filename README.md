### https://github.com/TheParadoxTurtle/JBJ-Torrent
### Group members:
### Jeffrey Lai, Benjamin Tong, Jefferson Zou
###
### JBJ-Torrent (JeffBenJeff-Torrent)

A torrenting network using a single server (TrackerServer) and any number of peers (ClientNode).

# Running the code 

To start the server, use

TrackerServer (port),

where (port) is the port the server is run on.

To start a node, use

ClientNode (server ip) (server port) (client port) (mode),

where (server ip) and (server port) are the ip address and port of the server, 
(client port) is the port the node should be run on, 
and (mode) is one of "leecher" or "seeder". 
A SEEDER is a node that has a complete file.
A LEECHER is a node that does not have a complete file, or no file at all.

To seed a file, one should start the client (after the server already up) in seeder mode and the type in the command 

seed <filename>

To start torrenting a file, one start the client (after the server is already up and the file has been seeded by at least one person) in leecher mode and type the command

<strategy> <filename>

where <strategy> is one of 'basic' or 'random'

# Design

Each node has a listening thread (see ClientListeningThread) that it uses to
receive messages from the server and other nodes. Thus, when a node sends a message
it creates a TCP connection and then closes it, but when the node receives
a response, this response is on a new TCP connection.

When the all pieces of a file have been completed, the new file is stored in the
current directory with its filename prefixed with "jbj_torrent_".

Each LEECHER node undergoes the following for each file in question:
1. The node tells the server they exist and gets a list of all its neighbors. 
This list is updated whenever a new neighbor communicates with the node.
2. The node selects a random neighbor and a random piece it does not have
completed that the neighbor has. 
	a. If the neighbor has not unchoked us, we just send an interested message.
	b. If the neighbor has unchoked us, we request the piece from them.
3. At the same time, if the node receives an interested message, it will unchoke
them if the number of pieces they have that the node needs is at least 3 times
as many as the number of pieces the node is missing. Otherwise, it will unchoke
with probability 1/(2n), where n is the number of neighbors.

## For more information look at the report
