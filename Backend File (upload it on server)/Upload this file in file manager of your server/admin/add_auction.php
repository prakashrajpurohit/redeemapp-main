<?php include('includes/header.php');

    include('includes/function.php');
	include('language/language.php'); 

 	require_once("thumbnail_images.class.php");
	 
	 
   if(isset($_SESSION['id']))
   {
        
     $qry="select * from tbl_vendor where id='".$_SESSION['id']."'";
      
     $result=mysqli_query($mysqli,$qry);
     $row=mysqli_fetch_assoc($result);
 
   }
   if(isset($_POST['submit']) and isset($_GET['add']))
	{		

			    $category_image=rand(0,99999)."_".$_FILES['o_image']['name'];
       
       //Main Image
     $tpath1='images/'.$category_image;        
       $pic1=compress_image($_FILES["o_image"]["tmp_name"], $tpath1, 80);
   
    //Thumb Image 
     $thumbpath='images/thumbs/'.$category_image;   
       $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'300','300');   
 
			$data = array(
		
			'id'  =>  $_SESSION['id'],
      'o_name'  =>  $_POST['o_name'],
			'o_desc'  =>  $_POST['o_desc'],
			'o_date'  =>  $_POST['o_date'],
			'o_edate'  =>  $_POST['o_edate'],
			'o_stime'  =>  $_POST['o_stime'],
      'o_etime'  =>  $_POST['o_etime'],
      'o_amount'  =>  $_POST['o_amount'],
      'c_id'  =>  2,
      'o_type'  =>  $_POST['o_type'],
      'o_min'  =>  $_POST['o_min'],
      'o_max'  =>  $_POST['o_max'],
      'o_price'  =>  $_POST['o_price'],
			'o_image' => $category_image,
			'o_status' => 1
			);

			$qry = Insert('tbl_offers',$data);

		
			$_SESSION['msg']="10";
			header("location:auctions.php");	 
			exit;
		
	}

	if(isset($_GET['o_id']))
	{
			 
			$user_qry="SELECT * FROM tbl_offers where o_id='".$_GET['o_id']."'";
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
	
	$user_qry12="SELECT c_name FROM tbl_cat";
			$user_result12=mysqli_query($mysqli,$user_qry12);
			if($user_result12->num_rows> 0){
      $options= mysqli_fetch_all($user_result12, MYSQLI_ASSOC);
    }
			
			
  if(isset($_GET['id']))
	{
			 
			$user_qry="SELECT * FROM tbl_vendor where id='".$_GET['id']."'";
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
	
	if(isset($_POST['submit']) and isset($_POST['o_id']))
	{
   if($_FILES['o_image']['name']!="")
     {   
     	  $img_res=mysqli_query($mysqli,'SELECT * FROM tbl_offers WHERE o_id='.$_GET['o_id'].'');
          $img_res_row=mysqli_fetch_assoc($img_res);
      

          if($img_res_row['o_image']!="")
            {
              unlink('images/thumbs/'.$img_res_row['o_image']);
              unlink('images/'.$img_res_row['o_image']);
           }

           $category_image=rand(0,99999)."_".$_FILES['o_image']['name'];
       
             //Main Image
           $tpath1='images/'.$category_image;        
             $pic1=compress_image($_FILES["o_image"]["tmp_name"], $tpath1, 80);
         
          //Thumb Image 
           $thumbpath='images/thumbs/'.$category_image;   
           $thumb_pic1=create_thumb_image($tpath1,$thumbpath,'300','300');

	
			$data = array(
			  'o_name'  =>  $_POST['o_name'],
        'o_desc'  =>  $_POST['o_desc'],
        'o_date'  =>  $_POST['o_date'],
        'o_edate'  =>  $_POST['o_edate'],
        'o_stime'  =>  $_POST['o_stime'],
        'o_etime'  =>  $_POST['o_etime'],
        'o_amount'  =>  $_POST['o_amount'],
      'o_type'  =>  $_POST['o_type'],
        'o_min'  =>  $_POST['o_min'],
        'o_max'  =>  $_POST['o_max'],
        'o_price'  =>  $_POST['o_price'],
        'o_image' => $category_image,
			);
			$user_edit=Update('tbl_offers', $data, "WHERE o_id = '".$_POST['o_id']."'");
		}
		else
		{
			$data = array(
			 'o_name'  =>  $_POST['o_name'],
       'o_desc'  =>  $_POST['o_desc'],
       'o_date'  =>  $_POST['o_date'],
       'o_edate'  =>  $_POST['o_edate'],
       'o_stime'  =>  $_POST['o_stime'],
       'o_etime'  =>  $_POST['o_etime'],
       'o_amount'  =>  $_POST['o_amount'],
       'o_min'  =>  $_POST['o_min'],
      'o_type'  =>  $_POST['o_type'],
       'o_max'  =>  $_POST['o_max'],
       'o_price'  =>  $_POST['o_price'],		
			);

			 $user_edit=Update('tbl_offers', $data, "WHERE o_id = '".$_POST['o_id']."'");
		 
		}

		
		  
			if ($user_edit > 0){
				
				$_SESSION['msg']="11";
				header("Location:add_auction.php?o_id=".$_POST['o_id']);
				exit;
			} 	
 }
		
	 
	
	
	
?>
 	

 <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
              <div class="page_title"><?php if(isset($_GET['o_id'])){?>Edit<?php }else{?>Add<?php }?>  Auction</div>
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
              <input  type="hidden" name="id" value="<?php echo $_GET['id'];?>" />

              <div class="section">
                <div class="section-body">
                   
                <div class="form-group">
                    <label class="col-md-3 control-label">Name :-</label>
                    <div class="col-md-6">
                      <input type="text" placeholder="enter item name " name="o_name" id="o_name" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_name'];}?>" class="form-control" required>
                    </div>
                  </div>
                 <div class="form-group">
                    <label class="col-md-3 control-label">Description:-</label>
                    <div class="col-md-6">
                      <input type="text" placeholder="enter item description "  name="o_desc" id="o_desc" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_desc'];}?>" class="form-control">
                    </div>
                  </div>
                
                  <div class="form-group">
                    <label class="col-md-3 control-label">Start Date :-</label>
                    <div class="col-md-6">
                      <input type="date" title="enter item starting date " name="o_date" id="o_date" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_date'];}?>" class="form-control">
                    </div>
                  </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">End Date :-</label>
                    <div class="col-md-6">
                      <input type="date" title="enter item ending date " name="o_edate" id="o_edate" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_edate'];}?>" class="form-control">
                    </div>
                  </div>
                 
                     <div class="form-group">
                    <label class="col-md-3 control-label">Cost per bid:-</label>
                    <div class="col-md-6">
                      <input type="text" placeholder="coins to be deducted per bid " name="o_amount" id="o_amount" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_amount'];}?>" class="form-control">
                    </div>
                  </div>
                    <div class="form-group">
                    <label class="col-md-3 control-label">Start time :-</label>
                    <div class="col-md-6">
                      <input type="time" title="enter item starting time " name="o_stime" id="o_stime" step="2" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_stime'];}?>" class="form-control">
                    </div>
                  </div>
                    <div class="form-group">
                    <label class="col-md-3 control-label">End time :-</label>
                    <div class="col-md-6">
                      <input type="time" title="enter item starting date " name="o_etime" id="o_etime" step="2" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_etime'];}?>" class="form-control">
                    </div>
                  </div>
    
                   <div class="form-group">
                    <label class="col-md-3 control-label">Auction Type :-</label>
                      <div class="col-md-6">                       
                        <select name="o_type" id="o_type" style="width:280px; height:25px;" class="select2" required>
                            <option value="">-Select Auction Type-</option>
                            <option value="1" <?php if($user_row['o_type']=='1'){?>selected<?php }?>>Lowest Unique Bid</option>
                            <option value="2" <?php if($user_row['o_type']=='2'){?>selected<?php }?>>Highest Unique Bid</option>
                        </select>
                      </div>
                  </div>
                    <div class="form-group">
                    <label class="col-md-3 control-label">Item Price:-</label>
                    <div class="col-md-6">
                      <input type="text" placeholder="enter the price of item in the market" name="o_price" id="o_price" value="<?php if(isset($_GET['o_id'])){echo $user_row['o_price'];}?>" class="form-control">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <div class="col-md-9 col-md-offset-3">
                      <button type="submit" name="submit" class="btn btn-primary">Save Item</button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
   
