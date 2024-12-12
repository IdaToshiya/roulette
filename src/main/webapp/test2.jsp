<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>ルーレットベットテーブル</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>ルーレットベットテーブル</h1>

       <form action="StartGameServlet" method="post" id="betForm">
           <div class="flex-container" id="flex-container">
	        <div class="bet-table">
	            <!-- 0と00の領域 -->
	            <div class="row">
	                <div class="cell zero"><input type="checkbox" name="bet" value="0"> 0</div>
	                <div class="cell zero"><input type="checkbox" name="bet" value="00"> 00</div>
	            </div>
	            <!-- ルーレット数字 -->
	            <div class="row">
	                <!-- 1列目 -->
	                <div class="cell red"><input type="checkbox" name="bet" value="1"> 1</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="4"> 4</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="7"> 7</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="10"> 10</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="13"> 13</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="16"> 16</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="19"> 19</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="22"> 22</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="25"> 25</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="28"> 28</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="31"> 31</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="34"> 34</div>
	            </div>
	            <!-- 2列目 -->
	            <div class="row">
	                <div class="cell black"><input type="checkbox" name="bet" value="2"> 2</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="5"> 5</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="8"> 8</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="11"> 11</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="14"> 14</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="17"> 17</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="20"> 20</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="23"> 23</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="26"> 26</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="29"> 29</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="32"> 32</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="35"> 35</div>
	            </div>
	            <!-- 3列目 -->
	            <div class="row">
	                <div class="cell red"><input type="checkbox" name="bet" value="3"> 3</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="6"> 6</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="9"> 9</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="12"> 12</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="15"> 15</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="18"> 18</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="21"> 21</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="24"> 24</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="27"> 27</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="30"> 30</div>
	                <div class="cell black"><input type="checkbox" name="bet" value="33"> 33</div>
	                <div class="cell red"><input type="checkbox" name="bet" value="36"> 36</div>
	            </div>
	              <!-- 特殊ベット領域 -->
	            <div class="special-bets">
	                <div class="cell"><input type="checkbox" name="bet" value="red"> 赤</div>
	                <div class="cell"><input type="checkbox" name="bet" value="black"> 黒</div>
	                <div class="cell"><input type="checkbox" name="bet" value="even"> 偶数</div>
	                <div class="cell"><input type="checkbox" name="bet" value="odd"> 奇数</div>
	                <div class="cell"><input type="checkbox" name="bet" value="1to18"> 1-18</div>
	                <div class="cell"><input type="checkbox" name="bet" value="19to36"> 19-36</div>
	                <div class="cell"><input type="checkbox" name="bet" value="1st12"> 1st 12</div>
	                <div class="cell"><input type="checkbox" name="bet" value="2nd12"> 2nd 12</div>
	                <div class="cell"><input type="checkbox" name="bet" value="3rd12"> 3rd 12</div>
	            </div>
	        </div>
	        <canvas id="rouletteCanvas"></canvas>
	        
	         </div>
	         <button type="submit">ベットを送信</button>
    	</form>
    
    <button id="spinButton" method="post">スピン</button>
    
    <form action="ResultServlet" method="post" id="betForm">
    	<button type="submit">結果</button>
    </form>
    
    <p>ベット: 
    <c:forEach var="betValue" items="${sessionScope.betValues}">
        ${betValue} 
    </c:forEach>
	</p>
   	<p>スピン結果: ${sessionScope.result}</p>
	<p>ベット結果: ${sessionScope.resultMessage}</p>

   	

    <script src="scripts.js"></script>
</body>
</html>
