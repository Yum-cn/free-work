(function($) {
	$.fn.serializeJson = function() {

		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(
				function() {
					if (serializeObj[this.name]) {
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		return serializeObj;
	};
})(jQuery);


/**
 * 毫秒日期转换年-月-日
 * 
 * @author Yum
 */
function getDate(mmsecond) {
	var result = [ 60, 60, 24 ];
	var flag;
	var result_re = "";
	mmsecond = Math.floor(mmsecond / 1000);
	// 变成秒单位,但是不操作
	var i;
	// 下面这个for计算时分秒
	for (i = 0; i < 3; i++) {
		flag = Math.floor(mmsecond % result[i]);
		mmsecond = Math.floor(mmsecond / result[i]);
		if (flag < 10) {
			result_re = "0" + flag + ":" + result_re;
		} else {
			result_re = flag + ":" + result_re;
		}
	}
	// 去掉最后的一个冒号
	result_re = result_re.substring(0, result_re.length - 1);
	// 下面计算年月日
	var year, month, day;
	var everyMonth = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	// 计算年
	flag = Math.floor(mmsecond / 365);
	year = 1970 - 0 + flag;
	mmsecond = Math.floor(mmsecond % 365);
	// 计算月和日
	for (i = 0; i < 12; i++) {
		// 判断闰月
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
			if (mmsecond == 59) {
				month = "02";
				day = "29";
				break;
			}
		}
		if (mmsecond > everyMonth[i]) {
			mmsecond -= everyMonth[i];
		} else {
			month = i + 1;
			day = mmsecond;
			month = month > 10 ? month : "0" + month;
			day = day > 10 ? day : "0" + day;
		}
	}
	// 拼起来
	result_re = year + "-" + month + "-" + day + " " + result_re;
	return result_re;
}

function formatUnixTime(time) {
	let unixtime = time;
	let unixTimestamp = new Date(unixtime * 1000);
	let Y = unixTimestamp.getFullYear();
	let M = ((unixTimestamp.getMonth() + 1) > 10 ? (unixTimestamp.getMonth() + 1)
			: '0' + (unixTimestamp.getMonth() + 1));
	let D = (unixTimestamp.getDate() > 10 ? unixTimestamp.getDate() : '0'
			+ unixTimestamp.getDate());
	
	 var h = unixTimestamp.getHours();
	    h = h < 10 ? ('0' + h) : h;
	    var minute = unixTimestamp.getMinutes();
	    var second = unixTimestamp.getSeconds();
	    minute = minute < 10 ? ('0' + minute) : minute;
	    second = second < 10 ? ('0' + second) : second;
	
	let toDay = Y + '-' + M + '-' + D+ ' ' + h + ':' + minute + ':' + second;
	return toDay;
}


function formatTimeStamp(time) {
	console.log(">>>"+time);
	let unixtime = time;
	console.log(unixtime);
	let unixTimestamp = new Date(unixtime);
	let Y = unixTimestamp.getFullYear();
	let M = ((unixTimestamp.getMonth() + 1) > 10 ? (unixTimestamp.getMonth() + 1)
			: '0' + (unixTimestamp.getMonth() + 1));
	let D = (unixTimestamp.getDate() > 10 ? unixTimestamp.getDate() : '0'
			+ unixTimestamp.getDate());
	
	 var h = unixTimestamp.getHours();
	    h = h < 10 ? ('0' + h) : h;
	    var minute = unixTimestamp.getMinutes();
	    var second = unixTimestamp.getSeconds();
	    minute = minute < 10 ? ('0' + minute) : minute;
	    second = second < 10 ? ('0' + second) : second;
	
	let toDay = Y + '-' + M + '-' + D+ ' ' + h + ':' + minute + ':' + second;
	return toDay;
}
