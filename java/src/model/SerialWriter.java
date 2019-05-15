package model;
import java.io.IOException;
import java.io.OutputStream;
public class SerialWriter implements Runnable {
           	public OutputStream out;
           	
           	public SerialWriter(OutputStream out) {
                        this.out = out;
           	}
           	
           	public void send(int i){
                          	try {
                          					String temp = "" + i;
                                        	System.out.println("Donneee envoyee : "+i);
                                        	this.out.write(temp.getBytes());
                          	} catch (IOException e) {
                                        	e.printStackTrace();
                          	}
                          	try {
                                        	Thread.sleep(100);
                          	} catch (InterruptedException e) {
                                        	// TODO Auto-generated catch block
                                        	e.printStackTrace();
                          	}
           	}
           	
           	public void run(){}
           	       	
}

