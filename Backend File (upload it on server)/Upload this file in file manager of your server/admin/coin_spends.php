<?php include('includes/header.php'); 

    include('includes/function.php');
	include('language/language.php');  


	if(isset($_POST['user_search']))
	 {
		 
		
		$user_qry="SELECT *,tbl_transaction.id as tid FROM tbl_transaction
						 left join tbl_users on tbl_users.id = tbl_transaction.user_id
				WHERE tbl_users.name like '%".addslashes($_POST['search_value'])."%' or tbl_transaction.type like '%".addslashes($_POST['search_value'])."%' ORDER BY tbl_transaction.id DESC";  
							 
		$users_result=mysqli_query($mysqli,$user_qry);
		
		 
	 }
	 else
	 {
//INSERT INTO `tbl_transaction`(`id`, `user_id`, `type`, `date`, `money`)
							$tableName="tbl_transaction";		
							$targetpage = "coin_spends.php"; 	
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
							
						 $users_qry="SELECT *,tbl_transaction.id as tid FROM tbl_transaction
						 left join tbl_users on tbl_users.id = tbl_transaction.user_id
						 ORDER BY tbl_transaction.id DESC LIMIT $start, $limit";  
							 
							$users_result=mysqli_query($mysqli,$users_qry);
							
	 }
	if(isset($_GET['tid']))
	{
		  
		 
		Delete('tbl_transaction','id='.$_GET['tid'].'');
		
		$_SESSION['msg']="12";
		header( "Location:coin_spends.php");
		exit;
	}
	
	//Active and Deactive status
	if(isset($_GET['status_deactive_id']))
	{
		$data = array('o_status'  =>  '0');
		
		$edit_status=Update('tbl_transaction', $data, "WHERE tid = '".$_GET['status_deactive_id']."'");
		
		 $_SESSION['msg']="14";
		 header( "Location:coin_spends.php");
		 exit;
	}
	if(isset($_GET['status_active_id']))
	{
		$data = array('o_status'  =>  '1');
		
		$edit_status=Update('tbl_transaction', $data, "WHERE tid = '".$_GET['status_active_id']."'");
		
		$_SESSION['msg']="13";
		 header( "Location:coin_spends.php");
		 exit;
	}
	
	
?>


 <div class="row">
      <div class="col-xs-12">
        <div class="card mrg_bottom">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title">All Coin Transactions</div>
            </div>
            <div class="col-md-7 col-xs-12">              
                  <div class="search_list">
                    <div class="search_block">
                      <form  method="post" action="">
                        <input class="form-control input-sm" placeholder="Search..." aria-controls="DataTables_Table_0" type="search" name="search_value" required>
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
                  <th>Transaction Id</th>						 
				  <th>User Name</th>
				  <th>Transaction Type</th>
				  <th>Transaction Details</th>
				   <th>Date</th>
				  <th>Amount</th>	 
                  <th class="cat_action_list">Action</th>
                </tr>
              </thead>
              <tbody>
              	<?php
						$i=0;
						while($users_row=mysqli_fetch_array($users_result))
						{

	 //INSERT INTO `tbl_transaction`(`id`, `user_id`, `type`, `date`, `money`)

				?>
                <tr>
                   <td><?php echo $users_row['tid'];?></td>
		           <td><?php echo $users_row['name'];?></td>
		           <td><?php  if($users_row['type'] == 1) { echo 'Coin Spended' ;}
		                      else if($users_row['type'] == 2) { echo 'Refer Earning' ;} 
		           ?></td>
		           <td><?php  if($users_row['type'] == 1) { 
                                $qry1 = "SELECT * FROM tbl_offers WHERE o_id = '".$users_row['type_no']."'"; 
                                $result1 = mysqli_query($mysqli,$qry1);
                                $row1 = mysqli_fetch_assoc($result1);
            		
	                            echo $row1['o_name'] ;}
		                        
		                         else if($users_row['type'] == 2) {
                                $qry2 = "SELECT * FROM tbl_users WHERE id = '".$users_row['type_no']."'"; 
                                $result2 = mysqli_query($mysqli,$qry2);
                                $row2 = mysqli_fetch_assoc($result2);
                                
		                          echo $row2['name'] ;} 
		           ?></td>
		           <td><?php echo $users_row['date'];?></td>
		           <td><?php echo $users_row['money'];?></td>
		           <!--<td>-->
		          	<!--	<?php if($users_row['o_status']!="0"){?>-->
		           <!--   <a href="raffles.php?status_deactive_id=<?php echo $users_row['o_id'];?>" title="Change Status"><span class="badge badge-success badge-icon"><i class="fa fa-check" aria-hidden="true"></i><span>Enable</span></span></a>-->

		           <!--   <?php }else{?>-->
		           <!--   <a href="raffles.php?status_active_id=<?php echo $users_row['o_id'];?>" title="Change Status"><span class="badge badge-danger badge-icon"><i class="fa fa-check" aria-hidden="true"></i><span>Disable </span></span></a>-->
		           <!--   <?php }?>-->
             <!-- 		</td>-->
                   <td>
                       <!--<a href="add_raffles.php?o_id=<?php echo $users_row['o_id'];?>" class="btn btn-primary">Edit</a>-->
                    <a href="coin_spends.php?tid=<?php echo $users_row['tid'];?>" onclick="return confirm('Are you sure you want to delete this transaction?');" class="btn btn-default">Delete</a></td>
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