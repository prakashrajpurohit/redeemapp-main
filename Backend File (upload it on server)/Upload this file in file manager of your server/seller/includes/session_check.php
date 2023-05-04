<?php
	
	if(!isset($_SESSION['vendor_name']))
	{
		session_destroy();
		
		header( "Location:index.php");
		exit;
		 
	}
	 
	
?>