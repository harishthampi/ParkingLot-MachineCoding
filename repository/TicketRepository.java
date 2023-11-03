package repository;

import models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Long, Ticket>ticketMap = new HashMap<>();
    private Long idSequence =0L;
    public Ticket saveTicket(Ticket ticket){
        idSequence+=1;
        ticket.setId(idSequence);
        ticketMap.put(idSequence,ticket);
        return ticket;
    }
}
