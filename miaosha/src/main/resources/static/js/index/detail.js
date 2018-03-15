$(function(){
	var goodsId = getQueryString("goodsId");
	var goodsurl ='/miaosha/goods/getGoodsById/'+goodsId;
	
	$.get(goodsurl,{},function(data){
		
		var imghtml='<img  id="img" src="/miaosha/'+data.goods.goodsImg+'" width="200" height="200" >';
		$('#imgdetail').html(imghtml);
	});
	
});