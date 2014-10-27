var t = $('#content').offset().top - 80;

$(document).scroll(function(){
	'use strict';
	    if($(this).scrollTop() > t)
	    {   
	    		$('.nav-dinerico').css({'background-color':'#20d3a0'});
	    		$('.nav-dinerico .container').css({'border-bottom':'none'});

	    }
	    else{
	    	$('.nav-dinerico').css({'background-color':'transparent'});
	    	$('.nav-dinerico .container').css({'border-bottom':'thin solid #fff'});
	    } 
});