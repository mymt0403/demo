async function initMap() {
  // Request needed libraries.
  const tokyo = { lat: 35.68168615415703, lng: 139.76705199614096 };
  const { Map } = await google.maps.importLibrary("maps");
  const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
  const map = new Map(document.getElementById("map"), {
    center: tokyo,
    zoom: 10,
    mapId: "4504f8b37365c3d0",
  });
  const contentString =
    '<div id="content">' +
    '<div id="siteNotice">' +
    "</div>" +
    '<h1 id="firstHeading" class="firstHeading">東京駅</h1>' +
    '<div id="bodyContent">' +
    '<p>詳細： <a href="https://www.tokyoinfo.com/">' +
    "https://en.wikipedia.org/w/index.php?title=Uluru</a></p>" +
    "</div>" +
    "</div>";

  const geowindow = new google.maps.InfoWindow();

  const infowindow = new google.maps.InfoWindow({
    content: contentString,
    ariaLabel: "tokyo",
  });

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
          icon.innerHTML = '<i class="fa fa-pizza-slice fa-lg"></i>';

          const faPin = new PinElement({
            glyph: icon,
            glyphColor: "#ff8300",
            background: "#FFD514",
            borderColor: "#ff8300",
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

  const marker1 = new AdvancedMarkerElement({
    map,
    position: { lat: 35.73166678315512, lng: 139.76539977479922 },
    title: "Uluru (Ayers Rock)",
  });
  const marker2 = new AdvancedMarkerElement({
    map,
    position: { lat: 35.71327072470615, lng: 139.79681380487145 },
  });
  const marker3 = new AdvancedMarkerElement({
    map,
    position: { lat: 35.69194270607873, lng: 139.7003401168901 },
  });
  const marker4 = new AdvancedMarkerElement({
    map,
    position: { lat: 35.75117311202674, lng: 139.89809401111523 },
  });
  const marker5 = new AdvancedMarkerElement({
    map,
    position: { lat: 35.65805706938805, lng: 139.90015394751342 },
  });

  marker1.addListener("click", () => {
    infowindow.open({
      anchor: marker1,
      map,
    });
  });
}

function handleLocationError(map, browserHasGeolocation, geowindow, pos) {
  geowindow.setPosition(pos);
  geowindow.setContent(
    browserHasGeolocation
      ? "Error: The Geolocation service failed."
      : "Error: Your browser doesn't support geolocation.",
  );
  geowindow.open(map);
}