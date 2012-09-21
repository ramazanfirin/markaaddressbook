	<ul class="nav">
		<?php 
	
if( strtolower($_SERVER["REQUEST_URI"]) == "/index.php" ) {

 echo  '<li><a href="index.php" class="first current"><em><b>Home </b></em></a></li>';
}
else {
 echo   '<li><a href="index.php" class="first"><em><b>Home</b></em></a></li>';
}


if( strtolower($_SERVER["REQUEST_URI"]) == "/hosting-plans.php" ) {
 echo  '<li><a href="hosting-plans.php" class="current"><b>Hosting Plans</b></a></li>';
}
else {
 echo   '<li><a href="hosting-plans.php"><b>Hosting Plans</b></a></li>';
}


if( strtolower($_SERVER["REQUEST_URI"]) == "/sss.php" ) {
 echo  '<li><a href="sss.php"  class="current"><b>F.A.Q.</b></a></li>';
}
else {
 echo   '<li><a href="sss.php"><b>F.A.Q.</b></a></li>';
}


if( strtolower($_SERVER["REQUEST_URI"]) == "/testimonials.php" ) {
  echo  '<li><a href="testimonials.php" class="current"><b>Testimonials</b></a></li>';
}
else {
 echo    '<li><a href="testimonials.php"><b>Testimonials</b></a></li>';
}

if( strtolower($_SERVER["REQUEST_URI"]) == "/contacts.php" ) {
  echo   '<li><a href="contacts.php" class="current"><b>Contacts</b></a></li>';
}
else {
  echo   '<li><a href="contacts.php"><b>Contacts</b></a></li>';
}


if( strtolower($_SERVER["REQUEST_URI"]) == "/download.php" ) {
  echo   '<li><a href="download.php" class="last current"><b>Privacy policy</b></a></li>';
}
else {
  echo   '<li><a href="download.php" class="last"><b>Privacy policy</b></a></li>';
}

?>
					
					</ul>