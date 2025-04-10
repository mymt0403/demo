let map, faPin, userLocationMarker, infoWindow;

async function initMap() {
    const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
    const { PinElement } = await google.maps.importLibrary("marker");
    const { MAP } = await google.maps.importLibrary("map");

    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 35.68168615415703, lng: 139.76705199614096 },
        zoom: 10,
        mapId: "4504f8b37365c3d0",
    });
}

function fetchData() {
    // mySelectの項目(value)を取得する
    const selectBox = document.getElementById('mySelect');
    const selectedValue = selectBox.value;

    // 選択された都道府県を表示するための中心座標を取得して表示する
    fetch(`/api/center/\${selectedValue}`)
        .then(response => response.json())
        .then(data => moveCenter(data));

    // 選択された都道府県の対象施設の座標を取得してピンを指す
    fetch(`/api/data/\${selectedValue}`)
        .then(response => response.json())
        .then(data => putPins(data));
}

function moveCenter(data) {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: data.shift(), lng: data.shift() },
        zoom: 10,
        mapId: "4504f8b37365c3d0",
    });
}

// 対象施設のピンを表示
async function putPins(pins) {
    // import library
    const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
    pins.forEach(pin => {
        new AdvancedMarkerElement({
            map: map,
            position: {
                lat: pin.latitude,
                lng: pin.longitude,
            }
        });
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

