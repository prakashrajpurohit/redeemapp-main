package com.mercury.redeem.Modelclas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetOffersWinner {
    private ArrayList<Get_offers_winner_Inner> JSON_DATA;

    public ArrayList<Get_offers_winner_Inner> getJSON_DATA() {
        return JSON_DATA;
    }

    public void setJSON_DATA(ArrayList<Get_offers_winner_Inner> JSON_DATA) {
        this.JSON_DATA = JSON_DATA;
    }

    @Override
    public String toString() {
        return "ClassPojo [JSON_DATA = " + JSON_DATA + "]";
    }

    public class Get_offers_winner_Inner implements Serializable {
        private String o_name;

        private String lbd_amount;

        private String o_stime;

        private String o_min;

        private String o_type;

        private String lname;

        private String o_etime;

        private String o_id;

        private String h_won;

        private String l_won;

        private String total_bids;

        private String o_max;

        private String hname;

        private String o_image;

        private String o_status;

        private String hbd_value;

        private String o_amount;

        private String hbd_amount;

        private String lbd_value;

        private String o_edate;

        private String o_desc;

        private String total_amount;

        private String o_price;

        private String lu_id;

        private String hu_id;
        private String o_click;

        public String getO_edate() {
            return o_edate;
        }

        public String getO_click() {
            return o_click;
        }

        public void setO_click(String o_click) {
            this.o_click = o_click;
        }

        public String getO_name() {
            return o_name;
        }

        public void setO_name(String o_name) {
            this.o_name = o_name;
        }

        public String getLbd_amount() {
            return lbd_amount;
        }

        public void setLbd_amount(String lbd_amount) {
            this.lbd_amount = lbd_amount;
        }

        public String getO_stime() {
            return o_stime;
        }

        public void setO_stime(String o_stime) {
            this.o_stime = o_stime;
        }

        public String getO_min() {
            return o_min;
        }

        public void setO_min(String o_min) {
            this.o_min = o_min;
        }

        public String getO_type() {
            return o_type;
        }

        public void setO_type(String o_type) {
            this.o_type = o_type;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getO_etime() {
            return o_etime;
        }

        public void setO_etime(String o_etime) {
            this.o_etime = o_etime;
        }

        public String getO_id() {
            return o_id;
        }

        public void setO_id(String o_id) {
            this.o_id = o_id;
        }

        public String getH_won() {
            return h_won;
        }

        public void setH_won(String h_won) {
            this.h_won = h_won;
        }

        public String getL_won() {
            return l_won;
        }

        public void setL_won(String l_won) {
            this.l_won = l_won;
        }

        public String getTotal_bids() {
            return total_bids;
        }

        public void setTotal_bids(String total_bids) {
            this.total_bids = total_bids;
        }

        public String getO_max() {
            return o_max;
        }

        public void setO_max(String o_max) {
            this.o_max = o_max;
        }

        public String getHname() {
            return hname;
        }

        public void setHname(String hname) {
            this.hname = hname;
        }

        public String getO_image() {
            return o_image;
        }

        public void setO_image(String o_image) {
            this.o_image = o_image;
        }

        public String getO_status() {
            return o_status;
        }

        public void setO_status(String o_status) {
            this.o_status = o_status;
        }

        public String getHbd_value() {
            return hbd_value;
        }

        public void setHbd_value(String hbd_value) {
            this.hbd_value = hbd_value;
        }

        public String getO_amount() {
            return o_amount;
        }

        public void setO_amount(String o_amount) {
            this.o_amount = o_amount;
        }

        public String getHbd_amount() {
            return hbd_amount;
        }

        public void setHbd_amount(String hbd_amount) {
            this.hbd_amount = hbd_amount;
        }

        public String getLbd_value() {
            return lbd_value;
        }

        public void setLbd_value(String lbd_value) {
            this.lbd_value = lbd_value;
        }

        public String geto_edate() {
            return o_edate;
        }

        public void setO_edate(String o_edate) {
            this.o_edate = o_edate;
        }

        public void seto_edate(String o_edate) {
            this.o_edate = o_edate;
        }

        public String getO_desc() {
            return o_desc;
        }

        public void setO_desc(String o_desc) {
            this.o_desc = o_desc;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getO_price() {
            return o_price;
        }

        public void setO_price(String o_price) {
            this.o_price = o_price;
        }

        public String getLu_id() {
            return lu_id;
        }

        public void setLu_id(String lu_id) {
            this.lu_id = lu_id;
        }

        public String getHu_id() {
            return hu_id;
        }

        public void setHu_id(String hu_id) {
            this.hu_id = hu_id;
        }

        @Override
        public String toString() {
            return "ClassPojo [o_name = " + o_name + ", lbd_amount = " + lbd_amount + ", o_stime = " + o_stime + ", o_min = " + o_min + ", o_type = " + o_type + ", lname = " + lname + ", o_etime = " + o_etime + ", o_id = " + o_id + ", h_won = " + h_won + ", l_won = " + l_won + ", total_bids = " + total_bids + ", o_max = " + o_max + ", hname = " + hname + ", o_image = " + o_image + ", o_status = " + o_status + ", hbd_value = " + hbd_value + ", o_amount = " + o_amount + ", hbd_amount = " + hbd_amount + ", lbd_value = " + lbd_value + ", o_edate = " + o_edate + ", o_desc = " + o_desc + ", total_amount = " + total_amount +
                    ", o_price = " + o_price +
                    ", lu_id = " + lu_id +
                    ", o_click = " + o_click +
                    ", hu_id = " + hu_id + "]";
        }
    }


}
	