package sda.project.auction.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import sda.project.auction.model.User;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserForm {

    private Long ID;

    @Email(message = "Wprowadź poprawny format email")
    @NotBlank(message = "Pole nie może być puste")
    private String email;

    @Size(min = 8, message = "Minimalna liczba znaków to: 8")
    @NotBlank(message = "Pole nie może być puste")
    private String password;

    @NotBlank(message = "Pole nie może być puste")
    private String account_name;

    @NotBlank(message = "Pole nie może być puste")
    private String voivodeship;

    @NotBlank(message = "Pole nie może być puste")
    private String city;

    @NotBlank(message = "Pole nie może być puste")
    private String address;

    @NotNull(message = "Pole nie może być puste")
    private User.UserRole userRole;
}
