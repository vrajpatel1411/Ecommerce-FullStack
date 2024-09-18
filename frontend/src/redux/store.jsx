import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./Auth/authSlice";
import productReducer from "../redux/Product/productSlice";
import cartReducer from "../redux/Cart/cartSlice";
import orderReducer from "../redux/Order/orderSlice";
export const store = configureStore({
  reducer: {
    authReducer: authReducer,
    productReducer: productReducer,
    cartReducer: cartReducer,
    orderReducer: orderReducer,
  },
});
