package com.mercury.redeem.Modelclas;

import java.util.ArrayList;

public class SettingModel
{
    private ArrayList<Setting_model_Inner> JSON_DATA;

    public ArrayList<Setting_model_Inner> getJSON_DATA() {
        return JSON_DATA;
    }

    public void setJSON_DATA(ArrayList<Setting_model_Inner> JSON_DATA) {
        this.JSON_DATA = JSON_DATA;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [JSON_DATA = "+JSON_DATA+"]";
    }

    public class Setting_model_Inner {



            private String publisher_id;

            private String app_contact;

            private String how_to_play;

            private String app_version;

            private String app_developed_by;

            private String app_author;

            private String interstital_ad;


            private String interstital_ad_id;

            private String app_name;

            private String app_logo;

            private String app_privacy_policy;

            private String app_email;

            private String about_us;

            private String banner_ad_id;

            private String banner_ad;

            private String app_website;

            private String app_description;

            public String getPublisher_id ()
            {
                return publisher_id;
            }

            public void setPublisher_id (String publisher_id)
            {
                this.publisher_id = publisher_id;
            }

            public String getApp_contact ()
            {
                return app_contact;
            }

            public void setApp_contact (String app_contact)
            {
                this.app_contact = app_contact;
            }

            public String getHow_to_play ()
            {
                return how_to_play;
            }

            public void setHow_to_play (String how_to_play)
            {
                this.how_to_play = how_to_play;
            }

            public String getApp_version ()
            {
                return app_version;
            }

            public void setApp_version (String app_version)
            {
                this.app_version = app_version;
            }

            public String getApp_developed_by ()
            {
                return app_developed_by;
            }

            public void setApp_developed_by (String app_developed_by)
            {
                this.app_developed_by = app_developed_by;
            }

            public String getApp_author ()
            {
                return app_author;
            }

            public void setApp_author (String app_author)
            {
                this.app_author = app_author;
            }

            public String getInterstital_ad ()
            {
                return interstital_ad;
            }

            public void setInterstital_ad (String interstital_ad)
            {
                this.interstital_ad = interstital_ad;
            }


            public String getInterstital_ad_id ()
            {
                return interstital_ad_id;
            }

            public void setInterstital_ad_id (String interstital_ad_id)
            {
                this.interstital_ad_id = interstital_ad_id;
            }

            public String getApp_name ()
            {
                return app_name;
            }

            public void setApp_name (String app_name)
            {
                this.app_name = app_name;
            }

            public String getApp_logo ()
            {
                return app_logo;
            }

            public void setApp_logo (String app_logo)
            {
                this.app_logo = app_logo;
            }

            public String getApp_privacy_policy ()
            {
                return app_privacy_policy;
            }

            public void setApp_privacy_policy (String app_privacy_policy)
            {
                this.app_privacy_policy = app_privacy_policy;
            }

            public String getApp_email ()
            {
                return app_email;
            }

            public void setApp_email (String app_email)
            {
                this.app_email = app_email;
            }

            public String getAbout_us ()
            {
                return about_us;
            }

            public void setAbout_us (String about_us)
            {
                this.about_us = about_us;
            }

            public String getBanner_ad_id ()
            {
                return banner_ad_id;
            }

            public void setBanner_ad_id (String banner_ad_id)
            {
                this.banner_ad_id = banner_ad_id;
            }

            public String getBanner_ad ()
            {
                return banner_ad;
            }

            public void setBanner_ad (String banner_ad)
            {
                this.banner_ad = banner_ad;
            }

            public String getApp_website ()
            {
                return app_website;
            }

            public void setApp_website (String app_website)
            {
                this.app_website = app_website;
            }

            public String getApp_description ()
            {
                return app_description;
            }

            public void setApp_description (String app_description)
            {
                this.app_description = app_description;
            }

            @Override
            public String toString()
            {
                return "ClassPojo [publisher_id = "+publisher_id+", app_contact = "+app_contact+", how_to_play = "+how_to_play+", app_version = "+app_version+", app_developed_by = "+app_developed_by+", app_author = "+app_author+", interstital_ad = "+interstital_ad+", rewarded_ad_id =  "+interstital_ad_id+", app_name = "+app_name+", app_logo = "+app_logo+", app_privacy_policy = "+app_privacy_policy+", app_email = "+app_email+", about_us = "+about_us+", banner_ad_id = "+banner_ad_id+", banner_ad = "+banner_ad+", app_website = "+app_website+", app_description = "+app_description+"]";
            }



    }
}
	