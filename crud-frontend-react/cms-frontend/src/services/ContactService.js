import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/contact';

export const listContact = () => {
    return axios.get(REST_API_BASE_URL);
}

export const createContact = (contact) => {
    return axios.post(REST_API_BASE_URL, contact);
}

export const getContact = (contactId) => {
    return axios.get(REST_API_BASE_URL + '/' + contactId);
}

export const updateContact = (contactId, contact) => {
    return axios.put(REST_API_BASE_URL + '/' + contactId, contact);
}

export const deleteContact = (contactId) => {
    return axios.delete(REST_API_BASE_URL + '/' + contactId);
}