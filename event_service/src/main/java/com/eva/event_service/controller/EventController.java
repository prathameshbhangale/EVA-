package com.eva.event_service.controller;

import com.eva.event_service.dto.EventRequestDto;
import com.eva.event_service.model.Event;
import com.eva.event_service.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @PostMapping  // by admin
    public ResponseEntity<?> createEvent(@RequestBody @Valid EventRequestDto event) {
        Event e = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(e);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(@PathVariable Long id) {
        Event e = eventService.getEventById(id);
        return ResponseEntity.ok(e);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PutMapping("/{id}") // by admin
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody @Valid EventRequestDto event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @DeleteMapping("/{id}") // by admin
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        boolean deleted = eventService.deleteEvent(id);
        if (deleted) {
            return ResponseEntity.ok("Event deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Event not found with id: " + id);
        }
    }
}
