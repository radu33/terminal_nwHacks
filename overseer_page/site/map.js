function initialize(){
	var myDataRef = new Firebase('https://curo.firebaseio.com/');
	var minions = myDataRef.child('minions');
	var overseer = myDataRef.child('Overseer');
	var locations = myDataRef.child('areas');
	var map;

$.getJSON('https://curo.firebaseio.com/minions/Overseer/.json', function(data){
	var lat,lon;
	$.each(data, function(key,val){
		if(key == "lat"){
			lat = val;
		}
		else if(key == "lon"){
			lon = val;
		}
	})
	myLatLng = new google.maps.LatLng(lat,lon);
	var mapOptions = {
		zoom: 4,
		center: myLatLng,
		mapTypeId: google.maps.MapTypeId.SATELLITE
	}
	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions)
})

$.getJSON('https://curo.firebaseio.com/minions/.json',function(data){
	var lat,lon;
	$.each(data, function(key,val){
		if(key == "lat"){
			lat = val;
		}
		else if(key == "lon"){
			lon = val;
			marker = new google.maps.Marker
				({
					position: new google.maps.LatLng(lat,lon),
					map: map,
					icon: {
					path: google.maps.SymbolPath.CIRCLE,
					scale: 4
					}
				});
		}
	})
})

$.getJSON('https://curo.firebaseio.com/areas/.json',function(data){
	var lat,lon,radius;
	$.each(data, function(key,val){
		if(key == "lat"){
			lat = val;
		}
		else if(key == "lon"){
			lon = val;
		}
		else if(key == "radius"){
			radius = val;
			marker = new google.maps.Marker
				({
					position: new google.maps.LatLng(lat,lon),
					map: map,
					opacity: 0.0
				});
				var populationOptions = {
					strokeColor: '#FF0000',
					strokeOpacity: 0.8,
					strokeWeight: 2,
					fillColor: '#FF0000',
					fillOpacity: 0.35,
					map: map,
					center: marker.position,
					radius: radius
				};
				cityCircle = new google.maps.Circle(populationOptions);
				bounds.extend(marker.getPosition());
				map.fitBounds(bounds);
		}		
	})
	
  })
}
google.maps.event.addDomListener(window, 'load', initialize);
