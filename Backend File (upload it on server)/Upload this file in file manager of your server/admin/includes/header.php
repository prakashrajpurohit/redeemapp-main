<?php include("includes/connection.php");
      include("includes/session_check.php");
      
      //Get file name
      $currentFile = $_SERVER["SCRIPT_NAME"];
      $parts = Explode('/', $currentFile);
      $currentFile = $parts[count($parts) - 1];       
       
      
?>
<!DOCTYPE html>
<html>
<head>
<meta name="author" content="">
<meta name="description" content="">
<meta http-equiv="Content-Type"content="text/html;charset=UTF-8"/>
<meta name="viewport"content="width=device-width, initial-scale=1.0">
<title>Admin Panel</title>
<link rel="stylesheet" type="text/css" href="assets/css/vendor.css">
<link rel="stylesheet" type="text/css" href="assets/css/flat-admin.css">

<!-- Theme -->
<link rel="stylesheet" type="text/css" href="assets/css/theme/blue-sky.css">
<link rel="stylesheet" type="text/css" href="assets/css/theme/blue.css">
<link rel="stylesheet" type="text/css" href="assets/css/theme/red.css">
<link rel="stylesheet" type="text/css" href="assets/css/theme/yellow.css">

 <script src="assets/ckeditor/ckeditor.js"></script>

</head>
<body>
<div class="app app-default">
  <aside class="app-sidebar" id="sidebar">
    <div class="sidebar-header"> <a class="sidebar-brand" href="home.php"><img src="images/<?php echo APP_LOGO;?>" alt="app logo" /></a>
      <button type="button" class="sidebar-toggle"> <i class="fa fa-times"></i> </button>
    </div>
    <div class="sidebar-menu">
      <ul class="sidebar-nav">
        <li <?php if($currentFile=="home.php"){?>class="active"<?php }?>> <a href="home.php">
          <div class="icon"> <i class="fa fa-dashboard" aria-hidden="true"></i> </div>
          <div class="title">Dashboard</div>
          </a> 
        </li>

      
        <li <?php if($currentFile=="auctions.php"){?>class="active"<?php }?>> <a href="auctions.php">
          <div class="icon"> <i class="fa fa-gavel" aria-hidden="true"></i> </div>
          <div class="title">Auctions</div>
          </a> 
        </li>
        
        <li <?php if($currentFile=="raffles.php"){?>class="active"<?php }?>> <a href="raffles.php">
          <div class="icon"> <i class="fa fa-ticket" aria-hidden="true"></i> </div>
          <div class="title">Raffles</div>
          </a> 
        </li>
        
        <li <?php if($currentFile=="redeem.php"){?>class="active"<?php }?>> <a href="redeem.php">
          <div class="icon"> <i class="fa fa-gift" aria-hidden="true"></i> </div>
          <div class="title">Redeem</div>
          </a> 
        </li>

        <li <?php if($currentFile=="vendors.php"){?>class="active"<?php }?>> <a href="vendors.php">
          <div class="icon"> <i class="fa fa-user-secret" aria-hidden="true"></i> </div>
          <div class="title">Sellers</div>
          </a> 
        </li>

        <li <?php if($currentFile=="banners.php"){?>class="active"<?php }?>> <a href="banners.php">
          <div class="icon"> <i class="fa fa-image" aria-hidden="true"></i> </div>
          <div class="title">Banners</div>
          </a> 
        </li>

        <li <?php if($currentFile=="tickets.php"){?>class="active"<?php }?>> <a href="tickets.php">
          <div class="icon"> <i class="fa fa-ticket" aria-hidden="true"></i> </div>
          <div class="title">Coin Spends</div>
          </a> 
        </li>

        <li <?php if($currentFile=="users.php"){?>class="active"<?php }?>> <a href="users.php">
          <div class="icon"> <i class="fa fa-user" aria-hidden="true"></i> </div>
          <div class="title">Users</div>
          </a> 
        </li>


        <li <?php if($currentFile=="coin_packages.php"){?>class="active"<?php }?>> <a href="coin_packages.php">
          <div class="icon"> <i class="fa fa-money" aria-hidden="true"></i> </div>
          <div class="title">Coin Packages</div>
          </a> 
        </li>
        
          <li <?php if($currentFile=="referrals.php"){?>class="active"<?php }?>> <a href="referrals.php">
          <div class="icon"> <i class="fa fa-users" aria-hidden="true"></i> </div>
          <div class="title">Refferals</div>
          </a> 
        </li>
        
         <li <?php if($currentFile=="coin_purchases.php"){?>class="active"<?php }?>> <a href="coin_purchases.php">
          <div class="icon"> <i class="fa fa-money" aria-hidden="true"></i> </div>
          <div class="title">Coin Purchases</div>
          </a> 
        </li>

         <li <?php if($currentFile=="orders.php"){?>class="active"<?php }?>> <a href="orders.php">
          <div class="icon"> <i class="fa fa-shopping-bag" aria-hidden="true"></i> </div>
          <div class="title">Orders</div>
          </a> 
        </li>
        


       <!--  <li <?php if($currentFile=="api_urls.php"){?>class="active"<?php }?>> <a href="api_urls.php">
          <div class="icon"> <i class="fa fa-exchange" aria-hidden="true"></i> </div>
          <div class="title">API URLS</div>
          </a> 
        </li>   -->


      </ul>
    </div>
     
  </aside>   
  <div class="app-container">
    <nav class="navbar navbar-default" id="navbar">
      <div class="container-fluid">
        <div class="navbar-collapse collapse in">
          <ul class="nav navbar-nav navbar-mobile">
            <li>
              <button type="button" class="sidebar-toggle"> <i class="fa fa-bars"></i> </button>
            </li>
            <li class="logo"> <a class="navbar-brand" href="#">Admin Panel</a> </li>
            <li>
              <button type="button" class="navbar-toggle">
                <?php if(PROFILE_IMG){?>               
                  <img class="profile-img" src="images/<?php echo PROFILE_IMG;?>">
                <?php }else{?>
                  <img class="profile-img" src="assets/images/profile.png">
                <?php }?>
                  
              </button>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-left">
            <li class="navbar-title">Admin Panel</li>
             
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown profile"> <a href="profile.php" class="dropdown-toggle" data-toggle="dropdown"> <?php if(PROFILE_IMG){?>               
                  <img class="profile-img" src="images/<?php echo PROFILE_IMG;?>">
                <?php }else{?>
                  <img class="profile-img" src="assets/images/profile.png">
                <?php }?>
              <div class="title">Profile</div>
              </a>
              <div class="dropdown-menu">
                <div class="profile-info">
                  <h4 class="username">Admin</h4>
                </div>
                <ul class="action">
                  <li><a href="profile.php">Profile</a></li>                  
                  <li><a href="logout.php">Logout</a></li>
                </ul>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>