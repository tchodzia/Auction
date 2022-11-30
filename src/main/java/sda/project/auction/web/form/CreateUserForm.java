package sda.project.auction.web.form;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserForm {

    private String email;
    private String password;
    private String account_name;
    private String voivodeship;
    private String city;
    private String address;
}
