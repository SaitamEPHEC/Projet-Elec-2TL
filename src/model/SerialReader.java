package model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Observable;

public class SerialReader extends Observable implements Runnable {
   	private InputStream in;
   	private int typeOfData=-50;
   	private Thermo t;
   	private String message; 
   	
   	public SerialReader(InputStream in,Thermo t) {
                  	this.in = in;
                  	this.t=t;
   	}
   	public void traiteData(int x) {
                  	if(typeOfData==-50){
                                	//typeOfData="-60";
                                	if(x == -60){
                                               	typeOfData=-60;
                                               	}
                                	if(x == -70){
                                               	typeOfData=-70;
                                               	}
                                	if(x == -80){
                                               	typeOfData=-80;
                                               	}
                                	System.out.println(typeOfData);
                  	}
                  	else{
                                	if(typeOfData==-60){
                                		t.setMin(x);
                                		typeOfData=-50;
                                	}
                                	if(typeOfData==-70){
                                		t.setMax(x);
                                		typeOfData=-50;
                                	}
                                	if(typeOfData==-80){
                                		t.setTemperature(x);
                                		typeOfData=-50;
                                	}
                                	System.out.println(typeOfData+" : "+x);
                                	if(t.getTemperature()>t.getMin() && t.getTemperature()<t.getMax()){
                                		message = "Température dans les normes";
                                		//t.lblOknok.setText("Température dans les normes");
                                		setChanged();
                        				notifyObservers();
                                	}
                                	if(t.getTemperature()<t.getMin()){
                                		message = "Inférieur au seuil minimum !";
                                        //t.lblOknok.setText("Inférieur au minimum !");
                                		setChanged();
                        				notifyObservers();
                                	}
                                	if(t.getTemperature()>t.getMax()){
                                		message = "Supérieur au seuil maximum !";
                                        //t.lblOknok.setText("Maximum dépassé !");
                                		setChanged();
                        				notifyObservers();
                                	}
                                	
                  	}
   	}
   	public void run() {
                  	byte[] buffer = new byte[8];
                  	int len=-1;
                  	float newint = 0;
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
	public Thermo getT() {
		return t;
	}
	public void setT(Thermo t) {
		this.t = t;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
   	
}
