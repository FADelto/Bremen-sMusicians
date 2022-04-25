$(function() {
    
    
    /* Fixed Header */
    let header = $("#header");
    let intro = $("#intro");
    let introH = intro.innerHeight();
    let scrollPos = $(window).scrollTop();

    

    
    $(window).on("scroll resize", function() {
        introH = intro.innerHeight();
        scrollPos = $(this).scrollTop();
        
        checkScroll(scrollPos, introH);
    });
    
    function checkScroll(scrollPos, introH) {
        if( scrollPos > introH ) {
            header.addClass("fixed");
        } else {
            header.removeClass("fixed");
        }
    }
    
    /* Reviews: https://kenwheeler.github.io/slick/ */
    let slider = $("#reviewsSlider");
    
    slider.slick({
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        fade: true,
        arrows: false,
        dots: true
    });
    
    $(window).scroll(function() {
        
        if($(this).scrollTop() != 0) {
            
            $('#toTop').fadeIn();
            
        } else {
            
            $('#toTop').fadeOut();
            
        }
        
    });
    
    $('#toTop').click(function() {
        
        $('body,html').animate({scrollTop:0},800);
        
    });
    
    
});



