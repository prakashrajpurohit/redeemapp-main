<?php include('includes/header.php');

    include('includes/function.php');
	include('language/language.php'); 

 	require_once("thumbnail_images.class.php");
	 
	 
	if(isset($_POST['submit']) and isset($_GET['add']))
	{		
			  
 
			$data = array(
		
			'o_address'  =>  $_POST['o_address'],
			'order_status'  =>  $_POST['order_status'],
			'o_status' => 1
			);

			$qry = Insert('tbl_order',$data);

		
			$_SESSION['msg']="10";
			header("location:orders.php");	 
			exit;
		
	}
	
	if(isset($_GET['o_id']))
	{
			 
			$user_qry="SELECT * FROM tbl_order where o_id='".$_GET['o_id']."'";
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
	
	if(isset($_POST['submit']) and isset($_POST['o_id']))
	{

			$data = array(
        'o_address'  =>  $_POST['o_address'],
        'order_status'  =>  $_POST['order_status'],
			);
			
		$user_edit=Update('tbl_order', $data, "WHERE o_id = '".$_POST['o_id']."'");
	
			if ($user_edit > 0){
				
				$_SESSION['msg']="11";
				header("Location:add_claim.php?o_id=".$_POST['o_id']);
				exit;
			} 	
 }
		
	 
	
	
	
?>
 	

 <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title"><?php if(isset($_GET['o_id'])){?>Edit<?php }else{?>Add<?php }?> Orders</div>
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
          <div class="card-body mrg_bottom"> 
            <form action="" name="addedituser" method="post" class="form form-horizontal" enctype="multipart/form-data" >
            	<input  type="hidden" name="o_id" value="<?php echo $_GET['o_id'];?>" />

              <div class="section">
                <div class="section-body">

                 <div class="form-group">
                    <label class="col-md-3 control-label">Address :-</label>
                    <div class="col-md-6">
                      <input type="text" name="o_address" id="o_address" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_address'];}?>" class="form-control" required>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label class="col-md-3 control-label">Order Status:-</label>
                      <div class="col-md-6">                       
                        <select name="order_status" id="order_status" style="width:280px; height:25px;" class="select2" required>
                        <option value="">-Change Order Status-</option>
                            <option value="1" <?php if($user_row['order_status']=='1'){?>selected<?php }?>>Received</option>
                            <option value="2" <?php if($user_row['order_status']=='2'){?>selected<?php }?>>Processing</option>
                            <option value="3" <?php if($user_row['order_status']=='3'){?>selected<?php }?>>Shipped</option>
                            <option value="4" <?php if($user_row['order_status']=='4'){?>selected<?php }?>>Delivered</option>
                            <option value="5" <?php if($user_row['order_status']=='5'){?>selected<?php }?>>Rejected</option>
                          
                        </select>
                      </div>
                  </div>
                  <div class="form-group">
                    <div class="col-md-9 col-md-offset-3">
                      <button type="submit" name="submit" class="btn btn-primary">Save</button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
   

<?php include('includes/footer.php');?>                  