<%-- 
    Document   : index
    Created on : Jul 12, 2019, 12:19:51 PM
    Author     : dragn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            var count = 0;
            var cells = [];
            var xmlHttp;
            var xmlDOM;
            var new_XMLDOM;
            var tmp = "${requestScope.INFO}";
            var tableName = 'dataTable';
            var parser = new DOMParser();
            xmlDOM = parser.parseFromString(tmp, "application/xml");

            function addRow(table_id, cells) {
                var tableElem = document.getElementById(table_id);
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

            function traversalDOMTree() {
                var tableElement = document.getElementById(tableName);
                var i = 1;
                while (i < tableElement.rows.length) {
                    delRow(tableElement, i);
                }

                count = 0;
                new_XMLDOM = null;
                getAll(xmlDOM);
            }

            function getAll(node) {
                if (node === null) {
                    return;
                }
                if (node.tagName === 'username') {
                    count++;
                    cells[0] = 0;
                    cells[1] = node.firstChild.nodeValue;
                    var sibling = node.nextSibling;
                    cells[2] = sibling.firstChid.nodeValue;
                    sibling = sibling.nextSibling;
                    cells[3] = sibling.firstChid.nodeValue;
                    addRow(tableName, cells);
                }

                var childs = node.childNodes;
                for (var i = 0; i < childs.length; i++) {
                    getAll(childs[i]);
                }
            }

            function searchDOMTree(table_id) {
                var tableElem = document.getElementById(table_id);
                var i = 1;
                while (i < tableElem.rows.length) {
                    delRow(tableName, i);
                }
                count = 0;
                new_XMLDOM = null;
                searchNode(xmlDOM, myForm.txtSearch.value);
            }

            function searchNode(node, strSearch) {
                if (node === null) {
                    return;
                }
                if (node.tagName === "username") {
                    var tmpFullname = node.nextSibling.firstChild.nodeValue;
                    if (tmpFullname.indexOf(strSearch, 0) > -1) {
                        new_XMLDOM += "<account>";
                        count++;
                        cells[0] = node.firstChild.nodeValue;
                        cells[2] = tmpFullname;
                        cells[3] = node.nextSibling.nextSibling.firstChild.nodeValue;
                        new_XMLDOM += "<username>" + cells[1] + "</username>";
                        new_XMLDOM += "<fullname>" + cells[2] + "</fullname>";
                        new_XMLDOM += "<role>" + cells[3] + "</role>";
                        addRow(tableName, cells);
                    }

                    var childs = node.childNodes;
                    for (var item in childs) {
                        searchNode(item, strSearch);
                    }
                }
            }

            function searchDB() {
                xmlHttp = getXmlHttpObject();
                if (xmlHttp === null) {
                    alert("Your browser not support AJAX");
                    return;
                }
                var url = "SearchServlet?txtSearch=" + myForm.txtSearch.value;
                xmlHttp.onreadystatechange = handleStateChange();
                xmlHttp.open("GET", url, true);
                xmlHttp.send(null);
            }

            function  handleStateChange() {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                    tmp = xmlHttp.responseText;
                    if (tmp != null) {
                        var parser = new DOMParser();
                        var data = "<account>" + tmp + "</account>";
                        alert("Nhan data tu server");
                        xmlDOM = parser.parseFromString(data, "application/xml");
                        getAll(xmlDOM);
                    } else {
                        alert("No record");
                    }
                }
            }
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="myForm">
            Fullname <input type="text" name="txtSearch" value="" />
            <input type="button" value="Search" name="action" onclick="searchDOMTree('dataTable')"/>
        </form>
        <table border="1" id="dataTable">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Username</th>
                    <th>Fullname</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
        <script type="text/javascript">
            traversalDOMTree();
        </script>
    </body>
</html>
