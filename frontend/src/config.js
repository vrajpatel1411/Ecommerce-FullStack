import axios from "axios";

export const BASE_API_URL = "http://localhost:8080/";
const token = localStorage.getItem("jwt");

export const api = axios.create({
  baseURL: BASE_API_URL,
  headers: {
    "Content-Type": "application/json",
    Authorization: `Bearer ${token}`,
  },
});
