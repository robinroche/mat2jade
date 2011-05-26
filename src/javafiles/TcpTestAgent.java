/*
 * jMatTcpJade
 * Robin Roche, 2011
 * Establishes a TCP connection with a JADE agent in Java
 * */


package javafiles;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.StringACLCodec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpTestAgent extends Agent
{

	private static final long serialVersionUID = -4394243932169660776L;
	
	
	// Class variables
	ServerSocket srvr = null;
	Socket skt = null;
	BufferedReader in;
	PrintWriter out;
	int value = 0;

	
	// Constructor
	public TcpTestAgent() 
	{
		super();
	}


	// Setup method
	protected void setup() 
	{
		System.out.println("Agent started");
		
		// Create the TCP connection
		try 
		{
			// Create server and socket
			srvr = new ServerSocket(1234);
			skt = srvr.accept();
			System.out.println("Server connection initiated");

			// Create writer and reader to send and receive data
			out = new PrintWriter(skt.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		
		// Run behavior
		CommWithMatlab commWithMatlab = new CommWithMatlab();
		addBehaviour(commWithMatlab);
		
	} // End setup

	
	class CommWithMatlab extends SimpleBehaviour
	{

		private static final long serialVersionUID = 8966535884137111965L;

		
		@Override
		public void action() 
		{	
			
			// Prepare the message to send
			String msgContentTest = "" + value;
			System.out.println("Message sent to Matlab: " + msgContentTest);
			
			// Get the answer and display it
			String msgReceived;
			msgReceived = callMatlab(msgContentTest);
			System.out.println("Message received from Matlab: " + msgReceived);
			
			// Increment the test variable
			value++;
			
		} // End action


		@Override
		public boolean done() 
		{
			return false;	
		}

	} // End behavior
		
	
	@Override
	protected void takeDown()
	{
		System.out.println("Agent being taken down");
		
		// Close writer and socket
		try 
		{
			out.close();
			in.close();
			skt.close();
			srvr.close();
		} 
		catch (IOException e) {	e.printStackTrace(); }
	}


	public String callMatlab(String msgContent) 
	{
		
		ACLMessage msg;
		String matlabAnswer = "";

		while(!matlabAnswer.equals(msgContent))
		{
			String ack = "";

			while(!ack.equals("ok"))
			{
				// Send the message to Matlab via JADE
				msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(new AID("localhost:1234", AID.ISGUID));
				msg.setContent(msgContent);

				// Encode message to send as an ACL Message
				StringACLCodec codec = new StringACLCodec(in, out);
				codec.write(msg);
				out.flush();

				// Wait for ACK message
				try 
				{
					ack = in.readLine().toString();
					in.reset();
					System.out.println("ack = " + ack);
				} 
				catch (IOException e1) {}
			}

			// Wait for its answer
			try 
			{
				while (!in.ready()) {}
				matlabAnswer = matlabAnswer + in.readLine().toString();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

		}
		
		return matlabAnswer;
		
	} // End callMatlab
	
}
