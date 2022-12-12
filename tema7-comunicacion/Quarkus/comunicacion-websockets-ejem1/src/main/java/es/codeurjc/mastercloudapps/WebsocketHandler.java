package es.codeurjc.mastercloudapps;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;

@ServerEndpoint("/notifications")
@ApplicationScoped
public class WebsocketHandler {

    Logger LOG = Logger.getLogger(WebsocketHandler.class);

    Session session;

    @OnOpen
    public void onOpen(Session session) {
        LOG.info("Connection established...");
        session.getAsyncRemote().sendObject("Connection established", result ->  {
            if (result.getException() != null) {
                LOG.error("Unable to send message: " + result.getException());
            }
        });
        this.session = session;
    }

    @OnClose
    public void onClose(Session session) {
        LOG.info("Session disconnected...");
        this.session = null;
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.error("Error: " + throwable.getMessage());
        this.session = null;
    }

    @OnMessage
    public void onMessage(String message) {
        LOG.info("Message received: " + message);
        session.getAsyncRemote().sendObject("Echo: " + message, result -> {
            if(result.getException() != null) {
                LOG.error("Exception sending message: " + result.getException());
            }
        });
    }

}