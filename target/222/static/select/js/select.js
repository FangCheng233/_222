//引入新的插件
layui.config({
    base: '/static/select/js/',//拓展模块的根目录
}).extend({
    pca: 'pca'
});
layui.config({
    base: '/static/select/js/'//拓展模块的根目录
}).extend({
    cmc: 'cmc'
});

//使用自定义的插件pca
layui.use(['form', 'layedit', 'laydate', 'upload', "jquery", "pca"], function () {
    var $ = layui.$
        , form = layui.form
        , pca = layui.pca;
    //带初始值进行初始化
    pca.init('select[name=basePlaceP]', 'select[name=basePlaceC]', 'select[name=basePlaceA]', '陕西', '西安', '长安区');

    //不带初始值

    //输入提示
    $("#address").bind('input propertychange', function () {
        var address = document.getElementById("address").value;
        if (address == "") {
            document.getElementById("addressTip").style.display = "none";
            return;
        }
        var html = '';
        var province = document.getElementById("province").value;
        if (province == "全部") {
            province = '';
        }
        var city = document.getElementById("city").value;
        if (city == "全部") {
            city = '';
        }
        var area = document.getElementById("area").value;
        if (area == "全部") {
            area = '';
        }

        document.getElementById("addressTip").innerHTML = html;
        var s = document.getElementById("addressTip").innerHTML;
        if (html == "") {
            document.getElementById("addressTip").style.display = "none";
        } else {
            document.getElementById("addressTip").style.display = "block";
        }

        var lis = document.getElementById("addressDetail").getElementsByTagName("dd");
        for (var i = 0; i < lis.length; i++) {
            if (lis[i].tagName == "DD") {
                lis[i].onclick = (function () {//增加单击事件
                    return function () {
                        document.getElementById("address").value = this.innerText.trim().split(/\s+/)[0];
                        document.getElementById("addressTip").style.display = "none";
                    }
                }
                )
                    (i);
            }

        }

    }
    );

});
//使用自定义的插件pca
layui.use(['form', 'layedit', 'laydate', 'upload', "jquery", "cmc"], function () {
    var $ = layui.$
        , form = layui.form
        , cmc = layui.cmc;
    //带初始值进行初始化
    cmc.init('select[name=userCollege]', 'select[name=userMajor]', 'select[name=userClass]', '全部', '全部', '全部');

    //不带初始值

    //输入提示
    $("#address").bind('input propertychange', function () {
            var address = document.getElementById("address").value;
            if (address == "") {
                document.getElementById("addressTip").style.display = "none";
                return;
            }
            var html = '';
            var userCollege = document.getElementById("userCollege").value;
            if (userCollege == "全部") {
                userCollege = '';
            }
            var userMajor = document.getElementById("userMajor").value;
            if (userMajor == "全部") {
                userMajor = '';
            }
            var userClass = document.getElementById("userClass").value;
            if (userClass == "全部") {
                userClass = '';
            }
            document.getElementById("addressTip").innerHTML = html;
            var s = document.getElementById("addressTip").innerHTML;
            if (html == "") {
                document.getElementById("addressTip").style.display = "none";
            } else {
                document.getElementById("addressTip").style.display = "block";
            }
            var lis = document.getElementById("addressDetail").getElementsByTagName("dd");
            for (var i = 0; i < lis.length; i++) {
                if (lis[i].tagName == "DD") {
                    lis[i].onclick = (function () {//增加单击事件
                            return function () {
                                document.getElementById("address").value = this.innerText.trim().split(/\s+/)[0];
                                document.getElementById("addressTip").style.display = "none";
                            }
                        }
                    )
                    (i);
                }

            }

        }
    );

});