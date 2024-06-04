import http from 'k6/http';
import { sleep } from 'k6';

function generateRandomString(length) {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let randomString = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        randomString += characters.charAt(randomIndex);
    }
    return randomString;
}

export const options = {
    vus: 5,
    duration: `3600s`,
};

export default async function() {
    let randomString = generateRandomString(64);
    await http.asyncRequest("GET", http.url`http://localhost:9090/test/Xi2816${randomString}`)
}