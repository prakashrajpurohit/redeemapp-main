package com.mercury.redeem.Modelclas;

import java.util.ArrayList;

public class AllBidderInner {
    private String o_image;

    private String o_status;

    private String o_name;

    private String o_amount;
    private String total_users;

    private String o_stime;

    private String o_min;

    private ArrayList<UserBid> user_bid;

    private String o_date;
    private String o_edate;
    private String won_id;

    private String o_type;

    private String o_desc;

    private String o_price;

    private String o_etime;
    private String won_name;

    private String o_id;

    private ArrayList<AllBid> all_bid;

    private String total_bids;

    private String o_max;


    public String getO_edate() {
        return o_edate;
    }

    public void setO_edate(String o_edate) {
        this.o_edate = o_edate;
    }

    public String getO_image ()
    {
        return o_image;
    }

    public void setO_image (String o_image)
    {
        this.o_image = o_image;
    }

    public String getO_status ()
    {
        return o_status;
    }

    public void setO_status (String o_status)
    {
        this.o_status = o_status;
    }

    public String getO_name ()
    {
        return o_name;
    }

    public void setO_name (String o_name)
    {
        this.o_name = o_name;
    }


    public String getWon_id() {
        return won_id;
    }

    public void setWon_id(String won_id) {
        this.won_id = won_id;
    }

    public String getO_amount ()
    {
        return o_amount;
    }


    public String getWon_name() {
        return won_name;
    }

    public void setWon_name(String won_name) {
        this.won_name = won_name;
    }

    public void setO_amount (String o_amount)
    {
        this.o_amount = o_amount;
    }

    public String getO_stime ()
    {
        return o_stime;
    }

    public void setO_stime (String o_stime)
    {
        this.o_stime = o_stime;
    }

    public String getO_min ()
    {
        return o_min;
    }

    public ArrayList<UserBid> getUser_bid() {
        return user_bid;
    }

    public void setUser_bid(ArrayList<UserBid> user_bid) {
        this.user_bid = user_bid;
    }

    public ArrayList<AllBid> getAll_bid() {
        return all_bid;
    }

    public void setAll_bid(ArrayList<AllBid> all_bid) {
        this.all_bid = all_bid;
    }

    public void setO_min (String o_min)
    {
        this.o_min = o_min;
    }


    public String getTotal_users() {
        return total_users;
    }

    public void setTotal_users(String total_users) {
        this.total_users = total_users;
    }

    public String getO_date ()
    {
        return o_date;
    }

    public void setO_date (String o_date)
    {
        this.o_date = o_date;
    }

    public String getO_type ()
    {
        return o_type;
    }

    public void setO_type (String o_type)
    {
        this.o_type = o_type;
    }

    public String getO_desc ()
    {
        return o_desc;
    }

    public void setO_desc (String o_desc)
    {
        this.o_desc = o_desc;
    }

    public String getO_price ()
    {
        return o_price;
    }

    public void setO_price (String o_price)
    {
        this.o_price = o_price;
    }

    public String getO_etime ()
    {
        return o_etime;
    }

    public void setO_etime (String o_etime)
    {
        this.o_etime = o_etime;
    }

    public String getO_id ()
    {
        return o_id;
    }

    public void setO_id (String o_id)
    {
        this.o_id = o_id;
    }


    public String getTotal_bids ()
    {
        return total_bids;
    }

    public void setTotal_bids (String total_bids)
    {
        this.total_bids = total_bids;
    }

    public String getO_max ()
    {
        return o_max;
    }

    public void setO_max (String o_max)
    {
        this.o_max = o_max;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [o_image = "+o_image+", o_status = "+o_status+", o_name = "+o_name+", o_amount = "+o_amount+", o_stime = "+o_stime+", o_min = "+o_min+", user_bid = "+user_bid+", o_date = "+o_date+", o_type = "+o_type+", o_desc = "+o_desc+", o_price = "+o_price+", o_etime = "+o_etime+", o_id = "+o_id+", all_bid = "+all_bid+", total_bids = "+total_bids+", o_max = "+o_max+"]";
    }

}
