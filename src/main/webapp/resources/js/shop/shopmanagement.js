$(function () {
    var shopId = getQueryString('shopId');
    alert(shopId)
    var shopInfoUrl = "/o2o/shopadmin/getshopmanagementinfo?shopId=" + shopId;
    alert(shopInfoUrl)
    $.getJSON(shopInfoUrl, function (data) {
        if (data.redirect) {
            window.location.href = data.url;
        } else {
            if (data.shopId != undefined && data.shopId != null) {
                shopId = data.shopId;
            }
            $("#shopInfo").attr('href', '/o2o/shopadmin/shopoperation?shopId=' + shopId);
        }
    })

})