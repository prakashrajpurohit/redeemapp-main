<?php include('includes/header.php');

    include('includes/function.php');
	include('language/language.php'); 

 	require_once("thumbnail_images.class.php");
	 
	 
	if(isset($_POST['submit']) and isset($_GET['add']))
	{		

			    $category_image=rand(0,99999)."_".$_FILES['w_image']['name'];
       
       //Main Image
     $tpath1='images/'.$category_image;        
       $pic1=compress_image($_FILES["w_image"]["tmp_name"], $tpath1, 80);
   
    //Thumb Image 
     $thumbpath='images/thumbs/'.$category_image;   
       $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'300','300');   
 
			$data = array(
		
			'w_oname'  =>  $_POST['w_oname'],
			'w_time'  =>  $_POST['w_time'],
      'w_ticket'  =>  $_POST['w_ticket'],
			'w_name'  =>  $_POST['w_name'],
			'w_image' => $category_image,
			'w_status' => 1
			);

			$qry = Insert('tbl_raffle',$data);

		
			$_SESSION['msg']="10";
			header("location:manage_winner.php");	 
			exit;
		
	}
	    

	if(isset($_GET['w_id']))
	{
			 
			$user_qry="SELECT * FROM tbl_raffle where w_id='".$_GET['w_id']."'";
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
	
	if(isset($_POST['submit']) and isset($_POST['w_id']))
	{
   if($_FILES['w_image']['name']!="")
     {   
     	  $img_res=mysqli_query($mysqli,'SELECT * FROM tbl_raffle WHERE w_id='.$_GET['w_id'].'');
          $img_res_row=mysqli_fetch_assoc($img_res);
      

          if($img_res_row['w_image']!="")
            {
              unlink('images/thumbs/'.$img_res_row['w_image']);
              unlink('images/'.$img_res_row['w_image']);
           }

           $category_image=rand(0,99999)."_".$_FILES['w_image']['name'];
       
             //Main Image
           $tpath1='images/'.$category_image;        
             $pic1=compress_image($_FILES["w_image"]["tmp_name"], $tpath1, 80);
         
          //Thumb Image 
           $thumbpath='images/thumbs/'.$category_image;   
           $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'300','300');

	
			$data = array(
        'w_oname'  =>  $_POST['w_oname'],
        'w_time'  =>  $_POST['w_time'],
        'w_ticket'  =>  $_POST['w_ticket'],
        'w_name'  =>  $_POST['w_name'],
        'w_image' => $category_image,
			);
			$user_edit=Update('tbl_raffle', $data, "WHERE w_id = '".$_POST['w_id']."'");
		}
		else
		{
			$data = array(
        'w_oname'  =>  $_POST['w_oname'],
        'w_time'  =>  $_POST['w_time'],
        'w_ticket'  =>  $_POST['w_ticket'],
        'w_name'  =>  $_POST['w_name'],
        'w_image' => $category_image,	
			);

			 $user_edit=Update('tbl_raffle', $data, "WHERE w_id = '".$_POST['w_id']."'");
		 
		}

		
		  
			if ($user_edit > 0){
				
				$_SESSION['msg']="11";
				header("Location:add_winner.php?w_id=".$_POST['w_id']);
				exit;
			} 	
 }
		
	 
	
	
	
?>
 	

 <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title"><?php if(isset($_GET['w_id'])){?>Edit<?php }else{?>Add<?php }?> Winner</div>
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
            	<input  type="hidden" name="w_id" value="<?php echo $_GET['w_id'];?>" />

              <div class="section">
                <div class="section-body">
                   
                  <div class="form-group">
                    <label class="col-md-3 control-label">Item Name :-</label>
                    <div class="col-md-6">
                      <input type="text" name="w_oname" id="w_oname" value="<?php if(isset($_GET['w_id'])){echo $user_row['w_oname'];}?>" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Winner Name:-</label>
                    <div class="col-md-6">
                      <input type="text" name="w_name" id="w_name" value="<?php if(isset($_GET['w_id'])){echo $user_row['w_name'];}?>" class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Winning Ticket:-</label>
                    <div class="col-md-6">
                      <input type="text" name="w_ticket" id="w_ticket" value="<?php if(isset($_GET['w_id'])){echo $user_row['w_ticket'];}?>" class="form-control">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Winning Date :-</label>
                    <div class="col-md-6">
                      <input type="date" name="w_time" id="w_time" value="<?php if(isset($_GET['w_id'])){echo $user_row['w_time'];}?>" class="form-control">
                    </div>
                  </div>
                     <div class="form-group">
                    <label class="col-md-3 control-label">Item Image :-
                      <p class="control-label-help">(Upload the Item Image)</p>
                    </label>
                    <div class="col-md-6">
                      <div class="fileupload_block">
                        <input type="file" name="w_image" value="fileupload" id="fileupload">
                            
                            <div class="fileupload_img"><img type="image" src="assets/images/add-image.png" alt="category image" /></div>
                           
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">&nbsp; </label>
                    <div class="col-md-6">
                      <?php if(isset($_GET['w_id']) and $user_row['w_image']!="") {?>
                            <div class="block_wallpaper"><img src="images/<?php echo $user_row['w_image'];?>" alt="category image" /></div>
                          <?php } ?>
                    </div>
                  </div><br>
                  <div class="form-group">
                    <div class="col-md-9 col-md-offset-3">
                      <button type="submit" name="submit" class="btn btn-primary">Save Winner</button>
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