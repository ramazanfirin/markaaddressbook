<title>Home | Free website template from TemplateMonster.com</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="Home page - feel free to download free hoting website template at TemplateMonster.com."/>
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="layout.css" rel="stylesheet" type="text/css" />

<!--  <link rel="stylesheet" href="css/global.css">  -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
	<script src="http://gsgd.co.uk/sandbox/jquery/easing/jquery.easing.1.3.js"></script>
	<script src="js/slides.min.jquery.js"></script>
	<script>
	var preloadArr = new Array();
	 var i;
	 var currImg = 1;
	$(document).ready(function() {
		//thebackground();	
		//$('div.background').fadeIn(1000); // works for all the browsers other than IE
		//$('div.background img').fadeIn(1000); // IE tweak

		var imgArr = new Array( // relative paths of images
		 '/images/cityliner_sitze_PictureGalleryII.jpg',
		 '/images/starliner_blau_PictureGalleryII.jpg',
		 '/images/starliner_individual_beleuchtung_PictureGalleryII.jpg',
		 '/images/starliner_individual_boden_PictureGalleryII.jpg',
		 '/images/starliner_individual_sitze_PictureGalleryII.jpg'
		 );
		 
		 
		 
		 /* preload images */
		 for(i=0; i < imgArr.length; i++){
		 preloadArr[i] = new Image();
		 preloadArr[i].src = imgArr[i];
		 }
		 
		
		 var intID = setInterval(changeImg, 10000);
		 
		 


		});
		/* image rotator */
		 function changeImg(){
		 $('.main-box').animate({
			 opacity: 0.8
			 
		 }, 1000, function(){
		 	 $('.main-box').css("background-image","url('"+ preloadArr[currImg++%preloadArr.length].src+"')");
		 });
		 $('.main-box').animate({opacity: 1}, 1000);
		 
		 //alert(preloadArr[currImg++%preloadArr.length].src);// }).animate({opacity: 1}, 1000);
		 
		 
		 
		 }
		 	
	
	
	
	
	
	
	$(function(){
			$('#slides').slides({
				preload: true,
				preloadImage: 'img/loading.gif',
				play: 5000,
				pause: 2500,
				hoverPause: true
			});
		});
	</script>
	