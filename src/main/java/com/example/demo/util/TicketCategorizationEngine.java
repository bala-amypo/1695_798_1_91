package com.example.demo.util;

import com.example.demo.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketCategorizationEngine {

    public String categorize(Ticket ticket) {

        if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase().contains("login")) {
            return "AUTH";
        }

        if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase().contains("payment")) {
            return "BILLING";
        }

        return "GENERAL";
    }
}
