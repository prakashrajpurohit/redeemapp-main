package com.mercury.redeem.Modelclas;

import java.util.ArrayList;

public class GetWallet
{
    private ArrayList<Get_Wallet_Inner> JSON_DATA;

    public ArrayList<Get_Wallet_Inner> getJSON_DATA() {
        return JSON_DATA;
    }

    public void setJSON_DATA(ArrayList<Get_Wallet_Inner> JSON_DATA) {
        this.JSON_DATA = JSON_DATA;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [JSON_DATA = "+JSON_DATA+"]";
    }

    public class Get_Wallet_Inner {
        private String wp_id;

        private String wp_user;

        private String wp_package_id;

        private String c_coin;

        private String wp_order_id;

        private String wp_date;

        private String wp_status;

        private String c_name;

        private String c_amount;

        public String getWp_id ()
        {
            return wp_id;
        }

        public void setWp_id (String wp_id)
        {
            this.wp_id = wp_id;
        }

        public String getWp_user ()
        {
            return wp_user;
        }

        public void setWp_user (String wp_user)
        {
            this.wp_user = wp_user;
        }

        public String getWp_package_id ()
        {
            return wp_package_id;
        }

        public void setWp_package_id (String wp_package_id)
        {
            this.wp_package_id = wp_package_id;
        }

        public String getC_coin ()
        {
            return c_coin;
        }

        public void setC_coin (String c_coin)
        {
            this.c_coin = c_coin;
        }

        public String getWp_order_id ()
        {
            return wp_order_id;
        }

        public void setWp_order_id (String wp_order_id)
        {
            this.wp_order_id = wp_order_id;
        }

        public String getWp_date ()
        {
            return wp_date;
        }

        public void setWp_date (String wp_date)
        {
            this.wp_date = wp_date;
        }

        public String getWp_status ()
        {
            return wp_status;
        }

        public void setWp_status (String wp_status)
        {
            this.wp_status = wp_status;
        }

        public String getC_name ()
        {
            return c_name;
        }

        public void setC_name (String c_name)
        {
            this.c_name = c_name;
        }

        public String getC_amount ()
        {
            return c_amount;
        }

        public void setC_amount (String c_amount)
        {
            this.c_amount = c_amount;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [wp_id = "+wp_id+", wp_user = "+wp_user+", wp_package_id = "+wp_package_id+", c_coin = "+c_coin+", wp_order_id = "+wp_order_id+", wp_date = "+wp_date+", wp_status = "+wp_status+", c_name = "+c_name+", c_amount = "+c_amount+"]";
        }
    }
}
	