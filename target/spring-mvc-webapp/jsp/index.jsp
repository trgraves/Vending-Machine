<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Vending Machine</h1>
            <hr/>
            <div class="row">

                <div class="col-md-8">
                    <!-- Load items to items-container -->
                    <div class="" id="items-container">
                        <c:forEach var="currentItem" items="${itemList}">
                            <a href="selectItem?itemId=${currentItem.itemId}">
                                <button type="button" class="btn btn-default col-md-3" style="margin: 15px">
                                    <p class="text-left">${currentItem.itemId}</p>
                                    <p>${currentItem.name}</p>
                                    <p>$${currentItem.price}</p>
                                    <p>Quantity: ${currentItem.quantity}</p>
                                </button>
                            </a>
                        </c:forEach>
                    </div>
                </div>

                <div class="col-md-4">
                    <!-- TOTAL MONEY FORM-->
                    <h2>Total $ In</h2>
                    <form class="form-horizontal" role="form" id="total-money-form">
                        <div class="form-group">
                            <input type="text" class="col-md-6" id="total-money-in" value="${totalMoney}" readonly/>
                        </div>

                        <div class="form-group">
                            <div class="col-md-3">
                                <a href="addMoney?moneyValue=1">
                                    <button type="button" id="add-dollar" class="btn btn-default">Add Dollar</button>
                                </a>
                            </div>
                            <div class="col-md-3">
                                <a href="addMoney?moneyValue=.25">
                                    <button type="button" id="add-quarter" class="btn btn-default">Add Quarter</button>
                                </a>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-3">
                                <a href="addMoney?moneyValue=.10">
                                    <button type="button" id="add-dime" class="btn btn-default">Add Dime</button>
                                </a>
                            </div>
                            <div class="col-md-3">
                                <a href="addMoney?moneyValue=.05">
                                    <button type="button" id="add-nickel" class="btn btn-default">Add Nickel</button>
                                </a>
                            </div>
                        </div>

                    </form>
                    <hr />

                    <!-- MESSAGES FORM -->
                    <h2>Messages</h2>
                    <form class="form-horizontal" role="form" id="purchase-form">
                        <div class="form-group">
                            <input type="text" class="col-md-6" id="message-display" value="${message}"readonly />
                        </div>

                        <div class="form-group">
                            <label for="item-number" class="col-md-2 control-label">Item:</label>
                            <input type="number" class="col-md-4" id="item-number" value="${selectedItem.itemId}" readonly/>
                        </div>

                        <div class="form-group">
                            <a href="purchaseItem">
                                <button type="button" id="purchase-button" class="btn btn-default col-md-6">Make Purchase</button>
                            </a>
                        </div>
                    </form>
                    <hr />

                    <!-- CHANGE FORM -->
                    <h2>Change</h2>
                    <form class="form-horizontal" role="form" id="change-form">
                     
                        <c:if test="${quarters > 0}">
                            <div class="form-group" id="quartersDiv">
                                <label for="quarters" class="col-md-3 control-label">Quarters:</label>
                                <input type="number" class="col-md-2" id="quarters" value="${quarters}" readOnly />
                            </div>
                        </c:if>
                        
                        <c:if test="${dimes > 0}">
                            <div class="form-group" id="dimesDiv">
                                <label for="dimes" class="col-md-3 control-label">Dimes:</label>
                                <input type="number" class="col-md-2" id="dimes" value="${dimes}" readOnly />
                            </div>
                        </c:if>
                        
                        <c:if test="${nickels > 0}">
                            <div class="form-group" id="nickelsDiv">
                                <label for="nickels" class="col-md-3 control-label">Nickels:</label>
                                <input type="number" class="col-md-2" id="nickels" value="${nickels}" readOnly />
                            </div>
                        </c:if>
                        <div class="form-group">
                            <a href="returnChange">
                            <button type="button" id="change-button" class="btn btn-default col-md-6">Return Change</button>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

