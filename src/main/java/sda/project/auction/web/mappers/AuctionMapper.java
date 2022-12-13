package sda.project.auction.web.mappers;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import sda.project.auction.model.Auction;
import sda.project.auction.model.Category;
import sda.project.auction.model.User;
import sda.project.auction.web.form.CreateAuctionForm;

import java.time.LocalDateTime;

public class AuctionMapper {

    public static Auction toEntity(CreateAuctionForm form, User user){
        Auction auction = new Auction();
        auction.setDate_of_issue(form.getDate_of_issue());
        auction.setEnd_date(form.getEnd_date());
        return new Auction(
                form.getTitle(),
                form.getDescription(),
                form.getCategory(),
                form.getMin_price(),
                form.getBUY_NOW_price(),
                form.isPromoted(),
                form.getLocalization(),
                auction.getDate_of_issue(),
                auction.getEnd_date(),
                user
        );
    }


    public static Auction toUpdateEntity(Auction auction, User user, CreateAuctionForm form){
        auction.setTitle(form.getTitle());
        auction.setDescription(form.getDescription());
        auction.setCategory(form.getCategory());
        auction.setMin_price(form.getMin_price());
        auction.setBUY_NOW_price(form.getBUY_NOW_price());
        auction.setPromoted(form.isPromoted());
        auction.setLocalization(form.getLocalization());
        auction.setDate_of_issue(form.getDate_of_issue());
        auction.setEnd_date(form.getEnd_date());
        auction.setUser(user);
        return auction;
    }
}
