$(function () {

    var shopId = getQueryString("shopId");
    var listUrl = '/o2o/shopadmin/listproductcategorys?shopId=' + shopId;
    //  var addUrl = '/o2o/shop/addproductcategorys';
    var deleteUrl = '/o2o/shopadmin/removeproductcategory';
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


                //  移除已经入库的 商品类别
                var nowItems = document.querySelectorAll(".category-wrap .now a");
                nowItems.forEach(function (nowItem, index) {
                    nowItem.addEventListener("click", function (evt) {
                        var tempProductCategoryId = this.getAttribute("data-id");
                        removeStoredItem(deleteUrl, tempProductCategoryId);
                    })
                })

            } else {
                $.toast('查询失败！' + data.errorCode + "   " + data.errorMsg);
            }
        });
});
var newButton = document.getElementById("new");
// 新增按钮
newButton.onclick = function () {
    var tempHtml = '<div class="row row-product-category temp">'
        + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
        + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
        + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
        + '</div>';

    var listItem = document.querySelector('.category-wrap');
    listItem.innerHTML += tempHtml;
    // 新增的节点可以随时移除
    var nowItems = document.querySelectorAll(".category-wrap .temp a");
    nowItems.forEach(function (nowItem, index) {
        nowItem.addEventListener("click", function (evt) {
            nowItem.parentNode.parentNode.parentNode.removeChild(nowItem.parentNode.parentNode);
        })
    })
}


// 移除某个项目 这里使用 fetch 替换了的 .ajax
function removeStoredItem(deleteUrl, tempProductCategoryId) {
    var data = {tempProductCategoryId: tempProductCategoryId}
    alert(JSON.stringify(data));
    alert(tempProductCategoryId)
    $.confirm("确定要删除吗？", function () {
        fetch(deleteUrl,
            {
                method: 'POST',
                body: JSON.stringify(data),
                headers:
                    new Headers({
                        'Content-Type': 'application/json'
                    })
            }).then(response => response.json()).then(response => {
        console.log(JSON.stringify(response))
        if (response.success) {
            $.toast("删除成功")

        }
    }
    )
        /* .then((responseData)=>{
             debugger;
             var data2 = responseData.json();
             alert(responseData.resultMap);
             console.log(JSON.stringify(responseData))
     })*/
    });
}