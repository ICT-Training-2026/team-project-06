function updateClock() {
    const now = new Date();  // 現在の日時を取得する
    const year = now.getFullYear();     // 年（例：2025）
    const month = String(now.getMonth() + 1).padStart(2, '0');  // 1月-12月
    const date = String(now.getDate()).padStart(2, '0');        // 1-31日まで
    const dayNames = ['日', '月', '火', '水', '木', '金', '土'];
    const day = dayNames[now.getDay()];  // 曜日（0〜6）
    const hours = String(now.getHours()).padStart(2, '0');   // 00-23
    const minutes = String(now.getMinutes()).padStart(2, '0'); // 59分
    const seconds = String(now.getSeconds()).padStart(2, '0'); // 59秒
    
    document.getElementById('date').textContent = `${year}年${month}月${date}日(${day})`;
    document.getElementById('time').textContent = `${hours}:${minutes}:${seconds}`;
  }
  updateClock(); // 最初に1回表示
  setInterval(updateClock, 1000); // 1000ミリ秒ごとにupdateClockを実行（＝1秒ごと）