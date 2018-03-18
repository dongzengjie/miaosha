$(function() {
	var goodsId = getQueryString("goodsId");
	var goodsurl = '/miaosha/goods/getGoodsById/' + goodsId;

	$.get(goodsurl, {}, function(data) {
		var token = $.cookie('token');// 从cookie中获取手机号
		if (!token) {
			window.location.href = "/miaosha/login/tologin";
		}

		var imghtml = '<img  id="img" src="/miaosha' + data.goods.goodsImg
				+ '" width="200" height="200" >';
		$('#imgdetail').html(imghtml);
		var goods = data.goods;
		var startTime = goods.startDate;
		var endTime = goods.endDate;
		var nowTime = data.nowtime;
		countdown(goods.goodsId, nowTime, startTime, endTime);
	});

});

function countdown(goodsId, nowTime, startTime, endTime) {
	var seckillBox = $("#seckill-box");
	if (nowTime > endTime) {
		seckillBox.html("秒杀结束");
	} else if (nowTime < startTime) {

		var killTime = new Date(startTime + 1000);
		seckillBox.countdown(killTime, function(event) {
			// 时间格式
			var format = event.strftime('秒杀计时 ：%D天 %H时 %M分 %S秒');
			seckillBox.html(format);
		}).on('finish.countdown', function() {
			handleSeckill(goodsId, seckillBox);

		});

	} else {
		// 秒杀开始
		handleSeckill(goodsId, seckillBox);
	}
}

function handleSeckill(goodsId, node) {
	var exposeUrl = '/miaosha/miaosha/' + goodsId + '/getMd5';
	node
			.hide()
			.html(
					'<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');

		$.post(exposeUrl, {}, function(data) {
			if(data.code ==0){
				if (data.data.exposed) {
					var killurl = '/miaosha/miaosha/' + goodsId + '/' + data.data.key_MD5
							+ '/domiaosha';
					console.log("killUrl:" + killurl);
					$('#killBtn').one(
							'click',
							function() {
								// 禁用按钮
								$("#killBtn").addClass('disabled');

								$.post(killurl, {}, function(data) {
								
										node.html('<span class="labe label-success">'+data.msg+'</spna>')
								
									

								});
							});
					
					node.show(400);
				}else{
					countdown(goodsId ,data.data.exposed.now, data.data.exposed.start, data.data.exposed.end);
				}
			}else{
				layer.msg(data.msg);
			}
	});

}