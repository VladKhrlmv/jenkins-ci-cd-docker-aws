package pwr.piisw.cinema.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BookedSeatId.class)
@Table(name = "booked_seats")
public class BookedSeat {
    @Id
    @Column(name = "ticket_id")
    private Integer ticketId;

    @Id
    @Column(name = "seat_id")
    private Integer seatId;

    private Timestamp reservationExpiry;

    @ManyToOne
    @JoinColumn(name = "ticket_id", insertable = false, updatable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "seat_id", insertable = false, updatable = false)
    private Seat seat;
}