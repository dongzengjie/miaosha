$(function(){
	var getGoodsListUrl='/miaosha/goods/getGoodsList';
	
	$.get(getGoodsListUrl,{},function(data){
		var goodsHtml='';
		
		var goodslist=data.data.goodslist;
		goodslist.map(function(item,index){
			goodsHtml +='<tr>'
					  +'<td>'+item.goodsName+'</td>'
					  +'<td>'+item.goodsStock+'</td>'
					  +'<td>'+item.goodsPrice+'</td>'
					  +'<td>'+item.miaoshaPrice	+'</td>'
					  +'<td>'+item.stockCount+'</td>'
					  +'<td>'+datetimeFormat(item.startDate)+'</td>'
					  +'<td>'+datetimeFormat(item.endDate)+'</td>'
					  +'<td><a class="btn btn-info" href="/miaosha/goods/todetail?goodsId='+item.goodsId+'">详情页面</a></td>'
						
		});
		
		$('#index').html(goodsHtml);
	});
});