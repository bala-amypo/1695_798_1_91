package com.example.demo.service;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.Ticket;
import java.util.List;

public interface CategorizationEngineService {
    void categorizeTicket(Long ticketId);
    List<CategorizationLog> getLogsForTicket(Long ticketId);
    CategorizationLog getLog(Long id);
}
