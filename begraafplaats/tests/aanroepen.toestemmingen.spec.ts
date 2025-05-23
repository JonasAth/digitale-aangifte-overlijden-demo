import {getAxios} from './axios';
import {describe, expect, test} from "vitest";

describe('oproepen van toestemmingen api', () => {
    test('Oproepen met enkel van ', async () => {
        const axios = getAxios()
        const response = await axios.get('/begraafplaats/v1/toestemmingen?van=2024-12-20T10:00:00+02:00')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Oproepen met van en tot', async () => {
        const axios = getAxios()
        const response = await axios.get('/begraafplaats/v1/toestemmingen?van=2024-12-20T10:00:00+02:00&tot=2024-12-28T10:00:00+02:00')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Oproepen met dossiernummer', async () => {
        const axios = getAxios()
        const response = await axios.get('/begraafplaats/v1/toestemmingen?dossiernummer=20250213-001-142S')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Oproepen met rijksregisternummer', async () => {
        const axios = getAxios()
        const response = await axios.get('/begraafplaats/v1/toestemmingen?rijksregisternummer=80010343003')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Downloaden', async () => {
        const axios = getAxios()
        const response = await axios.get('/begraafplaats/v1/toestemmingen/20250213-001-142S/toestemming')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
})
