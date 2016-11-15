import java.io.*; 
import java.net.*;
import java.nio.file.*; 

class Client { 

    public static void main(String args[]) throws Exception 
    { 
        if (args.length != 2)
	    {
		System.out.println("Usage: TCPClient <Server IP> <Server Port>");
		System.exit(1);
	    }

        // Initialize a client socket connection to the server
        Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1])); 

        // Initialize input and an output stream for the connection(s)
        DataOutputStream outBuffer = new DataOutputStream(clientSocket.getOutputStream()); 
        BufferedReader inBuffer    = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

        // Initialize user input stream
        String line;
        char fileChar; 
        int fileCharInt = 0;
        BufferedReader inFromUser  = new BufferedReader(new InputStreamReader(System.in)); 

        // Get user input and send to the server
        System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
        line = "it works yay"; 
        while (!line.equals("logout"))
	    {
		// Send to the server
		outBuffer.writeBytes(line + '\n'); 
            
		// Getting response from the server
		line = inBuffer.readLine();
            
		if(!line.equals("terminate"))
		    {
			System.out.println("\nServer: " + line);
		    }
		while (inBuffer.ready())
		    {
			line = inBuffer.readLine();
			System.out.println(line);
		    }
            
		System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
            
		while((!inFromUser.ready() && !inBuffer.ready())){		
		}
		if(inFromUser.ready()){
		    line = inFromUser.readLine();
		}
		else if(inBuffer.ready()){
		    line = inBuffer.readLine();
		}
	    }
        // Close the socket
        clientSocket.close();           
    } 
} 