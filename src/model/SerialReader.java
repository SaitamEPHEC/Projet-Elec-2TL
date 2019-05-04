package model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Observable;

public class SerialReader extends Observable implements Runnable {
   	private InputStream in;
   	private int typeOfData=-50;
   	private Temperature t;
   	private String message; 
   	
   	public SerialReader(InputStream in,Temperature t) {
      	this.in = in;
      	this.t=t;
   	}
   	
   	public void traiteData(int x) {
    	if(t.getTemperature()<t.getMax()){
    		message = "Température dans les normes";
    		setChanged();
			notifyObservers();
    	}
    	if(t.getTemperature()>t.getMax()){
    		message = "Supérieur au seuil maximum !";
    		setChanged();
			notifyObservers();
    	}
   	}
   	
   	public void run() {
      	byte[] buffer = new byte[8];
      	int len=-1;
      	int newint = 0;
      	try {
                    	while ((len = this.in.read(buffer)) > -1) {
                                   	newint+=ByteBuffer.wrap(buffer).getFloat();
                                   	System.out.println(newint);
                                    System.out.print("Donnée reçue :" + Math.round(newint)+"\n");
                                    traiteData(Math.round(newint));
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
	public int getTypeOfData() {
		return typeOfData;
	}
	public void setTypeOfData(int typeOfData) {
		this.typeOfData = typeOfData;
	}
	public Temperature getT() {
		return t;
	}
	public void setT(Temperature t) {
		this.t = t;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
   	
}
