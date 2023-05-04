<?php include('includes/header.php');

    include('includes/function.php');
	include('language/language.php'); 

 	require_once("thumbnail_images.class.php");
	 
	 
	if(isset($_POST['submit']) and isset($_GET['add']))
	{		
			  
 
			$data = array(
		
		'username'  =>  $_POST['username'],
			'email'  =>  $_POST['email'],
			'link'  =>  $_POST['link'],
			'about'  =>  $_POST['about'],
			'password'  =>  $_POST['password'],
			);

			$qry = Insert('tbl_vendor',$data);

		
			$_SESSION['msg']="10";
			header("location:vendors.php");	 
			exit;
		
	}
	
	if(isset($_GET['id']))
	{
			 
			$user_qry="SELECT * FROM tbl_vendor where id='".$_GET['id']."'";
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
	
	if(isset($_POST['submit']) and isset($_POST['id']))
	{

			$data = array(
			'username'  =>  $_POST['username'],
			'email'  =>  $_POST['email'],
				'link'  =>  $_POST['link'],
			'about'  =>  $_POST['about'],
			'password'  =>  $_POST['password'],
			);
			
		$user_edit=Update('tbl_vendor', $data, "WHERE id = '".$_POST['id']."'");
	
			if ($user_edit > 0){
				
				$_SESSION['msg']="11";
				header("Location:add_vendors.php?id=".$_POST['id']);
				exit;
			} 	
 }
		
	 
	
	
	
?>
 	

 <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title"><?php if(isset($_GET['id'])){?>Edit<?php }else{?>Add<?php }?> Seller</div>
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
                      <input type="text" name="email" id="email" value="<?php if(isset($_GET['id'])){echo $user_row['email'];}?>" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Email Id:-</label>
                    <div class="col-md-6">
                      <input type="text" name="username" id="username" value="<?php if(isset($_GET['id'])){echo $user_row['username'];}?>" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Password:-</label>
                    <div class="col-md-6">
                      <input type="password" name="password" id="password" value="<?php if(isset($_GET['id'])){echo $user_row['password'];}?>" class="form-control" <?php if(!isset($_GET['id'])){ echo $user_row['password'];?>required<?php }?>>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">About:-</label>
                    <div class="col-md-6">
                      <input type="text" name="about" id="about" value="<?php if(isset($_GET['id'])){echo $user_row['about'];}?>" class="form-control">
                    </div>
                  </div>
                      <div class="form-group">
                    <label class="col-md-3 control-label">Link:-</label>
                    <div class="col-md-6">
                      <input type="text" name="link" id="link" value="<?php if(isset($_GET['id'])){echo $user_row['link'];}?>" class="form-control">
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