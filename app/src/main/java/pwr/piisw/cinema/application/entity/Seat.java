package pwr.piisw.cinema.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import pwr.piisw.cinema.application.utils.StatusType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    private Integer seatId;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    private int screenNumber;
    private char rowLabel;
    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.AVAILABLE;
}