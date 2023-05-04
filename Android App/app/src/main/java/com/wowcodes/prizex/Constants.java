package com.mercury.redeem;

public interface Constants {
    // TODO: Change the backend urls below ;)
    // TODO: Change (demo.wowcodes.in/prizex) to yourdomain ;) https://klyngon.com/mercury_reddem/seller/
    String baseurl = "https://klyngon.com/mercury_reddem/seller/api.php?";
    String imageurl = "https://klyngon.com/mercury_reddem/seller/images/";
    String retrobaseurl = "https://klyngon.com/mercury_reddem/seller/";
     int CONNECT_TIMEOUT = 60 * 1000;

    int READ_TIMEOUT = 60 * 1000;

    int WRITE_TIMEOUT = 60 * 1000;
//MPESA START
     String BASE_URL = "https://sandbox.safaricom.co.ke/";
     String BUSINESS_SHORT_CODE = "174379";
     String PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
     String TRANSACTION_TYPE = "CustomerPayBillOnline";
     String PARTYB = "174379"; //same as business shortcode above
     String CALLBACKURL = "https://demo.wowcodes.in/raffking/seller/api.php?add_bid";
//MPESA DONE

//PAYPAL

    String PAYPAL_CURRENCY = "USD";
    String CLIENT_ID = "AZ1WslU_9hTHf-VfwaS26gG78HQbD7lGcUyl0d-vWm8zt1blz4D389tIx7MT6PuswvTxzSQr7zgnYiMe";
    String CLIENT_SECRET = "ECTXDcIodoam0ZIwaSt2Ds580NGGGKHMBe1lVFKzRTNptz_VWrwbRtEfRmGBCBiCoE6gB-B621Pd6O_g";
    String PAYPAL_BASE_URL = "https://api-m.sandbox.paypal.com"; // FOR PRODUCTION https://api-m.paypal.com


//PAYPAL


}