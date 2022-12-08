package sda.project.auction.web.mappers;

import sda.project.auction.model.User;
import sda.project.auction.web.form.CreateUserForm;

public class UserMapper {

    public static User toEntity(CreateUserForm form){
        return new User(form.getEmail(), form.getPassword(), form.getAccount_name(), form.getVoivodeship(), form.getCity(), form.getAddress());
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
