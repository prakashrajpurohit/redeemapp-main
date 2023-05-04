<?php include('includes/header.php');

    include('includes/function.php');
	include('language/language.php'); 

 	require_once("thumbnail_images.class.php");
	 
	 
	if(isset($_POST['submit']) and isset($_GET['add']))
	{		
			  
 
			$data = array(
		
		'name'  =>  $_POST['name'],
			'email'  =>  $_POST['email'],
			'phone'  =>  $_POST['phone'],
			'password'  =>  $_POST['password'],
			'wallet'  =>  $_POST['wallet'],
			'status' => 1
			);

			$qry = Insert('tbl_users',$data);

		
			$_SESSION['msg']="10";
			header("location:users.php");	 
			exit;
		
	}
	
	if(isset($_GET['id']))
	{
			 
			$user_qry="SELECT * FROM tbl_users where id='".$_GET['id']."'";
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
	
	if(isset($_POST['submit']) and isset($_POST['id']))
	{

			$data = array(
			'name'  =>  $_POST['name'],
			'email'  =>  $_POST['email'],
			'phone'  =>  $_POST['phone'],
			'password'  =>  $_POST['password'],
			'wallet'  =>  $_POST['wallet'],
			);
			
		$user_edit=Update('tbl_users', $data, "WHERE id = '".$_POST['id']."'");
	
			if ($user_edit > 0){
				
				$_SESSION['msg']="11";
				header("Location:add_user.php?id=".$_POST['id']);
				exit;
			} 	
 }
		
	 
	
	
	
?>
 	

 <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title"><?php if(isset($_GET['id'])){?>Edit<?php }else{?>Add<?php }?> User</div>
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
            	<input  type="hidden" name="id" value="<?php echo $_GET['id'];?>" />

              <div class="section">
                <div class="section-body">
                    
                 <div class="form-group">
                    <label class="col-md-3 control-label">Name :-</label>
                    <div class="col-md-6">
                      <input type="text" placeholder="enter user name "  name="name" id="name" value="<?php if(isset($_GET['id'])){echo $user_row['name'];}?>" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Email Id:-</label>
                    <div class="col-md-6">
                      <input type="email" placeholder="enter email id of user "  name="email" id="email" value="<?php if(isset($_GET['id'])){echo $user_row['email'];}?>" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Password:-</label>
                    <div class="col-md-6">
                      <input type="password" placeholder="enter login password "  name="password" id="password" value="<?php if(isset($_GET['id'])){echo $user_row['password'];}?>" class="form-control" <?php if(!isset($_GET['id'])){ echo $user_row['password'];?>required<?php }?>>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Mobile Number:-</label>
                    <div class="col-md-6">
                      <input type="text" placeholder="enter user number without (+) "  name="phone" id="phone" value="<?php if(isset($_GET['id'])){echo $user_row['phone'];}?>" class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Update Coins:-</label>
                    <div class="col-md-6">
                      <input type="text" placeholder="set user coin balance"   name="wallet" id="wallet" value="<?php if(isset($_GET['id'])){echo $user_row['wallet'];}?>" class="form-control">
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