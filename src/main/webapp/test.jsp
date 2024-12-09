<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ルーレット</title>
    <link rel="stylesheet" href="roulette.css" charset="UTF-8">
</head>
<body>
    <h1>ルーレットゲーム</h1>
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
    <button id="spinButton">回す</button>
    <p id="result">結果: -</p>

    <script src="roulette.js"></script>
</body>
</html>
