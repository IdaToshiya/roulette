document.addEventListener("DOMContentLoaded", () => {
    const canvas = document.getElementById("rouletteCanvas");
    const ctx = canvas.getContext("2d");

    const canvasSize = 400; // キャンバスサイズ
    const wheelRadius = canvasSize / 2; // ホイールの半径
    const sectors = 38; // ルーレットのセクター数 (0, 00, 1-36)
    const sectorNumbers = ["0", "28", "9", "26", "30", "11", "7", "20", "32", "17", "5", "22",
                           "34", "15", "3", "24", "36", "13", "1", "00", "27", "10", "25", "29",
                           "12", "8", "19", "31", "18", "6", "21", "33", "16", "4", "23", "35",
                           "14", "2"];
    const sectorColors = ["green", "black", "red", "black", "red", "black", "red", "black", "red", "black",
                          "red", "black", "red", "black", "red", "black", "red", "black", "red", "green",
                          "red", "black", "red", "black", "red", "black", "red", "black", "red", "black",
                          "red", "black", "red", "black", "red", "black", "red", "black"];

    let angle = 0; // ホイールの初期角度
    let isSpinning = false;
    let spinSpeed = 0; // 回転速度

    // キャンバスサイズ設定
    canvas.width = canvasSize;
    canvas.height = canvasSize;

    // ルーレットホイールの描画
    function drawWheel(highlightSector = null) {
        ctx.clearRect(0, 0, canvas.width, canvas.height); // キャンバスをクリア
        const sectorAngle = 2 * Math.PI / sectors; // 1セクターの角度

        for (let i = 0; i < sectors; i++) {
            const startAngle = sectorAngle * i + angle;
            const endAngle = startAngle + sectorAngle;

            ctx.beginPath();
            ctx.moveTo(wheelRadius, wheelRadius);
            ctx.arc(wheelRadius, wheelRadius, wheelRadius, startAngle, endAngle);
            ctx.closePath();

            // セクターの色
            if (highlightSector === i) {
                ctx.fillStyle = "yellow"; // ハイライト表示
            } else {
                ctx.fillStyle = sectorColors[i];
            }
            ctx.fill();
            ctx.stroke();

            // 数字の描画
            const textAngle = startAngle + (endAngle - startAngle) / 2;
            const textX = wheelRadius + Math.cos(textAngle) * wheelRadius * 0.7;
            const textY = wheelRadius + Math.sin(textAngle) * wheelRadius * 0.7;

            ctx.save();
            ctx.translate(textX, textY);
            ctx.rotate(textAngle + Math.PI / 2);
            ctx.fillStyle = "white";
            ctx.font = "14px Arial";
            const number = sectorNumbers[i];
            ctx.fillText(number, -ctx.measureText(number).width / 2, 4);
            ctx.restore();
        }
    }

    // 矢印の描画
    function drawArrow() {
        ctx.beginPath();
        ctx.moveTo(wheelRadius + 10, wheelRadius); // ホイールの中心から右側
        ctx.lineTo(wheelRadius - 10, wheelRadius); // 左側へ
        ctx.lineWidth = 3;
        ctx.strokeStyle = "white";
        ctx.stroke();
    }

	function spinRoulette() {
	    if (isSpinning) return; // 現在回転中の場合は無視
	    isSpinning = true;
	    spinSpeed = Math.random() * 10 + 20; // ランダムな初期速度

	    const spinInterval = setInterval(() => {
	        angle += spinSpeed * Math.PI / 180; // 角度を増加
	        drawWheel();
	        drawArrow();
	        spinSpeed *= 0.98; // 回転速度を減速
	        if (spinSpeed < 0.1) {
	            clearInterval(spinInterval);
	            isSpinning = false;
	            showResult(); // 回転が終了した後に結果を表示
	        }
	    }, 20);
	}

	function showResult() {
	    const sectorAngle = 2 * Math.PI / sectors;
	    const finalAngle = (angle % (2 * Math.PI) + 2 * Math.PI) % (2 * Math.PI);
	    const winningSector = Math.floor((sectors - finalAngle / sectorAngle) % sectors);

	    const result = sectorNumbers[winningSector];
	    sendResultToServer(result); // 結果をサーブレットに送信

	    alert(`当選番号: ${result}`);
	    drawArrow();
	}

	function sendResultToServer(result) {
	    fetch('RouletteServlet', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/x-www-form-urlencoded',
	        },
	        body: `result=${encodeURIComponent(result)}`,
	    })
	    .then(response => {
	        if (!response.ok) {
	            throw new Error(`HTTP error! Status: ${response.status}`);
	        }
	        return response.text();
	    })
	    .then(data => console.log("サーバーの応答:", data))
	    .catch(error => alert("サーバー通信に失敗しました: " + error.message));
	}


    drawWheel();
    drawArrow();

	document.getElementById("spinButton").addEventListener("click", spinRoulette);
});
