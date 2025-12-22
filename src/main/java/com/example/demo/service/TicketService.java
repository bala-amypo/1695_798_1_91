package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // ✅ Create a new ticket
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // ✅ Get all tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // ✅ Get ticket by ID
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }

    // ✅ Update a ticket
    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        Ticket existing = getTicketById(id);
        existing.setTitle(updatedTicket.getTitle());
        existing.setDescription(updatedTicket.getDescription());
        existing.setStatus(updatedTicket.getStatus());
        existing.setUrgencyLevel(updatedTicket.getUrgencyLevel());
        existing.setCategory(updatedTicket.getCategory());
        return ticketRepository.save(existing);
    }

    // ✅ Delete a ticket
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
