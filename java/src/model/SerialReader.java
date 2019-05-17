package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;

public class SerialReader extends Observable implements Runnable {
   	private InputStream in;
   	private Temperature t;
   	
   	public SerialReader(InputStream in,Temperature t) {
      	this.in = in;
      	this.t=t;
   	}
   	
   	public void run() {
      	byte[] buffer = new byte[4];
      	try {
      		int len;
        	while ((len = this.in.read(buffer)) > -1) {
        		if(len != 0) { 
        		String stringBuffer = new String (buffer,0,len);
        		int intBuffer = Integer.parseInt(stringBuffer);
        		System.out.print("Donnee recue :" + stringBuffer +"\n");
        		t.setTemperatureActuelle(intBuffer);
        		t.traiteData(intBuffer);
        		}
                        try {
                        	Thread.sleep(1000);
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
