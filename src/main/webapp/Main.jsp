<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ルーレット</title>
<link rel="stylesheet" href="roulette.css" charset="UTF-8">
</head>
<body>
	<h1>ルーレット</h1>
	<p>結果：${result}</p>
	
	<div class="roulette-container">
        <div class="roulette" id="roulette">
            	<div class="number" style="transform: rotate(0deg);">0</div>
            <div class="number" style="transform: rotate(30deg);">1</div>
            <div class="number" style="transform: rotate(60deg);">2</div>
            <div class="number" style="transform: rotate(90deg);">3</div>
            <div class="number" style="transform: rotate(120deg);">4</div>
            <div class="number" style="transform: rotate(150deg);">5</div>
            <div class="number" style="transform: rotate(180deg);">6</div>
            <div class="number" style="transform: rotate(210deg);">7</div>
            <div class="number" style="transform: rotate(240deg);">8</div>
            <div class="number" style="transform: rotate(270deg);">9</div>
            <div class="number" style="transform: rotate(300deg);">10</div>
            <div class="number" style="transform: rotate(330deg);">11</div>
            <div class="number" style="transform: rotate(360deg);">12</div>
        </div>
    </div>
	
	<form action="spin" method="get">
	
		<p>結果：${result}</p>
		<button id="spinButton">スピン</button>
		<h3>数字予想</h3>
		<input type="number" name="yosou" min="0" max="36" placeholder="数字 (0-36)">
		<h3>色に賭ける</h3>
        <button type="submit" name="betColor" value="red">赤</button>
        <button type="submit" name="betColor" value="black">黒</button>
        <button type="submit" name="betColor" value="green">緑</button>
		<p>${gouhi}</p>
	
	</form>
	<script src="roulette.js"></script>
</body>
</html>