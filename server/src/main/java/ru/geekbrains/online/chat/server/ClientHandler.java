package ru.geekbrains.online.chat.server;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {
    private static final Logger log = LogManager.getLogger(ClientHandler.class);

    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;

    public String getUsername() {
        return username;
    }

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String msg = in.readUTF();
                    if (executeAuthenticationMessage(msg)) {
                        break;
                    }
                }
                while (true) {
                    String msg = in.readUTF();
                    if (msg.startsWith("/")) {
                        executeCommand(msg);
                        continue;
                    }
                    server.broadcastMessage(username + ": " + msg);
                }
            } catch (IOException e) {
                log.throwing(Level.ERROR, e);
            } finally {
                disconnect();
            }
        }).start();
    }

    private boolean executeAuthenticationMessage(String msg) {
        if (msg.startsWith("/login ")) {
            String[] tokens = msg.split("\\s+");
            if (tokens.length != 3) {
                sendMessage("/login_failed Введите имя пользователя и пароль");
                return false;
            }
            String login = tokens[1];
            String password = tokens[2];

            Optional<String> userNickname = server.getAuthenticationProvider().getNicknameByLoginAndPassword(login, password);
            if (!userNickname.isPresent()) {
                sendMessage("/login_failed Введен некорретный логин/пароль");
                return false;
            }
            if (server.isUserOnline(userNickname.get())) {
                sendMessage("/login_failed Учетная запись уже используется");
                return false;
            }
            username = userNickname.get();
            sendMessage("/login_ok " + login + " " + username);
            server.subscribe(this);
            return true;
        }
        return false;
    }

    private void executeCommand(String cmd) {
        if (cmd.startsWith("/w ")) {
            String[] tokens = cmd.split("\\s+", 3);
            if (tokens.length != 3) {
                sendMessage("Server: Введена некорректная команда");
                return;
            }
            server.sendPrivateMessage(this, tokens[1], tokens[2]);
            return;
        }

        if (cmd.startsWith("/change_nick ")) {
            String[] tokens = cmd.split("\\s+");
            if (tokens.length != 2) {
                sendMessage("Server: Введена некорректная команда");
                return;
            }
            String newNickname = tokens[1];
            if (server.getAuthenticationProvider().isNickBusy(newNickname)) {
                sendMessage("Server: Такой никнейм уже занят");
                return;
            }
            server.getAuthenticationProvider().changeNickname(username, newNickname);
            username = newNickname;
            sendMessage("Server: Вы изменили никнейм на " + newNickname);
            server.broadcastClientsList();
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            log.throwing(Level.ERROR, e);
            disconnect();
        }
    }

    public void disconnect() {
        server.unsubscribe(this);
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                log.throwing(Level.ERROR, e);
            }
        }
    }
}
