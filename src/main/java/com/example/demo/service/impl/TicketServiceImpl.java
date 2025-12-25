package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    
    private final TicketRepository ticketRepository;
    
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    
    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    
    @Override
    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
    }
    
    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    
    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existing = getTicket(id);
        existing.setTitle(ticket.getTitle());
        existing.setDescription(ticket.getDescription());
        existing.setAssignedCategory(ticket.getAssignedCategory());
        existing.setUrgencyLevel(ticket.getUrgencyLevel());
        return ticketRepository.save(existing);
    }
    
    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = getTicket(id);
        ticketRepository.delete(ticket);
    }
}