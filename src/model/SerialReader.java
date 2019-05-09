package model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Observable;

public class SerialReader extends Observable implements Runnable {
   	private InputStream in;
   	private Temperature t;
   	
   	public SerialReader(InputStream in,Temperature t) {
      	this.in = in;
      	this.t=t;
   	}
   	
   	public void run() {
      	byte[] buffer = new byte[16];
      	int len=-1;
      	float tempFloat = 0;
      	int tempInt;
      	try {
                    	while ((len = this.in.read(buffer)) > -1) {
                    		System.out.print("Donnée reçue :" + buffer[0] +"\n");
                    		t.setTemperatureActuelle(buffer[0]);
                    		t.traiteData(buffer[0]);
                    			
                    		
                    				tempFloat = ByteBuffer.wrap(buffer).getFloat();
//                                  	System.out.println(ByteBuffer.wrap(buffer).getInt());
                                   	tempInt = Math.round(tempFloat);
//                                    System.out.print("Donnée reçue :" + tempInt +"\n");
//                                    t.setTemperatureActuelle(tempInt);
//                                    t.traiteData(tempInt);
                                    
/*                                    // ZONE DE TEST YEAYEAH
                                    System.out.println("-------------------");
                                    System.out.println(buffer[0]);
            						System.out.println(buffer[1]);
            						System.out.println(buffer[2]);
            						System.out.println(buffer[3]);
            						System.out.println(buffer[4]);	
                                    System.out.println("-------------------");                                   
*/                                    //
                                    try {
                                    	Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                    	// TODO Auto-generated catch block
                                    	e.printStackTrace();
                      	}
                    	}
      	} catch (IOException e) {
                    	// TODO Auto-generated catch block
                    	e.printStackTrace();
      	}
   	}
   	
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}

	public Temperature getT() {
		return t;
	}
	public void setT(Temperature t) {
		this.t = t;
	}
   	
}
