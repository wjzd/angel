layui.define(function(exports){
	//do something
	var $=layui.$;
	$("#zHeader").load("/getHeader");
	$("#footer").load("/getFooter");
	$(".search-unlogin").click(function () {
		$(".search-input-hull").removeClass("hide");
		$(".menu-box").addClass("hide");
	});
	$(".search-cancel").click(function () {
		$(".search-input-hull").addClass("hide");
		$(".menu-box").removeClass("hide");
	})
	$(".menu-list-box").each(function () {
		var name=$("#categoryName").val();
		if ($(this).text()==name){
			$(this).parent("li").addClass("current")
			$(this).attr("href","#");
		}else {
			$(this).parent("li").removeClass("current");
		}
	});

	var height = $(this).scrollTop();
	exports('common', {});
});