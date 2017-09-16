package fr.smartberry.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.simple.JSONObject;

public class SocketUdpClient implements Runnable {

	JSONObject json = null;
	String name = "";
	long sleepTime = 500;
	int timeout = 750;
	String ipServeur;
	int port;
	private String messageClient=""; 

	// constructeurs
	public SocketUdpClient(String name, String ipServeur, int port) {
		this.name = name;
		this.json = null;
		this.ipServeur = ipServeur;
		this.port = port;
	}

	public SocketUdpClient(JSONObject json, String ipServeur, int port) {
		this.name = "";
		this.json = json;
		this.ipServeur = ipServeur;
		this.port = port;
	}
	
	
	//Accesseurs
	public JSONObject getJson() {
		return json;
	}
	
	public void setJson(JSONObject json) {
		this.json = json;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public long getSleepTime() {
		return sleepTime;
	}
	
	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public String getIpServeur() {
		return ipServeur;
	}

	public void setIpServeur(String ipServeur) {
		this.ipServeur = ipServeur;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getMessageClient() {
		return messageClient;
	}

	public void setMessageClient(String messageClient) {
		this.messageClient = messageClient;
	}

	
	
	//Methodes
	public void run() {

		byte[] buffer = null;

		if (json != null) { // conversion avant envoi

			buffer = json.toJSONString().getBytes();

		} else { // création d'un json pour envoi et conversion avant envoi
			JSONObject jsonTest = new JSONObject();
			//TEST RENVOI VALEURS
			jsonTest.put("idArduino", name);
			jsonTest.put("action", "sendValues");
			
			messageClient = jsonTest.toJSONString();
			System.out.println(messageClient);
			buffer = jsonTest.toJSONString().getBytes();
		}

		try {
			// On initialise la connexion client
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress adresse = InetAddress.getByName(ipServeur);

			// On crée le paquet à envoyer
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, adresse, port);
			packet.setData(buffer);

			// On envoie au serveur
			clientSocket.send(packet);
			System.out.println("paquet envoyé");
			
			// On prépare le paquet à recevoir
			byte[] bufferR = new byte[8192];
			DatagramPacket packetR = new DatagramPacket(bufferR, bufferR.length);
			clientSocket.setSoTimeout(timeout);
			clientSocket.receive(packetR); // On attend la reception d'un paquet jusqu'au timeout

			// on récupère le contenu du paquet reçu et on l'affiche
			messageClient = new String(packetR.getData(), 0, packetR.getLength()); // conversion des bytes re�us en string
			System.out.println("Message Reçu de la part de " + packetR.getAddress() + " sur le port " + packetR.getPort() + " : ");
			System.out.println(messageClient);

			// On réinitialise la taille du datagramme, pour les futures réceptions
			packetR.setLength(buffer.length);
			clientSocket.close();


		} catch (SocketTimeoutException e) {
			messageClient = "reponse non reçue";
			System.out.println("reponse non reçue de " + ipServeur);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
