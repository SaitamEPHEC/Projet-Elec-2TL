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
      	int len;
      	try {
                    	while ((len = this.in.read(buffer)) > -1) {
                    		int hexaToInt = Integer.parseInt(buffer[0] + "",16);
                    		System.out.print("Donnee recue :" + hexaToInt +"\n");
                    		t.setTemperatureActuelle(hexaToInt);
                    		t.traiteData(hexaToInt);
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
