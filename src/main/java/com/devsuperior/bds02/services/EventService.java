package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    public EventDTO update(Long id, EventDTO dto) {
        try {
            var event = eventRepository.findById(id).get();
            updateEvent(event, dto);
            return new EventDTO(eventRepository.save(event));
        } catch (javax.persistence.EntityNotFoundException | NoSuchElementException exception) {
            throw new EntityNotFoundException("Falha ao realizar update do evento");
        }
    }

    private void updateEvent(Event event,EventDTO dto) {
        event.setName(dto.getName());
        event.setUrl(dto.getUrl());
        event.setDate(dto.getDate());

        var city = cityRepository.findById(dto.getCityId());
        city.ifPresent(event::setCity);
    }
}
