<?php 

include("includes/header.php");

$file_path = 'http://'.$_SERVER['SERVER_NAME'] . dirname($_SERVER['REQUEST_URI']).'/';
?>
<div class="row">
      <div class="col-sm-12 col-xs-12">
        <div class="card">
            <div class="card-header">
              Example API urls
            </div>
                <div class="card-body no-padding">
            
               <pre><code class="html">

<!--                 <b>User Register</b><br><?php echo $file_path."api.php?user_register"?><br>
<b>User Login</b><br><?php echo $file_path."api.php?users_login&email=abc@gmail.com&password=abc"?><br><br><b>User Profile
</b><?php echo $file_path."api.php?user_profile&id=8"?><br>
<br><b>User Profile Update
</b><?php echo $file_path."api.php?user_profile_update"?><br> -->

<br><b>Add Registration
</b><?php echo $file_path."api.php?postUserRegister"?><br>

<br><b>Login
</b><?php echo $file_path."api.php?postUserLogin"?><br>

<br><b>Update Profile
</b><?php echo $file_path."api.php?postUserProfileUpdate"?><br>

<br><b>Get Profile 
</b><?php echo $file_path."api.php?getUserProfile"?><br>

<br><b>All Category List 
</b><?php echo $file_path."api.php?get_all_category"?><br>

<br><b>All Notes List 
</b><?php echo $file_path."api.php?get_all_notes"?><br>

<br><b>All Notes category Wise
</b><?php echo $file_path."api.php?get_notes_cat_wise"?><br>

<br><b>Get All Video
</b><?php echo $file_path."api.php?get_video"?><br>

<br><b>Get All image Category
</b><?php echo $file_path."api.php?get_img_category"?><br>

<br><b>Get All image Category wise
</b><?php echo $file_path."api.php?get_img_cat"?><br>

<br><b>Get All Video Category
</b><?php echo $file_path."api.php?get_video_category"?><br>

<br><b>Get All Video Category Wise
</b><?php echo $file_path."api.php?get_video_cat"?><br>

<br><b>Get Location
</b><?php echo $file_path."api.php?get_location"?><br>

<br><b>Get event
</b><?php echo $file_path."api.php?get_event"?><br>

<br><b>Get Prayer
</b><?php echo $file_path."api.php?get_prayer"?><br>

<br><b>Add Prayer Request
</b><?php echo $file_path."api.php?prayer_request"?><br>

<br><b>Get Discover
</b><?php echo $file_path."api.php?get_discover"?><br>

<br><b>Get Store
</b><?php echo $file_path."api.php?get_store"?><br>

<br><b> Get Settings
</b><?php echo $file_path."api.php?settings"?><br>

        </div>
            </div>
        </div>
</div>
    <br/>
    <div class="clearfix"></div>
        
<?php include("includes/footer.php");?>       
