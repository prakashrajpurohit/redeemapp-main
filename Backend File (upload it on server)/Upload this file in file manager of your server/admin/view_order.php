<?php include('includes/header.php');

    include('includes/function.php');
	include('language/language.php'); 

 	require_once("thumbnail_images.class.php");

	if(isset($_GET['o_id']))
	{
	
			
			 $user_qry="SELECT * FROM tbl_order 
		    left join tbl_users on tbl_users.id = tbl_order.u_id
		    left join tbl_offers on tbl_offers.o_id = tbl_order.offer_id
		  	where tbl_order.o_id='".$_GET['o_id']."' ";  
		  
		  
			$user_result=mysqli_query($mysqli,$user_qry);
			$user_row=mysqli_fetch_assoc($user_result);
		
	}
           // INSERT INTO `tbl_order`(`o_id`, `u_id`, `offer_id`, `total_amount`, `dis_amount`, `pay_amount`, `o_address`, `o_date`, `o_status`)	 
	

	
?>


<style>







#table {
  width:100%;
}
#table, #th, #td {
  border: 1px solid #dfd7ca;
  border-collapse: collapse;
}
#th, #td {
  padding: 15px;
  text-align: left;
}
#table#t01 #tr:nth-child(even) {
  background-color: #eee;
}
#table#t01 #tr:nth-child(odd) {
 background-color: #fff;
}
#table#t01 #th {
  background-color: white;
  color: black;
}


table {
  width:100%;
}
table, th, td {
  border: 0px solid #dfd7ca;
  border-collapse: collapse;
      margin-bottom: 10px;
}
th, td {
  padding: 0px;
  text-align: left;
}


.line {
  width:40%;
  
  margin-left: 600px;
}

 th, td {
  border: 0px solid #dfd7ca;
  border-collapse: collapse;
      margin-bottom: 10px;
}




</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



 <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="page_title_block">
            <div class="col-md-5 col-xs-12">
            <div class="page_title">Claim Details</div>
           
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

                   
                    
                    
                    
   
                    <div class="col-md-4" >
                    <center><h5> <b>Customer Details</b> </h5></center>
                       <hr />
                    <div class="form-group">
                        <label class="col-md-12 control-label"><b>Customer Name:- </b><?php if(isset($_GET['o_id'])){echo $user_row['name'];}?> </label>
                     </div>
                  
                    <div class="form-group">
                      <label class="col-md-12 control-label"><b>Email ID:-  </b> <?php if(isset($_GET['o_id'])){echo $user_row['email'];}?></label>
                    </div>
                    <div class="form-group">
                      <label class="col-md-12 control-label"><b>Mobile Number:-  </b> <?php if(isset($_GET['o_id'])){echo $user_row['phone'];}?></label>
                    </div>
                  </div>
                 <!--<div class="col-md-4" >-->
                 <!--   <center><h5> <b>Shipping details</b> </h5></center>-->
                 <!--      <hr />-->
                 <!--   <div class="form-group">-->
                 <!--       <label class="col-md-12 control-label"><b> Type:- </b><?php if(isset($_GET['o_id'])){echo $user_row['a_type'];}?> </label>-->
                 <!--    </div>-->
                
                 <!--   <div class="form-group">-->
                 <!--     <label class="col-md-12 control-label"><b>Contact :-  </b> <?php if(isset($_GET['o_id'])){echo $user_row['a_name'].' | '.$user_row['a_number'];}?></label>-->
                 <!--   </div>-->
                 <!--       <div class="form-group">-->
                 <!--     <label class="col-md-12 control-label"><b> House :-  </b><?php if(isset($_GET['o_id'])){echo $user_row['a_houser_no'].' | '.$user_row['a_lendmark'];}?></label>-->
                 <!--   </div>-->
                 <!--    <div class="form-group">-->
                 <!--     <label class="col-md-12 control-label"><b>City :-  </b> <?php if(isset($_GET['o_id'])){echo $user_row['a_city'];}?></label>-->
                 <!--   </div>-->
             
                 <!--   <div class="form-group">-->
                 <!--     <label class="col-md-12 control-label"><b>Address :-  </b> <?php if(isset($_GET['o_id'])){echo $user_row['a_adderss'];}?></label>-->
                 <!--   </div>-->
                 <!-- </div>-->
       <div class="col-md-4" >
          
                       <center><h5> <b>Claim Details</b> </h5></center>
                       <hr />
                         <div class="form-group">
                      <label class="col-md-12 control-label"><b> Claim ID :-  </b><?php if(isset($_GET['o_id'])){echo $user_row['o_id'];}?></label>
                    </div>
                     <!-- <div class="form-group">-->
                          
                     <!--   <label class="col-md-12 control-label"><b>Order Created :- </b><?php if(isset($_GET['o_id'])){echo $user_row['o_cdate'];}?> </label>-->
                     <!--</div>-->
                  
                    <div class="form-group">
                      <label class="col-md-12 control-label"><b>Claim Date :-  </b> <?php if(isset($_GET['o_id'])){echo $user_row['o_date'];}?></label>
                    </div>
                    <div class="form-group">
                      <label class="col-md-12 control-label"><b>Claim Details:-  </b> <?php if(isset($_GET['o_id'])){echo $user_row['o_address'];}?></label>
                    </div>
                
       </div>
                
                
 
 




 <table id='table'>   

             <div class="col-md-12 mrg-top">
            <table id="t01" class="table table-striped table-bordered table-hover">

                 <tr id='tr'>
                     <th id='th'>Item Name</th>
                     <th id='th'>Image</th>
                     <th id='th'>Coins Spended</th>
                
                </tr>
                <tr>
                    <td>
                     <?php if(isset($_GET['o_id'])){echo $user_row['o_name'];}?>
                    </td>
                    <td><span class="category_img"><img src="images/thumbs/<?php echo $user_row['o_image'];?>" /></span>
                  </td>
                    <td>
                     <?php if(isset($_GET['o_id'])){echo $user_row['total_amount'].' Coins';}?>
                    </td>
                </tr>

      

  
   </div>
   </table>
   </table>

    
      
                </div>
              </div>
            </form>
            
            
          </div>
        </div>
      </div>
    </div>
 
 
<?php include('includes/footer.php');?>                  