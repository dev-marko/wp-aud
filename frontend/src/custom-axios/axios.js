import axios from "axios";

// Vaka konfigurirame instanca od axios koja dozvoluva CORS
// celo vreme ke ja koristime ovaa instanca za da gi pravime
// nashite HTTP povici. Pri sekoj povik go dodava toj header

const instance = axios.create({
    baseURL : 'http://localhost:9090/api',
    headers : {
        'Access-Control-Allow-Origin' : '*'
    }
})

// Dokolku sakame da ja koristime ovaa instanca nadvor
// od ramkite na ovoj file, mora da napravam export
export default instance;