<?php include('includes/header.php'); 

    include('includes/function.php');
	include('language/language.php');  


	if(isset($_POST['user_search']))
	 {
		 
		
		$user_qry="SELECT * FROM tbl_offers WHERE tbl_offers.o_name like '%".addslashes($_POST['search_value'])."%' ORDER BY tbl_offers.o_id DESC";  
							 
		$users_result=mysqli_query($mysqli,$user_qry);
		
		 
	 }
	 else
	 {

							$tableName="tbl_order";		
							$targetpage = "manage_order.php"; 	
							$limit = 15; 
							
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
							
					 	    //INSERT INTO `tbl_offers`(`o_id`, `o_name`, `o_image`, `o_desc`, `o_date`, `o_stime`, `o_etime`, `o_status`) 
			
			$users_qry="SELECT *,tbl_order.o_id as o_id FROM tbl_order 
		    left join tbl_users on tbl_users.id = tbl_order.u_id
		    left join tbl_offers on tbl_offers.o_id = tbl_order.offer_id
			ORDER BY tbl_order.o_id DESC LIMIT $start, $limit";  
							 
							$users_result=mysqli_query($mysqli,$users_qry);
							
	 }
	if(isset($_GET['o_id']))
	{
		  
		 
		Delete('tbl_order','o_id='.$_GET['o_id'].'');
		
		$_SESSION['msg']="12";
		header( "Location:manage_order.php");
		exit;
	}
	
	//Active and Deactive status
	if(isset($_GET['status_deactive_id']))
	{
		$data = array('o_status'  =>  '0');
		
		$edit_status=Update('tbl_order', $data, "WHERE o_id = '".$_GET['status_deactive_id']."'");
		
		 $_SESSION['msg']="14";
		 header( "Location:manage_order.php");
		 exit;
	}
	if(isset($_GET['status_active_id']))
	{
		$data = array('o_status'  =>  '1');
		
		$edit_status=Update('tbl_order', $data, "WHERE o_id = '".$_GET['status_active_id']."'");
		
		$_SESSION['msg']="13";
		 header( "Location:manage_order.php");
		 exit;
	}
	
	
?>


 <div class="row">
      <div class="col-xs-12">
        <div class="card mrg_bottom">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title">Manage Redemptions</div>
            </div>
            <div class="col-md-7 col-xs-12">              
                  <div class="search_list">
                    <div class="search_block">
                      <form  method="post" action="">
                        <input class="form-control input-sm" placeholder="Search Redeptions.." aria-controls="DataTables_Table_0" type="search" name="search_value" required>
                        <button type="submit" name="user_search" class="btn-search"><i class="fa fa-search"></i></button>
                      </form>  
                    </div>
                    <!--<div class="add_btn_primary"> <a href="add_raffles.php?add">Add Offers</a> </div>-->
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
                  <th>Name</th>						 
				  <th>Item Name</th>
				  <th>Claim Date</th>
				  <th>Pay Amount</th>
				  <th>Claim Status</th>	 
                  <th class="cat_action_list">Action</th>
                </tr>
              </thead>
              <tbody>
              	<?php
						$i=0;
						while($users_row=mysqli_fetch_array($users_result))
						{
 // INSERT INTO `tbl_order`(`o_id`, `u_id`, `offer_id`, `total_amount`, `dis_amount`, `pay_amount`, `o_address`, `o_date`, `o_status`)	 
				?>
                <tr>
                    <td><?php echo $users_row['name'];?></td>
                    <td><?php echo $users_row['o_name'];?></td>
                    <td><?php echo $users_row['o_date'];?></td>
                    <td><?php echo $users_row['pay_amount'];?></td>
		           <td>
		          		<?php if($users_row['o_status']!="0"){?>
		              <a href="manage_order.php?status_deactive_id=<?php echo $users_row['o_id'];?>" title="Change Status"><span class="badge badge-success badge-icon"><i class="fa fa-check" aria-hidden="true"></i><span>Visible on App</span></span></a>

		              <?php }else{?>
		              <a href="manage_order.php?status_active_id=<?php echo $users_row['o_id'];?>" title="Change Status"><span class="badge badge-danger badge-icon"><i class="fa fa-check" aria-hidden="true"></i><span>Not Visible on App</span></span></a>
		              <?php }?>
              		</td>
                   <td><a href="view_order.php?o_id=<?php echo $users_row['o_id'];?>" class="btn btn-primary">View Claim Details</a>
                    <a href="manage_order.php?o_id=<?php echo $users_row['o_id'];?>" onclick="return confirm('Are you sure you want to delete this Claim?');" class="btn btn-default">Delete Redemption</a></td>
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