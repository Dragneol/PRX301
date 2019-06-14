/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const callApi = ({ url, method = "GET", success = () => {}, fail = () => {}, callback = () => {} }) => {
    const request = new XMLHttpRequest();
    
    request.open(method, url, true);
    request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    request.onreadystatechange = () => {
        if (request.readyState !== 4) {
            return;
        }
        
        const response = request.responseText;
        if (request.status >= 200 && request.status < 400) {
            if (success && typeof success === "function") {
                success(response);
            }
        } else {
            if (fail && typeof fail === "function") {
                fail(response);
            }
        }
        
        callback();
    };
    
    request.send();
};

const getDepartments = departmentXML => {
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(departmentXML, "text/xml");
    const departmentElements = xmlDoc.getElementsByTagName("Department");

    let position = null;
    
    return new Promise((resolve, reject) => {
        getLocation()
            .then(result => position = result)
            .catch(error => { console.error(error); alert(error); })
            .then(() => {
                const departments = Array.from(departmentElements).map(department => {
                    const id = department.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    const name = department.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                    const theaterId = department.getElementsByTagName("theaterId")[0].childNodes[0].nodeValue;

                    let address = department.getElementsByTagName("address")[0];
                    address = address ? address.childNodes[0].nodeValue : null;

                    let latitude = null;
                    let longitude = null;
                    let distance = null;
                    let mapAddress = null;

                    if (address) {
                        const [lat, lng, formattedAddress] = address.split(';');
                        latitude = parseFloat(lat);
                        longitude = parseFloat(lng);
                        mapAddress = formattedAddress;
                        
                        if (position) {
                            distance = getDistanceInKm(position.latitude, position.longitude, latitude, longitude);
                        }
                    }

                    return { id, name, theaterId, latitude, longitude, distance, address: mapAddress };
                });
                resolve(departments);
            });
    });
};

const getLocation = () => new Promise((resolve, reject) => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            position => resolve(position.coords),
            error => reject(error.message)
        );
    } else {
        reject('Your device does not support location service!');
    }
});

const getDistanceInKm = (lat1, lng1, lat2, lng2) => {
    const verticalDistance = (lat1 - lat2) * 110.574;
    const horizontalDistance = (lng1 * Math.cos(lat1 / 180 * Math.PI) - lng2 * Math.cos(lat2 / 180 * Math.PI)) * 111.320;

    return Math.sqrt(Math.pow(verticalDistance, 2) + Math.pow(horizontalDistance, 2));
};

const formatDate = date => {
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const dateOfMonth = date.getDate();

    return `${year}-${month < 10 ? '0' + month : month}-${dateOfMonth < 10 ? '0' + dateOfMonth : dateOfMonth}`;
};

const formatDateDisplay = date => {
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const dateOfMonth = date.getDate();

    return `${dateOfMonth < 10 ? '0' + dateOfMonth : dateOfMonth}-${month < 10 ? '0' + month : month}-${year}`;
};
