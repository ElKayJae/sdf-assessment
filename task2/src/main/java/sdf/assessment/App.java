package sdf.assessment;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class App 
{
    // mvn compile exec:java -Dexec.mainClass="sdf.assessment.App" -Dexec.args=""
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
            
            Socket socket = new Socket("68.183.239.26",80);
            System.out.println("connected");
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);

            String serverInput = ois.readUTF();
            System.out.println(serverInput);
            String[] splitInput = serverInput.split(" ");
            String requestId = splitInput[0];
            String[] numbers = splitInput[1].split(",");
            Float sum = 0f;
            for (int i = 0; i < numbers.length; i++) {
                sum += Float.parseFloat(numbers[i]);
            }
            System.out.println(sum);
            Float result = sum / ((float) numbers.length);
            System.out.println(result);

            oos.writeUTF(requestId);
            oos.writeUTF("Low Ke Jun");
            oos.writeUTF("lowkejun@gmail.com");
            oos.writeFloat(result);
            oos.flush();

            Boolean serverResponse = ois.readBoolean();

            if(serverResponse)
                System.out.println("SUCCESS");
                else System.out.println("FAILED");
            
            oos.close();
            ois.close();
            socket.close();


        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
