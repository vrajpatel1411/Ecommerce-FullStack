import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../redux/Auth/registerReducer";

export const store = configureStore({
  reducer: {
    authReducer: authReducer,
  },
});
