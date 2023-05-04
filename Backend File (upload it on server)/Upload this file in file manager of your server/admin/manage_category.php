<?php include('includes/header.php'); 

    include('includes/function.php');
	include('language/language.php');  


	if(isset($_POST['user_search']))
	 {
		 
		
		$user_qry="SELECT * FROM tbl_cat WHERE tbl_cat.c_name like '%".addslashes($_POST['search_value'])."%' ORDER BY tbl_cat.c_id DESC";  
							 
		$users_result=mysqli_query($mysqli,$user_qry);
		
		 
	 }
	 else
	 {

							$tableName="tbl_cat";		
							$targetpage = "manage_category.php"; 	
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
							
			
						 $users_qry="SELECT * FROM tbl_cat
						 ORDER BY tbl_cat.c_id DESC LIMIT $start, $limit";  
							 
							$users_result=mysqli_query($mysqli,$users_qry);
							
	 }
	if(isset($_GET['c_id']))
	{
		  
		 
		Delete('tbl_cat','c_id='.$_GET['c_id'].'');
		
		$_SESSION['msg']="12";
		header( "Location:manage_category.php");
		exit;
	}
	
	//Active and Deactive status
	if(isset($_GET['status_deactive_id']))
	{
		$data = array('c_status'  =>  '0');
		
		$edit_status=Update('tbl_cat', $data, "WHERE c_id = '".$_GET['status_deactive_id']."'");
		
		 $_SESSION['msg']="14";
		 header( "Location:manage_category.php");
		 exit;
	}
	if(isset($_GET['status_active_id']))
	{
		$data = array('c_status'  =>  '1');
		
		$edit_status=Update('tbl_cat', $data, "WHERE c_id = '".$_GET['status_active_id']."'");
		
		$_SESSION['msg']="13";
		 header( "Location:manage_category.php");
		 exit;
	}
	
	
?>


 <div class="row">
      <div class="col-xs-12">
        <div class="card mrg_bottom">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title">Manage Category</div>
            </div>
            <div class="col-md-7 col-xs-12">              
                  <div class="search_list">
                    <div class="search_block">
                      <form  method="post" action="">
                        <input class="form-control input-sm" placeholder="Search Category" aria-controls="DataTables_Table_0" type="search" name="search_value" required>
                        <button type="submit" name="user_search" class="btn-search"><i class="fa fa-search"></i></button>
                      </form>  
                    </div>
                    <div class="add_btn_primary"> <a href="add_category.php?add">Add Category</a> </div>
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
                  <th>Category Name</th>						 
				  <th>Category Description</th>
                  <th>Category ID</th>
				  <th>Colour</th>
				   <th>View</th>
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
		           <td><?php echo $users_row['c_desc'];?></td>   
		           <td><?php echo $users_row['c_id'];?></td>
		           <td><?php echo $users_row['c_color'];?></td>
				   <td><?php  if($users_row['c_view'] == 1) { echo 'Square View' ;}
		                      else if($users_row['c_view'] == 2) { echo 'Banner' ;}
							  else if($users_row['c_view'] == 3) { echo 'Sliding Square View' ;}
							  else if($users_row['c_view'] == 4) { echo 'Sliding Banner View' ;} 
		           ?></td>
                   <td><a href="add_category.php?c_id=<?php echo $users_row['c_id'];?>" class="btn btn-primary">Edit Category</a>
                    <a href="manage_category.php?c_id=<?php echo $users_row['c_id'];?>" onclick="return confirm('Are you sure you want to delete this Category?');" class="btn btn-default">Delete Category</a></td>
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