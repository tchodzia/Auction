package sda.project.auction.web.mappers;

import sda.project.auction.model.Account_State;
import sda.project.auction.model.Account_Type;
import sda.project.auction.model.User;
import sda.project.auction.model.UserRole;
import sda.project.auction.web.form.CreateUserForm;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(CreateUserForm form){
        User user = new User(form.getEmail(), form.getPassword(), form.getAccount_name(), form.getVoivodeship(), form.getCity(), form.getAddress());
        user.setAccount_state(Account_State.ACTIVE);
        user.setAccount_type(Account_Type.NORMAL);
        user.setUserRole(UserRole.ROLE_USER);
        user.setCreated_date(LocalDateTime.now());
        return user;
    }

    public static User toUpdateEntity(User user, CreateUserForm form){
        user.setAccount_name(form.getAccount_name());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setVoivodeship(form.getVoivodeship());
        user.setCity(form.getCity());
        user.setAddress(form.getAddress());
        return user;
    }
}
