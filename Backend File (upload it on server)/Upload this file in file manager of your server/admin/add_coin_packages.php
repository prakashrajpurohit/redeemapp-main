<?php include('includes/header.php');

    include('includes/function.php');
	include('language/language.php'); 

 	require_once("thumbnail_images.class.php");
	 
	 
	if(isset($_POST['submit']) and isset($_GET['add']))
	{		
			  
 
			$data = array(
		
			'c_name'  =>  $_POST['c_name'],
			'c_coin'  =>  $_POST['c_coin'],
			'c_amount'  =>  $_POST['c_amount'],
			'c_status' => 1
			);

			$qry = Insert('tbl_coin_list',$data);

		
			$_SESSION['msg']="10";
			header("location:coin_packages.php");	 
			exit;
		
	}
	
	if(isset($_GET['c_id']))
	{
			 
			$user_qry="SELECT * FROM tbl_coin_list where c_id='".$_GET['c_id']."'";
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
	
	if(isset($_POST['submit']) and isset($_POST['c_id']))
	{

			$data = array(
			'c_name'  =>  $_POST['c_name'],
			'c_coin'  =>  $_POST['c_coin'],
			'c_amount'  =>  $_POST['c_amount']
			);
			
		$user_edit=Update('tbl_coin_list', $data, "WHERE c_id = '".$_POST['c_id']."'");
	
			if ($user_edit > 0){
				
				$_SESSION['msg']="11";
				header("Location:add_coin_packages.php?c_id=".$_POST['c_id']);
				exit;
			} 	
 }
		
	 
	
	
	
?>
 	

 <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title"><?php if(isset($_GET['c_id'])){?>Edit<?php }else{?>Add<?php }?> Coin Packages</div>
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
            	<input  type="hidden" name="c_id" value="<?php echo $_GET['c_id'];?>" />

              <div class="section">
                <div class="section-body">
                    
                  <div class="form-group">
                    <label class="col-md-3 control-label">Name :-</label>
                    <div class="col-md-6">
                      <input type="text" name="c_name" id="c_name" placeholder="coin package name" value="<?php if(isset($_GET['c_id'])){echo $user_row['c_name'];}?>" class="form-control" required>
                    </div>
                  </div>
                  
                 <div class="form-group">
                    <label class="col-md-3 control-label">Coin :-</label>
                    <div class="col-md-6">
                      <input type="text" name="c_coin" id="c_coin" placeholder="number of coins which should be credited on purchase" value="<?php if(isset($_GET['c_id'])){echo $user_row['c_coin'];}?>" class="form-control" required>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label class="col-md-3 control-label">Amount :-</label>
                    <p class="control-label-help"></p>
                    <div class="col-md-6">
                      <input type="text" name="c_amount" id="c_amount" placeholder="enter the price of this pack" value="<?php if(isset($_GET['c_id'])){echo $user_row['c_amount'];}?>" class="form-control">
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