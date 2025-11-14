package com.example.prm392_client.ui.messages;

import com.example.prm392_client.ui.messages.models.Message;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;

public class SignalRHelper {
    private static SignalRHelper instance;
    private final HubConnection hub;
    private OnMessageListener listener;
    private boolean isListenerRegistered = false; // ← CỜ ĐÁNH DẤU

    public interface OnMessageListener {
        void onReceived(Message message);
    }

    private SignalRHelper() {
        hub = HubConnectionBuilder.create(MessagesFragment.CoreResource.BASE_URL + "chatHub").build();
    }

    public static SignalRHelper get() {
        if (instance == null)
            instance = new SignalRHelper();
        return instance;
    }

    public void connect() {
        if (hub.getConnectionState() == HubConnectionState.DISCONNECTED) {
            try {
                hub.start().blockingAwait();

                // CHỈ ĐĂNG KÝ LISTENER 1 LẦN DUY NHẤT
                if (!isListenerRegistered) {
                    hub.on("ReceiveMessage", msg -> {
                        if (listener != null) {
                            listener.onReceived(msg);
                        }
                    }, Message.class);
                    isListenerRegistered = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setListener(OnMessageListener l) {
        this.listener = l;
    }

    public void join(String room) {
        hub.send("JoinRoom", room);
    }

    public void leave(String room) {
        hub.send("LeaveRoom", room);
    }

    public void send(Message msg) {
        hub.send("SendMessage", msg);
    }

    // CHỈ DÙNG KHI APP THOÁT HOÀN TOÀN
    public void disconnect() {
        hub.stop();
        isListenerRegistered = false; // Cho phép đăng ký lại nếu cần
    }
}