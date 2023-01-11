package sda.project.auction.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    public User(String email, String password, String voivodeship, String city, String address, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.voivodeship = voivodeship;
        this.city = city;
        this.address = address;
        this.userRole = userRole;
    }

    public User(String email, String password, String account_name, String voivodeship, String city, String address, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.account_name = account_name;
        this.voivodeship = voivodeship;
        this.city = city;
        this.address = address;
        this.userRole = userRole;
    }

    public User(String email, String password, String account_name, String voivodeship, String city, String address, UserRole userRole, Account_State account_state) {
        this.email = email;
        this.password = password;
        this.account_name = account_name;
        this.voivodeship = voivodeship;
        this.city = city;
        this.address = address;
        this.userRole = userRole;
        this.account_state = account_state;
    }

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
    private String voivodeship;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private LocalDateTime created_date;

    @Column
    @Enumerated(EnumType.STRING)
    private Account_State account_state;

    @Column
    private String icon;

    @Column
    private Account_Type account_type;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;



    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }
}
