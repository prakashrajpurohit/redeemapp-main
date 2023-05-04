<?php include('includes/header.php'); 

    include('includes/function.php');
	include('language/language.php');  


	if(isset($_POST['user_search']))
	 {
		 
		
		$user_qry="SELECT * FROM tbl_coin_list WHERE tbl_coin_list.name like '%".addslashes($_POST['search_value'])."%' or tbl_coin_list.email like '%".addslashes($_POST['search_value'])."%' ORDER BY tbl_coin_list.id DESC";  
							 
		$users_result=mysqli_query($mysqli,$user_qry);
		
		 
	 }
	 else
	 {

					$tableName="tbl_coin_list";		
					$targetpage = "coin_packages.php"; 	
					$limit = 150; 
					
					$query = "SELECT COUNT(*) as num FROM $tableName";
					$total_pages = mysqli_fetch_array(mysqli_query($mysqli,$query));
					$total_pages = $total_pages['num'];
					
					$stages = 3;
					$page=0;
					if(isset($_GET['page'])){
					$page = mysqli_real_escape_string($mysqli,$_GET['page']);
					}
					if($page){
						$start = ($page - 1) * $limit; 
					}else{
						$start = 0;	
						}	
							
							
						 $users_qry="SELECT * FROM tbl_coin_list
						 ORDER BY tbl_coin_list.c_id DESC LIMIT $start, $limit";  
							 
							$users_result=mysqli_query($mysqli,$users_qry);
							
	 }
	if(isset($_GET['c_id']))
	{
		  
		 
		Delete('tbl_coin_list','c_id='.$_GET['c_id'].'');
		
		$_SESSION['msg']="12";
		header( "Location:coin_packages.php");
		exit;
	}
	

	
	
?>


 <div class="row">
      <div class="col-xs-12">
        <div class="card mrg_bottom">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title">Manage Coin Package</div>
            </div>
            <div class="col-md-7 col-xs-12">              
                  <div class="search_list">
                
                    <div class="add_btn_primary"> <a href="add_coin_packages.php?add">Add Coin Package</a> </div>
                  </div>
                  
            </div>
          </div>
          <div class="clearfix"></div>
          <div class="row mrg-top">
            <div class="col-md-12">
               
              <div class="col-md-12 col-sm-12">
                <?php if(isset($_SESSION['msg'])){?> 
               	 <div class="alert alert-success alert-dismissible" role="alert"> <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                	<?php echo $client_lang[$_SESSION['msg']] ; ?></a> </div>
                <?php unset($_SESSION['msg']);}?>	
              </div>
            </div>
          </div>
          <div class="col-md-12 mrg-top">
            <table class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
                    <th>Coin Package Name</th>						 
                    <th>Coins</th>
                    <th>Price of Package</th>
                  <th class="cat_action_list">Action</th>
                </tr>
              </thead>
              <tbody>
              	<?php
						$i=0;
						while($users_row=mysqli_fetch_array($users_result))
						{
						 
				?>
                <tr>
                    <td><?php echo $users_row['c_name'];?></td>
                    <td><?php echo $users_row['c_coin'];?></td>   
                    <td><?php echo'$'. $users_row['c_amount'].'/-';?></td>
		          
                   <td><a href="add_coin_packages.php?c_id=<?php echo $users_row['c_id'];?>" class="btn btn-primary">Edit Coin package</a>
                    <a href="coin_packages.php?c_id=<?php echo $users_row['c_id'];?>" onclick="return confirm('Are you sure you want to delete this coin package?');" class="btn btn-default">Delete Coin Package</a></td>
                </tr>
               <?php
						
						$i++;
						}
			   ?>
              </tbody>
            </table>
          </div>
          <div class="col-md-12 col-xs-12">
            <div class="pagination_item_block">
              <nav>
              	<?php if(!isset($_POST["search"])){ include("pagination.php");}?>                 
              </nav>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>     



<?php include('includes/footer.php');?>                  