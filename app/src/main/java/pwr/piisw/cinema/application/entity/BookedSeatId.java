package pwr.piisw.cinema.application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookedSeatId implements Serializable {
    private Integer ticketId;
    private Integer seatId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedSeatId that = (BookedSeatId) o;
        return Objects.equals(ticketId, that.ticketId) &&
                Objects.equals(seatId, that.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, seatId);
    }
}