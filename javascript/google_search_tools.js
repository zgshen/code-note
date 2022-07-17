// ==UserScript==
// @name         google search tools display
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  解决登录状态下谷歌搜索结果的工具按钮不见的问题
// @author       You
// @match        https://www.google.com/*
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @grant        none
// ==/UserScript==


function addTools(obj, new_url) {
    let a_html = '<a href="'+new_url+'" style="padding: 0px 30px;" >Tools</a>';
    let div = window.document.createElement("div");
    div.className = "Nk2ERd HU5Dkd";
    div.style.cssText = 'vertical-align: bottom;height: 26px;';
    div.innerHTML = a_html;
    obj.appendChild(div);
}

function updateQueryStringParameter(uri, key, value) {
    if(!value) {
        return uri;
    }
    const re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
    const separator = uri.indexOf('?') !== -1 ? "&" : "?";
    if (uri.match(re)) {
        return uri.replace(re, '$1' + key + "=" + value + '$2');
    }
    else {
        return uri + separator + key + "=" + value;
    }
}

(function() {
    'use strict';
    // Your code here...
    const url = window.location.href;
    let search_url = 'https://www.google.com/search';
    let div_class_name = 'a6ZQye';
    const arr = document.getElementsByClassName(div_class_name);
    // 在列表后加一个a标签，点击后就会显示搜索工具
    if (url.indexOf(search_url) >= 0 && arr.length > 0) {
        let new_url = updateQueryStringParameter(url, 'tbs', 'qdr:y');
        addTools(arr[0], new_url);
    } else console.log("didn't work...")

    // 显示的工具样式有问题的话就调整下
    let tool_id_name = 'hdtbMenus';
    const tool_obj = document.getElementById(tool_id_name);
    if (tool_obj) tool_obj.style.cssText = 'top: 15px;';

})();
