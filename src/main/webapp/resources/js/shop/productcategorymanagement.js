$(function () {

    var shopId = getQueryString("shopId");
    var listUrl = '/o2o/shopadmin/listproductcategorys?shopId=' + shopId;
    //  var addUrl = '/o2o/shop/addproductcategorys';
    //  var deleteUrl = '/o2o/shop/removeproductcategory';
    $.getJSON(listUrl,
        function (data) {
            if (data.success) {
                var dataList = data.productCategoryList;

                $('.category-wrap').html('');
                var tempHtml = '';
                dataList.map(function (item, index) {
                    tempHtml += ''
                        + '<div class="row row-product-category now">'
                        + '<div class="col-33 product-category-name">'
                        + item.productCategoryName
                        + '</div>'
                        + '<div class="col-33">'
                        + item.priority
                        + '</div>'
                        + '<div class="col-33"><a href="#" class="button delete" data-id="'
                        + item.productCategoryId
                        + '">删除</a></div>' + '</div>';
                });
                $('.category-wrap').append(tempHtml);
            } else {
                $.toast('查询失败！' + data.errorCode + "   " + data.errorMsg);
            }
        });
    var newButton = document.getElementById("new");

    newButton.onclick = function () {
        var tempHtml = '<div class="row row-product-category temp">'
            + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
            + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
            + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
            + '</div>';

        var listItem = document.querySelectorAll('.category-wrap');
      //  alert(listItem[0].innerHTML)
        listItem[0].innerHTML+=tempHtml;
      //  listItem[]
      //  $('.category-wrap').append(tempHtml);
    }
});

