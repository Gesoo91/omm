/**
 * 
 */
$(document).ready(function() {
  $('.tab_head').hover(function(){
  	$(this).css('background-color', 'brown');
    $(this).siblings().show();
  }, function() {
 	$(this).css('background-color', 'yellow');
    $(this).siblings().hide();
  
  });
  $('.tab_head').siblings().hover(function() {
    $(this).siblings().show();
    $(this).show();
  }, function() {
    $(this).hide();
    $(this).siblings().hide();
    $('.tab_head').show();
  });
});