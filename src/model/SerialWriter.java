package model;
import java.io.IOException;
import java.io.OutputStream;
public class SerialWriter implements Runnable {
           	public OutputStream out;
           	
           	public SerialWriter(OutputStream out, Temperature t) {
                        this.out = out;
           	}
           	
           	public void send(int i){
                          	try {
                                        	System.out.println("Donn�e envoy�e : "+i);
                                        	this.out.write(i);
                          	} catch (IOException e) {
                                        	e.printStackTrace();
                          	}
                          	try {
                                        	Thread.sleep(10);
                          	} catch (InterruptedException e) {
                                        	// TODO Auto-generated catch block
                                        	e.printStackTrace();
                          	}
           	}
           	
           	public void run(){}
           	       	
}

