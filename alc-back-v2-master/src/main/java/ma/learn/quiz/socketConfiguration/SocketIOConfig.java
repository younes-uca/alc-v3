package ma.learn.quiz.socketConfiguration;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import ma.learn.quiz.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@CrossOrigin("*")
@Component
@Log4j2
@org.springframework.context.annotation.Configuration
public class SocketIOConfig {
    private SocketIOServer server;
    public Map<String, List<String>> sessions = new ConcurrentHashMap<>();

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8088);
        config.setMaxHttpContentLength(5 * 1024 * 1024); // 5MB
        config.setPingTimeout(60000); // set the ping timeout to 1min
        config.setPingInterval(25000); // set the ping interval to 25s
        config.setAllowCustomRequests(true);
        config.setUpgradeTimeout(60000); // set the upgrade timeout to 1h
        server = new SocketIOServer(config);
        server.addEventListener("message", String.class, onSendMessage);
        server.addEventListener("userConnect", User.class, onConnect);
        server.addEventListener("userDisconnect", User.class, onDisConnect);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {

                HandshakeData handshakeData = client.getHandshakeData();
                String userId = handshakeData.getSingleUrlParam("userId");
                String key = handshakeData.getSingleUrlParam("key");
                log.info("new user connected with id =  " + userId);
                log.info("new user connected with key  =  " + key);
                if (userId == null || key == null) {
                    // Connected user has ID userId
                    // Do something with the user ID
                } else {
                    if (sessions.containsKey(key)) {
                        List<String> existingList = sessions.get(key);
                        if (!existingList.contains(userId)) existingList.add(userId);
                    } else {
                        sessions.computeIfAbsent(key, k -> new ArrayList<>()).add(userId);
                    }
                    System.out.println("MAP SIZE FOR KEY= " + key + " IS " + sessions.get(key).size());
                }
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                HandshakeData handshakeData = client.getHandshakeData();
                String userId = handshakeData.getSingleUrlParam("userId");
                String key = handshakeData.getSingleUrlParam("key");
                log.warn("USER DISCONNECT");
                System.out.println("userId = " + userId);
                System.out.println("key = " + key);
                if (userId != null && key != null) {
                    if (sessions.containsKey(key)) {
                        List<String> existingList = sessions.get(key);
                        existingList.remove(userId);
                        if (existingList.size() == 0) {
                            log.info("This sessions is finished");
                            sessions.remove(key);
                        }
                    }
                }
            }
        });
        server.start();
        return server;
    }


    public DataListener<User> onConnect = (socketIOClient, user, ackRequest) -> {
        System.out.println(socketIOClient.getSessionId());
        log.error(user.getUsername() + " connected");
        ackRequest.sendAckData("You are connected");
    };


    public DataListener<User> onDisConnect = (socketIOClient, user, ackRequest) -> {
        System.out.println(socketIOClient.getSessionId());
        ackRequest.sendAckData("You are disconnected");
        log.error(user.getUsername() + " disconnected");
    };


    public DataListener<String> onSendMessage = new DataListener<String>() {
        @Override
        public void onData(SocketIOClient client, String message, AckRequest acknowledge) throws Exception {
            Message messageObject = new Gson().fromJson(message, Message.class);
            log.info(messageObject.getKey());

            // Convert the message to a byte array
            byte[] messageBytes = message.getBytes();

            // Get the size of the message in bytes
            int messageSize = messageBytes.length;
            System.out.println("messageSize = " + messageSize);

            /**
             * Sending message to target user
             * target user should subscribe the socket event with his/her name.
             * Send the same payload to user
             */
            List<String> clientId = sessions.get(messageObject.getKey());
            for (String str : clientId
            ) {
                Stream<SocketIOClient> clientStream = server.getAllClients().stream().filter(d ->
                        d.getHandshakeData().getSingleUrlParam("userId").equals(str));
                SocketIOClient ioClient;
                try {
                    ioClient = clientStream.findAny().get();
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                    log.error("Client not found !");
                    return;
                }
                HandshakeData handshakeData = ioClient.getHandshakeData();
                String userId = handshakeData.getSingleUrlParam("userId");
                String key = handshakeData.getSingleUrlParam("key");
                System.out.println("------------INFORMATION OF CLIENT ------------------------------------");
                System.out.println("key = " + key);
                System.out.println("userID = " + userId);
                System.out.println("----------------------------------------------------------------------");
                ioClient.sendEvent("message", message);

            }

            /**
             * After sending message to target user we can send acknowledge to sender
             */
            acknowledge.sendAckData("Message send to target user successfully");
        }
    };


    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }

    public List<String> getConnectedUsers(String key) {
        return sessions.get(key);
    }
}
