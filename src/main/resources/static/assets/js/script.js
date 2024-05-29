let navbar = document.querySelector('.navbar');

document.querySelector('#menu-btn').onclick = () =>{
    navbar.classList.toggle('active');
    searchForm.classList.remove('active');
    cartItem.classList.remove('active');
}
let searchForm = document.querySelector('.search-form');

document.querySelector('#search-btn').onclick = () =>{
    searchForm.classList.toggle('active');
    navbar.classList.remove('active');
    cartItem.classList.remove('active');
}
let cartItem = document.querySelector('.cart-itens-container');

document.querySelector('#cart-btn').onclick = () =>{
    cartItem.classList.toggle('active');
    navbar.classList.remove('active');
    searchForm.classList.remove('active');
}
window.onscroll = () =>{
    navbar.classList.remove('active');
    searchForm.classList.remove('active');
    cartItem.classList.remove('active');
}

document.addEventListener('DOMContentLoaded', function() {
    const userInfoLi = document.querySelector('.userInfo');
    const loginLi = document.querySelector('.loginOptions');
    const registerLi = document.querySelector('.registerOption');

    // Giả sử đã đăng nhập, bạn cần thay đổi logic này
    const isAuthenticated = true;

    if (isAuthenticated) {
        userInfoLi.style.display = 'block'; // Hiển thị phần tử thông tin người dùng
        loginLi.style.display = 'none'; // Ẩn phần tử đăng nhập
        registerLi.style.display = 'none'; // Ẩn phần tử đăng ký
    } else {
        userInfoLi.style.display = 'none'; // Ẩn phần tử thông tin người dùng
        loginLi.style.display = 'block'; // Hiển thị phần tử đăng nhập
        registerLi.style.display = 'block'; // Hiển thị phần tử đăng ký
    }
});

 document.addEventListener("DOMContentLoaded", function() {
        // Lấy ra tất cả các nút "-" và "+"
        var minusButtons = document.querySelectorAll('.fa-minus');
        var plusButtons = document.querySelectorAll('.fa-plus');

        // Lấy ra tất cả các trường input
        var atyInputs = document.querySelectorAll('.cart-aty');

        // Xử lý sự kiện cho tất cả các nút "-"
        minusButtons.forEach(function(minusButton, index) {
            minusButton.addEventListener("click", function() {
                // Giảm giá trị của aty tương ứng
                var currentValue = parseInt(atyInputs[index].value);
                if (!isNaN(currentValue) && currentValue > 1) {
                    atyInputs[index].value = currentValue - 1;
                }
            });
        });

        // Xử lý sự kiện cho tất cả các nút "+"
        plusButtons.forEach(function(plusButton, index) {
            plusButton.addEventListener("click", function() {
                // Tăng giá trị của aty tương ứng
                var currentValue = parseInt(atyInputs[index].value);
                if (!isNaN(currentValue)) {
                    atyInputs[index].value = currentValue + 1;
                }
            });
        });
    });
    document.addEventListener("DOMContentLoaded", function() {
        // Lấy tất cả các biểu tượng <i> có class "fa-ellipsis"
        var ellipsisIcons = document.querySelectorAll(".fa-ellipsis");

        // Lặp qua từng biểu tượng và thêm sự kiện click
        ellipsisIcons.forEach(function(icon) {
            icon.addEventListener("click", function() {
                // Tìm đến phần tử cha <div> gần nhất có class "action-buttons"
                var actionButtons = this.closest(".icon-all").nextElementSibling;

                // Toggle sự hiển thị của actionButtons
                if (actionButtons.style.display === "none") {
                    actionButtons.style.display = "block";

                    //Nhan vao nut chinh sửa sẽ hiển thi form
                    const editButtons = document.querySelectorAll("#edit-button");
                    const editForm = document.getElementById('edit-form');
                    editButtons.forEach(function(button) {
                        button.addEventListener("click", function() {
                            var editForm = this.previousElementSibling;
                            if (editForm.style.display === "block") {
                               editForm.style.display = "none";
                            } else {
                                editForm.style.display = "block";
                                editButtons.style.display = "none";
                            }
                        });
                    });
                } else {
                    actionButtons.style.display = "none";
                }


            });
        });
    });

document.getElementById("favorite-icon").addEventListener("click", function() {
    if (this.classList.contains('favorite')) {
        this.classList.remove('favorite');
    } else {
        this.classList.add('favorite');
    }
});





