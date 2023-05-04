package com.mercury.redeem.Modelclas;

import java.util.ArrayList;

public class GetBidUser
{
    private ArrayList<Get_biduser_Inner> JSON_DATA;


    public ArrayList<Get_biduser_Inner> getJSON_DATA() {
        return JSON_DATA;
    }

    public void setJSON_DATA(ArrayList<Get_biduser_Inner> JSON_DATA) {
        this.JSON_DATA = JSON_DATA;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [JSON_DATA = "+JSON_DATA+"]";
    }

    public class Get_biduser_Inner {
        private String u_id;

        private String o_name;

        private String bd_amount;

        private String name;

        private String o_id;

        private String bd_status;

        private String bd_id;

        private String bd_date;
        private String o_image;

        private String bd_value;

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

        public String getBd_amount ()
        {
            return bd_amount;
        }

        public String getO_image() {
            return o_image;
        }

        public void setO_image(String o_image) {
            this.o_image = o_image;
        }

        public void setBd_amount (String bd_amount)
        {
            this.bd_amount = bd_amount;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getO_id ()
        {
            return o_id;
        }

        public void setO_id (String o_id)
        {
            this.o_id = o_id;
        }

        public String getBd_status ()
        {
            return bd_status;
        }

        public void setBd_status (String bd_status)
        {
            this.bd_status = bd_status;
        }

        public String getBd_id ()
        {
            return bd_id;
        }

        public void setBd_id (String bd_id)
        {
            this.bd_id = bd_id;
        }

        public String getBd_date ()
        {
            return bd_date;
        }

        public void setBd_date (String bd_date)
        {
            this.bd_date = bd_date;
        }

        public String getBd_value ()
        {
            return bd_value;
        }

        public void setBd_value (String bd_value)
        {
            this.bd_value = bd_value;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [u_id = "+u_id+", o_name = "+o_name+", bd_amount = "+bd_amount+", name = "+name+", o_id = "+o_id+", bd_status = "+bd_status+", bd_id = "+bd_id+", bd_date = "+bd_date+", bd_value = "+bd_value+"]";
        }
    }
}
			
