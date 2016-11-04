$(function() {
        
	var url = "ws://localhost:8080/CA2/noteSocket";
        
	var socket = new WebSocket(url);
        

        
        $('#addBtn').attr('onclick',alert("SEnding !!!") ,socket.send('msg'));
        
        
	socket.onopen = function() {
            alert("Conn");
	}
	socket.onclose = function() {
            alert("Diconn");
	}

});
