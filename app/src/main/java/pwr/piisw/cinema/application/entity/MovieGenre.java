package pwr.piisw.cinema.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MovieGenreId.class)
@Table(name = "movie_genres")
public class MovieGenre {
    @Id
    @Column(name = "movie_id")
    private Integer movieId;

    @Id
    @Column(name = "genre_id")
    private Integer genreId;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;
}