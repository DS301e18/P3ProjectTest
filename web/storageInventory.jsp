<%@ page import="java.util.List" %>
<%@ page import="model.Storage" %>
<%@ page import="model.Transactions" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 05/12/2018
  Time: 18.23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lager Inventar</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"
          charset="UTF-8">
</head>
<body>
<% Storage storage = (Storage) session.getAttribute("storageChosen");%>

<!-- TODO: Make storage inventory dynamic -->
<!-- Storage inventory-->
<div class="container" id="storage">
    <section>

        <!-- Inventory header -->
        <div class="contentBox" id="inventoryHeader">

            <form action="EditStorageController" method="get">
                <!--Storage name-->
                <label><%=storage.getName()%></label>

                <!-- Edit button -->
                <%if(session.getAttribute("role").equals("Chef")){%>
                    <button><span style="font-size: 20px"><i class="fas fa-hammer"></i></span></button>
                <%}%>
            </form>
            <!--Search Bar-->
            <form action="search" accept-charset="ISO-8859-1" method="post">
                <input id="search" type="text" placeholder="Søg..." name="search">
            </form>
        </div>

        <!-- Tabs only available for a manager-->
        <%if(session.getAttribute("role").equals("Chef")){%>
            <button class="tab" id="registerProductButton" onclick="show('registerProductPage', 'historyPage')">Registrer Vare</button>

            <form action="History" method="post">
                <button class="tab" id="historyButton" onclick="show('historyPage', 'registerProductPage')">Historik</button>
            </form>

            <!-- Breaklines TODO: should probably be done with css-->
            <br><br>
        <%}%>

        <jsp:include page="inventory.jsp"/>

        <!-- Price-box -->
        <div class="priceBox"><a>Total pris: <%=storage.calculateTotalPrice()%> kr.</a></div>
    </section>

    <!-- Register new product in the storage -->
    <!-- Will be hidden every time page is reloaded-->
    <aside id="registerProductPage" hidden>
        <div class="productHeader">
            <label style="font-size: 40px">Registrer Vare</label>
        </div>
        <div>
            <form action="RegisterProduct" accept-charset="ISO-8859-1" method="post">
                <input type="text" placeholder="Indtast navnet på produktet" name="name">
                <input type="text" placeholder="Indtast antal per batch" name="batchSize">
                <input type="text" placeholder="Indtast prisen for en batch" name="cost">
                <input type="submit" value="Registrer">
            </form>
        </div>
    </aside>

    <!-- History page -->
    <!-- Will be hidden every time page is reloaded-->
    <aside id="historyPage" <%if(!(boolean)session.getAttribute("historyPage")){%>hidden<%}%>>
        <div class="productHeader">
            <label style="font-size: 40px">Historik</label>
        </div>
        <form action="History" method="post">
            <input type="text" placeholder="Indtast Historik Størrelse..." name="historyInput">
        </form>
        <table class="productTable">
            <tr>
                <th>Dato</th>
                <th>Vare</th>
                <th>Batch Nr.</th>
                <th>Type</th>
                <th>Antal</th>
                <th>Medarbejder</th>
            </tr><%
        List<Transactions> history = (List) session.getAttribute("history");
        for (Transactions transaction : history){%>
            <tr>
                <td><%=transaction.getTimestamp()%></td>
                <td><%=transaction.getProduct()%></td>
                <td><%=transaction.getBatch()%></td>
                <td><%=transaction.getTranstype()%></td>
                <td><%=transaction.getAmount()%></td>
                <td><%=transaction.getName()%></td>
            </tr>
        <%}%>

        </table>
    </aside>

    <!-- Product information -->
    <!-- If a product has been chosen, attribute won't be null -->
    <%if(session.getAttribute("productChosen") != null){%>
    <jsp:include page="productInformation.jsp"/>
    <%}%>
</div>

<script>
    //Show history and register product, and close the other tab if open
    function show(ID1, ID2) {
        var show1 = document.getElementById(ID1);
        var show2 = document.getElementById(ID2);

        if(show1.style.display === "none"){
            show1.style.display = "block";
            if(show2 !== "none"){
                show2.style.display = "none";
            }
        } else{
            show1.style.display = "none";
        }
    }
</script>

</body>
</html>
