% jMatTcpJade
% Robin Roche, 2011
% Establishes a TCP connection with a JADE agent in Java


% Create TCP/IP object 't'. Specify server machine and port number. 
t = tcpip('localhost', 1234);

% Open connection to the server.
pause(0.1)
fopen(t);
disp('Connection established')


while(1)


%% Prepare to receive a message from JADE    
    
ack = '';
while(strcmp(ack,'ok')==0)

% Receive a message from JADE
val = tcp_receive_function(t);
%disp(val)

if(size(val)>0)
    if(size(val{1},1)==3)
        msg = val{1}{3};
        ack = 'ok';
    else
        ack = 'error';
        pause(0.1);
    end
else
    ack = 'error';
    pause(0.1);
end

% Send acknowledgment
tcp_send_function(t,ack);

end


%% Data processing

% Display it
disp('Message from JADE:')
disp(msg)


%% Send a message back to JADE

% Format it to be sent back to JADE
resultsString = msg;

% Send the results back to JADE
tcp_send_function(t,resultsString);

% Pause for 1 second
pause(1)

end


%% End the TCP connection

% Disconnect and clean up the server connection. 
fclose(t); 
delete(t); 
clear t 
