package smart.smartbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "wholebook")
public class WholeBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String author;

    @Column(name = "book")
    private String bookString;

    public WholeBook(String name, String author, String bookString) {
        this.name = name;
        this.author = author;
        this.bookString = bookString;
    }
}
