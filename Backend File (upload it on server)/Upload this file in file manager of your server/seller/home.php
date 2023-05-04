<?php include("includes/header.php");

      include('includes/function.php');
    	include('language/language.php'); 

      if(isset($_SESSION['id']))
      {
           
        $qry="select * from tbl_vendor where id='".$_SESSION['id']."'";
         
        $result=mysqli_query($mysqli,$qry);
        $row=mysqli_fetch_assoc($result);
    
      }

$id = $_SESSION['id'];
$qry_sub_cat="SELECT COUNT(*) as num FROM tbl_offers WHERE o_type = '4' and tbl_offers.id like '$id'";
$total_sub_cat = mysqli_fetch_array(mysqli_query($mysqli,$qry_sub_cat));
$total_sub_cat = $total_sub_cat['num'];

$id = $_SESSION['id'];
$qry_banner="SELECT COUNT(*) as num FROM tbl_offers  WHERE o_type = '6' and tbl_offers.id like '$id'";
$total_banner = mysqli_fetch_array(mysqli_query($mysqli,$qry_banner));
$total_banner = $total_banner['num'];

$id = $_SESSION['id'];
$qry_auction="SELECT COUNT(*) as num FROM tbl_offers  WHERE o_type = '1' OR o_type ='2' and tbl_offers.id like '$id'";
$total_auction = mysqli_fetch_array(mysqli_query($mysqli,$qry_auction));
$total_auction = $total_auction['num'];

$id = $_SESSION['id'];
$qry_redeem="SELECT COUNT(*) as num FROM tbl_offers  WHERE o_type = '3' and tbl_offers.id like '$id'";
$total_redeem = mysqli_fetch_array(mysqli_query($mysqli,$qry_redeem));
$total_redeem = $total_redeem['num'];

?>       



    <div class="row">
        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="raffles.php" class="card card-banner card-green-light">
        <div class="card-body"> <i class="icon fa fa-ticket fa-4x"></i>
          <div class="content">
            <div class="title">Raffles</div>
            <div class="value"><span class="sign"></span><?php echo $total_sub_cat;?></div>
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
        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="manage_auctions.php" class="card card-banner card-green-light">
        <div class="card-body"> <i class="icon fa fa-gavel fa-4x"></i>
          <div class="content">
            <div class="title">Auctions</div>
            <div class="value"><span class="sign"></span><?php echo $total_auction;?></div>
          </div>
        </div>
         </a> </div>
        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="redeem.php" class="card card-banner card-yellow-light">
        <div class="card-body"> <i class="icon fa fa-gift fa-4x"></i>
          <div class="content">
            <div class="title">Redeem</div>
            <div class="value"><span class="sign"></span><?php echo $total_redeem;?></div>
          </div>
        </div>
        </a> </div> 

     
    </div>

        
<?php include("includes/footer.php");?>       
