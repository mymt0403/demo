let map;

async function initMap() {
    const { Map } = await google.maps.importLibrary("maps");
    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 9,
        mapId: "4504f8b37365c3d0",
    });
}

function fetchData() {
    /** mySelectの項目(value)を取得する */
    const selectBox = document.getElementById('mySelect');
    const selectedValue = selectBox.value;

    fetch(`/api/center/\${selectedValue}`)
        .then(response => response.json())
        .then(data =>
            map = new google.maps.Map(document.getElementById("map"), {
                center: {
                    lat: data.latitude,
                    lng: data.longitude,
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
    const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
    const { PinElement } = google.maps.importLibrary("marker");
    pins.forEach((pin, i) => {
        new AdvancedMarkerElement({
            map: map,
            position: {
                lat: pin.latitude,
                lng: pin.longitude,
            },
            title: pin.facilityName,
            content: new google.maps.marker.PinElement({
                glyph: `\${i + 1}`,
                glyphColor: "white",
                scale: 1,
            }).element,
        });
    });
    displayFacilities(pins);
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

               /**  Font Awesome のアイコンを使用する */
                const icon = document.createElement("div");
                icon.innerHTML = '<i class="fa-solid fa-user"></i>';

                const faPin = new google.maps.marker.PinElement({
                    glyph: icon,
                    glyphColor: "#000000",
                    background: "#FF6633",
                    borderColor: "#FF0000",
                });

                /** 現在地をマークする */
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

function displayFacilities(facilities) {
    const listContainer = document.getElementById("facility-list");
    listContainer.innerHTML = '';

    if (!Array.isArray(facilities) || facilities.length === 0) {
        listContainer.textContent = '※対象の施設は見つかりませんでした。';
        return;
    }

    facilities.forEach((facility, i) => {
        const div = document.createElement('div');
        const name = document.createElement('div');
        const hr = document.createElement('hr');
        const addressDiv = document.createElement('div');
        const position = document.createElement('div');
        const mapLink = document.createElement('a');

        const addressText = document.createTextNode(`住所: \${facility.address}（`);
        const closingParen = document.createTextNode('）');

        name.textContent = `\${i + 1}. \${facility.facilityName} (\${facility.garbageTypeName})`;
        name.className = 'facility-name';
        position.textContent = `緯度: \${facility.latitude}, 経度: \${facility.longitude}`;
        mapLink.href = `\${facility.mapUrl}`;
        mapLink.textContent = `GoogleMapで見る`
        mapLink.target = '_blank';

        // 住所表示
        addressDiv.appendChild(addressText);
        addressDiv.appendChild(mapLink);
        addressDiv.appendChild(closingParen);

        // リスト表示
        div.appendChild(name);
        div.appendChild(addressDiv);
        div.appendChild(position);
        div.appendChild(addressDiv);
        div.appendChild(hr);
        listContainer.appendChild(div);
    });
}

window.onload = function() {
    fetchData();
};
