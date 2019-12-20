<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script>
		var sock = new SockJS("http://localhost:9000/spring/stompTest"); // endpoint
		var client = Stomp.over(sock);

		client.connect({}, function() {
			console.log("!!!");
			console.log("Connected stompTest!");
			// Controller's MessageMapping, header, message(자유형식)
			client.send('/TTT', {}, "msg: Haha~~~");

			// 해당 토픽을 구독한다!
			client.subscribe('/topic/message', function(event) {
				console.log("!!!!!!!!!!!!event>>", event)
			});
		});
	</script>
</body>
</html>