var json_visible = 1,
    json_pnl_size = 1,
    table_visible = 1,
    tree_visible = 1,
    tree_pnl_size = 1,
    xxa_pnl_size = 1;
$("#inner_tbl").show();
jQuery(function(a) {

    /*a("#all_panels").split({
        orientation: "vertical",
        //limit: 0,
        position: "33%"
    });
    a("#xxa").split({
        orientation: "vertical",
        //limit: 0,
        position: "50%"
    });*/
    json_pnl_size = a("#json_pnl").width();
    xxa_pnl_size = a("#xxa").width();
    
    a("#load_json_btn").click(function() {
        processJson()
    });

});

function sendMsg() {
    $.ajax({
        type: "GET",
        url: "http://json2table-env-ayji8pibkt.elasticbeanstalk.com/save_msg",
        data: {
            callback: "call",
            msg: $("#leaveMsg").val()
        },
        contentType: "application/json",
        dataType: "jsonp",
        success: function(a) {},
        error: function(a) {}
    })
}
var g;

function loadfromURL(a) {
    $("#json_vl").val("Loading...");
    $("#inner_tbl").html("");
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/configs/toggler?serviceId=serviceCDE&version=1.0",
        accept: "application/json",
        success: function(a) {
            $("#json_vl").val(JSON.stringify(a, void 0, 2));
            processJson()
        },
        error: function(e) {
            $("#json_vl").val("");
            $("#error_msg").text("Not a valid JSON from " + a);
            $("#errorModal").modal("show");
            return {}
        }
    })
}

function call(a) {
    $("#json_vl").val(JSON.stringify(a, void 0, 2));
    processJson()
}

function processJson() {
    $("#inner_tbl").html(buildTable(getJsonVar()));
}

function getJsonVar() {
    try {
        var a = $.parseJSON($("#json_vl").val());
        $("#json_vl").val(JSON.stringify(a, void 0, 2));
        return a
    } catch (e) {
        return $("#error_msg").text(e.message), $("#errorModal").modal("show"), {}
    }
}

function buildTable(a) {
    var e = document.createElement("table"),
        d, b;
    if (isArray(a)) return buildArray(a);
    for (var c in a) "object" != typeof a[c] || isArray(a[c]) ? "object" == typeof a[c] && isArray(a[c]) ? (d = e.insertRow(-1), b = d.insertCell(-1), b.colSpan = 2, b.innerHTML = '<div class="td_head">' + encodeText(c) + '</div><table style="width:100%">' + $(buildArray(a[c]), !1).html() + "</table>") : (d = e.insertRow(-1), b = d.insertCell(-1), b.innerHTML = "<div class='td_head'>" + encodeText(c) + "</div>", d = d.insertCell(-1), d.innerHTML = "<div class='td_row_even' value="+encodeText(a[c])+">" +
        encodeText(a[c]) + "</div>") : (d = e.insertRow(-1), b = d.insertCell(-1), b.colSpan = 2, b.innerHTML = '<div class="td_head">' + encodeText(c) + '</div><table style="width:100%">' + $(buildTable(a[c]), !1).html() + "</table>");
    return e
}

function buildArray(a) {
    var e = document.createElement("table"),
        d, b, c = !1,
        p = !1,
        m = {},
        h = -1,
        n = 0,
        l;
    l = "";
    if (0 == a.length) return "<div></div>";
    d = e.insertRow(-1);
    for (var f = 0; f < a.length; f++)
        if ("object" != typeof a[f] || isArray(a[f])) "object" == typeof a[f] && isArray(a[f]) ? (b = d.insertCell(h), b.colSpan = 2, b.innerHTML = '<div class="td_head"></div><table style="width:100%">' + $(buildArray(a[f]), !1).html() + "</table>", c = !0) : p || (h += 1, p = !0, b = d.insertCell(h), m.empty = h, b.innerHTML = "<div class='td_head'>&nbsp;</div>");
        else
            for (var k in a[f]) l =
                "-" + k, l in m || (c = !0, h += 1, b = d.insertCell(h), m[l] = h, b.innerHTML = "<div class='td_head'>" + encodeText(k) + "</div>");
    c || e.deleteRow(0);
    n = h + 1;
    for (f = 0; f < a.length; f++)
        if (d = e.insertRow(-1), td_class = isEven(f) ? "td_row_even" : "td_row_odd", "object" != typeof a[f] || isArray(a[f]))
            if ("object" == typeof a[f] && isArray(a[f]))
                for (h = m.empty, c = 0; c < n; c++) b = d.insertCell(c), b.className = td_class, l = c == h ? '<table style="width:100%">' + $(buildArray(a[f]), !1).html() + "</table>" : " ", b.innerHTML = "<div value=" + encodeText(l) +" class='" + td_class + "'>" + encodeText(l) +
                    "</div>";
            else
                for (h = m.empty, c = 0; c < n; c++) b = d.insertCell(c), l = c == h ? a[f] : " ", b.className = td_class, b.innerHTML = "<div value=" + encodeText(l) +" class='" + td_class + "'>" + encodeText(l) + "</div>";
    else {
        for (c = 0; c < n; c++) b = d.insertCell(c), b.className = td_class, b.innerHTML = "<div class='" + td_class + "'>&nbsp;</div>";
        for (k in a[f]) c = a[f], l = "-" + k, h = m[l], b = d.cells[h], b.className = td_class, "object" != typeof c[k] || isArray(c[k]) ? "object" == typeof c[k] && isArray(c[k]) ? b.innerHTML = '<table style="width:100%">' + $(buildArray(c[k]), !1).html() + "</table>" : b.innerHTML =
            "<div value=" + encodeText(c[k]) +" class='" + td_class + "'>" + encodeText(c[k]) + "</div>" : b.innerHTML = '<table style="width:100%">' + $(buildTable(c[k]), !1).html() + "</table>"
    }
    return e
}

function encodeText(a) {
    return $("<div />").text(a).html()
}

function isArray(a) {
    return "[object Array]" === Object.prototype.toString.call(a)
}

function isEven(a) {
    return 0 == a % 2
}

function buildTree(a, e, d) {
    e += 1;
    if ("undefined" === typeof a) log("undef!!", e);
    else
        for (var b in a)
            if ("object" == typeof a[b]) {
                var c = addTree(b, d, isArray(a[b]));
                buildTree(a[b], e, c)
            } else addLeaf(b, a, d)
}

function addLeaf(a, e, d) {
    var b = "";
    isArray(e) || (b = a + ":");
    b += e[a];
    Math.random().toString(36).substr(2, 9);
    a = document.createElement("li");
    a.className = "file";
    a.innerHTML = "<a>" + encodeText(b) + "</a>";
    d.appendChild(a)
}

function log(a, e, d) {
    console.log(a)
};


function tableToObjFase1( table ) {
    var trs = table.rows,
        trl = trs.length,
        i = 0,
        j = 0,
        jsonObj = new Object(),
        obj, ret = [];
    
    

    for (; i < trs.length; i++) {
        	if(trs[i].lastChild != undefined && trs[i].lastChild.lastChild.localName == "table"){ 
        		console.log(trs[i].firstChild.firstChild.textContent);
        		console.log("recursivo!")
        		jsonObj[trs[i].firstChild.firstChild.textContent] = tableToObjFase2(trs[i].lastChild.lastChild);
        		console.log("retorno recursivo!")
        	}else{
        		console.log(trs[i].firstChild.textContent);
        		jsonObj[trs[i].firstChild.textContent] = trs[i].lastChild.textContent;  
        		
        	}
    }
    console.log(jsonObj);
    console.log(JSON.stringify(jsonObj));
    return jsonObj;
};

function tableToObjFase2(table) {
    var trs = table.rows,
        trl = trs.length,
        i = 1,
        //j = 0,
        
        ret = [];
    	var campoAtual = "";
    

    for (; i < trs.length; i++) {
    	j = 0;
    	jsonObj = new Object();
        for (; j < trs[i-1].children.length; j++) {
        	if(trs[0].children[j].firstChild.lastChild != undefined && trs[i].children[j].firstChild.localName == "table"){ 
        		console.log(trs[0].children[j].firstChild.lastChild.textContent);
        		jsonObj[trs[0].children.length<=j?trs[0].children[trs[0].children.length-1].firstChild.lastChild.textContent:trs[0].children[j].firstChild.lastChild.textContent] = tableToObjFase3(trs[i].children.length<=j?trs[i].children[j-1].firstChild:trs[i].children[j].firstChild);
        	}else{
        		console.log(trs[0].children[j].firstChild.lastChild.textContent);
        		jsonObj[trs[0].children[j].firstChild.lastChild.textContent] = trs[i].children[j].firstChild.lastChild.textContent;  
        		
        	}
        }
        ret.push(jsonObj);
    }
    console.log(JSON.stringify(ret));
    return ret;
};

function tableToObjFase3(table) {
    var trs = table.rows,
        trl = trs.length,
        i = 1,
        ret = [];
    	var campoAtual = "";
    for (; i < trs.length; i++) {
    	j = 0;
    	jsonObji = new Object();
        for (; j < trs[i-1].children.length; j++) {
        	if(trs[0].children[j].firstChild.lastChild != undefined && trs[i].children[j].firstChild.localName == "table"){ 
        		console.log(trs[0].children[j].firstChild.lastChild.textContent);
        		jsonObji[trs[0].children.length<=j?trs[0].children[trs[0].children.length-1].firstChild.lastChild.textContent:trs[0].children[j].firstChild.lastChild.textContent] = tableToObjFase2(trs[i].children.length<=j?trs[i].children[j-1].firstChild:trs[i].children[j].firstChild);
        	}else{
        		console.log(trs[0].children[j].firstChild.lastChild.textContent);
        		jsonObji[trs[0].children[j].firstChild.lastChild.textContent] = trs[i].children[j].firstChild.lastChild.textContent;  
        		
        	}
        }
        ret.push(jsonObji);
    }
    console.log(JSON.stringify(ret));
    return ret;
};

$('.td_row_even').click(function(){
	alert("editavel");
	var newelement = document;
 $(newelement).blur(function(){
  //use $.ajax to send the element's value to your server
  //remove the input element and then replace the previous element with the new value
 });
});
