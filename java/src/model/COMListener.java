package model;

import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;


public class COMListener {
   	public InputStream in;
   	public OutputStream out;
   	private int rate=9600;
   	public COMListener(String portName) throws Exception {
                  	super();
                  	CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
                  	if (portIdentifier.isCurrentlyOwned()) {
                                	System.out.println("Error: Port is currently in use");
                  	}
                  	else {
                                	CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
                                	if (commPort instanceof SerialPort) {
                                               	SerialPort serialPort = (SerialPort) commPort;
                                               	serialPort.setSerialPortParams(rate, SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                                               	in = serialPort.getInputStream();
                                               	out = serialPort.getOutputStream();
                                	}
                                	else {System.out.println("Error: Only serial ports are handled by this example.");
                                	}
                  	}
   	}
}
