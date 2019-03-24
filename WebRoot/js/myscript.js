function disptime() {
	var today = new Date();
	var y = today.getFullYear();
	var m = today.getMonth() + 1;
	var d = today.getDate();
	var w = today.getDay();
	var weekDay = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
	
	var hh = today.getHours();
	var a;
	if(hh > 12) {
		//hh = hh - 12;
		a = "PM";
	} else {
		//hh = hh;
		a = "AM";
	}
	var mm = today.getMinutes();
	var ss = today.getSeconds();
	document.getElementById("myclock").innerHTML = 
	"系统时间：" + y + "年" + (m<10 ? "0"+m : m) + "月" + (d<10 ? "0"+d : d) + "日" + "&nbsp" +
	(hh<10 ? "0"+hh : hh) + ":" + (mm<10 ? "0"+mm : mm) + ":" + (ss<10 ? "0"+ss : ss) +
	"&nbsp;"+a+"&nbsp;"+weekDay[w];
}
var myTime = setTimeout("disptime()", 1000);
var myTime = setInterval("disptime()", 1000);