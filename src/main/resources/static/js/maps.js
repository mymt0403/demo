const { Map } = await google.maps.importLibrary("maps");
const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
const { PinElement } = await google.maps.importLibrary("marker");
const tokyo = { lat: 35.68168615415703, lng: 139.76705199614096 };
const map = new Map(document.getElementById("map"), {
    center: tokyo,
    zoom: 10,
    mapId: "4504f8b37365c3d0",
});

async function initMap() {
  const geowindow = new google.maps.InfoWindow();
  const locationButton = document.createElement("button");
  locationButton.textContent = "現在地を表示（最初にアクセス権限を許可してください）";
  locationButton.classList.add("custom-map-control-button");
  map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);

  locationButton.addEventListener("click", () => {
    // Try HTML5 geolocation.
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };

          // A marker using a Font Awesome icon for the glyph.
          const icon = document.createElement("div");
          icon.innerHTML = '<i class="fa-solid fa-user"></i>';

          const faPin = new PinElement({
            glyph: icon,
            glyphColor: "#000000",
            background: "#FF6633",
            borderColor: "#FF0000",
          });

          // 現在地をマークする
          const geolocationMarker = new AdvancedMarkerElement({
            map,
            position: pos,
            content: faPin.element,
            title: "your location",
          });

          map.setCenter(pos);
        },
        () => {
          handleLocationError(map, true, geowindow, map.getCenter());
        },
      );
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(map, false, geowindow, map.getCenter());
    }
  });
}

// 現在地取得に失敗した場合、マップにエラー文を表示
function handleLocationError(map, browserHasGeolocation, window, pos) {
  window.setPosition(pos);
  window.setContent(
    browserHasGeolocation
      ? "Error: The Geolocation service failed."
      : "Error: Your browser doesn't support geolocation.",
  );
  window.open(map);
}

function fetchData() {
    const selectBox = document.getElementById('mySelect');
    const selectedValue = selectBox.value;

    fetch(`/api/data/${selectedValue}`)
        .then(response => response.json())
        .then(data => {
            const contentDiv = document.getElementById('content');
            contentDiv.innerHTML = '';

            data.forEach(item => {
                // マーカー作成
                const marker = new AdvancedMarkerElement({
                    map,
                    position: { lat: ${item.LATITUDE}, lng: ${item.LONGITUDE} },
                    title: `施設名: ${item.FACILITY_NAME}`,
                });

                // ウィンドウ内の詳細文
                const contentString =
                    '<div id="content">' +
                    '<div id="siteNotice">' +
                    "</div>" +
                    '<h1 id="firstHeading" class="firstHeading">東京駅</h1>' +
                    '<div id="bodyContent">' +
                    '<p>詳細： <a href="https://www.tokyoinfo.com/">' +
                    "https://www.tokyoinfo.com/</a></p>" +
                    "</div>" +
                    "</div>";

                // ウィンドウの設定
                const infowindow = new google.maps.InfoWindow({
                    content: contentString,
                    ariaLabel: "tokyo",
                });

                // クリックでウィンドウが開くよう設定
                marker.addListener("click", () => {
                    infowindow.open({
                        anchor: marker1,
                        map,
                    });
                });
            });
        })
        .catch(error => console.error('Error fetching data:', error));
}

// ページが読み込まれたときにデフォルトでセレクトボックス1の内容を表示
window.onload = function() {
    fetchData();
};

