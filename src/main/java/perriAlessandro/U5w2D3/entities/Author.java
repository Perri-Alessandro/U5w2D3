package perriAlessandro.U5w2D3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String cognome;
    private String mail;
    private LocalDate dataNascita;
    private String imageUrl;

    public void setImageUrl(String imageUrl) {
        if (nome != null && cognome != null) {
            this.imageUrl = String.format("https://ui-avatars.com/api/?name=%s+%s", nome, cognome);
        } else {
            this.imageUrl = "https://as1.ftcdn.net/v2/jpg/03/46/83/96/1000_F_346839683_6nAPzbhpSkIpb8pmAwufkC7c5eD7wYws.jpg";
        }
    }
}


