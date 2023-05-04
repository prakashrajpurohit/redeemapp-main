package com.mercury.redeem.Modelclas;

import java.io.Serializable;
import java.util.ArrayList;

public class GetCoin
{
    private ArrayList<Get_Coin_Inner> JSON_DATA;

    public ArrayList<Get_Coin_Inner> getJSON_DATA() {
        return JSON_DATA;
    }

    public void setJSON_DATA(ArrayList<Get_Coin_Inner> JSON_DATA) {
        this.JSON_DATA = JSON_DATA;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [JSON_DATA = "+JSON_DATA+"]";
    }

    public class Get_Coin_Inner implements Serializable {
        private String c_coin;

        private String c_id;

        private String c_name;

        private String c_amount;

        private String c_status;

        public String getC_coin ()
        {
            return c_coin;
        }

        public void setC_coin (String c_coin)
        {
            this.c_coin = c_coin;
        }

        public String getC_id ()
        {
            return c_id;
        }

        public void setC_id (String c_id)
        {
            this.c_id = c_id;
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

        public String getC_status ()
        {
            return c_status;
        }

        public void setC_status (String c_status)
        {
            this.c_status = c_status;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [c_coin = "+c_coin+", c_id = "+c_id+", c_name = "+c_name+", c_amount = "+c_amount+", c_status = "+c_status+"]";
        }
    }
}
	