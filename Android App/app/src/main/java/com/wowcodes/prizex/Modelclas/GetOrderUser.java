package com.mercury.redeem.Modelclas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetOrderUser
{
    private ArrayList<Get_order_user_Inner> JSON_DATA;

    public ArrayList<Get_order_user_Inner> getJSON_DATA() {
        return JSON_DATA;
    }

    public void setJSON_DATA(ArrayList<Get_order_user_Inner> JSON_DATA) {
        this.JSON_DATA = JSON_DATA;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [JSON_DATA = "+JSON_DATA+"]";
    }

    public class Get_order_user_Inner implements Serializable {
        private String o_image;

        private String o_status;

        private String u_id;

        private String o_name;

        private String total_amount;

        private String pay_amount;

        private String o_address;

        private String o_id;

        private String name;

        private String dis_amount;

        private String order_date;

        private String offer_id;
        private String order_status;

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

        public String getU_id ()
        {
            return u_id;
        }

        public void setU_id (String u_id)
        {
            this.u_id = u_id;
        }

        public String getO_name ()
        {
            return o_name;
        }

        public void setO_name (String o_name)
        {
            this.o_name = o_name;
        }

        public String getTotal_amount ()
        {
            return total_amount;
        }

        public void setTotal_amount (String total_amount)
        {
            this.total_amount = total_amount;
        }

        public String getPay_amount ()
        {
            return pay_amount;
        }

        public void setPay_amount (String pay_amount)
        {
            this.pay_amount = pay_amount;
        }

        public String getO_address ()
        {
            return o_address;
        }

        public void setO_address (String o_address)
        {
            this.o_address = o_address;
        }

        public String getO_id ()
        {
            return o_id;
        }

        public void setO_id (String o_id)
        {
            this.o_id = o_id;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getDis_amount ()
        {
            return dis_amount;
        }

        public void setDis_amount (String dis_amount)
        {
            this.dis_amount = dis_amount;
        }

        public String getOrder_date ()
        {
            return order_date;
        }

        public void setOrder_date (String order_date)
        {
            this.order_date = order_date;
        }

        public String getOffer_id ()
        {
            return offer_id;
        }

        public void setOffer_id (String offer_id)
        {
            this.offer_id = offer_id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [o_image = "+o_image+", o_status = "+o_status+", u_id = "+u_id+", o_name = "+o_name+", total_amount = "+total_amount+", pay_amount = "+pay_amount+", o_address = "+o_address+", o_id = "+o_id+", name = "+name+", dis_amount = "+dis_amount+", o_date = "+order_date+", offer_id = "+offer_id+"]";
        }

        public String getOrder_status() {
            return order_status;
        }
    }
}
