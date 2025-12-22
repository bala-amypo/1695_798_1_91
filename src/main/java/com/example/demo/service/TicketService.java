package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket t) {
        return ticketRepository.save(t);
    }

    public Ticket update(Long id, Ticket updated) {
        Ticket existing = ticketRepository.findById(id).orElseThrow();
        existing.setStatus(updated.getStatus());
        existing.setUrgencyLevel(updated.getUrgencyLevel());
        existing.setCategory(updated.getCategory());
        return ticketRepository.save(existing);
    }
}
