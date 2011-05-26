function y = tcp_receive_function(t)

message = '';
content = '';

% Receive lines of data from server 
while (get(t, 'BytesAvailable') > 0) 
DataReceived = fread(t,t.BytesAvailable); 
message = strcat(message,DataReceived');
end

if(size(message)>0)
    content = textscan(message, '%*s %s %*s', 'delimiter', '"');    
end

y = content;