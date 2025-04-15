let map;

async function initMap() {
    // import library
    // Request needed libraries.
    const { Map } = await google.maps.importLibrary("maps");
    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 9,
        mapId: "4504f8b37365c3d0",
    });
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
                zoom: 9,
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

function showUserLocation() {
    const { AdvancedMarkerElement } = google.maps.importLibrary("marker");
    const { PinElement } = google.maps.importLibrary("marker");
    const geowindow = new google.maps.InfoWindow();

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

                const faPin = new google.maps.marker.PinElement({
                    glyph: icon,
                    glyphColor: "#000000",
                    background: "#FF6633",
                    borderColor: "#FF0000",
                });

                // 現在地をマークする
                const geolocationMarker = new google.maps.marker.AdvancedMarkerElement({
                    map,
                    position: pos,
                    content: faPin.element,
                    title: "your location",
                });

              map.setCenter(pos);
            },
            () => {
              handleLocationError();
            },
        );
    } else {
        handleLocationError();
    }
}

function handleLocationError() {
    const errorMessageElement = document.getElementById("errorMessage");
    errorMessageElement.innerHTML = `
        <p>位置情報が取得できませんでした。<br>
        位置情報を表示させる場合はブラウザの設定から位置情報へのアクセスを許可してください。</p>`;
    errorMessageElement.style.display = "inline";
}

// ページが読み込まれたときにデフォルトでセレクトボックス1の内容を表示
window.onload = function() {
    fetchData();
};
