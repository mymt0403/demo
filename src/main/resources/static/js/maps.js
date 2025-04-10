let map;

async function initMap() {
    // import library
    // Request needed libraries.
    const { Map } = await google.maps.importLibrary("maps");
    const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
    const { PinElement } = await google.maps.importLibrary("marker");
    map = new Map(document.getElementById("map"), {
        zoom: 10,
        mapId: "4504f8b37365c3d0",
    });

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
    // mySelectの項目(value)を取得する
    const selectBox = document.getElementById('mySelect');
    const selectedValue = selectBox.value;

    fetch(`/api/center/\${selectedValue}`)
        .then(response => response.json())
        .then(data =>
            map = new google.maps.Map(document.getElementById("map"), {
                center: {
                    lat: data.shift(),
                    lng: data.shift(),
                },
                zoom: 10,
                mapId: "4504f8b37365c3d0",
            })
        );

    fetch(`/api/data/\${selectedValue}`)
        .then(response => response.json())
        .then(data => putPins(data));
}



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

// ページが読み込まれたときにデフォルトでセレクトボックス1の内容を表示
window.onload = function() {
    fetchData();
};

