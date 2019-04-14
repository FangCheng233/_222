window.onload=function(){
    var aslt=document.getElementsByTagName('select')[1];
    var aotn=aslt.getElementsByTagName('option')[1];
    aslt.onchange=function(){
        turnCity();
    }
};
function turn(){
    var provinceList=new Array();
    provinceList['请选择国家']=["请选择省份"];
    provinceList['中国']=["请选择省份","浙江","江苏","福建",".."];
    provinceList['美国']=["请选择省份","11","22","33"];
    provinceList['英国']=["请选择省份","44","55"];
    var province=document.forms[0].province;//文档中的第一个省份；
    province.options.length=0;//把province下拉列表的选项清0
    var index=document.forms[0].country.value; //文档中的第一个国家；
    province.options.length=0;
    for(var j in provinceList[index]){
        newOption=new Option(provinceList[index][j],provinceList[index][j]);
        province.options.add(newOption);
        var city=document.forms[0].city;//文档中的第一个市区；
        city.options.length=1;//把city下拉列表的选项清0
    }
}
function turnCity(){
    var cityList=new Array();
    cityList['请选择省份']=["请选择城市"];
    cityList['..']=["....."];
    cityList['浙江']=["请选择城市","杭州","宁波","温州","绍兴","金华","湖州","嘉兴"];
    cityList['江苏']=["请选择城市","南京","苏州","徐州","无锡","常州","镇江"];
    cityList['福建']=["请选择城市","福州市","厦门市","龙岩市","钓鱼岛","冲绳","三沙市","..."];
    cityList['11']=["请选择城市","aa","bb","cc"];
    cityList['22']=["请选择城市","dd","ee","ff"];
    cityList['33']=["请选择城市","gg"];
    cityList['44']=["请选择城市","hh11","iiaa","jjcc"];
    cityList['55']=["请选择城市","kk1234","ll4567","mm8910"];
    var city=document.forms[0].city;//文档中的第一个市区；
    city.options.length=0;//把city下拉列表的选项清0
    var index=document.forms[0].province.value;
    city.options.length=0;
    for(var j in cityList[index]){
        newOption=new Option(cityList[index][j],cityList[index][j]);
        city.options.add(newOption);
    }
}