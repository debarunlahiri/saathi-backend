package com.lambrk.saathi.location.websocket;

import com.lambrk.saathi.location.dto.LocationUpdateRequest;
import com.lambrk.saathi.location.entity.LocationEvent;
import com.lambrk.saathi.location.service.LocationService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LocationWebSocketController {
    private final LocationService service;
    private final SimpMessagingTemplate messagingTemplate;
    public LocationWebSocketController(LocationService service, SimpMessagingTemplate messagingTemplate) {
        this.service = service; this.messagingTemplate = messagingTemplate;
    }
    @MessageMapping("/location.update")
    public void update(LocationUpdateRequest request) {
        LocationEvent event = service.update(request);
        messagingTemplate.convertAndSend("/topic/location/task/" + request.taskId(), event);
    }
}
