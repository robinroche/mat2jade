package javafiles;


import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;


public class LaunchJade 
{

	static ContainerController cController;

	public static void main(String[] args)
	{
		try 
		{
			runJade();
		} 
		catch (StaleProxyException e) {}	
	}

	
	public static void runJade() throws StaleProxyException
	{

		// Launch JADE platform
		Runtime rt = Runtime.instance();
		Profile p;
		p = new ProfileImpl();
		cController = rt.createMainContainer(p);			
		rt.setCloseVM(true);
		
		// Launch initial agent
		addAgent("TcpTestAgent", "javafiles.TcpTestAgent");
		
		// Launch tool agents
		// addAgent("spa", "jade.tools.SocketProxyAgent.SocketProxyAgent");
		// addAgent("rma", "rma.class.getName()");
		// addAgent("rma", "Sniffer.class.getName()");
		
	}
	
	
	public static void addAgent(String name, String type) throws StaleProxyException 
	{		
		AgentController ac = cController.createNewAgent( name, type, null);
		ac.start();
	}
	
		
} // End class


