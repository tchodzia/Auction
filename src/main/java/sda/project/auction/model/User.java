package sda.project.auction.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity(name="user")
@Getter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String account_name;

    @Column
    private String voivodship;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private Date created_date;

    @Column
    private Account_State account_state;

    @Column
    private String icon;

    @Column
    private Account_Type account_type;

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }
}
