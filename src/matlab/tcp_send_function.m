function y = tcp_send_function(t,text)

% Transmit data to the server (or a request for data from the server). 
fprintf(t, text); 

end