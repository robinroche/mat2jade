function y = tcp_receive_function(t)
% Thanks to Sifakis Nikolaos for imrovement suggestions 
 
message = '';
content = '';
 
% Implement a waiting process
while t.BytesAvailable == 0
    pause(1)
end
% Receive lines of data from server
while (get(t, 'BytesAvailable') > 0) 
DataReceived = fread(t,t.BytesAvailable);
message = strcat(message,DataReceived');
end
 
if(size(message)>0)
    content = textscan(message, '%*s %s %*s', 'delimiter', '"');    
end
 
y = content;
