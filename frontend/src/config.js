import axios from "axios";

export const BASE_API_URL = "http://34.130.247.80:8080/";
const token = localStorage.getItem("jwt");

// console.log(token);

export const api = axios.create({
  baseURL: BASE_API_URL,
  headers: {
    "Content-Type": "application/json",
    Authorization: `Bearer ${token}`,
  },
});
