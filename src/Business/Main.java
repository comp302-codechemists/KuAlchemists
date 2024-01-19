package Business;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Screens.WelcomeFrame;

public class Main {
	
    public static void main(String[] args) throws UnknownHostException {
    	InetAddress address = InetAddress.getLocalHost();         
    	System.out.println("IP address: " + address.getHostAddress());
    	new WelcomeFrame();

    }

}

