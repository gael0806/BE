package fr.smartberry.sockets;

import java.net.DatagramSocket;
import java.net.SocketException;

import org.json.simple.JSONObject;

public class SocketUdpThread {

	private static SocketUdpClient mySUC;
	private static SocketUdpServer mySUS;

	// constructeurs
	public SocketUdpThread() {
	}

	public static void envoyerClient(String name, String ipServeur, int port) {

		mySUC = new SocketUdpClient(name, ipServeur, port);
		Thread cli1 = new Thread(mySUC);
		cli1.start();
	}

	public static void envoyerClient(JSONObject json, String ipServeur, int port) {

		mySUC = new SocketUdpClient(json, ipServeur, port);
		Thread cli1 = new Thread(mySUC);
		cli1.start();
	}

	public static void demarrerServeur(int port) {

		try {
			DatagramSocket server = new DatagramSocket(port);
			mySUS = new SocketUdpServer(server);
			mySUS.setStarted(true);
			System.out.println("d�marrage du serveur en UDP, port d'�coute : " + port);
			Thread t = new Thread(mySUS);
			t.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public static String getMessage() {
		return mySUC.getMessageClient();

	}

	public static void arreterServeur() {
		mySUS.setStarted(false);
	}
}
