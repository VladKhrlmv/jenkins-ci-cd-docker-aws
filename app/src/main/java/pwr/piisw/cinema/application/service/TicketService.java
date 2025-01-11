package pwr.piisw.cinema.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.piisw.cinema.application.entity.Ticket;
import pwr.piisw.cinema.application.repository.TicketRepository;
import pwr.piisw.cinema.application.utils.QRCodeGenerator;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;
import pwr.piisw.cinema.mail.EmailDetails;
import pwr.piisw.cinema.mail.EmailService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EmailService emailService;

    public TicketService(TicketRepository ticketRepository, EmailService emailService) {
        this.ticketRepository = ticketRepository;
        this.emailService = emailService;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.TICKET_NOT_FOUND));
    }

    public List<Ticket> findByUserId(Integer userId) {
        return ticketRepository.findByUser_UserId(userId);
    }

    public List<Ticket> findByShowId(Integer showId) {
        return ticketRepository.findByShowtime_ShowId(showId);
    }

    public List<Ticket> findByUserEmail(String email) {
        return ticketRepository.findByUserEmail(email);
    }

    public List<Ticket> findTicketsByBookingDateRange(Timestamp startDate, Timestamp endDate) {
        return ticketRepository.findTicketsByBookingDateRange(startDate, endDate);
    }

    public List<Ticket> findByCinemaId(Integer cinemaId) {
        return ticketRepository.findByCinemaId(cinemaId);
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!ticketRepository.existsById(id)) {
            throw new CinemaException(CinemaExceptionType.TICKET_NOT_FOUND);
        }
        ticketRepository.deleteById(id);
    }

    @Transactional
    public String payForTicketAndSendEmail(Integer ticketId, EmailDetails emailDetails) {
        Ticket ticket = findById(ticketId);

        String qrContent = "Ticket ID: " + ticket.getTicketId();
        try {
            byte[] qrCode = QRCodeGenerator.generateQRCodeImage(qrContent, 200, 200);
            return emailService.sendMailWithAttachment(emailDetails, qrCode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate QR code or send email", e);
        }
    }

    public boolean validateQRCode(String qrContent) {
        String[] parts = qrContent.split(": ");
        if (parts.length != 2 || !"Ticket ID".equals(parts[0])) {
            return false;
        }
        Integer ticketId = Integer.parseInt(parts[1]);
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        return ticket.isPresent();
    }
}