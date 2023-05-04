<?php include('includes/header.php');

    include('includes/function.php');
	include('language/language.php'); 

 	require_once("thumbnail_images.class.php");
	 
	 
	if(isset($_POST['submit']) and isset($_GET['add']))
	{		

			    $category_image=rand(0,99999)."_".$_FILES['c_image']['name'];
       
       //Main Image
     $tpath1='images/'.$category_image;        
       $pic1=compress_image($_FILES["c_image"]["tmp_name"], $tpath1, 80);
   
    //Thumb Image 
     $thumbpath='images/thumbs/'.$category_image;   
       $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'300','300');   
 
			$data = array(
		
			'c_name'  =>  $_POST['c_name'],
			'c_desc'  =>  $_POST['c_desc'],
			'c_color'  =>  $_POST['c_color'],
			'c_view'  =>  $_POST['c_view'],
			'c_image' => $category_image,
			'c_status' => 1
			);

			$qry = Insert('tbl_cat',$data);

		
			$_SESSION['msg']="10";
			header("location:manage_category.php");	 
			exit;
		
	}
	   
	if(isset($_GET['c_id']))
	{
			 
			$user_qry="SELECT * FROM tbl_cat where c_id='".$_GET['c_id']."'";
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
	
	if(isset($_POST['submit']) and isset($_POST['c_id']))
	{
   if($_FILES['c_image']['name']!="")
     {   
     	  $img_res=mysqli_query($mysqli,'SELECT * FROM tbl_cat WHERE c_id='.$_GET['c_id'].'');
          $img_res_row=mysqli_fetch_assoc($img_res);
      

          if($img_res_row['c_image']!="")
            {
              unlink('images/thumbs/'.$img_res_row['c_image']);
              unlink('images/'.$img_res_row['c_image']);
           }

           $category_image=rand(0,99999)."_".$_FILES['c_image']['name'];
       
             //Main Image
           $tpath1='images/'.$category_image;        
             $pic1=compress_image($_FILES["c_image"]["tmp_name"], $tpath1, 80);
         
          //Thumb Image 
           $thumbpath='images/thumbs/'.$category_image;   
           $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'300','300');

	
			$data = array(
                
			  'c_name'  =>  $_POST['c_name'],
			  'c_desc'  =>  $_POST['c_desc'],
			  'c_color'  =>  $_POST['c_color'],
			  'c_view'  =>  $_POST['c_view'],
			  'c_image' => $category_image,
			);
			$user_edit=Update('tbl_cat', $data, "WHERE c_id = '".$_POST['c_id']."'");
		}
		else
		{
			$data = array(
                'c_name'  =>  $_POST['c_name'],
                'c_desc'  =>  $_POST['c_desc'],
                'c_color'  =>  $_POST['c_color'],
                'c_view'  =>  $_POST['c_view'],
                'c_image' => $category_image,
			);

			 $user_edit=Update('tbl_cat', $data, "WHERE c_id = '".$_POST['c_id']."'");
		 
		}

		
		  
			if ($user_edit > 0){
				
				$_SESSION['msg']="11";
				header("Location:add_category.php?c_id=".$_POST['c_id']);
				exit;
			} 	
 }
		
	 
	
	
	
?>
 	

 <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title"><?php if(isset($_GET['c_id'])){?>Edit<?php }else{?>Add<?php }?> Category</div>
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
                    <label class="col-md-3 control-label">Category Name :-</label>
                    <div class="col-md-6">
                      <input type="text" name="c_name" id="c_name" value="<?php if(isset($_GET['c_id'])){echo $user_row['c_name'];}?>" class="form-control" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">Category Description:-</label>
                    <div class="col-md-6">
                      <input type="text" name="c_desc" id="c_desc" value="<?php if(isset($_GET['c_id'])){echo $user_row['c_desc'];}?>" class="form-control">
                    </div>
                  </div>
                     <div class="form-group">
                    <label class="col-md-3 control-label">Category Colour:-</label>
                    <p class="control-label-help">(Enter Colour Hex Code. For Example, If you want category colour to be red then the hex code will be FF0000)</p>
                    <div class="col-md-6">
                      <input type="text" name="c_color" id="c_color" value="<?php if(isset($_GET['c_id'])){echo $user_row['c_color'];}?>" class="form-control">
                    </div>
                  </div>
                     <div class="form-group">
                    <label class="col-md-3 control-label">Select Category Image :-
                      <p class="control-label-help">(Recommended Square Image)</p>
                    </label>
                    <div class="col-md-6">
                      <div class="fileupload_block">
                        <input type="file" name="c_image" value="fileupload" id="fileupload">
                            
                            <div class="fileupload_img"><img type="image" src="assets/images/add-image.png" alt="category image" /></div>
                           
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-md-3 control-label">&nbsp; </label>
                    <div class="col-md-6">
                      <?php if(isset($_GET['c_id']) and $user_row['c_image']!="") {?>
                            <div class="block_wallpaper"><img src="images/<?php echo $user_row['c_image'];?>" alt="category image" /></div>
                          <?php } ?>
                    </div>
                  </div><br>
                   <div class="form-group">
                    <label class="col-md-3 control-label">Cateegory View Type :-</label>
                      <div class="col-md-6">                       
                        <select name="c_view" id="c_view" style="width:280px; height:25px;" class="select2" required>
                            <option value="">-Select Category View Type-</option>
                            <option value="1" <?php if($user_row['c_view']=='1'){?>selected<?php }?>>Square View</option>
                            <option value="2" <?php if($user_row['c_view']=='2'){?>selected<?php }?>>Banner</option>
                            <option value="3" <?php if($user_row['c_view']=='3'){?>selected<?php }?>>Sliding Square View</option>
                            <option value="4" <?php if($user_row['c_view']=='4'){?>selected<?php }?>>Sliding Banner View</option>
                          
                        </select>
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