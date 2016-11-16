import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
@SuppressWarnings("resource")
public static void main(String args[]) throws Exception
      {
         String clientSentence;
         String capitalizedSentence;
         ServerSocket welcomeSocket = new ServerSocket(Integer.valueOf(args[0]));
         boolean terminated = false;

         if (args.length != 1){
     	    System.out.println("Enter <Listening Port>");
     	    System.exit(1);
     	}
         
         Socket connectionSocket = welcomeSocket.accept();
         BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
         DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
         
         while(!terminated)
         {
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            
            
            if(clientSentence.contains("logout")){
            	terminated = true;
            	System.out.println("BYE BYE");
            }
            if (clientSentence.contains("send file")){
            
            	clientSentence = "";
            	File file =  new File("outfile.ser");
            	myObject obj = new myObject("John");
            	FileOutputStream fout = new FileOutputStream(file);
        		ObjectOutputStream oos = new ObjectOutputStream(fout);
        		oos.writeObject(obj);
        		oos.close();
        		System.out.println("Done");
        		
        		byte[] mybytearray = new byte[(int) file.length()];

                FileInputStream fis = null;
                
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException ex) {
                    // Do exception handling
                }
                BufferedInputStream bis = new BufferedInputStream(fis);

                try {
                    bis.read(mybytearray, 0, mybytearray.length);
                    outToClient.write(mybytearray, 0, mybytearray.length);
                    outToClient.flush();
                } catch (IOException ex) {
                    // Do exception handling
                }
                System.out.println("Server Send File Complete");
            }
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
         }
      }
}