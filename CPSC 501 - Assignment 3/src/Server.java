import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
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
            
            if(clientSentence.contains("exit")){
            	terminated = true;
            }
            
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
         }
      }
}