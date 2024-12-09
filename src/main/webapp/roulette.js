// サーブレットから渡された結果を取得
      var result =! null; // サーブレットから送信された結果（0〜36の番号）

      // ルーレットを回す
      document.getElementById("spinButton").addEventListener("click", function() {
          const roulette = document.getElementById("roulette");
          const resultDisplay = document.getElementById("result");

          // ランダム回転角度（回転後の結果位置）
          const randomSpin = Math.floor(Math.random() * 3600) + 360;

          // アニメーションを設定
          roulette.style.transition = "transform 4s ease-out";
          roulette.style.transform = `rotate(${randomSpin}deg)`;

          // 結果を表示する
          setTimeout(function() {
              const finalDegree = randomSpin % 360;
              const resultNumber = Math.floor(finalDegree / 30); // 番号を10度ごとに分割
              resultDisplay.textContent = `結果: ${resultNumber}`;
          }, 4000); // 4秒後に結果を表示
      });