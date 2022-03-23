package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;


    @Transactional
    public EventDTO update(Long id, EventDTO eventDTO) {
        try {
            Event eventEntity = eventRepository.getOne(id);
            City entity = cityRepository.getOne(eventDTO.getCityId());


            eventEntity.setName(eventDTO.getName());
            eventEntity.setDate(eventDTO.getDate());
            eventEntity.setUrl(eventDTO.getUrl());
            eventEntity.setCity(entity);


            eventRepository.save(eventEntity);

            return new EventDTO(eventEntity);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Id not found");
        }
    }
}
