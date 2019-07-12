<%-- 
    Document   : index
    Created on : Jun 26, 2019, 12:36:59 PM
    Author     : dragn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XML Final Demo Page</title>
        <script type="text/javascript">
            var count = 0;
            var cells = [];
            var new_XMLDOM = "";
            var xmlHttp;

            function addRow(tableid, cells) {
                var tableElem = document.getElementById(tableid);
                var newRow = tableElem.insertRow(tableElem.rows.length);
                var newCell;
                for (var i = 0, max = cells.length; i < max; i++) {
                    newCell = newRow.insertCell(newRow.cells.length);
                    newCell.innerHTML = cells[i];
                }
                return newRow;
            }

            function delRow(table_id, rowNumber) {
                var tableElem = document.getElementById(table_id);
                if (rowNumber > 0 && rowNumber < tableElem.rows.length) {
                    tableElem.deleteRow(rowNumber);
                } else {
                    alert("Failed");
                }
            }

            function getXmlHttpObject() {
//                var xmlHttp = null;
//                try { //firefox, opera, safari, chrome
//                    xmlHttp = new XMLHttpRequest();
//                } catch (e) {// IE
//                    try {
//                        xmlHttp = new ActiveXObject("Msxml12.XMLHTTP");
//                    } catch (e) {
//                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
//                    }
//                }
//                return xmlHttp;

                var xmlHttp = null;
                if (window.XMLHttpRequest) {
                    // code for modern browsers
                    xmlHttp = new XMLHttpRequest();
                } else {
                    // code for old IE browsers
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                return xmlHttp;
            }

            function  searchNode(node, strSearch, table_id) {
                if (node === null) {
                    return;
                }
//                alert(node.tagName);
                if (node.tagName === "booktitle") {
                    var tmp = node.firstChild.nodeValue;
//                    alert(tmp.indexOf(strSearch, 0) + "----" + tmp + "----" + strSearch);
                    if (tmp.indexOf(strSearch, 0) > -1) {
                        var parent = node.parentNode;
                        var attrID = parent.attributes.getNamedItem("id").text;
                        new_XMLDOM += "<book id='" + attrID + "'>";
                        count++;
                        cells[0] = count;
                        cells[1] = parent.attributes.getNamedItem("id").text;
                        cells[2] = node.firstChild.nodeValue;
                        new_XMLDOM += "<booktitle>" + node.firstChild.nodeValue + "</booktitle>";
                        var sibling = node.nextSibling;
                        cells[3] = sibling.firstChild.nodeValue;
                        new_XMLDOM += "<author>" + sibling.firstChild.nodeValue + "</author>";
                        sibling = sibling.nextSibling;
                        cells[4] = sibling.firstChild.nodeValue;
                        new_XMLDOM += "<price>" + sibling.firstChild.nodeValue + "</price>";
                        new_XMLDOM += "</book>"
                        addRow(table_id, cells);
                    }
                }

                var childs = node.childNodes;
                for (var i = 0; i < childs.length; i++) {
                    searchNode(childs[i], strSearch, table_id);
                }
            }

            function travelsailDOMTree(fileName, table_id) {
                var tableElem = document.getElementById(table_id);
                var i = 1;
                while (i < tableElem.rows.length) {
                    delRow(table_id, i);
                }

                count = 0;
                new_XMLDOM = "";

                var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
//                var xmlDom = getXmlHttpObject();
                new_XMLDOM = '<library xmlns="http://netbeans.org.schema.library">';
                xmlDom.async = false;
                xmlDom.load(fileName);
                searchNode(xmlDom, myForm.txtSearch.value, table_id);
                new_XMLDOM += "</library>";
//                alert(new_XMLDOM);
            }

            function updateUnmarshall() {
                xmlHttp = getXmlHttpObject();
                if (xmlHttp === null) {
                    alert("Your browser not support AJAX");
                    return;
                }

                xmlHttp.open("POST", "UpdateController", true);
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                var url = "xmlContent=";
                url += new_XMLDOM;
                xmlHttp.send(url);
            }
        </script>
    </head>
    <body>
        <h1>Javascript with DOM Demo</h1>
        <form name="myForm">
            Name: <input type="text" name="txtSearch" value="" />
            <input type="button" value="search" onclick="travelsailDOMTree('./library.xml', 'datatable')" />
        </form>
        <table border="1" id="datatable">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Code</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price</th>
                </tr>
            </thead>
        </table>

        <br/>
        <form action="XsdController" method="POST">
            <input type="submit" value="Create" name="action" />
        </form>
        <br/>
        <form name="updateForm">
            <input type="button" value="Synchronize" onclick="updateUnmarshall()"/>
        </form>
        <br/>
        <form action="PrintPdfController">
            Full name: <input type="text" name="txtSearch" value="" /><br/>
            <input type="submit" value="Search" />
        </form>

    </body>
</html>
