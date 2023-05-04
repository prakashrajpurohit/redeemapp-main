<?php include("includes/header.php");

$qry_cat="SELECT COUNT(*) as num FROM tbl_users";
$total_category= mysqli_fetch_array(mysqli_query($mysqli,$qry_cat));
$total_category = $total_category['num'];

$qry_sub_cat="SELECT COUNT(*) as num FROM tbl_offers  WHERE o_type = '4'";
$total_sub_cat = mysqli_fetch_array(mysqli_query($mysqli,$qry_sub_cat));
$total_sub_cat = $total_sub_cat['num'];

$qry_redeem="SELECT COUNT(*) as num FROM tbl_offers  WHERE o_type = '3'";
$total_redeem = mysqli_fetch_array(mysqli_query($mysqli,$qry_redeem));
$total_redeem = $total_redeem['num'];

$qry_auction="SELECT COUNT(*) as num FROM tbl_offers  WHERE o_type = '1' OR o_type = '2'";
$total_auction = mysqli_fetch_array(mysqli_query($mysqli,$qry_auction));
$total_auction = $total_auction['num'];


$qry_sub_sub_cat="SELECT COUNT(*) as num FROM tbl_bid";
$total_sub_sub_cat = mysqli_fetch_array(mysqli_query($mysqli,$qry_sub_sub_cat));
$total_sub_sub_cat = $total_sub_sub_cat['num'];

$qry_pdf="SELECT COUNT(*) as num FROM tbl_transaction";
$total_pdf = mysqli_fetch_array(mysqli_query($mysqli,$qry_pdf));
$total_pdf = $total_pdf['num'];
 
$qry_network="SELECT COUNT(*) as num FROM tbl_network";
$total_network = mysqli_fetch_array(mysqli_query($mysqli,$qry_network));
$total_network = $total_network['num'];

$qry_vendor="SELECT COUNT(*) as num FROM tbl_vendor";
$total_vendor = mysqli_fetch_array(mysqli_query($mysqli,$qry_vendor));
$total_vendor = $total_vendor['num'];

$qry_orders="SELECT COUNT(*) as num FROM tbl_order";
$total_orders = mysqli_fetch_array(mysqli_query($mysqli,$qry_orders));
$total_orders = $total_orders['num'];

$qry_banner="SELECT COUNT(*) as num FROM tbl_offers  WHERE o_type = '6'";
$total_banner = mysqli_fetch_array(mysqli_query($mysqli,$qry_banner));
$total_banner = $total_banner['num'];

$qry_package="SELECT COUNT(*) as num FROM tbl_coin_list";
$total_package = mysqli_fetch_array(mysqli_query($mysqli,$qry_package));
$total_package = $total_package['num'];
?>       



    <div class="row">
      <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="users.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-user fa-4x"></i>
          <div class="content">
            <div class="title">Users</div>
            <div class="value"><span class="sign"></span><?php echo $total_category;?></div>
          </div>
        </div>
        </a> 
        </div>
        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="raffles.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-ticket fa-4x"></i>
          <div class="content">
            <div class="title">Raffles</div>
            <div class="value"><span class="sign"></span><?php echo $total_sub_cat;?></div>
          </div>
        </div>
        </a> 
        </div> 
     
      <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="auctions.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-gavel fa-4x"></i>
          <div class="content">
            <div class="title">Auctions</div>
            <div class="value"><span class="sign"></span><?php echo $total_auction;?></div>
          </div>
        </div>
        </a> 
        </div>
          <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="redeem.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-gift fa-4x"></i>
          <div class="content">
            <div class="title">Redeem Center</div>
            <div class="value"><span class="sign"></span><?php echo $total_redeem;?></div>
          </div>
        </div>
        </a> 
        </div>
        
     
        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="coin_spends.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-money fa-4x"></i>
          <div class="content">
            <div class="title">Transactions</div>
            <div class="value"><span class="sign"></span><?php echo $total_pdf;?></div>
          </div>
        </div>
        </a> </div> 
        
        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="referrals.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-users fa-4x"></i>
          <div class="content">
            <div class="title">Refferals</div>
            <div class="value"><span class="sign"></span><?php echo $total_network;?></div>
          </div>
        </div>
        </a> </div>

        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="vendors.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-user-secret fa-4x"></i>
          <div class="content">
            <div class="title">Sellers</div>
            <div class="value"><span class="sign"></span><?php echo $total_vendor;?></div>
          </div>
        </div>
        </a> </div>

        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="orders.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-shopping-bag fa-4x"></i>
          <div class="content">
            <div class="title">Orders</div>
            <div class="value"><span class="sign"></span><?php echo $total_orders;?></div>
          </div>
        </div>
        </a> </div>

        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="banners.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-image fa-4x"></i>
          <div class="content">
            <div class="title">Banners</div>
            <div class="value"><span class="sign"></span><?php echo $total_banner;?></div>
          </div>
        </div>
        </a> </div>

        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="coin_packages.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-money fa-4x"></i>
          <div class="content">
            <div class="title">Coin Shop</div>
            <div class="value"><span class="sign"></span><?php echo $total_package;?></div>
          </div>
        </div>
        </a> </div>
     
    </div>

        
<?php include("includes/footer.php");?>       
