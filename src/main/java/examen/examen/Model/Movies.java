package examen.examen.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Arrays;

@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private Genre genre;
    private int year;

    @Column(columnDefinition = "LONGTEXT")
    private String imageBase64;


    @JsonCreator
    public Movies(@JsonProperty("name") String name,
                  @JsonProperty("genre") Genre genre,
                  @JsonProperty("year") int year,
                  @JsonProperty("imageBase64") String imageBase64) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.imageBase64 = imageBase64;
    }

    public Movies() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }


    @JsonCreator
    public Movies(@JsonProperty("name") String name,
                  @JsonProperty("genre") Genre genre,
                  @JsonProperty("year") int year,
                  @JsonProperty("image") byte[] image,
                  @JsonProperty("imageBase64") String imageBase64) {
        this.name = name;
        this.genre = genre;
        this.year = year;

        this.imageBase64 = imageBase64;
    }


    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", year=" + year +
                ", imageBase64='" + imageBase64 + '\'' +
                '}';
    }
}
