layui.define(['table', 'form', 'layer', 'jquery', 'element'], function (exports) {
    var $ = layui.$;
    var form = layui.form;
    var cmc = {};
    cmc.keys = {};
    cmc.ckeys = {};

    cmc.init = function (userCollege, userMajor, userClass, inituserCollege, inituserMajor, inituserClass, form) {//jQuery选择器, 省-市-区
        var form = layui.form;
        if (!userCollege || !$(userCollege).length) return;
        $(userCollege).html('');
        $(userCollege).append('<option selected>全部</option>');
        for (var i in colleges) {
            $(userCollege).append('<option>' + colleges[i].name + '</option>');
            cmc.keys[colleges[i].name] = colleges[i];
        }
        form.render('select');
        if (inituserCollege) $(userCollege).next().find('[lay-value="' + inituserCollege + '"]').click();
        if (!userMajor || !$(userMajor).length) return;
        cmc.formRender(userMajor);
        form.on('select(userCollege)', function (data) {
            var cs = cmc.keys[data.value];
            $(userMajor).html('');
            $(userMajor).append('<option>全部</option>');
            if (cs) {
                cs = cs.userMajor;
                for (var i in cs) {
                    $(userMajor).append('<option>' + cs[i].name + '</option>');
                    cmc.ckeys[cs[i].name] = cs[i];
                }
                $(userMajor).find('option:eq(1)').attr('selected', true);
            }
            form.render('select');
            $(userMajor).next().find('.layui-this').removeClass('layui-this').click();
            cmc.formHidden('province', data.value);
            $('.cmc-label-province').html(data.value);//此处可以自己修改 显示的位置, 不想显示可以直接去掉
        });
        if (inituserCollege) $(userCollege).next().find('[lay-value="' + inituserCollege + '"]').click();
        if (inituserMajor) $(userMajor).next().find('[lay-value="' + inituserMajor + '"]').click();
        if (!userClass || !$(userClass).length) return;
        cmc.formRender(userClass);
        form.on('select(userMajor)', function (data) {
            var cs = cmc.ckeys[data.value];
            $(userClass).html('');
            $(userClass).append('<option>全部</option>');
            if (cs) {
                cs = cs.userClass;
                for (var i in cs) {
                    $(userClass).append('<option>' + cs[i] + '</option>');
                }
                $(userClass).find('option:eq(1)').attr('selected', true);
            }
            form.render('select');
            $(userClass).next().find('.layui-this').removeClass('layui-this').click();
            cmc.formHidden('userMajor', data.value);
            $('.cmc-label-city').html(data.value);	//此处可以自己修改 显示的位置, 不想显示可以直接去掉
        });
        form.on('select(userClass)', function (data) {
            cmc.formHidden('userClass', data.value);
            $('.cmc-label-area').html(data.value);	//此处可以自己修改 显示的位置, 不想显示可以直接去掉
        });
        if (inituserCollege) $(userCollege).next().find('[lay-value="' + inituserCollege + '"]').click();
        if (inituserMajor) $(userMajor).next().find('[lay-value="' + inituserMajor + '"]').click();
        if (inituserClass) $(userClass).next().find('[lay-value="' + inituserClass + '"]').click();
    }

    cmc.formRender = function (obj) {
        $(obj).html('');
        $(obj).append('<option>全部</option>');
        form.render('select');
    }

    cmc.formHidden = function (obj, val) {
        if (!$('#cmc-hide-' + obj).length)
            $('body').append('<input id="cmc-hide-' + obj + '" type="hidden" value="' + val + '" />');
        else
            $('#cmc-hide-' + obj).val(val);
    }

    var colleges =
        [
            {
                "name": "信息工程学院",
                "userMajor": [{
                    "name": "计算机科学与技术",
                    "userClass": ["10111501", "10111502", "10111503", "10111504"]
                }, {
                    "name": "软件工程",
                    "userClass": ["10121501", "10121502", "10121503", "10121504"]
                }, {
                    "name": "电子商务",
                    "userClass": ["10131501", "10131502", "10131503", "10131504"]
                }, {
                    "name": "信息管理",
                    "userClass": ["10141501", "10141502", "10141503", "10141504"]
                }, {
                    "name": "信息管理与信息系统",
                    "userClass": ["10151501", "10151502", "10151503", "10151504"]
                }, {
                    "name": "信息与计算科学",
                    "userClass": ["10161501", "10161502", "10161503", "10161504"]
                }, {
                    "name": "通信工程",
                    "userClass": ["10211501", "10211502", "10211503", "10211504"]
                }, {
                    "name": "电子信息工程",
                    "userClass": ["10221501", "10221502", "10221503", "10221504"]
                }, {
                    "name": "电子科学与技术",
                    "userClass": ["10231501", "10231502", "10231503", "10231504"]
                }, {
                    "name": "物联网工程",
                    "userClass": ["10241501", "10241502", "10241503", "10241504"]
                }
                ]
            },
            {
                "name": "外国语言系",
                "userMajor": [{
                    "name": "英语",
                    "userClass": ["10311501", "10311502", "10311503", "10311504"]
                }, {
                    "name": "德语",
                    "userClass": ["10321501", "10321502", "10321503", "10321504"]
                }, {
                    "name": "日语",
                    "userClass": ["10331501", "10331502", "10331503", "10331504"]
                }]
            },
            {
                "name": "经济系",
                "userMajor": [{
                    "name": "国际经济与贸易",
                    "userClass": ["10411501", "10411502", "10411503", "10411504"]
                }, {
                    "name": "金融学",
                    "userClass": ["10421501", "10421502", "10421503", "10421504"]
                }, {
                    "name": "能源经济",
                    "userClass": ["10431501", "10431502", "10431503", "10431504"]
                }]
            },
            {
                "name": "艺术与设计系",
                "userMajor": [{
                    "name": "播音与主持艺术",
                    "userClass": ["10111501", "10111502", "10111503", "10111504"]
                }, {
                    "name": "舞蹈表演",
                    "userClass": ["10121501", "10121502", "10121503", "10121504"]
                }, {
                    "name": "视觉传达设计",
                    "userClass": ["10111501", "10111502", "10111503", "10111504"]
                },{
                    "name": "工业设计",
                    "userClass": ["10111501", "10111502", "10111503", "10111504"]
                }]
            },
            {
                "name": "管理系",
                "userMajor": [{
                    "name": "市场营销",
                    "userClass": ["10111501", "10111502", "10111503", "10111504"]
                }, {
                    "name": "公共事业管理",
                    "userClass": ["10121501", "10121502", "10121503", "10121504"]
                }, {
                    "name": "合计学",
                    "userClass": ["10111501", "10111502", "10111503", "10111504"]
                }]
            }
        ];
    exports('cmc',cmc);

});