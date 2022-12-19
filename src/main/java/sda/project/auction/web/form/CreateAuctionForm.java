package sda.project.auction.web.form;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import sda.project.auction.model.User;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuctionForm {
    private Long ID;

    @NotBlank(message = "Pole nie może być puste")
    private String title;

    private String description;

    @NotNull(message = "Pole nie może być puste")
    private Long category;

    @NotNull(message = "Pole nie może być puste")
    private Long min_price;

    @NotNull(message = "Pole nie może być puste")
    private Long BUY_NOW_price;

    private boolean promoted;

    private String localization;

    @NotBlank(message = "Pole nie może być puste")
    private String date_of_issue;

    @NotBlank(message = "Pole nie może być puste")
    private String end_date;

    private User user;

}
