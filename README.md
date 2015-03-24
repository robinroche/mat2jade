# mat2jade

A basic interface between JADE agents and Matlab using TCP/IP. 

### Licence

None. Feel free to use it as you wish.

### Context

Developing smart control strategies for power systems requires testing their behavior and performance, at first in simulation, as shown in a recent [paper](http://dx.doi.org/10.1109/DEXA.2012.9). To do that, you need a simulation tool capable of advanced analysis (e.g., power flow) and of advanced and customizable control algorithms. 

As we could not find any satisfactory existing solution, we developed an interface between two/three software to achieve it: [JADE](http://jade.tilab.com/) / [Matlab](http://www.mathworks.com/products/matlab/) / [PowerWorld Simulator](http://www.powerworld.com/).

This interface was developed in collaboration with Colorado State University's [Dr. Suryanarayanan](http://www.engr.colostate.edu/~ssuryana).

![Interface between JADE, Matlab and PowerWorld Simulator](http://robinroche.com/webpage/images/Jadepw.png)

mat2jade is only a portion of the whole interface, as it only concerns the interface between Matlab and JADE. See [mat2pws](https://github.com/robinroche/mat2pws) and [jade2pws](https://github.com/robinroche/jade2pws) for the other parts of the whole interface.

### Interface concept

This script is a basic interface between Matlab and JADE agents. It can be easily tested and modified.

### Code structure

- In JADE:
  - LauncheJade.java: A simple class that runs JADE and the test agent.
  - TcpTestAgent.java: An agent that exchanges data with Matlab through TCP.

- In Matlab:
  - tcpTest.m: The main file for the Matlab side.
  - tcp_send_function.m: A function that sends data through TCP.
  - tcp_receive_function.m: A function that receives data through TCP. 


### Using the code

The following software are required:

- Matlab (tested with R2011b) with the Instrument Control Toolbox. This toolbox is only used for the TCP functions. If you find another way to use TCP communication with Matlab, you may not need this toolbox.
- JADE (tested with 4.0) libraries. 

Code use instructions are as follows:

1. Get the mat2jade files.
2. Import them to your favorite IDE, like Eclipse.
3. Get JADE jar libraries.
4. Include the libraries to the mat2jade project.
6. Run the JADE program with the Launcher class.
7. In Matlab, open the tcpTest.m file and run it.
8. The communication should then be established and data should be exchanged. You should see things displaying in the console. If not, well, there is a problem somewhere.

Please cite one of my papers if you use it, especially for research: http://dx.doi.org/10.1109/DEXA.2012.9

### Sample output

When running the test, you should see in the java console:
Agent started
Server connection initiated
Message sent to Matlab: 0
Message received from Matlab: 0
Message sent to Matlab: 1
Message received from Matlab: 1
etc.

And in Matlab:
Connection established
Message from JADE:
0
Message from JADE:
1
Message from JADE:
2
etc.

### Limitations 

- No extensive testing has been done, so use it at your own risk. 
- If you find bugs, errors, etc., or want to contribute, please let me know.

### Contact

Robin Roche - robinroche.com
