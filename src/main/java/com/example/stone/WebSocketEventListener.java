package com.example.stone;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 클라이언트가 연결될 때 호출
    @EventListener
    public void handleJoinEvent(SessionConnectedEvent event) {
        messagingTemplate.convertAndSend("/topic/join", "User joined");
    }

    // 회의 시작 이벤트 리스너
    @EventListener
    public void handleStartMeetingEvent(SessionConnectedEvent event) {
        messagingTemplate.convertAndSend("/topic/meeting_started", "Meeting started");
    }

    // 회의 종료 이벤트 리스너
    @EventListener
    public void handleEndMeetingEvent(SessionDisconnectEvent event) {
        messagingTemplate.convertAndSend("/topic/meeting_ended", "Meeting ended");
    }

    // 실시간 텍스트 처리
    public void handleTranscriptionEvent(String transcription) {
        messagingTemplate.convertAndSend("/topic/transcription", transcription);
    }
}
