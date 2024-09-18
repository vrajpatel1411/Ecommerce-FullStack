import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { api } from "../../config";

export const createOrders = createAsyncThunk(
  "orders/createOrder",
  async (reqData) => {
    try {
      const res = await api.post("/api/orders/", reqData.address);
      if (res.data.id) {
        reqData.navigate({ search: `step=3&order_id=${res.data.id}` });
      }

      console.log("Order created");

      return res.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);
export const getPaymentLink = createAsyncThunk(
  "orders/getPaymentLink",
  async (id) => {
    try {
      const res = await api.get(`/api/orders/checkout/${id}`);

      if (res.data.paymentUrl) {
        window.location.href = res.data.paymentUrl;
      }
      return res.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);
export const userOrderHistory = createAsyncThunk(
  "orders/usersOrderHistory",
  async () => {
    try {
      const res = await api.get("/api/orders/user");

      return res.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);
export const getOrdersById = createAsyncThunk(
  "orders/getOrderById",
  async (orderId) => {
    try {
      const res = await api.get(`/api/orders/${orderId}`);

      return res.data;
    } catch (err) {
      const error = {
        error: err.message,
      };
      return error;
    }
  }
);

const initialState = {
  orders: [],
  order: null,
  error: null,
  loading: false,
  message: null,
};

const orderSlice = createSlice({
  name: "orders",
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(createOrders.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(createOrders.fulfilled, (state, action) => {
        state.loading = false;
        state.order = action.payload;
      })
      .addCase(createOrders.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(getOrdersById.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(getOrdersById.fulfilled, (state, action) => {
        state.loading = false;
        state.order = action.payload;
      })
      .addCase(getOrdersById.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(userOrderHistory.pending, (state) => {
        state.loading = true;
      })
      .addCase(userOrderHistory.fulfilled, (state, action) => {
        state.orders = action.payload;
        state.loading = false;
      })
      .addCase(userOrderHistory.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(getPaymentLink.pending, (state) => {
        state.loading = true;
        state.message = null;
      })
      .addCase(getPaymentLink.fulfilled, (state, action) => {
        state.message = action.payload;
        state.loading = false;
      })
      .addCase(getPaymentLink.rejected, (state, action) => {
        state.error = action.payload;
        state.loading = false;
      });
  },
});

export default orderSlice.reducer;
