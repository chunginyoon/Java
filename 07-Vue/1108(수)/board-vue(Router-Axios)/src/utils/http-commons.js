import axios from "axios";

const { VITE_API_BASE_URL } = import.meta.env;

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { localAxios };
