import java.io.*; 
import java.net.*; 

class Client { 
    public static void main(String args[]) throws Exception 
    { 
        if (args.length != 2){
			System.out.println("Usage: TCPClient <Server IP> <Server Port>");
			System.exit(1);
	    }
        
        // Initialize a client socket connection to the server
        Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1])); 

        // Initialize input and an output stream for the connection(s)
        DataOutputStream outBuffer = new DataOutputStream(clientSocket.getOutputStream()); 
        BufferedReader inBuffer    = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

        // Initialize user input stream
        String line = "";
        boolean terminated = false;
        BufferedReader inFromUser  = new BufferedReader(new InputStreamReader(System.in)); 
        
        while (!terminated)
	    {
        	System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
	        // Waits until something is read from user or server
			while((!inFromUser.ready() && !inBuffer.ready())){		
			}
			if(inFromUser.ready()){
			    line = inFromUser.readLine();
			}else if(inBuffer.ready()){
			    line = inBuffer.readLine();
			}
			
			if (line.contains("send file")){
				// Send input to the server
	        	outBuffer.writeBytes(line + '\n'); 
	        	
	        	// Write input from server to a file				
	        	File file =  new File("file.ser");
            	FileOutputStream fout = new FileOutputStream(file);
        		ObjectOutputStream oos = new ObjectOutputStream(fout);
        		
        		// Get serialized server output from buffer
        		ObjectInputStream test = new ObjectInputStream(clientSocket.getInputStream());
        		
        		// Write serialized content to file.ser
        		oos.writeObject(test.readObject());
        		oos.close();
        		
        		System.out.println("Done");
        		
        		// Deserialize the file
        		FileInputStream fin =  new FileInputStream(file);
        		ObjectInputStream ois =  new ObjectInputStream(fin);
        		myObject obj2 = (myObject)ois.readObject();
        		
        		
        		System.out.println(obj2.firstName);
        		
        		// cleans up buffer
        		line = inBuffer.readLine();

        		
        		//close file
        		fin.close();
			}else{
				if(line.equals("logout")){
					terminated = true;
				}
				// Send user input to the server
	        	outBuffer.writeBytes(line + '\n'); 
	        	
				// Getting response from the server
				line = inBuffer.readLine();
				
				System.out.println("\nServer: " + line);
			}
	    }
	        // Close the socket
        	System.out.println("BYE BYE");
	        clientSocket.close();           
    } 
} 