package com.eva.event_service.service;

import com.eva.event_service.dto.EventRequestDto;
import com.eva.event_service.mapper.EventMapper;
import com.eva.event_service.model.Event;
import com.eva.event_service.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public Event createEvent(EventRequestDto event) {
        Event event1 = eventMapper.toEntity(event);
        return eventRepository.save(event1);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> searchEvents(String keyword) {
        return eventRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Event updateEvent(Long id, EventRequestDto updatedEvent) {
        Event existing = getEventById(id);
        existing.setTitle(updatedEvent.getTitle());
        existing.setVenue(updatedEvent.getVenue());
        existing.setDate(updatedEvent.getDate());
        existing.setPrice(updatedEvent.getPrice());
        existing.setAvailableSeats(updatedEvent.getAvailableSeats());
        return eventRepository.save(existing);
    }

    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
