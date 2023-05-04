<?php include('includes/header.php'); 

    include('includes/function.php');
	include('language/language.php');  
	
function get_user_info($user_id,$field_name) 
	{
		global $mysqli;

		$qry_user="SELECT * FROM tbl_users WHERE id='".$user_id."'";
		$query1=mysqli_query($mysqli,$qry_user);
		$row_user = mysqli_fetch_array($query1);

		$num_rows1 = mysqli_num_rows($query1);
		
		if ($num_rows1 > 0)
		{		 	
			return $row_user[$field_name];
		}
		else
		{
			return "";
		}
	}

	if(isset($_POST['user_search']))
	 {
		 
		
		$user_qry="SELECT *,tbl_network.id as nid, tbl_network.status as nstatus FROM tbl_network
			left join tbl_users on tbl_users.id = tbl_network.user_id
        WHERE tbl_users.name like '%".addslashes($_POST['search_value'])."%' ORDER BY tbl_network.id DESC";  
							 
		$users_result=mysqli_query($mysqli,$user_qry);
		
		 
	 }
	 else
	 {

                            $tableName="tbl_network";		
							$targetpage = "referrals.php"; 	
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
						 $users_qry="SELECT *,tbl_network.id as nid, tbl_network.status as nstatus FROM tbl_network
						 left join tbl_users on tbl_users.id = tbl_network.user_id
						 ORDER BY tbl_network.id DESC LIMIT $start, $limit";  
							 
							$users_result=mysqli_query($mysqli,$users_qry);
							
	 }
	if(isset($_GET['nid']))
	{
		  
		 
		Delete('tbl_network','id='.$_GET['nid'].'');
		
		$_SESSION['msg']="12";
		header( "Location:referrals.php");
		exit;
	}
	
	//Active and Deactive status
	if(isset($_GET['status_deactive_id']))
	{
		$data = array('status'  =>  '0');
		
		$edit_status=Update('tbl_network', $data, "WHERE id = '".$_GET['status_deactive_id']."'");
		
		 $_SESSION['msg']="14";
		 header( "Location:referrals.php");
		 exit;
	}
	if(isset($_GET['status_active_id']))
	{
		$data = array('status'  =>  '1');
		
		$edit_status=Update('tbl_network', $data, "WHERE id = '".$_GET['status_active_id']."'");
		
		$_SESSION['msg']="13";
		 header( "Location:referrals.php");
		 exit;
	}
	
	
?>


 <div class="row">
      <div class="col-xs-12">
        <div class="card mrg_bottom">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title">Manage Refferals</div>
            </div>
            <div class="col-md-7 col-xs-12">              
                  <div class="search_list">
                    <div class="search_block">
                      <form  method="post" action="">
                        <input class="form-control input-sm" placeholder="Search Refferals.." aria-controls="DataTables_Table_0" type="search" name="search_value" required>
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
                  <th>Id</th>						 
				  <th>Reffered By</th>
				   <th>Coins Earned</th>
				   <th>Refferal user</th>
                  <th class="cat_action_list">Action</th>
                </tr>
              </thead>
              <tbody>
              	<?php
						$i=0;
						while($users_row=mysqli_fetch_array($users_result))
						{
//INSERT INTO `tbl_network`(`id`, `user_id`, `level`, `level`, `refferal_user_id`, `status`)


				?>
                <tr>
                   <td><?php echo $users_row['nid'];?></td>
		           <td><?php echo $users_row['name'];?></td>   
		           <td><?php echo $users_row['money'];?></td>
		            <td><?php echo get_user_info($users_row['refferal_user_id'],'name'); ?> </td>
		          
                   <td>
                       <!--<a href="add_raffles.php?o_id=<?php echo $users_row['o_id'];?>" class="btn btn-primary">Edit</a>-->
                    <a href="referrals.php?nid=<?php echo $users_row['nid'];?>" onclick="return confirm('Are you sure you want to delete this transaction?');" class="btn btn-default">Delete</a></td>
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