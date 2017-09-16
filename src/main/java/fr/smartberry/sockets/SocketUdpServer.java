package fr.smartberry.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SocketUdpServer implements Runnable {
	private DatagramSocket server;
	private String messageClient; 
	private boolean isStarted = false;
	
	//constructeurs
	public SocketUdpServer(DatagramSocket server) {
		this.server = server;
	}

	//Accesseurs
	public DatagramSocket getServer() {
		return server;
	}

	public void setServer(DatagramSocket server) {
		this.server = server;
	}

	public  String getMessageClient() {
		return messageClient;
	}

	public  void setMessageClient(String messageClient) {
		this.messageClient = messageClient;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	//Methodes
	@Override
	public void run() {

		try {
			while (isStarted) {

				// On prépare le paquet à recevoir
				byte[] buffer = new byte[8192];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				server.receive(packet); // On attend la reception d'un paquet (cette methode bloque le thread jusqu'� reception de quelquechose)

				// on récupère le contenu du paquet reçu et on l'affiche
				messageClient = new String(packet.getData(), 0, packet.getLength()); // conversion des bytes re�us en string
				System.out.println("Reçu de la part de " + packet.getAddress() + " sur le port " + packet.getPort() + " : ");
				System.out.println(messageClient);

				// On réinitialise la taille du datagramme, pour les futures réceptions
				packet.setLength(buffer.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}