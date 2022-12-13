package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @ManyToOne
    private Auction auction;
    @Column
    private String file_name;
    @Column
    private String file_type;
    @Lob
    private byte[]  file_content;

    public File(String name, String type, byte[] data) {
        this.file_name = name;
        this.file_type = type;
        this.file_content = data;
    }
}