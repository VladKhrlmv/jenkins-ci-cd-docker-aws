package pwr.piisw.cinema.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinemas")
public class Cinema {
    @Id
    private Integer cinemaId;

    @Column(nullable = false)
    private String name;

    private String address;
    private String city;
    private String contactPhone;
}