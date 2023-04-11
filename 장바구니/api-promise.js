window.addEventListener("DOMContentLoaded",function(){
'use strict';

    const loginForm = document.getElementById("login-form");
    const validateForm=function(form){
        if(form['userId'].value.trim() == '' ){
            alert("userId empty!");
            form['userId'].focus();
            return false;
        }
        if(form['userPassword'].value.trim() == '' ){
            alert("userPassword empty!");
            form['userPassword'].focus();
            return false;
        }
    }

    loginForm.addEventListener("submit",function(event){
        event.preventDefault();

        if( validateForm(event.target)==false ){
            return ;
        }
        
        //로그인 api 호출
        const userId = event.target['userId'].value;
        const userPassword = event.target['userPassword'].value;
        
        doLogin(userId, userPassword).then((user)=>{
            //로그인 display 처리
            const loginWrapper = document.getElementById("login-wrapper");
            loginWrapper.setAttribute("style","display:none;");
            const loginSuccess = document.getElementById("login-success");
            loginSuccess.setAttribute("style","display:block");
            
            const loginUserId = document.getElementById("login-userId");
            const loginUserName = document.getElementById("login-userName");
            const loginCartId = document.getElementById("login-cartId");

            loginUserId.innerText=user.userId;
            loginUserName.innerText=user.userName;
            loginCartId.innerText=user.cartId;

            return user;
        }).catch(e=>{
            throw new Error("api-error-");
            reject(e);
        }).then((user)=>{
            return getCartItems(user.userId, user.cartId);
        }).then((items)=>{
            const cartTable = document.getElementById("cart-table");
            const body = cartTable.getElementsByTagName("tbody")[0];
            const intl = new Intl.NumberFormat();

            for (const item of items) {
                const tr = document.createElement("tr");
                const td1 = document.createElement("td");
                const td2 = document.createElement("td");
                const td3 = document.createElement("td");
                const td4 = document.createElement("td");
                const td5 = document.createElement("td");
                td1.innerText=item.productId;
                td2.innerText=item.name;
                td3.innerText=intl.format(item.price);
                td4.innerText=intl.format(item.amount);
                td5.innerText= intl.format(item.totalPrice);
                tr.append(td1,td2,td3,td4,td5);
                body.append(tr);
            }

        });
    });

});


function doLogin(userId, userPassword){
    const promise = new Promise((resolve, reject)=>{
        const xhr = new XMLHttpRequest();
        const url = "http://133.186.144.236:8100/api/users/login";

        const data = {
            userId : userId,
            userPassword :userPassword
        }
        
        xhr.addEventListener("loadend",function(){
            console.log(this.responseText);
            if(this.status==200){
                const user = JSON.parse(this.responseText);
                resolve(user);
            }else{
                reject(new Error("login api error"));
            }
        });
        xhr.addEventListener("error",function(){
            reject(new Error("login api error"));
        });

        xhr.open("POST",url);
        xhr.setRequestHeader("content-type","application/json");
        xhr.send(JSON.stringify(data));
    });
    return promise;
}

function getCartItems(userId, cartId){
    const promise = new Promise((resolve, reject)=>{
        const xhr = new XMLHttpRequest();
        const url ="http://133.186.144.236:8100/api/nhnmart/shopping-cart/" + cartId;

        xhr.addEventListener("loadend", function(){
            console.log(this.responseText);
            const items = JSON.parse(this.responseText);
            resolve(items);
        });

        xhr.addEventListener("error",function(){
            reject(new Error("cart api error"));
        });
        xhr.open("GET",url);
        xhr.setRequestHeader("content-type","application/json");
        xhr.setRequestHeader("X-USER-ID", userId);
        xhr.send('');
    });

    return promise;
}