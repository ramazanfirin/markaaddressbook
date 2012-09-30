	<ul class="nav">
		<?php 
	
if( strtolower($_SERVER["REQUEST_URI"]) == "/index.php" || strtolower($_SERVER["REQUEST_URI"]) == "/" ) {

 echo  '<li><a href="index.php" class="first current"><em><b>Ana Sayfa</b></em></a></li>';
}
else {
 echo   '<li><a href="index.php" class="first"><em><b>Ana Sayfa</b></em></a></li>';
}


if( strtolower($_SERVER["REQUEST_URI"]) == "/ayrintilar.php" ) {
 echo  '<li><a href="hosting-plans.php" class="current"><b>DetayBilgi</b></a></li>';
}
else {
 echo   '<li><a href="ayrintilar.php"><b>DetayBilgi</b></a></li>';
}


if( strtolower($_SERVER["REQUEST_URI"]) == "/sss.php" ) {
 echo  '<li><a href="sss.php"  class="current"><b>S.S.S</b></a></li>';
}
else {
 echo   '<li><a href="sss.php"><b>S.S.S</b></a></li>';
}


if( strtolower($_SERVER["REQUEST_URI"]) == "/testimonials.php" ) {
  echo  '<li><a href="testimonials.php" class="current"><b>Ekran Görüntüleri</b></a></li>';
}
else {
 echo    '<li><a href="testimonials.php"><b>Ekran Görüntüleri</b></a></li>';
}

if( strtolower($_SERVER["REQUEST_URI"]) == "/iletisim.php" ) {
  echo   '<li><a href="iletisim.php" class="current"><b>İletişim</b></a></li>';
}
else {
  echo   '<li><a href="iletisim.php"><b>İletişim</b></a></li>';
}


if( strtolower($_SERVER["REQUEST_URI"]) == "/download.php" ) {
  echo   '<li><a href="download.php" class="last current"><b>Download</b></a></li>';
}
else {
  echo   '<li><a href="download.php" class="last"><b>Download</b></a></li>';
}

?>
					
					</ul>